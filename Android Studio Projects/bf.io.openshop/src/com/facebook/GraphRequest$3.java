package com.facebook;

import org.json.*;

static final class GraphRequest$3 implements Callback {
    final /* synthetic */ GraphJSONArrayCallback val$callback;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        if (this.val$callback != null) {
            final JSONObject jsonObject = graphResponse.getJSONObject();
            JSONArray optJSONArray;
            if (jsonObject != null) {
                optJSONArray = jsonObject.optJSONArray("data");
            }
            else {
                optJSONArray = null;
            }
            this.val$callback.onCompleted(optJSONArray, graphResponse);
        }
    }
}