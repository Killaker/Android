package com.android.volley.toolbox;

import android.widget.*;
import android.graphics.*;
import com.android.volley.*;

public class ImageRequest extends Request<Bitmap>
{
    private static final float IMAGE_BACKOFF_MULT = 2.0f;
    private static final int IMAGE_MAX_RETRIES = 2;
    private static final int IMAGE_TIMEOUT_MS = 1000;
    private static final Object sDecodeLock;
    private final Bitmap$Config mDecodeConfig;
    private final Response.Listener<Bitmap> mListener;
    private final int mMaxHeight;
    private final int mMaxWidth;
    private ImageView$ScaleType mScaleType;
    
    static {
        sDecodeLock = new Object();
    }
    
    public ImageRequest(final String s, final Response.Listener<Bitmap> listener, final int n, final int n2, final Bitmap$Config bitmap$Config, final Response.ErrorListener errorListener) {
        this(s, listener, n, n2, ImageView$ScaleType.CENTER_INSIDE, bitmap$Config, errorListener);
    }
    
    public ImageRequest(final String s, final Response.Listener<Bitmap> mListener, final int mMaxWidth, final int mMaxHeight, final ImageView$ScaleType mScaleType, final Bitmap$Config mDecodeConfig, final Response.ErrorListener errorListener) {
        super(0, s, errorListener);
        this.setRetryPolicy(new DefaultRetryPolicy(1000, 2, 2.0f));
        this.mListener = mListener;
        this.mDecodeConfig = mDecodeConfig;
        this.mMaxWidth = mMaxWidth;
        this.mMaxHeight = mMaxHeight;
        this.mScaleType = mScaleType;
    }
    
    private Response<Bitmap> doParse(final NetworkResponse networkResponse) {
        final byte[] data = networkResponse.data;
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        Bitmap bitmap;
        if (this.mMaxWidth == 0 && this.mMaxHeight == 0) {
            bitmapFactory$Options.inPreferredConfig = this.mDecodeConfig;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, bitmapFactory$Options);
        }
        else {
            bitmapFactory$Options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, bitmapFactory$Options);
            final int outWidth = bitmapFactory$Options.outWidth;
            final int outHeight = bitmapFactory$Options.outHeight;
            final int resizedDimension = getResizedDimension(this.mMaxWidth, this.mMaxHeight, outWidth, outHeight, this.mScaleType);
            final int resizedDimension2 = getResizedDimension(this.mMaxHeight, this.mMaxWidth, outHeight, outWidth, this.mScaleType);
            bitmapFactory$Options.inJustDecodeBounds = false;
            bitmapFactory$Options.inSampleSize = findBestSampleSize(outWidth, outHeight, resizedDimension, resizedDimension2);
            final Bitmap decodeByteArray = BitmapFactory.decodeByteArray(data, 0, data.length, bitmapFactory$Options);
            if (decodeByteArray != null && (decodeByteArray.getWidth() > resizedDimension || decodeByteArray.getHeight() > resizedDimension2)) {
                bitmap = Bitmap.createScaledBitmap(decodeByteArray, resizedDimension, resizedDimension2, true);
                decodeByteArray.recycle();
            }
            else {
                bitmap = decodeByteArray;
            }
        }
        if (bitmap == null) {
            return Response.error(new ParseError(networkResponse));
        }
        return Response.success(bitmap, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
    
    static int findBestSampleSize(final int n, final int n2, final int n3, final int n4) {
        double min;
        float n5;
        for (min = Math.min(n / n3, n2 / n4), n5 = 1.0f; 2.0f * n5 <= min; n5 *= 2.0f) {}
        return (int)n5;
    }
    
    private static int getResizedDimension(final int n, final int n2, final int n3, final int n4, final ImageView$ScaleType imageView$ScaleType) {
        if (n != 0 || n2 != 0) {
            if (imageView$ScaleType == ImageView$ScaleType.FIT_XY) {
                if (n != 0) {
                    return n;
                }
            }
            else {
                if (n == 0) {
                    return n2 / n4 * n3;
                }
                if (n2 == 0) {
                    return n;
                }
                final double n5 = n4 / n3;
                int n6 = n;
                if (imageView$ScaleType == ImageView$ScaleType.CENTER_CROP) {
                    if (n5 * n6 < n2) {
                        n6 = (int)(n2 / n5);
                    }
                    return n6;
                }
                if (n5 * n6 > n2) {
                    n6 = (int)(n2 / n5);
                }
                return n6;
            }
        }
        return n3;
    }
    
    @Override
    protected void deliverResponse(final Bitmap bitmap) {
        this.mListener.onResponse(bitmap);
    }
    
    @Override
    public Priority getPriority() {
        return Priority.LOW;
    }
    
    @Override
    protected Response<Bitmap> parseNetworkResponse(final NetworkResponse networkResponse) {
        synchronized (ImageRequest.sDecodeLock) {
            try {
                return this.doParse(networkResponse);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                VolleyLog.e("Caught OOM for %d byte image, url=%s", networkResponse.data.length, this.getUrl());
                return Response.error(new ParseError(outOfMemoryError));
            }
        }
    }
}
