package com.facebook.internal;

import android.net.*;
import java.io.*;
import com.facebook.*;

class UrlRedirectCache
{
    private static final String REDIRECT_CONTENT_TAG;
    static final String TAG;
    private static volatile FileLruCache urlRedirectCache;
    
    static {
        TAG = UrlRedirectCache.class.getSimpleName();
        REDIRECT_CONTENT_TAG = UrlRedirectCache.TAG + "_Redirect";
    }
    
    static void cacheUriRedirect(final Uri uri, final Uri uri2) {
        if (uri == null || uri2 == null) {
            return;
        }
        OutputStream openPutStream = null;
        try {
            openPutStream = getCache().openPutStream(uri.toString(), UrlRedirectCache.REDIRECT_CONTENT_TAG);
            openPutStream.write(uri2.toString().getBytes());
        }
        catch (IOException ex) {}
        finally {
            Utility.closeQuietly(openPutStream);
        }
    }
    
    static void clearCache() {
        try {
            getCache().clearCache();
        }
        catch (IOException ex) {
            Logger.log(LoggingBehavior.CACHE, 5, UrlRedirectCache.TAG, "clearCache failed " + ex.getMessage());
        }
    }
    
    static FileLruCache getCache() throws IOException {
        synchronized (UrlRedirectCache.class) {
            if (UrlRedirectCache.urlRedirectCache == null) {
                UrlRedirectCache.urlRedirectCache = new FileLruCache(UrlRedirectCache.TAG, new FileLruCache.Limits());
            }
            return UrlRedirectCache.urlRedirectCache;
        }
    }
    
    static Uri getRedirectedUri(final Uri p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       6
        //     4: aconst_null    
        //     5: areturn        
        //     6: aload_0        
        //     7: invokevirtual   android/net/Uri.toString:()Ljava/lang/String;
        //    10: astore_1       
        //    11: aconst_null    
        //    12: astore_2       
        //    13: invokestatic    com/facebook/internal/UrlRedirectCache.getCache:()Lcom/facebook/internal/FileLruCache;
        //    16: astore          5
        //    18: iconst_0       
        //    19: istore          6
        //    21: aconst_null    
        //    22: astore          7
        //    24: aload           5
        //    26: aload_1        
        //    27: getstatic       com/facebook/internal/UrlRedirectCache.REDIRECT_CONTENT_TAG:Ljava/lang/String;
        //    30: invokevirtual   com/facebook/internal/FileLruCache.get:(Ljava/lang/String;Ljava/lang/String;)Ljava/io/InputStream;
        //    33: astore          9
        //    35: aload           9
        //    37: ifnull          128
        //    40: iconst_1       
        //    41: istore          6
        //    43: new             Ljava/io/InputStreamReader;
        //    46: dup            
        //    47: aload           9
        //    49: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    52: astore_2       
        //    53: sipush          128
        //    56: newarray        C
        //    58: astore          12
        //    60: new             Ljava/lang/StringBuilder;
        //    63: dup            
        //    64: invokespecial   java/lang/StringBuilder.<init>:()V
        //    67: astore          13
        //    69: aload_2        
        //    70: aload           12
        //    72: iconst_0       
        //    73: aload           12
        //    75: arraylength    
        //    76: invokevirtual   java/io/InputStreamReader.read:([CII)I
        //    79: istore          14
        //    81: iload           14
        //    83: ifle            108
        //    86: aload           13
        //    88: aload           12
        //    90: iconst_0       
        //    91: iload           14
        //    93: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //    96: pop            
        //    97: goto            69
        //   100: astore          4
        //   102: aload_2        
        //   103: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   106: aconst_null    
        //   107: areturn        
        //   108: aload_2        
        //   109: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   112: aload           13
        //   114: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   117: astore          16
        //   119: aload           16
        //   121: astore_1       
        //   122: aload_2        
        //   123: astore          7
        //   125: goto            24
        //   128: iload           6
        //   130: ifeq            147
        //   133: aload_1        
        //   134: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //   137: astore          11
        //   139: aload           7
        //   141: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   144: aload           11
        //   146: areturn        
        //   147: aload           7
        //   149: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   152: aload           7
        //   154: pop            
        //   155: aconst_null    
        //   156: areturn        
        //   157: astore_3       
        //   158: aload_2        
        //   159: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   162: aload_3        
        //   163: athrow         
        //   164: astore_3       
        //   165: aload           7
        //   167: astore_2       
        //   168: goto            158
        //   171: astore          8
        //   173: aload           7
        //   175: astore_2       
        //   176: goto            102
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  13     18     100    102    Ljava/io/IOException;
        //  13     18     157    158    Any
        //  24     35     171    179    Ljava/io/IOException;
        //  24     35     164    171    Any
        //  43     53     171    179    Ljava/io/IOException;
        //  43     53     164    171    Any
        //  53     69     100    102    Ljava/io/IOException;
        //  53     69     157    158    Any
        //  69     81     100    102    Ljava/io/IOException;
        //  69     81     157    158    Any
        //  86     97     100    102    Ljava/io/IOException;
        //  86     97     157    158    Any
        //  108    119    100    102    Ljava/io/IOException;
        //  108    119    157    158    Any
        //  133    139    171    179    Ljava/io/IOException;
        //  133    139    164    171    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
