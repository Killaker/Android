package com.facebook;

import android.content.*;
import org.json.*;
import android.os.*;
import com.facebook.internal.*;

class AccessTokenCache
{
    static final String CACHED_ACCESS_TOKEN_KEY = "com.facebook.AccessTokenManager.CachedAccessToken";
    private final SharedPreferences sharedPreferences;
    private LegacyTokenHelper tokenCachingStrategy;
    private final SharedPreferencesTokenCachingStrategyFactory tokenCachingStrategyFactory;
    
    public AccessTokenCache() {
        this(FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0), new SharedPreferencesTokenCachingStrategyFactory());
    }
    
    AccessTokenCache(final SharedPreferences sharedPreferences, final SharedPreferencesTokenCachingStrategyFactory tokenCachingStrategyFactory) {
        this.sharedPreferences = sharedPreferences;
        this.tokenCachingStrategyFactory = tokenCachingStrategyFactory;
    }
    
    private AccessToken getCachedAccessToken() {
        final String string = this.sharedPreferences.getString("com.facebook.AccessTokenManager.CachedAccessToken", (String)null);
        AccessToken fromJSONObject = null;
        if (string == null) {
            return fromJSONObject;
        }
        try {
            fromJSONObject = AccessToken.createFromJSONObject(new JSONObject(string));
            return fromJSONObject;
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    private AccessToken getLegacyAccessToken() {
        final Bundle load = this.getTokenCachingStrategy().load();
        AccessToken fromLegacyCache = null;
        if (load != null) {
            final boolean hasTokenInformation = LegacyTokenHelper.hasTokenInformation(load);
            fromLegacyCache = null;
            if (hasTokenInformation) {
                fromLegacyCache = AccessToken.createFromLegacyCache(load);
            }
        }
        return fromLegacyCache;
    }
    
    private LegacyTokenHelper getTokenCachingStrategy() {
        Label_0029: {
            if (this.tokenCachingStrategy != null) {
                break Label_0029;
            }
            synchronized (this) {
                if (this.tokenCachingStrategy == null) {
                    this.tokenCachingStrategy = this.tokenCachingStrategyFactory.create();
                }
                // monitorexit(this)
                return this.tokenCachingStrategy;
            }
        }
    }
    
    private boolean hasCachedAccessToken() {
        return this.sharedPreferences.contains("com.facebook.AccessTokenManager.CachedAccessToken");
    }
    
    private boolean shouldCheckLegacyToken() {
        return FacebookSdk.isLegacyTokenUpgradeSupported();
    }
    
    public void clear() {
        this.sharedPreferences.edit().remove("com.facebook.AccessTokenManager.CachedAccessToken").apply();
        if (this.shouldCheckLegacyToken()) {
            this.getTokenCachingStrategy().clear();
        }
    }
    
    public AccessToken load() {
        AccessToken accessToken;
        if (this.hasCachedAccessToken()) {
            accessToken = this.getCachedAccessToken();
        }
        else {
            final boolean shouldCheckLegacyToken = this.shouldCheckLegacyToken();
            accessToken = null;
            if (shouldCheckLegacyToken) {
                accessToken = this.getLegacyAccessToken();
                if (accessToken != null) {
                    this.save(accessToken);
                    this.getTokenCachingStrategy().clear();
                    return accessToken;
                }
            }
        }
        return accessToken;
    }
    
    public void save(final AccessToken accessToken) {
        Validate.notNull(accessToken, "accessToken");
        try {
            this.sharedPreferences.edit().putString("com.facebook.AccessTokenManager.CachedAccessToken", accessToken.toJSONObject().toString()).apply();
        }
        catch (JSONException ex) {}
    }
    
    static class SharedPreferencesTokenCachingStrategyFactory
    {
        public LegacyTokenHelper create() {
            return new LegacyTokenHelper(FacebookSdk.getApplicationContext());
        }
    }
}
