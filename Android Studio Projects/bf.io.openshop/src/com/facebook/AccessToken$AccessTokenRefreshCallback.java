package com.facebook;

public interface AccessTokenRefreshCallback
{
    void OnTokenRefreshFailed(final FacebookException p0);
    
    void OnTokenRefreshed(final AccessToken p0);
}
