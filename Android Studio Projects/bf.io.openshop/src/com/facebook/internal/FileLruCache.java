package com.facebook.internal;

import java.util.concurrent.atomic.*;
import java.util.*;
import com.facebook.*;
import java.io.*;
import java.security.*;
import org.json.*;

public final class FileLruCache
{
    private static final String HEADER_CACHEKEY_KEY = "key";
    private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
    static final String TAG;
    private static final AtomicLong bufferIndex;
    private final File directory;
    private boolean isTrimInProgress;
    private boolean isTrimPending;
    private AtomicLong lastClearCacheTime;
    private final Limits limits;
    private final Object lock;
    private final String tag;
    
    static {
        TAG = FileLruCache.class.getSimpleName();
        bufferIndex = new AtomicLong();
    }
    
    public FileLruCache(final String tag, final Limits limits) {
        this.lastClearCacheTime = new AtomicLong(0L);
        this.tag = tag;
        this.limits = limits;
        this.directory = new File(FacebookSdk.getCacheDir(), tag);
        this.lock = new Object();
        if (this.directory.mkdirs() || this.directory.isDirectory()) {
            BufferFile.deleteAll(this.directory);
        }
    }
    
    private void postTrim() {
        synchronized (this.lock) {
            if (!this.isTrimPending) {
                this.isTrimPending = true;
                FacebookSdk.getExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        FileLruCache.this.trim();
                    }
                });
            }
        }
    }
    
    private void renameToTargetAndTrim(final String s, final File file) {
        if (!file.renameTo(new File(this.directory, Utility.md5hash(s)))) {
            file.delete();
        }
        this.postTrim();
    }
    
    private void trim() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: iconst_0       
        //     9: putfield        com/facebook/internal/FileLruCache.isTrimPending:Z
        //    12: aload_0        
        //    13: iconst_1       
        //    14: putfield        com/facebook/internal/FileLruCache.isTrimInProgress:Z
        //    17: aload_1        
        //    18: monitorexit    
        //    19: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //    22: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //    25: ldc             "trim started"
        //    27: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //    30: new             Ljava/util/PriorityQueue;
        //    33: dup            
        //    34: invokespecial   java/util/PriorityQueue.<init>:()V
        //    37: astore          6
        //    39: lconst_0       
        //    40: lstore          7
        //    42: lconst_0       
        //    43: lstore          9
        //    45: aload_0        
        //    46: getfield        com/facebook/internal/FileLruCache.directory:Ljava/io/File;
        //    49: invokestatic    com/facebook/internal/FileLruCache$BufferFile.excludeBufferFiles:()Ljava/io/FilenameFilter;
        //    52: invokevirtual   java/io/File.listFiles:(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //    55: astore          11
        //    57: aload           11
        //    59: ifnull          185
        //    62: aload           11
        //    64: arraylength    
        //    65: istore          12
        //    67: iconst_0       
        //    68: istore          13
        //    70: iload           13
        //    72: iload           12
        //    74: if_icmpge       185
        //    77: aload           11
        //    79: iload           13
        //    81: aaload         
        //    82: astore          14
        //    84: new             Lcom/facebook/internal/FileLruCache$ModifiedFile;
        //    87: dup            
        //    88: aload           14
        //    90: invokespecial   com/facebook/internal/FileLruCache$ModifiedFile.<init>:(Ljava/io/File;)V
        //    93: astore          15
        //    95: aload           6
        //    97: aload           15
        //    99: invokevirtual   java/util/PriorityQueue.add:(Ljava/lang/Object;)Z
        //   102: pop            
        //   103: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   106: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   109: new             Ljava/lang/StringBuilder;
        //   112: dup            
        //   113: invokespecial   java/lang/StringBuilder.<init>:()V
        //   116: ldc             "  trim considering time="
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   121: aload           15
        //   123: invokevirtual   com/facebook/internal/FileLruCache$ModifiedFile.getModified:()J
        //   126: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   132: ldc             " name="
        //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: aload           15
        //   139: invokevirtual   com/facebook/internal/FileLruCache$ModifiedFile.getFile:()Ljava/io/File;
        //   142: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   151: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //   154: aload           14
        //   156: invokevirtual   java/io/File.length:()J
        //   159: lstore          17
        //   161: lload           7
        //   163: lload           17
        //   165: ladd           
        //   166: lstore          7
        //   168: lload           9
        //   170: lconst_1       
        //   171: ladd           
        //   172: lstore          9
        //   174: iinc            13, 1
        //   177: goto            70
        //   180: astore_2       
        //   181: aload_1        
        //   182: monitorexit    
        //   183: aload_2        
        //   184: athrow         
        //   185: lload           7
        //   187: aload_0        
        //   188: getfield        com/facebook/internal/FileLruCache.limits:Lcom/facebook/internal/FileLruCache$Limits;
        //   191: invokevirtual   com/facebook/internal/FileLruCache$Limits.getByteCount:()I
        //   194: i2l            
        //   195: lcmp           
        //   196: ifgt            213
        //   199: lload           9
        //   201: aload_0        
        //   202: getfield        com/facebook/internal/FileLruCache.limits:Lcom/facebook/internal/FileLruCache$Limits;
        //   205: invokevirtual   com/facebook/internal/FileLruCache$Limits.getFileCount:()I
        //   208: i2l            
        //   209: lcmp           
        //   210: ifle            310
        //   213: aload           6
        //   215: invokevirtual   java/util/PriorityQueue.remove:()Ljava/lang/Object;
        //   218: checkcast       Lcom/facebook/internal/FileLruCache$ModifiedFile;
        //   221: invokevirtual   com/facebook/internal/FileLruCache$ModifiedFile.getFile:()Ljava/io/File;
        //   224: astore          19
        //   226: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   229: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   232: new             Ljava/lang/StringBuilder;
        //   235: dup            
        //   236: invokespecial   java/lang/StringBuilder.<init>:()V
        //   239: ldc             "  trim removing "
        //   241: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: aload           19
        //   246: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   252: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   255: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //   258: lload           7
        //   260: aload           19
        //   262: invokevirtual   java/io/File.length:()J
        //   265: lsub           
        //   266: lstore          7
        //   268: lload           9
        //   270: lconst_1       
        //   271: lsub           
        //   272: lstore          9
        //   274: aload           19
        //   276: invokevirtual   java/io/File.delete:()Z
        //   279: pop            
        //   280: goto            185
        //   283: astore_3       
        //   284: aload_0        
        //   285: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //   288: astore          4
        //   290: aload           4
        //   292: monitorenter   
        //   293: aload_0        
        //   294: iconst_0       
        //   295: putfield        com/facebook/internal/FileLruCache.isTrimInProgress:Z
        //   298: aload_0        
        //   299: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //   302: invokevirtual   java/lang/Object.notifyAll:()V
        //   305: aload           4
        //   307: monitorexit    
        //   308: aload_3        
        //   309: athrow         
        //   310: aload_0        
        //   311: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //   314: astore          21
        //   316: aload           21
        //   318: monitorenter   
        //   319: aload_0        
        //   320: iconst_0       
        //   321: putfield        com/facebook/internal/FileLruCache.isTrimInProgress:Z
        //   324: aload_0        
        //   325: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //   328: invokevirtual   java/lang/Object.notifyAll:()V
        //   331: aload           21
        //   333: monitorexit    
        //   334: return         
        //   335: astore          22
        //   337: aload           21
        //   339: monitorexit    
        //   340: aload           22
        //   342: athrow         
        //   343: astore          5
        //   345: aload           4
        //   347: monitorexit    
        //   348: aload           5
        //   350: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      19     180    185    Any
        //  19     39     283    351    Any
        //  45     57     283    351    Any
        //  62     67     283    351    Any
        //  77     161    283    351    Any
        //  181    183    180    185    Any
        //  185    213    283    351    Any
        //  213    268    283    351    Any
        //  274    280    283    351    Any
        //  293    308    343    351    Any
        //  319    334    335    343    Any
        //  337    340    335    343    Any
        //  345    348    343    351    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void clearCache() {
        final File[] listFiles = this.directory.listFiles(BufferFile.excludeBufferFiles());
        this.lastClearCacheTime.set(System.currentTimeMillis());
        if (listFiles != null) {
            FacebookSdk.getExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    final File[] val$filesToDelete = listFiles;
                    for (int length = val$filesToDelete.length, i = 0; i < length; ++i) {
                        val$filesToDelete[i].delete();
                    }
                }
            });
        }
    }
    
    public InputStream get(final String s) throws IOException {
        return this.get(s, null);
    }
    
    public InputStream get(final String s, final String s2) throws IOException {
        final File file = new File(this.directory, Utility.md5hash(s));
        Label_0067: {
            BufferedInputStream bufferedInputStream;
            try {
                final BufferedInputStream bufferedInputStream2;
                bufferedInputStream = (bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file), 8192));
                final JSONObject jsonObject = StreamHeader.readHeader(bufferedInputStream2);
                final JSONObject jsonObject3;
                final JSONObject jsonObject2 = jsonObject3 = jsonObject;
                if (jsonObject3 == null) {
                    return null;
                }
                break Label_0067;
            }
            catch (IOException ex) {
                return null;
            }
            try {
                final BufferedInputStream bufferedInputStream2 = bufferedInputStream;
                final JSONObject jsonObject = StreamHeader.readHeader(bufferedInputStream2);
                final JSONObject jsonObject3;
                final JSONObject jsonObject2 = jsonObject3 = jsonObject;
                if (jsonObject3 == null) {
                    return null;
                }
                final String optString = jsonObject2.optString("key");
                if (optString == null || !optString.equals(s)) {
                    return null;
                }
                final String optString2 = jsonObject2.optString("tag", (String)null);
                if ((s2 == null && optString2 != null) || (s2 != null && !s2.equals(optString2))) {
                    return null;
                }
                final long time = new Date().getTime();
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "Setting lastModified to " + (Object)time + " for " + file.getName());
                file.setLastModified(time);
                if (!true) {
                    bufferedInputStream.close();
                }
                return bufferedInputStream;
            }
            finally {
                if (!false) {
                    bufferedInputStream.close();
                }
            }
        }
    }
    
    public String getLocation() {
        return this.directory.getPath();
    }
    
    public InputStream interceptAndPut(final String s, final InputStream inputStream) throws IOException {
        return new CopyingInputStream(inputStream, this.openPutStream(s));
    }
    
    public OutputStream openPutStream(final String s) throws IOException {
        return this.openPutStream(s, null);
    }
    
    public OutputStream openPutStream(final String p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/internal/FileLruCache.directory:Ljava/io/File;
        //     4: invokestatic    com/facebook/internal/FileLruCache$BufferFile.newFile:(Ljava/io/File;)Ljava/io/File;
        //     7: astore_3       
        //     8: aload_3        
        //     9: invokevirtual   java/io/File.delete:()Z
        //    12: pop            
        //    13: aload_3        
        //    14: invokevirtual   java/io/File.createNewFile:()Z
        //    17: ifne            51
        //    20: new             Ljava/io/IOException;
        //    23: dup            
        //    24: new             Ljava/lang/StringBuilder;
        //    27: dup            
        //    28: invokespecial   java/lang/StringBuilder.<init>:()V
        //    31: ldc_w           "Could not create file at "
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: aload_3        
        //    38: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    41: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    44: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    47: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //    50: athrow         
        //    51: new             Ljava/io/FileOutputStream;
        //    54: dup            
        //    55: aload_3        
        //    56: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    59: astore          5
        //    61: new             Ljava/io/BufferedOutputStream;
        //    64: dup            
        //    65: new             Lcom/facebook/internal/FileLruCache$CloseCallbackOutputStream;
        //    68: dup            
        //    69: aload           5
        //    71: new             Lcom/facebook/internal/FileLruCache$1;
        //    74: dup            
        //    75: aload_0        
        //    76: invokestatic    java/lang/System.currentTimeMillis:()J
        //    79: aload_3        
        //    80: aload_1        
        //    81: invokespecial   com/facebook/internal/FileLruCache$1.<init>:(Lcom/facebook/internal/FileLruCache;JLjava/io/File;Ljava/lang/String;)V
        //    84: invokespecial   com/facebook/internal/FileLruCache$CloseCallbackOutputStream.<init>:(Ljava/io/OutputStream;Lcom/facebook/internal/FileLruCache$StreamCloseCallback;)V
        //    87: sipush          8192
        //    90: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    93: astore          6
        //    95: new             Lorg/json/JSONObject;
        //    98: dup            
        //    99: invokespecial   org/json/JSONObject.<init>:()V
        //   102: astore          7
        //   104: aload           7
        //   106: ldc             "key"
        //   108: aload_1        
        //   109: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   112: pop            
        //   113: aload_2        
        //   114: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //   117: ifne            129
        //   120: aload           7
        //   122: ldc             "tag"
        //   124: aload_2        
        //   125: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   128: pop            
        //   129: aload           6
        //   131: aload           7
        //   133: invokestatic    com/facebook/internal/FileLruCache$StreamHeader.writeHeader:(Ljava/io/OutputStream;Lorg/json/JSONObject;)V
        //   136: iconst_1       
        //   137: ifne            145
        //   140: aload           6
        //   142: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   145: aload           6
        //   147: areturn        
        //   148: astore          12
        //   150: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   153: iconst_5       
        //   154: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   157: new             Ljava/lang/StringBuilder;
        //   160: dup            
        //   161: invokespecial   java/lang/StringBuilder.<init>:()V
        //   164: ldc_w           "Error creating buffer output stream: "
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: aload           12
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   175: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   178: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
        //   181: new             Ljava/io/IOException;
        //   184: dup            
        //   185: aload           12
        //   187: invokevirtual   java/io/FileNotFoundException.getMessage:()Ljava/lang/String;
        //   190: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   193: athrow         
        //   194: astore          9
        //   196: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   199: iconst_5       
        //   200: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   203: new             Ljava/lang/StringBuilder;
        //   206: dup            
        //   207: invokespecial   java/lang/StringBuilder.<init>:()V
        //   210: ldc_w           "Error creating JSON header for cache file: "
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: aload           9
        //   218: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   221: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   224: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
        //   227: new             Ljava/io/IOException;
        //   230: dup            
        //   231: aload           9
        //   233: invokevirtual   org/json/JSONException.getMessage:()Ljava/lang/String;
        //   236: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   239: athrow         
        //   240: astore          8
        //   242: iconst_0       
        //   243: ifne            251
        //   246: aload           6
        //   248: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   251: aload           8
        //   253: athrow         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  51     61     148    194    Ljava/io/FileNotFoundException;
        //  95     129    194    240    Lorg/json/JSONException;
        //  95     129    240    254    Any
        //  129    136    194    240    Lorg/json/JSONException;
        //  129    136    240    254    Any
        //  196    240    240    254    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    long sizeInBytesForTest() {
        long n;
        synchronized (this.lock) {
            while (true) {
                if (!this.isTrimPending) {
                    if (!this.isTrimInProgress) {
                        break;
                    }
                }
                try {
                    this.lock.wait();
                }
                catch (InterruptedException ex) {}
            }
            // monitorexit(this.lock)
            final File[] listFiles = this.directory.listFiles();
            n = 0L;
            if (listFiles != null) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    n += listFiles[i].length();
                }
            }
        }
        return n;
    }
    
    @Override
    public String toString() {
        return "{FileLruCache: tag:" + this.tag + " file:" + this.directory.getName() + "}";
    }
    
    private static class BufferFile
    {
        private static final String FILE_NAME_PREFIX = "buffer";
        private static final FilenameFilter filterExcludeBufferFiles;
        private static final FilenameFilter filterExcludeNonBufferFiles;
        
        static {
            filterExcludeBufferFiles = new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String s) {
                    return !s.startsWith("buffer");
                }
            };
            filterExcludeNonBufferFiles = new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String s) {
                    return s.startsWith("buffer");
                }
            };
        }
        
        static void deleteAll(final File file) {
            final File[] listFiles = file.listFiles(excludeNonBufferFiles());
            if (listFiles != null) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    listFiles[i].delete();
                }
            }
        }
        
        static FilenameFilter excludeBufferFiles() {
            return BufferFile.filterExcludeBufferFiles;
        }
        
        static FilenameFilter excludeNonBufferFiles() {
            return BufferFile.filterExcludeNonBufferFiles;
        }
        
        static File newFile(final File file) {
            return new File(file, "buffer" + Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString());
        }
    }
    
    private static class CloseCallbackOutputStream extends OutputStream
    {
        final StreamCloseCallback callback;
        final OutputStream innerStream;
        
        CloseCallbackOutputStream(final OutputStream innerStream, final StreamCloseCallback callback) {
            this.innerStream = innerStream;
            this.callback = callback;
        }
        
        @Override
        public void close() throws IOException {
            try {
                this.innerStream.close();
            }
            finally {
                this.callback.onClose();
            }
        }
        
        @Override
        public void flush() throws IOException {
            this.innerStream.flush();
        }
        
        @Override
        public void write(final int n) throws IOException {
            this.innerStream.write(n);
        }
        
        @Override
        public void write(final byte[] array) throws IOException {
            this.innerStream.write(array);
        }
        
        @Override
        public void write(final byte[] array, final int n, final int n2) throws IOException {
            this.innerStream.write(array, n, n2);
        }
    }
    
    private static final class CopyingInputStream extends InputStream
    {
        final InputStream input;
        final OutputStream output;
        
        CopyingInputStream(final InputStream input, final OutputStream output) {
            this.input = input;
            this.output = output;
        }
        
        @Override
        public int available() throws IOException {
            return this.input.available();
        }
        
        @Override
        public void close() throws IOException {
            try {
                this.input.close();
            }
            finally {
                this.output.close();
            }
        }
        
        @Override
        public void mark(final int n) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean markSupported() {
            return false;
        }
        
        @Override
        public int read() throws IOException {
            final int read = this.input.read();
            if (read >= 0) {
                this.output.write(read);
            }
            return read;
        }
        
        @Override
        public int read(final byte[] array) throws IOException {
            final int read = this.input.read(array);
            if (read > 0) {
                this.output.write(array, 0, read);
            }
            return read;
        }
        
        @Override
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            final int read = this.input.read(array, n, n2);
            if (read > 0) {
                this.output.write(array, n, read);
            }
            return read;
        }
        
        @Override
        public void reset() {
            synchronized (this) {
                throw new UnsupportedOperationException();
            }
        }
        
        @Override
        public long skip(final long n) throws IOException {
            final byte[] array = new byte[1024];
            long n2;
            int read;
            for (n2 = 0L; n2 < n; n2 += read) {
                read = this.read(array, 0, (int)Math.min(n - n2, array.length));
                if (read < 0) {
                    break;
                }
            }
            return n2;
        }
    }
    
    public static final class Limits
    {
        private int byteCount;
        private int fileCount;
        
        public Limits() {
            this.fileCount = 1024;
            this.byteCount = 1048576;
        }
        
        int getByteCount() {
            return this.byteCount;
        }
        
        int getFileCount() {
            return this.fileCount;
        }
        
        void setByteCount(final int byteCount) {
            if (byteCount < 0) {
                throw new InvalidParameterException("Cache byte-count limit must be >= 0");
            }
            this.byteCount = byteCount;
        }
        
        void setFileCount(final int fileCount) {
            if (fileCount < 0) {
                throw new InvalidParameterException("Cache file count limit must be >= 0");
            }
            this.fileCount = fileCount;
        }
    }
    
    private static final class ModifiedFile implements Comparable<ModifiedFile>
    {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        private final File file;
        private final long modified;
        
        ModifiedFile(final File file) {
            this.file = file;
            this.modified = file.lastModified();
        }
        
        @Override
        public int compareTo(final ModifiedFile modifiedFile) {
            if (this.getModified() < modifiedFile.getModified()) {
                return -1;
            }
            if (this.getModified() > modifiedFile.getModified()) {
                return 1;
            }
            return this.getFile().compareTo(modifiedFile.getFile());
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof ModifiedFile && this.compareTo((ModifiedFile)o) == 0;
        }
        
        File getFile() {
            return this.file;
        }
        
        long getModified() {
            return this.modified;
        }
        
        @Override
        public int hashCode() {
            return 37 * (1073 + this.file.hashCode()) + (int)(this.modified % 2147483647L);
        }
    }
    
    private interface StreamCloseCallback
    {
        void onClose();
    }
    
    private static final class StreamHeader
    {
        private static final int HEADER_VERSION;
        
        static JSONObject readHeader(final InputStream inputStream) throws IOException {
            if (inputStream.read() != 0) {
                return null;
            }
            int n = 0;
            for (int i = 0; i < 3; ++i) {
                final int read = inputStream.read();
                if (read == -1) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                n = (n << 8) + (read & 0xFF);
            }
            final byte[] array = new byte[n];
            int read2;
            for (int j = 0; j < array.length; j += read2) {
                read2 = inputStream.read(array, j, array.length - j);
                if (read2 < 1) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read stopped at " + (Object)j + " when expected " + array.length);
                    return null;
                }
            }
            final JSONTokener jsonTokener = new JSONTokener(new String(array));
            try {
                final Object nextValue = jsonTokener.nextValue();
                if (!(nextValue instanceof JSONObject)) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: expected JSONObject, got " + ((JSONObject)nextValue).getClass().getCanonicalName());
                    return null;
                }
                return (JSONObject)nextValue;
            }
            catch (JSONException ex) {
                throw new IOException(ex.getMessage());
            }
        }
        
        static void writeHeader(final OutputStream outputStream, final JSONObject jsonObject) throws IOException {
            final byte[] bytes = jsonObject.toString().getBytes();
            outputStream.write(0);
            outputStream.write(0xFF & bytes.length >> 16);
            outputStream.write(0xFF & bytes.length >> 8);
            outputStream.write(0xFF & bytes.length >> 0);
            outputStream.write(bytes);
        }
    }
}
