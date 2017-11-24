package com.facebook;

import android.content.*;

static final class FacebookSdk$4 implements Runnable {
    final /* synthetic */ Context val$applicationContext;
    final /* synthetic */ String val$applicationId;
    
    @Override
    public void run() {
        FacebookSdk.publishInstallAndWaitForResponse(this.val$applicationContext, this.val$applicationId);
    }
}