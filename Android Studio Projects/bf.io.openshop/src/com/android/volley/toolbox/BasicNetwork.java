package com.android.volley.toolbox;

import org.apache.http.impl.cookie.*;
import java.io.*;
import android.os.*;
import com.android.volley.*;
import java.util.*;
import java.net.*;
import org.apache.http.conn.*;
import org.apache.http.*;

public class BasicNetwork implements Network
{
    protected static final boolean DEBUG;
    private static int DEFAULT_POOL_SIZE;
    private static int SLOW_REQUEST_THRESHOLD_MS;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;
    
    static {
        DEBUG = VolleyLog.DEBUG;
        BasicNetwork.SLOW_REQUEST_THRESHOLD_MS = 3000;
        BasicNetwork.DEFAULT_POOL_SIZE = 4096;
    }
    
    public BasicNetwork(final HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(BasicNetwork.DEFAULT_POOL_SIZE));
    }
    
    public BasicNetwork(final HttpStack mHttpStack, final ByteArrayPool mPool) {
        this.mHttpStack = mHttpStack;
        this.mPool = mPool;
    }
    
    private void addCacheHeaders(final Map<String, String> map, final Cache.Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                map.put("If-None-Match", entry.etag);
            }
            if (entry.lastModified > 0L) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.lastModified)));
            }
        }
    }
    
    private static void attemptRetryOnException(final String s, final Request<?> request, final VolleyError volleyError) throws VolleyError {
        final RetryPolicy retryPolicy = request.getRetryPolicy();
        final int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", s, timeoutMs));
        }
        catch (VolleyError volleyError2) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", s, timeoutMs));
            throw volleyError2;
        }
    }
    
    protected static Map<String, String> convertHeaders(final Header[] array) {
        final TreeMap<String, String> treeMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < array.length; ++i) {
            treeMap.put(array[i].getName(), array[i].getValue());
        }
        return treeMap;
    }
    
    private byte[] entityToBytes(final HttpEntity p0) throws IOException, ServerError {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/android/volley/toolbox/PoolingByteArrayOutputStream;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //     8: aload_1        
        //     9: invokeinterface org/apache/http/HttpEntity.getContentLength:()J
        //    14: l2i            
        //    15: invokespecial   com/android/volley/toolbox/PoolingByteArrayOutputStream.<init>:(Lcom/android/volley/toolbox/ByteArrayPool;I)V
        //    18: astore_2       
        //    19: aconst_null    
        //    20: astore_3       
        //    21: aload_1        
        //    22: invokeinterface org/apache/http/HttpEntity.getContent:()Ljava/io/InputStream;
        //    27: astore          6
        //    29: aconst_null    
        //    30: astore_3       
        //    31: aload           6
        //    33: ifnonnull       67
        //    36: new             Lcom/android/volley/ServerError;
        //    39: dup            
        //    40: invokespecial   com/android/volley/ServerError.<init>:()V
        //    43: athrow         
        //    44: astore          4
        //    46: aload_1        
        //    47: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //    52: aload_0        
        //    53: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //    56: aload_3        
        //    57: invokevirtual   com/android/volley/toolbox/ByteArrayPool.returnBuf:([B)V
        //    60: aload_2        
        //    61: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.close:()V
        //    64: aload           4
        //    66: athrow         
        //    67: aload_0        
        //    68: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //    71: sipush          1024
        //    74: invokevirtual   com/android/volley/toolbox/ByteArrayPool.getBuf:(I)[B
        //    77: astore_3       
        //    78: aload           6
        //    80: aload_3        
        //    81: invokevirtual   java/io/InputStream.read:([B)I
        //    84: istore          7
        //    86: iload           7
        //    88: iconst_m1      
        //    89: if_icmpeq       103
        //    92: aload_2        
        //    93: aload_3        
        //    94: iconst_0       
        //    95: iload           7
        //    97: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.write:([BII)V
        //   100: goto            78
        //   103: aload_2        
        //   104: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.toByteArray:()[B
        //   107: astore          8
        //   109: aload_1        
        //   110: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   115: aload_0        
        //   116: getfield        com/android/volley/toolbox/BasicNetwork.mPool:Lcom/android/volley/toolbox/ByteArrayPool;
        //   119: aload_3        
        //   120: invokevirtual   com/android/volley/toolbox/ByteArrayPool.returnBuf:([B)V
        //   123: aload_2        
        //   124: invokevirtual   com/android/volley/toolbox/PoolingByteArrayOutputStream.close:()V
        //   127: aload           8
        //   129: areturn        
        //   130: astore          9
        //   132: ldc             "Error occured when calling consumingContent"
        //   134: iconst_0       
        //   135: anewarray       Ljava/lang/Object;
        //   138: invokestatic    com/android/volley/VolleyLog.v:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   141: goto            115
        //   144: astore          5
        //   146: ldc             "Error occured when calling consumingContent"
        //   148: iconst_0       
        //   149: anewarray       Ljava/lang/Object;
        //   152: invokestatic    com/android/volley/VolleyLog.v:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   155: goto            52
        //    Exceptions:
        //  throws java.io.IOException
        //  throws com.android.volley.ServerError
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  21     29     44     67     Any
        //  36     44     44     67     Any
        //  46     52     144    158    Ljava/io/IOException;
        //  67     78     44     67     Any
        //  78     86     44     67     Any
        //  92     100    44     67     Any
        //  103    109    44     67     Any
        //  109    115    130    144    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void logSlowRequests(final long n, final Request<?> request, final byte[] array, final StatusLine statusLine) {
        if (BasicNetwork.DEBUG || n > BasicNetwork.SLOW_REQUEST_THRESHOLD_MS) {
            final Object[] array2 = { request, n, null, null, null };
            Serializable value;
            if (array != null) {
                value = array.length;
            }
            else {
                value = "null";
            }
            array2[2] = value;
            array2[3] = statusLine.getStatusCode();
            array2[4] = request.getRetryPolicy().getCurrentRetryCount();
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", array2);
        }
    }
    
    protected void logError(final String s, final String s2, final long n) {
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", s, SystemClock.elapsedRealtime() - n, s2);
    }
    
    @Override
    public NetworkResponse performRequest(final Request<?> request) throws VolleyError {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        HttpResponse performRequest = null;
        Map<String, String> map = Collections.emptyMap();
        try {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            this.addCacheHeaders(hashMap, request.getCacheEntry());
            performRequest = this.mHttpStack.performRequest(request, hashMap);
            final StatusLine statusLine = performRequest.getStatusLine();
            final int statusCode = statusLine.getStatusCode();
            map = convertHeaders(performRequest.getAllHeaders());
            if (statusCode == 304) {
                final Cache.Entry cacheEntry = request.getCacheEntry();
                if (cacheEntry == null) {
                    return new NetworkResponse(304, null, map, true, SystemClock.elapsedRealtime() - elapsedRealtime);
                }
                cacheEntry.responseHeaders.putAll(map);
                return new NetworkResponse(304, cacheEntry.data, cacheEntry.responseHeaders, true, SystemClock.elapsedRealtime() - elapsedRealtime);
            }
            else {
                if (performRequest.getEntity() == null) {
                    goto Label_0237;
                }
                final byte[] entityToBytes = this.entityToBytes(performRequest.getEntity());
                try {
                    this.logSlowRequests(SystemClock.elapsedRealtime() - elapsedRealtime, request, entityToBytes, statusLine);
                    if (statusCode < 200 || statusCode > 299) {
                        throw new IOException();
                    }
                    goto Label_0245;
                }
                catch (SocketTimeoutException ex) {}
                catch (IOException ex2) {}
                catch (MalformedURLException ex3) {}
                catch (ConnectTimeoutException ex4) {}
            }
        }
        catch (ConnectTimeoutException ex5) {}
        catch (MalformedURLException ex6) {}
        catch (IOException ex7) {}
        catch (SocketTimeoutException ex8) {
            goto Label_0220;
        }
    }
}
