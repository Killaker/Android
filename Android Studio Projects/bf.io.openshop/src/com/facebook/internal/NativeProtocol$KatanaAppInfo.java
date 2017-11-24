package com.facebook.internal;

private static class KatanaAppInfo extends NativeAppInfo
{
    static final String KATANA_PACKAGE = "com.facebook.katana";
    
    @Override
    protected String getPackage() {
        return "com.facebook.katana";
    }
}
