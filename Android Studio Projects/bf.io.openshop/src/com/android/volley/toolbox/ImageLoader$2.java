package com.android.volley.toolbox;

import com.android.volley.*;
import android.graphics.*;

class ImageLoader$2 implements Listener<Bitmap> {
    final /* synthetic */ String val$cacheKey;
    
    public void onResponse(final Bitmap bitmap) {
        ImageLoader.this.onGetImageSuccess(this.val$cacheKey, bitmap);
    }
}