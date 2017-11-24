package com.facebook.login.widget;

import com.facebook.login.*;
import com.facebook.internal.*;
import java.util.*;

static class LoginButtonProperties
{
    private LoginAuthorizationType authorizationType;
    private DefaultAudience defaultAudience;
    private LoginBehavior loginBehavior;
    private List<String> permissions;
    
    LoginButtonProperties() {
        this.defaultAudience = DefaultAudience.FRIENDS;
        this.permissions = Collections.emptyList();
        this.authorizationType = null;
        this.loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK;
    }
    
    public void clearPermissions() {
        this.permissions = null;
        this.authorizationType = null;
    }
    
    public DefaultAudience getDefaultAudience() {
        return this.defaultAudience;
    }
    
    public LoginBehavior getLoginBehavior() {
        return this.loginBehavior;
    }
    
    List<String> getPermissions() {
        return this.permissions;
    }
    
    public void setDefaultAudience(final DefaultAudience defaultAudience) {
        this.defaultAudience = defaultAudience;
    }
    
    public void setLoginBehavior(final LoginBehavior loginBehavior) {
        this.loginBehavior = loginBehavior;
    }
    
    public void setPublishPermissions(final List<String> permissions) {
        if (LoginAuthorizationType.READ.equals(this.authorizationType)) {
            throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
        }
        if (Utility.isNullOrEmpty((Collection<Object>)permissions)) {
            throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
        }
        this.permissions = permissions;
        this.authorizationType = LoginAuthorizationType.PUBLISH;
    }
    
    public void setReadPermissions(final List<String> permissions) {
        if (LoginAuthorizationType.PUBLISH.equals(this.authorizationType)) {
            throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
        }
        this.permissions = permissions;
        this.authorizationType = LoginAuthorizationType.READ;
    }
}
