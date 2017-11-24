package com.facebook;

import com.facebook.share.internal.*;
import com.facebook.share.model.*;
import org.json.*;
import android.net.*;

static final class GraphRequest$7 implements PhotoJSONProcessor {
    @Override
    public JSONObject toJSONObject(final SharePhoto sharePhoto) {
        final Uri imageUrl = sharePhoto.getImageUrl();
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("url", (Object)imageUrl.toString());
            return jsonObject;
        }
        catch (Exception ex) {
            throw new FacebookException("Unable to attach images", ex);
        }
    }
}