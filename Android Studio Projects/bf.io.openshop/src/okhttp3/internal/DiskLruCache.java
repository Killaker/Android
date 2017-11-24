package okhttp3.internal;

import java.util.regex.*;
import okhttp3.internal.io.*;
import java.util.concurrent.*;
import okio.*;
import java.util.*;
import java.io.*;

public final class DiskLruCache implements Closeable, Flushable
{
    static final long ANY_SEQUENCE_NUMBER = -1L;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final Pattern LEGAL_KEY_PATTERN;
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final Sink NULL_SINK;
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Runnable cleanupRunnable;
    private boolean closed;
    private final File directory;
    private final Executor executor;
    private final FileSystem fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries;
    private long maxSize;
    private long nextSequenceNumber;
    private int redundantOpCount;
    private long size;
    private final int valueCount;
    
    static {
        LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
        NULL_SINK = new Sink() {
            @Override
            public void close() throws IOException {
            }
            
            @Override
            public void flush() throws IOException {
            }
            
            @Override
            public Timeout timeout() {
                return Timeout.NONE;
            }
            
            @Override
            public void write(final Buffer buffer, final long n) throws IOException {
                buffer.skip(n);
            }
        };
    }
    
    DiskLruCache(final FileSystem fileSystem, final File directory, final int appVersion, final int valueCount, final long maxSize, final Executor executor) {
        this.size = 0L;
        this.lruEntries = new LinkedHashMap<String, Entry>(0, 0.75f, true);
        this.nextSequenceNumber = 0L;
        this.cleanupRunnable = new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        okhttp3/internal/DiskLruCache$1.this$0:Lokhttp3/internal/DiskLruCache;
                //     4: astore_1       
                //     5: aload_1        
                //     6: monitorenter   
                //     7: aload_0        
                //     8: getfield        okhttp3/internal/DiskLruCache$1.this$0:Lokhttp3/internal/DiskLruCache;
                //    11: invokestatic    okhttp3/internal/DiskLruCache.access$000:(Lokhttp3/internal/DiskLruCache;)Z
                //    14: istore_3       
                //    15: iconst_0       
                //    16: istore          4
                //    18: iload_3        
                //    19: ifne            25
                //    22: iconst_1       
                //    23: istore          4
                //    25: iload           4
                //    27: aload_0        
                //    28: getfield        okhttp3/internal/DiskLruCache$1.this$0:Lokhttp3/internal/DiskLruCache;
                //    31: invokestatic    okhttp3/internal/DiskLruCache.access$100:(Lokhttp3/internal/DiskLruCache;)Z
                //    34: ior            
                //    35: ifeq            41
                //    38: aload_1        
                //    39: monitorexit    
                //    40: return         
                //    41: aload_0        
                //    42: getfield        okhttp3/internal/DiskLruCache$1.this$0:Lokhttp3/internal/DiskLruCache;
                //    45: invokestatic    okhttp3/internal/DiskLruCache.access$200:(Lokhttp3/internal/DiskLruCache;)V
                //    48: aload_0        
                //    49: getfield        okhttp3/internal/DiskLruCache$1.this$0:Lokhttp3/internal/DiskLruCache;
                //    52: invokestatic    okhttp3/internal/DiskLruCache.access$300:(Lokhttp3/internal/DiskLruCache;)Z
                //    55: ifeq            74
                //    58: aload_0        
                //    59: getfield        okhttp3/internal/DiskLruCache$1.this$0:Lokhttp3/internal/DiskLruCache;
                //    62: invokestatic    okhttp3/internal/DiskLruCache.access$400:(Lokhttp3/internal/DiskLruCache;)V
                //    65: aload_0        
                //    66: getfield        okhttp3/internal/DiskLruCache$1.this$0:Lokhttp3/internal/DiskLruCache;
                //    69: iconst_0       
                //    70: invokestatic    okhttp3/internal/DiskLruCache.access$502:(Lokhttp3/internal/DiskLruCache;I)I
                //    73: pop            
                //    74: aload_1        
                //    75: monitorexit    
                //    76: return         
                //    77: astore_2       
                //    78: aload_1        
                //    79: monitorexit    
                //    80: aload_2        
                //    81: athrow         
                //    82: astore          5
                //    84: new             Ljava/lang/RuntimeException;
                //    87: dup            
                //    88: aload           5
                //    90: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
                //    93: athrow         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  7      15     77     82     Any
                //  25     40     77     82     Any
                //  41     74     82     94     Ljava/io/IOException;
                //  41     74     77     82     Any
                //  74     76     77     82     Any
                //  78     80     77     82     Any
                //  84     94     77     82     Any
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        };
        this.fileSystem = fileSystem;
        this.directory = directory;
        this.appVersion = appVersion;
        this.journalFile = new File(directory, "journal");
        this.journalFileTmp = new File(directory, "journal.tmp");
        this.journalFileBackup = new File(directory, "journal.bkp");
        this.valueCount = valueCount;
        this.maxSize = maxSize;
        this.executor = executor;
    }
    
    private void checkNotClosed() {
        synchronized (this) {
            if (this.isClosed()) {
                throw new IllegalStateException("cache is closed");
            }
        }
    }
    // monitorexit(this)
    
    private void completeEdit(final Editor editor, final boolean b) throws IOException {
        final Entry access$1700;
        synchronized (this) {
            access$1700 = editor.entry;
            if (access$1700.currentEditor != editor) {
                throw new IllegalStateException();
            }
        }
    Label_0120:
        while (true) {
            if (b && !access$1700.readable) {
                for (int i = 0; i < this.valueCount; ++i) {
                    if (!editor.written[i]) {
                        editor.abort();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    }
                    if (!this.fileSystem.exists(access$1700.dirtyFiles[i])) {
                        editor.abort();
                        break Label_0120;
                    }
                }
            }
            Label_0129: {
                break Label_0129;
                return;
            }
            for (int j = 0; j < this.valueCount; ++j) {
                final File file = access$1700.dirtyFiles[j];
                if (b) {
                    if (this.fileSystem.exists(file)) {
                        final File file2 = access$1700.cleanFiles[j];
                        this.fileSystem.rename(file, file2);
                        final long n = access$1700.lengths[j];
                        final long size = this.fileSystem.size(file2);
                        access$1700.lengths[j] = size;
                        this.size = size + (this.size - n);
                    }
                }
                else {
                    this.fileSystem.delete(file);
                }
            }
            ++this.redundantOpCount;
            access$1700.currentEditor = null;
            if (b | access$1700.readable) {
                access$1700.readable = true;
                this.journalWriter.writeUtf8("CLEAN").writeByte(32);
                this.journalWriter.writeUtf8(access$1700.key);
                access$1700.writeLengths(this.journalWriter);
                this.journalWriter.writeByte(10);
                if (b) {
                    final long nextSequenceNumber = this.nextSequenceNumber;
                    this.nextSequenceNumber = 1L + nextSequenceNumber;
                    access$1700.sequenceNumber = nextSequenceNumber;
                }
            }
            else {
                this.lruEntries.remove(access$1700.key);
                this.journalWriter.writeUtf8("REMOVE").writeByte(32);
                this.journalWriter.writeUtf8(access$1700.key);
                this.journalWriter.writeByte(10);
            }
            this.journalWriter.flush();
            if (this.size > this.maxSize || this.journalRebuildRequired()) {
                this.executor.execute(this.cleanupRunnable);
            }
            continue Label_0120;
        }
    }
    // monitorexit(this)
    
    public static DiskLruCache create(final FileSystem fileSystem, final File file, final int n, final int n2, final long n3) {
        if (n3 <= 0L) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (n2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        return new DiskLruCache(fileSystem, file, n, n2, n3, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), Util.threadFactory("OkHttp DiskLruCache", true)));
    }
    
    private Editor edit(final String s, final long n) throws IOException {
        synchronized (this) {
            this.initialize();
            this.checkNotClosed();
            this.validateKey(s);
            Entry entry = this.lruEntries.get(s);
            Label_0070: {
                if (n == -1L) {
                    break Label_0070;
                }
                Editor editor = null;
                if (entry != null) {
                    final long n2 = lcmp(entry.sequenceNumber, n);
                    editor = null;
                    if (n2 == 0) {
                        break Label_0070;
                    }
                }
                return editor;
            }
            if (entry != null) {
                final Editor access$900 = entry.currentEditor;
                final Editor editor = null;
                if (access$900 != null) {
                    return editor;
                }
            }
            this.journalWriter.writeUtf8("DIRTY").writeByte(32).writeUtf8(s).writeByte(10);
            this.journalWriter.flush();
            final boolean hasJournalErrors = this.hasJournalErrors;
            Editor editor = null;
            if (!hasJournalErrors) {
                if (entry == null) {
                    entry = new Entry(s);
                    this.lruEntries.put(s, entry);
                }
                editor = new Editor(entry);
                entry.currentEditor = editor;
                return editor;
            }
            return editor;
        }
    }
    
    private boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }
    
    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) {
            @Override
            protected void onException(final IOException ex) {
                assert Thread.holdsLock(DiskLruCache.this);
                DiskLruCache.this.hasJournalErrors = true;
            }
        });
    }
    
    private void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        final Iterator<Entry> iterator = this.lruEntries.values().iterator();
        while (iterator.hasNext()) {
            final Entry entry = iterator.next();
            if (entry.currentEditor == null) {
                for (int i = 0; i < this.valueCount; ++i) {
                    this.size += entry.lengths[i];
                }
            }
            else {
                entry.currentEditor = null;
                for (int j = 0; j < this.valueCount; ++j) {
                    this.fileSystem.delete(entry.cleanFiles[j]);
                    this.fileSystem.delete(entry.dirtyFiles[j]);
                }
                iterator.remove();
            }
        }
    }
    
    private void readJournal() throws IOException {
        final BufferedSource buffer = Okio.buffer(this.fileSystem.source(this.journalFile));
        try {
            final String utf8LineStrict = buffer.readUtf8LineStrict();
            final String utf8LineStrict2 = buffer.readUtf8LineStrict();
            final String utf8LineStrict3 = buffer.readUtf8LineStrict();
            final String utf8LineStrict4 = buffer.readUtf8LineStrict();
            final String utf8LineStrict5 = buffer.readUtf8LineStrict();
            if (!"libcore.io.DiskLruCache".equals(utf8LineStrict) || !"1".equals(utf8LineStrict2) || !Integer.toString(this.appVersion).equals(utf8LineStrict3) || !Integer.toString(this.valueCount).equals(utf8LineStrict4) || !"".equals(utf8LineStrict5)) {
                throw new IOException("unexpected journal header: [" + utf8LineStrict + ", " + utf8LineStrict2 + ", " + utf8LineStrict4 + ", " + utf8LineStrict5 + "]");
            }
        }
        finally {
            Util.closeQuietly(buffer);
        }
        int n = 0;
        try {
            while (true) {
                this.readJournalLine(buffer.readUtf8LineStrict());
                ++n;
            }
        }
        catch (EOFException ex) {
            this.redundantOpCount = n - this.lruEntries.size();
            if (!buffer.exhausted()) {
                this.rebuildJournal();
            }
            else {
                this.journalWriter = this.newJournalWriter();
            }
            Util.closeQuietly(buffer);
        }
    }
    
    private void readJournalLine(final String s) throws IOException {
        final int index = s.indexOf(32);
        if (index == -1) {
            throw new IOException("unexpected journal line: " + s);
        }
        final int n = index + 1;
        final int index2 = s.indexOf(32, n);
        String s2 = null;
        Label_0104: {
            if (index2 != -1) {
                s2 = s.substring(n, index2);
                break Label_0104;
            }
            s2 = s.substring(n);
            if (index != "REMOVE".length() || !s.startsWith("REMOVE")) {
                break Label_0104;
            }
            this.lruEntries.remove(s2);
            return;
        }
        Entry entry = this.lruEntries.get(s2);
        if (entry == null) {
            entry = new Entry(s2);
            this.lruEntries.put(s2, entry);
        }
        if (index2 != -1 && index == "CLEAN".length() && s.startsWith("CLEAN")) {
            final String[] split = s.substring(index2 + 1).split(" ");
            entry.readable = true;
            entry.currentEditor = null;
            entry.setLengths(split);
            return;
        }
        if (index2 == -1 && index == "DIRTY".length() && s.startsWith("DIRTY")) {
            entry.currentEditor = new Editor(entry);
            return;
        }
        if (index2 != -1 || index != "READ".length() || !s.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + s);
        }
    }
    
    private void rebuildJournal() throws IOException {
        BufferedSink buffer;
        while (true) {
            while (true) {
                Entry entry = null;
                synchronized (this) {
                    if (this.journalWriter != null) {
                        this.journalWriter.close();
                    }
                    buffer = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
                    try {
                        buffer.writeUtf8("libcore.io.DiskLruCache").writeByte(10);
                        buffer.writeUtf8("1").writeByte(10);
                        buffer.writeDecimalLong(this.appVersion).writeByte(10);
                        buffer.writeDecimalLong(this.valueCount).writeByte(10);
                        buffer.writeByte(10);
                        final Iterator<Entry> iterator = this.lruEntries.values().iterator();
                        while (iterator.hasNext()) {
                            entry = iterator.next();
                            if (entry.currentEditor == null) {
                                break;
                            }
                            buffer.writeUtf8("DIRTY").writeByte(32);
                            buffer.writeUtf8(entry.key);
                            buffer.writeByte(10);
                        }
                    }
                    finally {
                        buffer.close();
                    }
                }
                buffer.writeUtf8("CLEAN").writeByte(32);
                buffer.writeUtf8(entry.key);
                entry.writeLengths(buffer);
                buffer.writeByte(10);
                continue;
            }
        }
        buffer.close();
        if (this.fileSystem.exists(this.journalFile)) {
            this.fileSystem.rename(this.journalFile, this.journalFileBackup);
        }
        this.fileSystem.rename(this.journalFileTmp, this.journalFile);
        this.fileSystem.delete(this.journalFileBackup);
        this.journalWriter = this.newJournalWriter();
        this.hasJournalErrors = false;
    }
    // monitorexit(this)
    
    private boolean removeEntry(final Entry entry) throws IOException {
        if (entry.currentEditor != null) {
            entry.currentEditor.hasErrors = true;
        }
        for (int i = 0; i < this.valueCount; ++i) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            this.size -= entry.lengths[i];
            entry.lengths[i] = 0L;
        }
        ++this.redundantOpCount;
        this.journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (this.journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }
    
    private void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            this.removeEntry(this.lruEntries.values().iterator().next());
        }
    }
    
    private void validateKey(final String s) {
        if (!DiskLruCache.LEGAL_KEY_PATTERN.matcher(s).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + s + "\"");
        }
    }
    
    @Override
    public void close() throws IOException {
        while (true) {
            while (true) {
                int n = 0;
                Label_0118: {
                    synchronized (this) {
                        if (!this.initialized || this.closed) {
                            this.closed = true;
                        }
                        else {
                            final Entry[] array = this.lruEntries.values().toArray(new Entry[this.lruEntries.size()]);
                            final int length = array.length;
                            n = 0;
                            if (n < length) {
                                final Entry entry = array[n];
                                if (entry.currentEditor != null) {
                                    entry.currentEditor.abort();
                                }
                                break Label_0118;
                            }
                            else {
                                this.trimToSize();
                                this.journalWriter.close();
                                this.journalWriter = null;
                                this.closed = true;
                            }
                        }
                        return;
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    public void delete() throws IOException {
        this.close();
        this.fileSystem.deleteContents(this.directory);
    }
    
    public Editor edit(final String s) throws IOException {
        return this.edit(s, -1L);
    }
    
    public void evictAll() throws IOException {
        synchronized (this) {
            this.initialize();
            final Entry[] array = this.lruEntries.values().toArray(new Entry[this.lruEntries.size()]);
            for (int length = array.length, i = 0; i < length; ++i) {
                this.removeEntry(array[i]);
            }
        }
    }
    
    @Override
    public void flush() throws IOException {
        synchronized (this) {
            if (this.initialized) {
                this.checkNotClosed();
                this.trimToSize();
                this.journalWriter.flush();
            }
        }
    }
    
    public Snapshot get(final String s) throws IOException {
        synchronized (this) {
            this.initialize();
            this.checkNotClosed();
            this.validateKey(s);
            final Entry entry = this.lruEntries.get(s);
            Closeable snapshot;
            if (entry == null || !entry.readable) {
                snapshot = null;
            }
            else {
                snapshot = entry.snapshot();
                if (snapshot == null) {
                    snapshot = null;
                }
                else {
                    ++this.redundantOpCount;
                    this.journalWriter.writeUtf8("READ").writeByte(32).writeUtf8(s).writeByte(10);
                    if (this.journalRebuildRequired()) {
                        this.executor.execute(this.cleanupRunnable);
                    }
                }
            }
            return (Snapshot)snapshot;
        }
    }
    
    public File getDirectory() {
        return this.directory;
    }
    
    public long getMaxSize() {
        synchronized (this) {
            return this.maxSize;
        }
    }
    
    public void initialize() throws IOException {
        synchronized (this) {
            assert Thread.holdsLock(this);
        }
        Label_0037: {
            if (!this.initialized) {
                if (this.fileSystem.exists(this.journalFileBackup)) {
                    if (this.fileSystem.exists(this.journalFile)) {
                        this.fileSystem.delete(this.journalFileBackup);
                    }
                    else {
                        this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                    }
                }
                if (this.fileSystem.exists(this.journalFile)) {
                    try {
                        this.readJournal();
                        this.processJournal();
                        this.initialized = true;
                        break Label_0037;
                    }
                    catch (IOException ex) {
                        Platform.get().logW("DiskLruCache " + this.directory + " is corrupt: " + ex.getMessage() + ", removing");
                        this.delete();
                        this.closed = false;
                    }
                }
                this.rebuildJournal();
                this.initialized = true;
            }
        }
    }
    // monitorexit(this)
    
    public boolean isClosed() {
        synchronized (this) {
            return this.closed;
        }
    }
    
    public boolean remove(final String s) throws IOException {
        synchronized (this) {
            this.initialize();
            this.checkNotClosed();
            this.validateKey(s);
            final Entry entry = this.lruEntries.get(s);
            return entry != null && this.removeEntry(entry);
        }
    }
    
    public void setMaxSize(final long maxSize) {
        synchronized (this) {
            this.maxSize = maxSize;
            if (this.initialized) {
                this.executor.execute(this.cleanupRunnable);
            }
        }
    }
    
    public long size() throws IOException {
        synchronized (this) {
            this.initialize();
            return this.size;
        }
    }
    
    public Iterator<Snapshot> snapshots() throws IOException {
        synchronized (this) {
            this.initialize();
            return new Iterator<Snapshot>() {
                final Iterator<Entry> delegate = new ArrayList<Entry>(DiskLruCache.this.lruEntries.values()).iterator();
                Snapshot nextSnapshot;
                Snapshot removeSnapshot;
                
                @Override
                public boolean hasNext() {
                    if (this.nextSnapshot != null) {
                        return true;
                    }
                    Label_0076: {
                        synchronized (DiskLruCache.this) {
                            if (DiskLruCache.this.closed) {
                                return false;
                            }
                            Snapshot snapshot = null;
                            Block_6: {
                                while (this.delegate.hasNext()) {
                                    snapshot = this.delegate.next().snapshot();
                                    if (snapshot != null) {
                                        break Block_6;
                                    }
                                }
                                break Label_0076;
                            }
                            this.nextSnapshot = snapshot;
                            return true;
                        }
                    }
                    // monitorexit(diskLruCache)
                    return false;
                }
                
                @Override
                public Snapshot next() {
                    if (!this.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    this.removeSnapshot = this.nextSnapshot;
                    this.nextSnapshot = null;
                    return this.removeSnapshot;
                }
                
                @Override
                public void remove() {
                    if (this.removeSnapshot == null) {
                        throw new IllegalStateException("remove() before next()");
                    }
                    try {
                        DiskLruCache.this.remove(this.removeSnapshot.key);
                    }
                    catch (IOException ex) {}
                    finally {
                        this.removeSnapshot = null;
                    }
                }
            };
        }
    }
    
    public final class Editor
    {
        private boolean committed;
        private final Entry entry;
        private boolean hasErrors;
        private final boolean[] written;
        
        private Editor(final Entry entry) {
            this.entry = entry;
            boolean[] written;
            if (entry.readable) {
                written = null;
            }
            else {
                written = new boolean[DiskLruCache.this.valueCount];
            }
            this.written = written;
        }
        
        public void abort() throws IOException {
            synchronized (DiskLruCache.this) {
                DiskLruCache.this.completeEdit(this, false);
            }
        }
        
        public void abortUnlessCommitted() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        okhttp3/internal/DiskLruCache$Editor.this$0:Lokhttp3/internal/DiskLruCache;
            //     4: astore_1       
            //     5: aload_1        
            //     6: monitorenter   
            //     7: aload_0        
            //     8: getfield        okhttp3/internal/DiskLruCache$Editor.committed:Z
            //    11: istore_3       
            //    12: iload_3        
            //    13: ifne            25
            //    16: aload_0        
            //    17: getfield        okhttp3/internal/DiskLruCache$Editor.this$0:Lokhttp3/internal/DiskLruCache;
            //    20: aload_0        
            //    21: iconst_0       
            //    22: invokestatic    okhttp3/internal/DiskLruCache.access$2600:(Lokhttp3/internal/DiskLruCache;Lokhttp3/internal/DiskLruCache$Editor;Z)V
            //    25: aload_1        
            //    26: monitorexit    
            //    27: return         
            //    28: astore_2       
            //    29: aload_1        
            //    30: monitorexit    
            //    31: aload_2        
            //    32: athrow         
            //    33: astore          4
            //    35: goto            25
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  7      12     28     33     Any
            //  16     25     33     38     Ljava/io/IOException;
            //  16     25     28     33     Any
            //  25     27     28     33     Any
            //  29     31     28     33     Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public void commit() throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.hasErrors) {
                    DiskLruCache.this.completeEdit(this, false);
                    DiskLruCache.this.removeEntry(this.entry);
                }
                else {
                    DiskLruCache.this.completeEdit(this, true);
                }
                this.committed = true;
            }
        }
        
        public Sink newSink(final int n) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
            }
            if (!this.entry.readable) {
                this.written[n] = true;
            }
            final File file = this.entry.dirtyFiles[n];
            try {
                // monitorexit(diskLruCache)
                return new FaultHidingSink(DiskLruCache.this.fileSystem.sink(file)) {
                    @Override
                    protected void onException(final IOException ex) {
                        synchronized (DiskLruCache.this) {
                            Editor.this.hasErrors = true;
                        }
                    }
                };
            }
            catch (FileNotFoundException ex) {
                // monitorexit(diskLruCache)
                return DiskLruCache.NULL_SINK;
            }
        }
        
        public Source newSource(final int n) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
            }
            if (!this.entry.readable) {
                // monitorexit(diskLruCache)
                return null;
            }
            try {
                // monitorexit(diskLruCache)
                return DiskLruCache.this.fileSystem.source(this.entry.cleanFiles[n]);
            }
            catch (FileNotFoundException ex) {
                // monitorexit(diskLruCache)
                return null;
            }
        }
    }
    
    private final class Entry
    {
        private final File[] cleanFiles;
        private Editor currentEditor;
        private final File[] dirtyFiles;
        private final String key;
        private final long[] lengths;
        private boolean readable;
        private long sequenceNumber;
        
        private Entry(final String key) {
            this.key = key;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            final StringBuilder append = new StringBuilder(key).append('.');
            final int length = append.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; ++i) {
                append.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, append.toString());
                append.append(".tmp");
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, append.toString());
                append.setLength(length);
            }
        }
        
        private IOException invalidLengths(final String[] array) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(array));
        }
        
        private void setLengths(final String[] array) throws IOException {
            if (array.length != DiskLruCache.this.valueCount) {
                throw this.invalidLengths(array);
            }
            int i = 0;
            try {
                while (i < array.length) {
                    this.lengths[i] = Long.parseLong(array[i]);
                    ++i;
                }
            }
            catch (NumberFormatException ex) {
                throw this.invalidLengths(array);
            }
        }
        
        Snapshot snapshot() {
            if (!Thread.holdsLock(DiskLruCache.this)) {
                throw new AssertionError();
            }
            final Source[] array = new Source[DiskLruCache.this.valueCount];
            final long[] array2 = this.lengths.clone();
            int i = 0;
            try {
                while (i < DiskLruCache.this.valueCount) {
                    array[i] = DiskLruCache.this.fileSystem.source(this.cleanFiles[i]);
                    ++i;
                }
                return new Snapshot(this.key, this.sequenceNumber, array, array2);
            }
            catch (FileNotFoundException ex) {
                for (int n = 0; n < DiskLruCache.this.valueCount && array[n] != null; ++n) {
                    Util.closeQuietly(array[n]);
                }
                return null;
            }
        }
        
        void writeLengths(final BufferedSink bufferedSink) throws IOException {
            final long[] lengths = this.lengths;
            for (int length = lengths.length, i = 0; i < length; ++i) {
                bufferedSink.writeByte(32).writeDecimalLong(lengths[i]);
            }
        }
    }
    
    public final class Snapshot implements Closeable
    {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final Source[] sources;
        
        private Snapshot(final String key, final long sequenceNumber, final Source[] sources, final long[] lengths) {
            this.key = key;
            this.sequenceNumber = sequenceNumber;
            this.sources = sources;
            this.lengths = lengths;
        }
        
        @Override
        public void close() {
            final Source[] sources = this.sources;
            for (int length = sources.length, i = 0; i < length; ++i) {
                Util.closeQuietly(sources[i]);
            }
        }
        
        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }
        
        public long getLength(final int n) {
            return this.lengths[n];
        }
        
        public Source getSource(final int n) {
            return this.sources[n];
        }
        
        public String key() {
            return this.key;
        }
    }
}
