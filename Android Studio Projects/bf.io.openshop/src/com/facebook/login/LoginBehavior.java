package com.facebook.login;

public enum LoginBehavior
{
    DEVICE_AUTH(false, false, true), 
    NATIVE_ONLY(true, false, false), 
    NATIVE_WITH_FALLBACK(true, true, false), 
    WEB_ONLY(false, true, false);
    
    private final boolean allowsDeviceAuth;
    private final boolean allowsKatanaAuth;
    private final boolean allowsWebViewAuth;
    
    private LoginBehavior(final boolean allowsKatanaAuth, final boolean allowsWebViewAuth, final boolean allowsDeviceAuth) {
        this.allowsKatanaAuth = allowsKatanaAuth;
        this.allowsWebViewAuth = allowsWebViewAuth;
        this.allowsDeviceAuth = allowsDeviceAuth;
    }
    
    boolean allowsDeviceAuth() {
        return this.allowsDeviceAuth;
    }
    
    boolean allowsKatanaAuth() {
        return this.allowsKatanaAuth;
    }
    
    boolean allowsWebViewAuth() {
        return this.allowsWebViewAuth;
    }
}
