package com.squareup.picasso;

import android.net.http.*;
import android.content.*;
import java.io.*;

private static class ResponseCacheIcs
{
    static void close(final Object o) {
        try {
            ((HttpResponseCache)o).close();
        }
        catch (IOException ex) {}
    }
    
    static Object install(final Context context) throws IOException {
        final File defaultCacheDir = Utils.createDefaultCacheDir(context);
        HttpResponseCache httpResponseCache = HttpResponseCache.getInstalled();
        if (httpResponseCache == null) {
            httpResponseCache = HttpResponseCache.install(defaultCacheDir, Utils.calculateDiskCacheSize(defaultCacheDir));
        }
        return httpResponseCache;
    }
}
