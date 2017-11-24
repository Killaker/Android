package com.facebook.internal;

import android.graphics.*;

static final class ImageDownloader$1 implements Runnable {
    final /* synthetic */ Bitmap val$bitmap;
    final /* synthetic */ ImageRequest.Callback val$callback;
    final /* synthetic */ Exception val$error;
    final /* synthetic */ boolean val$isCachedRedirect;
    final /* synthetic */ ImageRequest val$request;
    
    @Override
    public void run() {
        this.val$callback.onCompleted(new ImageResponse(this.val$request, this.val$error, this.val$isCachedRedirect, this.val$bitmap));
    }
}