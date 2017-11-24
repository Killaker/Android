package com.facebook.internal;

import android.content.*;
import android.net.*;

public static class Builder
{
    private boolean allowCachedRedirects;
    private Callback callback;
    private Object callerTag;
    private Context context;
    private Uri imageUrl;
    
    public Builder(final Context context, final Uri imageUrl) {
        Validate.notNull(imageUrl, "imageUri");
        this.context = context;
        this.imageUrl = imageUrl;
    }
    
    public ImageRequest build() {
        return new ImageRequest(this, null);
    }
    
    public Builder setAllowCachedRedirects(final boolean allowCachedRedirects) {
        this.allowCachedRedirects = allowCachedRedirects;
        return this;
    }
    
    public Builder setCallback(final Callback callback) {
        this.callback = callback;
        return this;
    }
    
    public Builder setCallerTag(final Object callerTag) {
        this.callerTag = callerTag;
        return this;
    }
}
