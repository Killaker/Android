package com.android.volley.toolbox;

import com.android.volley.*;

class ImageLoader$3 implements ErrorListener {
    final /* synthetic */ String val$cacheKey;
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        ImageLoader.this.onGetImageError(this.val$cacheKey, volleyError);
    }
}