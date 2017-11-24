package com.facebook.login;

import com.facebook.internal.*;
import android.os.*;
import com.facebook.*;

class WebViewLoginMethodHandler$1 implements OnCompleteListener {
    final /* synthetic */ LoginClient.Request val$request;
    
    @Override
    public void onComplete(final Bundle bundle, final FacebookException ex) {
        WebViewLoginMethodHandler.this.onWebDialogComplete(this.val$request, bundle, ex);
    }
}