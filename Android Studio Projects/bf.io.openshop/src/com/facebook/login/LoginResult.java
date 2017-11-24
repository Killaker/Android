package com.facebook.login;

import com.facebook.*;
import java.util.*;

public class LoginResult
{
    private final AccessToken accessToken;
    private final Set<String> recentlyDeniedPermissions;
    private final Set<String> recentlyGrantedPermissions;
    
    public LoginResult(final AccessToken accessToken, final Set<String> recentlyGrantedPermissions, final Set<String> recentlyDeniedPermissions) {
        this.accessToken = accessToken;
        this.recentlyGrantedPermissions = recentlyGrantedPermissions;
        this.recentlyDeniedPermissions = recentlyDeniedPermissions;
    }
    
    public AccessToken getAccessToken() {
        return this.accessToken;
    }
    
    public Set<String> getRecentlyDeniedPermissions() {
        return this.recentlyDeniedPermissions;
    }
    
    public Set<String> getRecentlyGrantedPermissions() {
        return this.recentlyGrantedPermissions;
    }
}
