package com.facebook;

public interface AccessTokenCreationCallback
{
    void onError(final FacebookException p0);
    
    void onSuccess(final AccessToken p0);
}
