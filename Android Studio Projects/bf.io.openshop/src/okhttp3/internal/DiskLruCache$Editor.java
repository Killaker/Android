package okhttp3.internal;

import java.util.*;
import java.io.*;
import okio.*;

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
            written = new boolean[DiskLruCache.access$2300(DiskLruCache.this)];
        }
        this.written = written;
    }
    
    public void abort() throws IOException {
        synchronized (DiskLruCache.this) {
            DiskLruCache.access$2600(DiskLruCache.this, this, false);
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
                DiskLruCache.access$2600(DiskLruCache.this, this, false);
                DiskLruCache.access$2700(DiskLruCache.this, this.entry);
            }
            else {
                DiskLruCache.access$2600(DiskLruCache.this, this, true);
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
            return new FaultHidingSink(DiskLruCache.access$2400(DiskLruCache.this).sink(file)) {
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
            return DiskLruCache.access$2500();
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
            return DiskLruCache.access$2400(DiskLruCache.this).source(this.entry.cleanFiles[n]);
        }
        catch (FileNotFoundException ex) {
            // monitorexit(diskLruCache)
            return null;
        }
    }
}
