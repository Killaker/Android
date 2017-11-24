package com.android.volley.toolbox;

import android.widget.*;
import com.android.volley.*;

static final class ImageLoader$1 implements ImageListener {
    final /* synthetic */ int val$defaultImageResId;
    final /* synthetic */ int val$errorImageResId;
    final /* synthetic */ ImageView val$view;
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (this.val$errorImageResId != 0) {
            this.val$view.setImageResource(this.val$errorImageResId);
        }
    }
    
    @Override
    public void onResponse(final ImageContainer imageContainer, final boolean b) {
        if (imageContainer.getBitmap() != null) {
            this.val$view.setImageBitmap(imageContainer.getBitmap());
        }
        else if (this.val$defaultImageResId != 0) {
            this.val$view.setImageResource(this.val$defaultImageResId);
        }
    }
}