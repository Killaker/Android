package com.squareup.picasso;

import android.graphics.*;
import java.io.*;

public static final class Result
{
    private final Bitmap bitmap;
    private final int exifOrientation;
    private final Picasso.LoadedFrom loadedFrom;
    private final InputStream stream;
    
    public Result(final Bitmap bitmap, final Picasso.LoadedFrom loadedFrom) {
        this(Utils.checkNotNull(bitmap, "bitmap == null"), null, loadedFrom, 0);
    }
    
    Result(final Bitmap bitmap, final InputStream stream, final Picasso.LoadedFrom loadedFrom, final int exifOrientation) {
        int n = 1;
        int n2;
        if (bitmap != null) {
            n2 = n;
        }
        else {
            n2 = 0;
        }
        if (stream == null) {
            n = 0;
        }
        if ((n ^ n2) == 0x0) {
            throw new AssertionError();
        }
        this.bitmap = bitmap;
        this.stream = stream;
        this.loadedFrom = Utils.checkNotNull(loadedFrom, "loadedFrom == null");
        this.exifOrientation = exifOrientation;
    }
    
    public Result(final InputStream inputStream, final Picasso.LoadedFrom loadedFrom) {
        this(null, Utils.checkNotNull(inputStream, "stream == null"), loadedFrom, 0);
    }
    
    public Bitmap getBitmap() {
        return this.bitmap;
    }
    
    int getExifOrientation() {
        return this.exifOrientation;
    }
    
    public Picasso.LoadedFrom getLoadedFrom() {
        return this.loadedFrom;
    }
    
    public InputStream getStream() {
        return this.stream;
    }
}
