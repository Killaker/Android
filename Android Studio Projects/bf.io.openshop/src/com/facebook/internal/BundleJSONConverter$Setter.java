package com.facebook.internal;

import android.os.*;
import org.json.*;

public interface Setter
{
    void setOnBundle(final Bundle p0, final String p1, final Object p2) throws JSONException;
    
    void setOnJSON(final JSONObject p0, final String p1, final Object p2) throws JSONException;
}
