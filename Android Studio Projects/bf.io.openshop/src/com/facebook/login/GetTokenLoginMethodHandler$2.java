package com.facebook.login;

import com.facebook.internal.*;
import android.os.*;
import com.facebook.*;
import org.json.*;

class GetTokenLoginMethodHandler$2 implements GraphMeRequestWithCacheCallback {
    final /* synthetic */ LoginClient.Request val$request;
    final /* synthetic */ Bundle val$result;
    
    @Override
    public void onFailure(final FacebookException ex) {
        GetTokenLoginMethodHandler.this.loginClient.complete(LoginClient.Result.createErrorResult(GetTokenLoginMethodHandler.this.loginClient.getPendingRequest(), "Caught exception", ex.getMessage()));
    }
    
    @Override
    public void onSuccess(final JSONObject jsonObject) {
        try {
            this.val$result.putString("com.facebook.platform.extra.USER_ID", jsonObject.getString("id"));
            GetTokenLoginMethodHandler.this.onComplete(this.val$request, this.val$result);
        }
        catch (JSONException ex) {
            GetTokenLoginMethodHandler.this.loginClient.complete(LoginClient.Result.createErrorResult(GetTokenLoginMethodHandler.this.loginClient.getPendingRequest(), "Caught exception", ex.getMessage()));
        }
    }
}