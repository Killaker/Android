package com.facebook.share;

import com.facebook.*;
import com.facebook.share.internal.*;
import org.json.*;

class ShareApi$1 implements Callback {
    final /* synthetic */ FacebookCallback val$callback;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        final JSONObject jsonObject = graphResponse.getJSONObject();
        String optString;
        if (jsonObject == null) {
            optString = null;
        }
        else {
            optString = jsonObject.optString("id");
        }
        ShareInternalUtility.invokeCallbackWithResults(this.val$callback, optString, graphResponse);
    }
}