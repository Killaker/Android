package com.squareup.picasso;

import java.io.*;

public static class ResponseException extends IOException
{
    final boolean localCacheOnly;
    final int responseCode;
    
    public ResponseException(final String s, final int n, final int responseCode) {
        super(s);
        this.localCacheOnly = NetworkPolicy.isOfflineOnly(n);
        this.responseCode = responseCode;
    }
}
