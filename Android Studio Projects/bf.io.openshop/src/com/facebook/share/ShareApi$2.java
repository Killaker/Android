package com.facebook.share;

import com.facebook.internal.*;
import com.facebook.share.model.*;
import android.os.*;
import java.net.*;
import com.facebook.share.internal.*;
import java.io.*;
import com.facebook.*;

class ShareApi$2 implements OnMapperCompleteListener {
    final /* synthetic */ ShareOpenGraphAction val$action;
    final /* synthetic */ FacebookCallback val$callback;
    final /* synthetic */ Bundle val$parameters;
    final /* synthetic */ GraphRequest.Callback val$requestCallback;
    
    @Override
    public void onComplete() {
        try {
            ShareApi.access$000(this.val$parameters);
            new GraphRequest(AccessToken.getCurrentAccessToken(), ShareApi.access$100(ShareApi.this, URLEncoder.encode(this.val$action.getActionType(), "UTF-8")), this.val$parameters, HttpMethod.POST, this.val$requestCallback).executeAsync();
        }
        catch (UnsupportedEncodingException ex) {
            ShareInternalUtility.invokeCallbackWithException(this.val$callback, ex);
        }
    }
    
    @Override
    public void onError(final FacebookException ex) {
        ShareInternalUtility.invokeCallbackWithException(this.val$callback, ex);
    }
}