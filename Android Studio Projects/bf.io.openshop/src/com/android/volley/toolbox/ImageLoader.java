package com.android.volley.toolbox;

import android.os.*;
import android.widget.*;
import com.android.volley.*;
import android.graphics.*;
import java.util.*;

public class ImageLoader
{
    private int mBatchResponseDelayMs;
    private final HashMap<String, BatchedImageRequest> mBatchedResponses;
    private final ImageCache mCache;
    private final Handler mHandler;
    private final HashMap<String, BatchedImageRequest> mInFlightRequests;
    private final RequestQueue mRequestQueue;
    private Runnable mRunnable;
    
    public ImageLoader(final RequestQueue mRequestQueue, final ImageCache mCache) {
        this.mBatchResponseDelayMs = 100;
        this.mInFlightRequests = new HashMap<String, BatchedImageRequest>();
        this.mBatchedResponses = new HashMap<String, BatchedImageRequest>();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mRequestQueue = mRequestQueue;
        this.mCache = mCache;
    }
    
    private void batchResponse(final String s, final BatchedImageRequest batchedImageRequest) {
        this.mBatchedResponses.put(s, batchedImageRequest);
        if (this.mRunnable == null) {
            this.mRunnable = new Runnable() {
                @Override
                public void run() {
                    for (final BatchedImageRequest batchedImageRequest : ImageLoader.this.mBatchedResponses.values()) {
                        for (final ImageContainer imageContainer : batchedImageRequest.mContainers) {
                            if (imageContainer.mListener != null) {
                                if (batchedImageRequest.getError() == null) {
                                    imageContainer.mBitmap = batchedImageRequest.mResponseBitmap;
                                    imageContainer.mListener.onResponse(imageContainer, false);
                                }
                                else {
                                    ((Response.ErrorListener)imageContainer.mListener).onErrorResponse(batchedImageRequest.getError());
                                }
                            }
                        }
                    }
                    ImageLoader.this.mBatchedResponses.clear();
                    ImageLoader.this.mRunnable = null;
                }
            };
            this.mHandler.postDelayed(this.mRunnable, (long)this.mBatchResponseDelayMs);
        }
    }
    
    private static String getCacheKey(final String s, final int n, final int n2, final ImageView$ScaleType imageView$ScaleType) {
        return new StringBuilder(12 + s.length()).append("#W").append(n).append("#H").append(n2).append("#S").append(imageView$ScaleType.ordinal()).append(s).toString();
    }
    
    public static ImageListener getImageListener(final ImageView imageView, final int n, final int n2) {
        return (ImageListener)new ImageListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                if (n2 != 0) {
                    imageView.setImageResource(n2);
                }
            }
            
            @Override
            public void onResponse(final ImageContainer imageContainer, final boolean b) {
                if (imageContainer.getBitmap() != null) {
                    imageView.setImageBitmap(imageContainer.getBitmap());
                }
                else if (n != 0) {
                    imageView.setImageResource(n);
                }
            }
        };
    }
    
    private void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }
    
    public ImageContainer get(final String s, final ImageListener imageListener) {
        return this.get(s, imageListener, 0, 0);
    }
    
    public ImageContainer get(final String s, final ImageListener imageListener, final int n, final int n2) {
        return this.get(s, imageListener, n, n2, ImageView$ScaleType.CENTER_INSIDE);
    }
    
    public ImageContainer get(final String s, final ImageListener imageListener, final int n, final int n2, final ImageView$ScaleType imageView$ScaleType) {
        this.throwIfNotOnMainThread();
        final String cacheKey = getCacheKey(s, n, n2, imageView$ScaleType);
        final Bitmap bitmap = this.mCache.getBitmap(cacheKey);
        if (bitmap != null) {
            final ImageContainer imageContainer = new ImageContainer(bitmap, s, null, null);
            imageListener.onResponse(imageContainer, true);
            return imageContainer;
        }
        final ImageContainer imageContainer2 = new ImageContainer(null, s, cacheKey, imageListener);
        imageListener.onResponse(imageContainer2, true);
        final BatchedImageRequest batchedImageRequest = this.mInFlightRequests.get(cacheKey);
        if (batchedImageRequest != null) {
            batchedImageRequest.addContainer(imageContainer2);
            return imageContainer2;
        }
        final Request<Bitmap> imageRequest = this.makeImageRequest(s, n, n2, imageView$ScaleType, cacheKey);
        this.mRequestQueue.add((Request<Object>)imageRequest);
        this.mInFlightRequests.put(cacheKey, new BatchedImageRequest(imageRequest, imageContainer2));
        return imageContainer2;
    }
    
    public boolean isCached(final String s, final int n, final int n2) {
        return this.isCached(s, n, n2, ImageView$ScaleType.CENTER_INSIDE);
    }
    
    public boolean isCached(final String s, final int n, final int n2, final ImageView$ScaleType imageView$ScaleType) {
        this.throwIfNotOnMainThread();
        return this.mCache.getBitmap(getCacheKey(s, n, n2, imageView$ScaleType)) != null;
    }
    
    protected Request<Bitmap> makeImageRequest(final String s, final int n, final int n2, final ImageView$ScaleType imageView$ScaleType, final String s2) {
        return new ImageRequest(s, new Response.Listener<Bitmap>() {
            public void onResponse(final Bitmap bitmap) {
                ImageLoader.this.onGetImageSuccess(s2, bitmap);
            }
        }, n, n2, imageView$ScaleType, Bitmap$Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                ImageLoader.this.onGetImageError(s2, volleyError);
            }
        });
    }
    
    protected void onGetImageError(final String s, final VolleyError error) {
        final BatchedImageRequest batchedImageRequest = this.mInFlightRequests.remove(s);
        if (batchedImageRequest != null) {
            batchedImageRequest.setError(error);
            this.batchResponse(s, batchedImageRequest);
        }
    }
    
    protected void onGetImageSuccess(final String s, final Bitmap bitmap) {
        this.mCache.putBitmap(s, bitmap);
        final BatchedImageRequest batchedImageRequest = this.mInFlightRequests.remove(s);
        if (batchedImageRequest != null) {
            batchedImageRequest.mResponseBitmap = bitmap;
            this.batchResponse(s, batchedImageRequest);
        }
    }
    
    public void setBatchedResponseDelay(final int mBatchResponseDelayMs) {
        this.mBatchResponseDelayMs = mBatchResponseDelayMs;
    }
    
    private class BatchedImageRequest
    {
        private final LinkedList<ImageContainer> mContainers;
        private VolleyError mError;
        private final Request<?> mRequest;
        private Bitmap mResponseBitmap;
        
        public BatchedImageRequest(final Request<?> mRequest, final ImageContainer imageContainer) {
            this.mContainers = new LinkedList<ImageContainer>();
            this.mRequest = mRequest;
            this.mContainers.add(imageContainer);
        }
        
        public void addContainer(final ImageContainer imageContainer) {
            this.mContainers.add(imageContainer);
        }
        
        public VolleyError getError() {
            return this.mError;
        }
        
        public boolean removeContainerAndCancelIfNecessary(final ImageContainer imageContainer) {
            this.mContainers.remove(imageContainer);
            if (this.mContainers.size() == 0) {
                this.mRequest.cancel();
                return true;
            }
            return false;
        }
        
        public void setError(final VolleyError mError) {
            this.mError = mError;
        }
    }
    
    public interface ImageCache
    {
        Bitmap getBitmap(final String p0);
        
        void putBitmap(final String p0, final Bitmap p1);
    }
    
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
                final BatchedImageRequest batchedImageRequest = ImageLoader.this.mInFlightRequests.get(this.mCacheKey);
                if (batchedImageRequest != null) {
                    if (batchedImageRequest.removeContainerAndCancelIfNecessary(this)) {
                        ImageLoader.this.mInFlightRequests.remove(this.mCacheKey);
                    }
                }
                else {
                    final BatchedImageRequest batchedImageRequest2 = ImageLoader.this.mBatchedResponses.get(this.mCacheKey);
                    if (batchedImageRequest2 != null) {
                        batchedImageRequest2.removeContainerAndCancelIfNecessary(this);
                        if (batchedImageRequest2.mContainers.size() == 0) {
                            ImageLoader.this.mBatchedResponses.remove(this.mCacheKey);
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
    
    public interface ImageListener extends ErrorListener
    {
        void onResponse(final ImageContainer p0, final boolean p1);
    }
}
