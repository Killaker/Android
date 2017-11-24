package okhttp3.internal;

class DiskLruCache$1 implements Runnable {
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
}