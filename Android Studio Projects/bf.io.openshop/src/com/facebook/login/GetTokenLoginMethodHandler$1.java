package com.facebook.login;

import com.facebook.internal.*;
import android.os.*;

class GetTokenLoginMethodHandler$1 implements CompletedListener {
    final /* synthetic */ LoginClient.Request val$request;
    
    @Override
    public void completed(final Bundle bundle) {
        GetTokenLoginMethodHandler.this.getTokenCompleted(this.val$request, bundle);
    }
}