package com.android.volley.toolbox;

import com.android.volley.*;

public interface Authenticator
{
    String getAuthToken() throws AuthFailureError;
    
    void invalidateAuthToken(final String p0);
}
