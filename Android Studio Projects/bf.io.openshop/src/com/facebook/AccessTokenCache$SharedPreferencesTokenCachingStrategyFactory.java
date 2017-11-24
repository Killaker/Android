package com.facebook;

static class SharedPreferencesTokenCachingStrategyFactory
{
    public LegacyTokenHelper create() {
        return new LegacyTokenHelper(FacebookSdk.getApplicationContext());
    }
}
