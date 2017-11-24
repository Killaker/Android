package com.facebook;

import com.facebook.internal.*;
import org.json.*;
import android.net.*;

static final class Profile$1 implements GraphMeRequestWithCacheCallback {
    @Override
    public void onFailure(final FacebookException ex) {
    }
    
    @Override
    public void onSuccess(final JSONObject jsonObject) {
        final String optString = jsonObject.optString("id");
        if (optString == null) {
            return;
        }
        final String optString2 = jsonObject.optString("link");
        final String optString3 = jsonObject.optString("first_name");
        final String optString4 = jsonObject.optString("middle_name");
        final String optString5 = jsonObject.optString("last_name");
        final String optString6 = jsonObject.optString("name");
        Uri parse;
        if (optString2 != null) {
            parse = Uri.parse(optString2);
        }
        else {
            parse = null;
        }
        Profile.setCurrentProfile(new Profile(optString, optString3, optString4, optString5, optString6, parse));
    }
}