package com.facebook.share.internal;

import com.facebook.share.model.*;
import com.facebook.*;
import org.json.*;
import android.net.*;

static final class ShareInternalUtility$7 implements PhotoJSONProcessor {
    @Override
    public JSONObject toJSONObject(final SharePhoto sharePhoto) {
        final Uri imageUrl = sharePhoto.getImageUrl();
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("url", (Object)imageUrl.toString());
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new FacebookException("Unable to attach images", (Throwable)ex);
        }
    }
}