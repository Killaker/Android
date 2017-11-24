package com.facebook.internal;

import android.os.*;
import org.json.*;

static final class BundleJSONConverter$1 implements Setter {
    @Override
    public void setOnBundle(final Bundle bundle, final String s, final Object o) throws JSONException {
        bundle.putBoolean(s, (boolean)o);
    }
    
    @Override
    public void setOnJSON(final JSONObject jsonObject, final String s, final Object o) throws JSONException {
        jsonObject.put(s, o);
    }
}