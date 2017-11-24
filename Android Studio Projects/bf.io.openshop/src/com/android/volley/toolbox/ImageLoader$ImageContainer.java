package com.android.volley.toolbox;

import android.graphics.*;
import java.util.*;
import com.android.volley.*;

public class ImageContainer
{
    private Bitmap mBitmap;
    private final String mCacheKey;
    private final ImageListener mListener;
    private final String mRequestUrl;
    
    public ImageContainer(final Bitmap mBitmap, final String mRequestUrl, final String mCacheKey, final ImageListener mListener) {
        this.mBitmap = mBitmap;
        this.mRequestUrl = mRequestUrl;
        this.mCacheKey = mCacheKey;
        this.mListener = mListener;
    }
    
    public void cancelRequest() {
        if (this.mListener != null) {
            final BatchedImageRequest batchedImageRequest = ImageLoader.access$100(ImageLoader.this).get(this.mCacheKey);
            if (batchedImageRequest != null) {
                if (batchedImageRequest.removeContainerAndCancelIfNecessary(this)) {
                    ImageLoader.access$100(ImageLoader.this).remove(this.mCacheKey);
                }
            }
            else {
                final BatchedImageRequest batchedImageRequest2 = ImageLoader.access$200(ImageLoader.this).get(this.mCacheKey);
                if (batchedImageRequest2 != null) {
                    batchedImageRequest2.removeContainerAndCancelIfNecessary(this);
                    if (batchedImageRequest2.mContainers.size() == 0) {
                        ImageLoader.access$200(ImageLoader.this).remove(this.mCacheKey);
                    }
                }
            }
        }
    }
    
    public Bitmap getBitmap() {
        return this.mBitmap;
    }
    
    public String getRequestUrl() {
        return this.mRequestUrl;
    }
}
