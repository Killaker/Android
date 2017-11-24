package com.facebook.applinks;

import android.content.*;

static final class AppLinkData$1 implements Runnable {
    final /* synthetic */ Context val$applicationContext;
    final /* synthetic */ String val$applicationIdCopy;
    final /* synthetic */ CompletionHandler val$completionHandler;
    
    @Override
    public void run() {
        AppLinkData.access$000(this.val$applicationContext, this.val$applicationIdCopy, this.val$completionHandler);
    }
}