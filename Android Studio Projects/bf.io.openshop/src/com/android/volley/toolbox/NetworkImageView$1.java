package com.android.volley.toolbox;

import com.android.volley.*;

class NetworkImageView$1 implements ImageListener {
    final /* synthetic */ boolean val$isInLayoutPass;
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (NetworkImageView.access$000(NetworkImageView.this) != 0) {
            NetworkImageView.this.setImageResource(NetworkImageView.access$000(NetworkImageView.this));
        }
    }
    
    @Override
    public void onResponse(final ImageContainer imageContainer, final boolean b) {
        if (b && this.val$isInLayoutPass) {
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
            if (NetworkImageView.access$100(NetworkImageView.this) != 0) {
                NetworkImageView.this.setImageResource(NetworkImageView.access$100(NetworkImageView.this));
            }
        }
    }
}