package com.android.volley.toolbox;

import android.graphics.*;
import java.util.*;
import com.android.volley.*;

class ImageLoader$4 implements Runnable {
    @Override
    public void run() {
        for (final BatchedImageRequest batchedImageRequest : ImageLoader.access$200(ImageLoader.this).values()) {
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
        ImageLoader.access$200(ImageLoader.this).clear();
        ImageLoader.access$602(ImageLoader.this, null);
    }
}