package com.facebook.share;

import com.facebook.internal.*;
import com.facebook.*;
import org.json.*;

class ShareApi$10 implements Callback {
    final /* synthetic */ CollectionMapper.OnMapValueCompleteListener val$onOpenGraphObjectStagedListener;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        final FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            String errorMessage = error.getErrorMessage();
            if (errorMessage == null) {
                errorMessage = "Error staging Open Graph object.";
            }
            ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError(new FacebookGraphResponseException(graphResponse, errorMessage));
            return;
        }
        final JSONObject jsonObject = graphResponse.getJSONObject();
        if (jsonObject == null) {
            ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError(new FacebookGraphResponseException(graphResponse, "Error staging Open Graph object."));
            return;
        }
        final String optString = jsonObject.optString("id");
        if (optString == null) {
            ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError(new FacebookGraphResponseException(graphResponse, "Error staging Open Graph object."));
            return;
        }
        this.val$onOpenGraphObjectStagedListener.onComplete(optString);
    }
}