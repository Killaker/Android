package com.android.volley.toolbox;

import com.android.volley.*;
import android.os.*;
import java.util.*;
import java.io.*;

public class DiskBasedCache implements Cache
{
    private static final int CACHE_MAGIC = 538247942;
    private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    private static final float HYSTERESIS_FACTOR = 0.9f;
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;
    
    public DiskBasedCache(final File file) {
        this(file, 5242880);
    }
    
    public DiskBasedCache(final File mRootDirectory, final int mMaxCacheSizeInBytes) {
        this.mEntries = new LinkedHashMap<String, CacheHeader>(16, 0.75f, true);
        this.mTotalSize = 0L;
        this.mRootDirectory = mRootDirectory;
        this.mMaxCacheSizeInBytes = mMaxCacheSizeInBytes;
    }
    
    private String getFilenameForKey(final String s) {
        final int n = s.length() / 2;
        return String.valueOf(s.substring(0, n).hashCode()) + String.valueOf(s.substring(n).hashCode());
    }
    
    private void pruneIfNeeded(final int n) {
        if (this.mTotalSize + n >= this.mMaxCacheSizeInBytes) {
            if (VolleyLog.DEBUG) {
                VolleyLog.v("Pruning old cache entries.", new Object[0]);
            }
            final long mTotalSize = this.mTotalSize;
            int n2 = 0;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final Iterator<Map.Entry<String, CacheHeader>> iterator = this.mEntries.entrySet().iterator();
            while (iterator.hasNext()) {
                final CacheHeader cacheHeader = iterator.next().getValue();
                if (this.getFileForKey(cacheHeader.key).delete()) {
                    this.mTotalSize -= cacheHeader.size;
                }
                else {
                    VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", cacheHeader.key, this.getFilenameForKey(cacheHeader.key));
                }
                iterator.remove();
                ++n2;
                if (this.mTotalSize + n < 0.9f * this.mMaxCacheSizeInBytes) {
                    break;
                }
            }
            if (VolleyLog.DEBUG) {
                VolleyLog.v("pruned %d files, %d bytes, %d ms", n2, this.mTotalSize - mTotalSize, SystemClock.elapsedRealtime() - elapsedRealtime);
            }
        }
    }
    
    private void putEntry(final String s, final CacheHeader cacheHeader) {
        if (!this.mEntries.containsKey(s)) {
            this.mTotalSize += cacheHeader.size;
        }
        else {
            this.mTotalSize += cacheHeader.size - this.mEntries.get(s).size;
        }
        this.mEntries.put(s, cacheHeader);
    }
    
    private static int read(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        if (read == -1) {
            throw new EOFException();
        }
        return read;
    }
    
    static int readInt(final InputStream inputStream) throws IOException {
        return 0x0 | read(inputStream) << 0 | read(inputStream) << 8 | read(inputStream) << 16 | read(inputStream) << 24;
    }
    
    static long readLong(final InputStream inputStream) throws IOException {
        return 0x0L | (0xFFL & read(inputStream)) << 0 | (0xFFL & read(inputStream)) << 8 | (0xFFL & read(inputStream)) << 16 | (0xFFL & read(inputStream)) << 24 | (0xFFL & read(inputStream)) << 32 | (0xFFL & read(inputStream)) << 40 | (0xFFL & read(inputStream)) << 48 | (0xFFL & read(inputStream)) << 56;
    }
    
    static String readString(final InputStream inputStream) throws IOException {
        return new String(streamToBytes(inputStream, (int)readLong(inputStream)), "UTF-8");
    }
    
    static Map<String, String> readStringStringMap(final InputStream inputStream) throws IOException {
        final int int1 = readInt(inputStream);
        Map<String, String> emptyMap;
        if (int1 == 0) {
            emptyMap = Collections.emptyMap();
        }
        else {
            emptyMap = new HashMap<String, String>(int1);
        }
        for (int i = 0; i < int1; ++i) {
            emptyMap.put(readString(inputStream).intern(), readString(inputStream).intern());
        }
        return emptyMap;
    }
    
    private void removeEntry(final String s) {
        final CacheHeader cacheHeader = this.mEntries.get(s);
        if (cacheHeader != null) {
            this.mTotalSize -= cacheHeader.size;
            this.mEntries.remove(s);
        }
    }
    
    private static byte[] streamToBytes(final InputStream inputStream, final int n) throws IOException {
        final byte[] array = new byte[n];
        int i;
        int read;
        for (i = 0; i < n; i += read) {
            read = inputStream.read(array, i, n - i);
            if (read == -1) {
                break;
            }
        }
        if (i != n) {
            throw new IOException("Expected " + n + " bytes, read " + i + " bytes");
        }
        return array;
    }
    
    static void writeInt(final OutputStream outputStream, final int n) throws IOException {
        outputStream.write(0xFF & n >> 0);
        outputStream.write(0xFF & n >> 8);
        outputStream.write(0xFF & n >> 16);
        outputStream.write(0xFF & n >> 24);
    }
    
    static void writeLong(final OutputStream outputStream, final long n) throws IOException {
        outputStream.write((byte)(n >>> 0));
        outputStream.write((byte)(n >>> 8));
        outputStream.write((byte)(n >>> 16));
        outputStream.write((byte)(n >>> 24));
        outputStream.write((byte)(n >>> 32));
        outputStream.write((byte)(n >>> 40));
        outputStream.write((byte)(n >>> 48));
        outputStream.write((byte)(n >>> 56));
    }
    
    static void writeString(final OutputStream outputStream, final String s) throws IOException {
        final byte[] bytes = s.getBytes("UTF-8");
        writeLong(outputStream, bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }
    
    static void writeStringStringMap(final Map<String, String> map, final OutputStream outputStream) throws IOException {
        if (map != null) {
            writeInt(outputStream, map.size());
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                writeString(outputStream, entry.getKey());
                writeString(outputStream, entry.getValue());
            }
        }
        else {
            writeInt(outputStream, 0);
        }
    }
    
    @Override
    public void clear() {
        int i = 0;
        synchronized (this) {
            final File[] listFiles = this.mRootDirectory.listFiles();
            if (listFiles != null) {
                while (i < listFiles.length) {
                    listFiles[i].delete();
                    ++i;
                }
            }
            this.mEntries.clear();
            this.mTotalSize = 0L;
            VolleyLog.d("Cache cleared.", new Object[0]);
        }
    }
    
    @Override
    public Entry get(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/android/volley/toolbox/DiskBasedCache.mEntries:Ljava/util/Map;
        //     6: aload_1        
        //     7: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: checkcast       Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //    15: astore_3       
        //    16: aconst_null    
        //    17: astore          4
        //    19: aload_3        
        //    20: ifnonnull       28
        //    23: aload_0        
        //    24: monitorexit    
        //    25: aload           4
        //    27: areturn        
        //    28: aload_0        
        //    29: aload_1        
        //    30: invokevirtual   com/android/volley/toolbox/DiskBasedCache.getFileForKey:(Ljava/lang/String;)Ljava/io/File;
        //    33: astore          5
        //    35: aconst_null    
        //    36: astore          6
        //    38: new             Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;
        //    41: dup            
        //    42: new             Ljava/io/BufferedInputStream;
        //    45: dup            
        //    46: new             Ljava/io/FileInputStream;
        //    49: dup            
        //    50: aload           5
        //    52: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    55: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    58: aconst_null    
        //    59: invokespecial   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.<init>:(Ljava/io/InputStream;Lcom/android/volley/toolbox/DiskBasedCache$1;)V
        //    62: astore          7
        //    64: aload           7
        //    66: invokestatic    com/android/volley/toolbox/DiskBasedCache$CacheHeader.readHeader:(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //    69: pop            
        //    70: aload_3        
        //    71: aload           7
        //    73: aload           5
        //    75: invokevirtual   java/io/File.length:()J
        //    78: aload           7
        //    80: invokestatic    com/android/volley/toolbox/DiskBasedCache$CountingInputStream.access$100:(Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;)I
        //    83: i2l            
        //    84: lsub           
        //    85: l2i            
        //    86: invokestatic    com/android/volley/toolbox/DiskBasedCache.streamToBytes:(Ljava/io/InputStream;I)[B
        //    89: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CacheHeader.toCacheEntry:([B)Lcom/android/volley/Cache$Entry;
        //    92: astore          14
        //    94: aload           7
        //    96: ifnull          104
        //    99: aload           7
        //   101: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.close:()V
        //   104: aload           14
        //   106: astore          4
        //   108: goto            23
        //   111: astore          15
        //   113: aconst_null    
        //   114: astore          4
        //   116: goto            23
        //   119: astore          8
        //   121: iconst_2       
        //   122: anewarray       Ljava/lang/Object;
        //   125: astore          11
        //   127: aload           11
        //   129: iconst_0       
        //   130: aload           5
        //   132: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   135: aastore        
        //   136: aload           11
        //   138: iconst_1       
        //   139: aload           8
        //   141: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   144: aastore        
        //   145: ldc_w           "%s: %s"
        //   148: aload           11
        //   150: invokestatic    com/android/volley/VolleyLog.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   153: aload_0        
        //   154: aload_1        
        //   155: invokevirtual   com/android/volley/toolbox/DiskBasedCache.remove:(Ljava/lang/String;)V
        //   158: aconst_null    
        //   159: astore          4
        //   161: aload           6
        //   163: ifnull          23
        //   166: aload           6
        //   168: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.close:()V
        //   171: aconst_null    
        //   172: astore          4
        //   174: goto            23
        //   177: astore          12
        //   179: aconst_null    
        //   180: astore          4
        //   182: goto            23
        //   185: astore          9
        //   187: aload           6
        //   189: ifnull          197
        //   192: aload           6
        //   194: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.close:()V
        //   197: aload           9
        //   199: athrow         
        //   200: astore_2       
        //   201: aload_0        
        //   202: monitorexit    
        //   203: aload_2        
        //   204: athrow         
        //   205: astore          10
        //   207: aconst_null    
        //   208: astore          4
        //   210: goto            23
        //   213: astore          9
        //   215: aload           7
        //   217: astore          6
        //   219: goto            187
        //   222: astore          8
        //   224: aload           7
        //   226: astore          6
        //   228: goto            121
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      16     200    205    Any
        //  28     35     200    205    Any
        //  38     64     119    121    Ljava/io/IOException;
        //  38     64     185    187    Any
        //  64     94     222    231    Ljava/io/IOException;
        //  64     94     213    222    Any
        //  99     104    111    119    Ljava/io/IOException;
        //  99     104    200    205    Any
        //  121    158    185    187    Any
        //  166    171    177    185    Ljava/io/IOException;
        //  166    171    200    205    Any
        //  192    197    205    213    Ljava/io/IOException;
        //  192    197    200    205    Any
        //  197    200    200    205    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public File getFileForKey(final String s) {
        return new File(this.mRootDirectory, this.getFilenameForKey(s));
    }
    
    @Override
    public void initialize() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: aload_0        
        //     3: monitorenter   
        //     4: aload_0        
        //     5: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //     8: invokevirtual   java/io/File.exists:()Z
        //    11: ifne            52
        //    14: aload_0        
        //    15: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //    18: invokevirtual   java/io/File.mkdirs:()Z
        //    21: ifne            49
        //    24: iconst_1       
        //    25: anewarray       Ljava/lang/Object;
        //    28: astore          16
        //    30: aload           16
        //    32: iconst_0       
        //    33: aload_0        
        //    34: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //    37: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    40: aastore        
        //    41: ldc_w           "Unable to create cache dir %s"
        //    44: aload           16
        //    46: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    49: aload_0        
        //    50: monitorexit    
        //    51: return         
        //    52: aload_0        
        //    53: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //    56: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    59: astore_3       
        //    60: aload_3        
        //    61: ifnull          49
        //    64: aload_3        
        //    65: arraylength    
        //    66: istore          4
        //    68: iload_1        
        //    69: iload           4
        //    71: if_icmpge       49
        //    74: aload_3        
        //    75: iload_1        
        //    76: aaload         
        //    77: astore          5
        //    79: aconst_null    
        //    80: astore          6
        //    82: new             Ljava/io/BufferedInputStream;
        //    85: dup            
        //    86: new             Ljava/io/FileInputStream;
        //    89: dup            
        //    90: aload           5
        //    92: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    95: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    98: astore          7
        //   100: aload           7
        //   102: invokestatic    com/android/volley/toolbox/DiskBasedCache$CacheHeader.readHeader:(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //   105: astore          13
        //   107: aload           13
        //   109: aload           5
        //   111: invokevirtual   java/io/File.length:()J
        //   114: putfield        com/android/volley/toolbox/DiskBasedCache$CacheHeader.size:J
        //   117: aload_0        
        //   118: aload           13
        //   120: getfield        com/android/volley/toolbox/DiskBasedCache$CacheHeader.key:Ljava/lang/String;
        //   123: aload           13
        //   125: invokespecial   com/android/volley/toolbox/DiskBasedCache.putEntry:(Ljava/lang/String;Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;)V
        //   128: aload           7
        //   130: ifnull          138
        //   133: aload           7
        //   135: invokevirtual   java/io/BufferedInputStream.close:()V
        //   138: iinc            1, 1
        //   141: goto            68
        //   144: astore          14
        //   146: goto            138
        //   149: astore          15
        //   151: aload           5
        //   153: ifnull          162
        //   156: aload           5
        //   158: invokevirtual   java/io/File.delete:()Z
        //   161: pop            
        //   162: aload           6
        //   164: ifnull          138
        //   167: aload           6
        //   169: invokevirtual   java/io/BufferedInputStream.close:()V
        //   172: goto            138
        //   175: astore          9
        //   177: goto            138
        //   180: astore          10
        //   182: aload           6
        //   184: ifnull          192
        //   187: aload           6
        //   189: invokevirtual   java/io/BufferedInputStream.close:()V
        //   192: aload           10
        //   194: athrow         
        //   195: astore_2       
        //   196: aload_0        
        //   197: monitorexit    
        //   198: aload_2        
        //   199: athrow         
        //   200: astore          11
        //   202: goto            192
        //   205: astore          10
        //   207: aload           7
        //   209: astore          6
        //   211: goto            182
        //   214: astore          8
        //   216: aload           7
        //   218: astore          6
        //   220: goto            151
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      49     195    200    Any
        //  52     60     195    200    Any
        //  64     68     195    200    Any
        //  74     79     195    200    Any
        //  82     100    149    151    Ljava/io/IOException;
        //  82     100    180    182    Any
        //  100    128    214    223    Ljava/io/IOException;
        //  100    128    205    214    Any
        //  133    138    144    149    Ljava/io/IOException;
        //  133    138    195    200    Any
        //  156    162    180    182    Any
        //  167    172    175    180    Ljava/io/IOException;
        //  167    172    195    200    Any
        //  187    192    200    205    Ljava/io/IOException;
        //  187    192    195    200    Any
        //  192    195    195    200    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void invalidate(final String s, final boolean b) {
        synchronized (this) {
            final Entry value = this.get(s);
            if (value != null) {
                value.softTtl = 0L;
                if (b) {
                    value.ttl = 0L;
                }
                this.put(s, value);
            }
        }
    }
    
    @Override
    public void put(final String s, final Entry entry) {
        synchronized (this) {
            this.pruneIfNeeded(entry.data.length);
            final File fileForKey = this.getFileForKey(s);
            BufferedOutputStream bufferedOutputStream = null;
            CacheHeader cacheHeader = null;
            Label_0129: {
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileForKey));
                    cacheHeader = new CacheHeader(s, entry);
                    if (!cacheHeader.writeHeader(bufferedOutputStream)) {
                        bufferedOutputStream.close();
                        VolleyLog.d("Failed to write header for %s", fileForKey.getAbsolutePath());
                        throw new IOException();
                    }
                    break Label_0129;
                }
                catch (IOException ex) {
                    if (!fileForKey.delete()) {
                        VolleyLog.d("Could not clean up file %s", fileForKey.getAbsolutePath());
                    }
                }
                return;
            }
            bufferedOutputStream.write(entry.data);
            bufferedOutputStream.close();
            this.putEntry(s, cacheHeader);
        }
    }
    
    @Override
    public void remove(final String s) {
        synchronized (this) {
            final boolean delete = this.getFileForKey(s).delete();
            this.removeEntry(s);
            if (!delete) {
                VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", s, this.getFilenameForKey(s));
            }
        }
    }
    
    static class CacheHeader
    {
        public String etag;
        public String key;
        public long lastModified;
        public Map<String, String> responseHeaders;
        public long serverDate;
        public long size;
        public long softTtl;
        public long ttl;
        
        private CacheHeader() {
        }
        
        public CacheHeader(final String key, final Entry entry) {
            this.key = key;
            this.size = entry.data.length;
            this.etag = entry.etag;
            this.serverDate = entry.serverDate;
            this.lastModified = entry.lastModified;
            this.ttl = entry.ttl;
            this.softTtl = entry.softTtl;
            this.responseHeaders = entry.responseHeaders;
        }
        
        public static CacheHeader readHeader(final InputStream inputStream) throws IOException {
            final CacheHeader cacheHeader = new CacheHeader();
            if (DiskBasedCache.readInt(inputStream) != 538247942) {
                throw new IOException();
            }
            cacheHeader.key = DiskBasedCache.readString(inputStream);
            cacheHeader.etag = DiskBasedCache.readString(inputStream);
            if (cacheHeader.etag.equals("")) {
                cacheHeader.etag = null;
            }
            cacheHeader.serverDate = DiskBasedCache.readLong(inputStream);
            cacheHeader.lastModified = DiskBasedCache.readLong(inputStream);
            cacheHeader.ttl = DiskBasedCache.readLong(inputStream);
            cacheHeader.softTtl = DiskBasedCache.readLong(inputStream);
            cacheHeader.responseHeaders = DiskBasedCache.readStringStringMap(inputStream);
            return cacheHeader;
        }
        
        public Entry toCacheEntry(final byte[] data) {
            final Entry entry = new Entry();
            entry.data = data;
            entry.etag = this.etag;
            entry.serverDate = this.serverDate;
            entry.lastModified = this.lastModified;
            entry.ttl = this.ttl;
            entry.softTtl = this.softTtl;
            entry.responseHeaders = this.responseHeaders;
            return entry;
        }
        
        public boolean writeHeader(final OutputStream outputStream) {
            try {
                DiskBasedCache.writeInt(outputStream, 538247942);
                DiskBasedCache.writeString(outputStream, this.key);
                String etag;
                if (this.etag == null) {
                    etag = "";
                }
                else {
                    etag = this.etag;
                }
                DiskBasedCache.writeString(outputStream, etag);
                DiskBasedCache.writeLong(outputStream, this.serverDate);
                DiskBasedCache.writeLong(outputStream, this.lastModified);
                DiskBasedCache.writeLong(outputStream, this.ttl);
                DiskBasedCache.writeLong(outputStream, this.softTtl);
                DiskBasedCache.writeStringStringMap(this.responseHeaders, outputStream);
                outputStream.flush();
                return true;
            }
            catch (IOException ex) {
                VolleyLog.d("%s", ex.toString());
                return false;
            }
        }
    }
    
    private static class CountingInputStream extends FilterInputStream
    {
        private int bytesRead;
        
        private CountingInputStream(final InputStream inputStream) {
            super(inputStream);
            this.bytesRead = 0;
        }
        
        @Override
        public int read() throws IOException {
            final int read = super.read();
            if (read != -1) {
                ++this.bytesRead;
            }
            return read;
        }
        
        @Override
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            final int read = super.read(array, n, n2);
            if (read != -1) {
                this.bytesRead += read;
            }
            return read;
        }
    }
}
