package com.facebook.share;

import com.facebook.internal.*;
import com.facebook.share.model.*;
import org.json.*;
import com.facebook.*;

class ShareApi$12 implements Callback {
    final /* synthetic */ CollectionMapper.OnMapValueCompleteListener val$onPhotoStagedListener;
    final /* synthetic */ SharePhoto val$photo;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        final FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            String errorMessage = error.getErrorMessage();
            if (errorMessage == null) {
                errorMessage = "Error staging photo.";
            }
            ((CollectionMapper.OnErrorListener)this.val$onPhotoStagedListener).onError(new FacebookGraphResponseException(graphResponse, errorMessage));
            return;
        }
        final JSONObject jsonObject = graphResponse.getJSONObject();
        if (jsonObject == null) {
            ((CollectionMapper.OnErrorListener)this.val$onPhotoStagedListener).onError(new FacebookException("Error staging photo."));
            return;
        }
        final String optString = jsonObject.optString("uri");
        if (optString == null) {
            ((CollectionMapper.OnErrorListener)this.val$onPhotoStagedListener).onError(new FacebookException("Error staging photo."));
            return;
        }
        final JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject2.put("url", (Object)optString);
            jsonObject2.put("user_generated", this.val$photo.getUserGenerated());
            this.val$onPhotoStagedListener.onComplete(jsonObject2);
        }
        catch (JSONException ex) {
            String localizedMessage = ex.getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = "Error staging photo.";
            }
            ((CollectionMapper.OnErrorListener)this.val$onPhotoStagedListener).onError(new FacebookException(localizedMessage));
        }
    }
}