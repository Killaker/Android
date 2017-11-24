package com.facebook.internal;

import com.facebook.*;
import org.json.*;

public interface GraphMeRequestWithCacheCallback
{
    void onFailure(final FacebookException p0);
    
    void onSuccess(final JSONObject p0);
}
