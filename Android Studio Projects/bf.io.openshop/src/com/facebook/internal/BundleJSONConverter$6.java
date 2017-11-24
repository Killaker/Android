package com.facebook.internal;

import android.os.*;
import org.json.*;

static final class BundleJSONConverter$6 implements Setter {
    @Override
    public void setOnBundle(final Bundle bundle, final String s, final Object o) throws JSONException {
        throw new IllegalArgumentException("Unexpected type from JSON");
    }
    
    @Override
    public void setOnJSON(final JSONObject jsonObject, final String s, final Object o) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final String[] array = (String[])o;
        for (int length = array.length, i = 0; i < length; ++i) {
            jsonArray.put((Object)array[i]);
        }
        jsonObject.put(s, (Object)jsonArray);
    }
}