package com.facebook;

import com.facebook.internal.*;
import org.json.*;

class GraphRequest$4 implements Callback {
    final /* synthetic */ Callback val$callback;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        final JSONObject jsonObject = graphResponse.getJSONObject();
        JSONObject optJSONObject;
        if (jsonObject != null) {
            optJSONObject = jsonObject.optJSONObject("__debug__");
        }
        else {
            optJSONObject = null;
        }
        JSONArray optJSONArray;
        if (optJSONObject != null) {
            optJSONArray = optJSONObject.optJSONArray("messages");
        }
        else {
            optJSONArray = null;
        }
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); ++i) {
                final JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                String s;
                if (optJSONObject2 != null) {
                    s = optJSONObject2.optString("message");
                }
                else {
                    s = null;
                }
                String optString;
                if (optJSONObject2 != null) {
                    optString = optJSONObject2.optString("type");
                }
                else {
                    optString = null;
                }
                String optString2;
                if (optJSONObject2 != null) {
                    optString2 = optJSONObject2.optString("link");
                }
                else {
                    optString2 = null;
                }
                if (s != null && optString != null) {
                    LoggingBehavior loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_INFO;
                    if (optString.equals("warning")) {
                        loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
                    }
                    if (!Utility.isNullOrEmpty(optString2)) {
                        s = s + " Link: " + optString2;
                    }
                    Logger.log(loggingBehavior, GraphRequest.TAG, s);
                }
            }
        }
        if (this.val$callback != null) {
            this.val$callback.onCompleted(graphResponse);
        }
    }
}