package com.facebook.internal;

private static class WakizashiAppInfo extends NativeAppInfo
{
    static final String WAKIZASHI_PACKAGE = "com.facebook.wakizashi";
    
    @Override
    protected String getPackage() {
        return "com.facebook.wakizashi";
    }
}
