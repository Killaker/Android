package com.facebook.internal;

import android.os.*;
import java.util.*;
import org.json.*;

static final class BundleJSONConverter$7 implements Setter {
    @Override
    public void setOnBundle(final Bundle bundle, final String s, final Object o) throws JSONException {
        final JSONArray jsonArray = (JSONArray)o;
        final ArrayList<String> list = new ArrayList<String>();
        if (jsonArray.length() == 0) {
            bundle.putStringArrayList(s, (ArrayList)list);
            return;
        }
        for (int i = 0; i < jsonArray.length(); ++i) {
            final Object value = jsonArray.get(i);
            if (!(value instanceof String)) {
                throw new IllegalArgumentException("Unexpected type in an array: " + ((String)value).getClass());
            }
            list.add((String)value);
        }
        bundle.putStringArrayList(s, (ArrayList)list);
    }
    
    @Override
    public void setOnJSON(final JSONObject jsonObject, final String s, final Object o) throws JSONException {
        throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
    }
}