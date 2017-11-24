package com.facebook.share;

import java.util.*;
import com.facebook.internal.*;
import com.facebook.*;
import com.facebook.share.internal.*;
import org.json.*;

class ShareApi$3 implements Callback {
    final /* synthetic */ FacebookCallback val$callback;
    final /* synthetic */ ArrayList val$errorResponses;
    final /* synthetic */ Mutable val$requestCount;
    final /* synthetic */ ArrayList val$results;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        final JSONObject jsonObject = graphResponse.getJSONObject();
        if (jsonObject != null) {
            this.val$results.add(jsonObject);
        }
        if (graphResponse.getError() != null) {
            this.val$errorResponses.add(graphResponse);
        }
        this.val$requestCount.value = (T)(-1 + (int)this.val$requestCount.value);
        if ((int)this.val$requestCount.value == 0) {
            if (!this.val$errorResponses.isEmpty()) {
                ShareInternalUtility.invokeCallbackWithResults(this.val$callback, null, this.val$errorResponses.get(0));
            }
            else if (!this.val$results.isEmpty()) {
                ShareInternalUtility.invokeCallbackWithResults(this.val$callback, this.val$results.get(0).optString("id"), graphResponse);
            }
        }
    }
}