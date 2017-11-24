package com.android.volley.toolbox;

import android.content.*;
import android.util.*;
import android.graphics.*;
import android.text.*;
import com.android.volley.*;
import android.widget.*;
import android.view.*;

public class NetworkImageView extends ImageView
{
    private int mDefaultImageId;
    private int mErrorImageId;
    private ImageLoader.ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private String mUrl;
    
    public NetworkImageView(final Context context) {
        this(context, null);
    }
    
    public NetworkImageView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NetworkImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    private void setDefaultImageOrNull() {
        if (this.mDefaultImageId != 0) {
            this.setImageResource(this.mDefaultImageId);
            return;
        }
        this.setImageBitmap((Bitmap)null);
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.invalidate();
    }
    
    void loadImageIfNecessary(final boolean b) {
        final int width = this.getWidth();
        final int height = this.getHeight();
        final ImageView$ScaleType scaleType = this.getScaleType();
        final ViewGroup$LayoutParams layoutParams = this.getLayoutParams();
        boolean b2 = false;
        boolean b3 = false;
        if (layoutParams != null) {
            if (this.getLayoutParams().width == -2) {
                b3 = true;
            }
            else {
                b3 = false;
            }
            if (this.getLayoutParams().height == -2) {
                b2 = true;
            }
            else {
                b2 = false;
            }
        }
        boolean b4;
        if (b3 && b2) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        if (width != 0 || height != 0 || b4) {
            if (TextUtils.isEmpty((CharSequence)this.mUrl)) {
                if (this.mImageContainer != null) {
                    this.mImageContainer.cancelRequest();
                    this.mImageContainer = null;
                }
                this.setDefaultImageOrNull();
                return;
            }
            if (this.mImageContainer != null && this.mImageContainer.getRequestUrl() != null) {
                if (this.mImageContainer.getRequestUrl().equals(this.mUrl)) {
                    return;
                }
                this.mImageContainer.cancelRequest();
                this.setDefaultImageOrNull();
            }
            int n;
            if (b3) {
                n = 0;
            }
            else {
                n = width;
            }
            int n2;
            if (b2) {
                n2 = 0;
            }
            else {
                n2 = height;
            }
            this.mImageContainer = this.mImageLoader.get(this.mUrl, (ImageLoader.ImageListener)new ImageLoader.ImageListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (NetworkImageView.this.mErrorImageId != 0) {
                        NetworkImageView.this.setImageResource(NetworkImageView.this.mErrorImageId);
                    }
                }
                
                @Override
                public void onResponse(final ImageContainer imageContainer, final boolean b) {
                    if (b && b) {
                        NetworkImageView.this.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                ImageListener.this.onResponse(imageContainer, false);
                            }
                        });
                    }
                    else {
                        if (imageContainer.getBitmap() != null) {
                            NetworkImageView.this.setImageBitmap(imageContainer.getBitmap());
                            return;
                        }
                        if (NetworkImageView.this.mDefaultImageId != 0) {
                            NetworkImageView.this.setImageResource(NetworkImageView.this.mDefaultImageId);
                        }
                    }
                }
            }, n, n2, scaleType);
        }
    }
    
    protected void onDetachedFromWindow() {
        if (this.mImageContainer != null) {
            this.mImageContainer.cancelRequest();
            this.setImageBitmap((Bitmap)null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.loadImageIfNecessary(true);
    }
    
    public void setDefaultImageResId(final int mDefaultImageId) {
        this.mDefaultImageId = mDefaultImageId;
    }
    
    public void setErrorImageResId(final int mErrorImageId) {
        this.mErrorImageId = mErrorImageId;
    }
    
    public void setImageUrl(final String mUrl, final ImageLoader mImageLoader) {
        this.mUrl = mUrl;
        this.mImageLoader = mImageLoader;
        this.loadImageIfNecessary(false);
    }
}
