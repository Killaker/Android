package com.squareup.picasso;

import android.net.*;
import android.graphics.*;
import java.io.*;

public abstract class RequestHandler
{
    static void calculateInSampleSize(final int n, final int n2, final int n3, final int n4, final BitmapFactory$Options bitmapFactory$Options, final Request request) {
        int inSampleSize = 1;
        if (n4 > n2 || n3 > n) {
            if (n2 == 0) {
                inSampleSize = (int)Math.floor(n3 / n);
            }
            else if (n == 0) {
                inSampleSize = (int)Math.floor(n4 / n2);
            }
            else {
                final int n5 = (int)Math.floor(n4 / n2);
                final int n6 = (int)Math.floor(n3 / n);
                if (request.centerInside) {
                    inSampleSize = Math.max(n5, n6);
                }
                else {
                    inSampleSize = Math.min(n5, n6);
                }
            }
        }
        bitmapFactory$Options.inSampleSize = inSampleSize;
        bitmapFactory$Options.inJustDecodeBounds = false;
    }
    
    static void calculateInSampleSize(final int n, final int n2, final BitmapFactory$Options bitmapFactory$Options, final Request request) {
        calculateInSampleSize(n, n2, bitmapFactory$Options.outWidth, bitmapFactory$Options.outHeight, bitmapFactory$Options, request);
    }
    
    static BitmapFactory$Options createBitmapOptions(final Request request) {
        final boolean hasSize = request.hasSize();
        boolean b;
        if (request.config != null) {
            b = true;
        }
        else {
            b = false;
        }
        if (!hasSize) {
            final BitmapFactory$Options bitmapFactory$Options = null;
            if (!b) {
                return bitmapFactory$Options;
            }
        }
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = hasSize;
        if (b) {
            bitmapFactory$Options.inPreferredConfig = request.config;
        }
        return bitmapFactory$Options;
    }
    
    static boolean requiresInSampleSize(final BitmapFactory$Options bitmapFactory$Options) {
        return bitmapFactory$Options != null && bitmapFactory$Options.inJustDecodeBounds;
    }
    
    public abstract boolean canHandleRequest(final Request p0);
    
    int getRetryCount() {
        return 0;
    }
    
    public abstract Result load(final Request p0, final int p1) throws IOException;
    
    boolean shouldRetry(final boolean b, final NetworkInfo networkInfo) {
        return false;
    }
    
    boolean supportsReplay() {
        return false;
    }
    
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
}
