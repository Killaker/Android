package com.squareup.picasso;

import android.graphics.*;
import java.io.*;

public static class Response
{
    final Bitmap bitmap;
    final boolean cached;
    final long contentLength;
    final InputStream stream;
    
    public Response(final Bitmap bitmap, final boolean cached) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap may not be null.");
        }
        this.stream = null;
        this.bitmap = bitmap;
        this.cached = cached;
        this.contentLength = -1L;
    }
    
    public Response(final Bitmap bitmap, final boolean b, final long n) {
        this(bitmap, b);
    }
    
    public Response(final InputStream inputStream, final boolean b) {
        this(inputStream, b, -1L);
    }
    
    public Response(final InputStream stream, final boolean cached, final long contentLength) {
        if (stream == null) {
            throw new IllegalArgumentException("Stream may not be null.");
        }
        this.stream = stream;
        this.bitmap = null;
        this.cached = cached;
        this.contentLength = contentLength;
    }
    
    @Deprecated
    public Bitmap getBitmap() {
        return this.bitmap;
    }
    
    public long getContentLength() {
        return this.contentLength;
    }
    
    public InputStream getInputStream() {
        return this.stream;
    }
}
