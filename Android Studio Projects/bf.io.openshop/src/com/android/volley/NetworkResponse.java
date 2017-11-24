package com.android.volley;

import java.util.*;

public class NetworkResponse
{
    public final byte[] data;
    public final Map<String, String> headers;
    public final long networkTimeMs;
    public final boolean notModified;
    public final int statusCode;
    
    public NetworkResponse(final int n, final byte[] array, final Map<String, String> map, final boolean b) {
        this(n, array, map, b, 0L);
    }
    
    public NetworkResponse(final int statusCode, final byte[] data, final Map<String, String> headers, final boolean notModified, final long networkTimeMs) {
        this.statusCode = statusCode;
        this.data = data;
        this.headers = headers;
        this.notModified = notModified;
        this.networkTimeMs = networkTimeMs;
    }
    
    public NetworkResponse(final byte[] array) {
        this(200, array, Collections.emptyMap(), false, 0L);
    }
    
    public NetworkResponse(final byte[] array, final Map<String, String> map) {
        this(200, array, map, false, 0L);
    }
}
