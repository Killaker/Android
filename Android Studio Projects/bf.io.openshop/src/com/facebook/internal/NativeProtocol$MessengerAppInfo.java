package com.facebook.internal;

private static class MessengerAppInfo extends NativeAppInfo
{
    static final String MESSENGER_PACKAGE = "com.facebook.orca";
    
    @Override
    protected String getPackage() {
        return "com.facebook.orca";
    }
}
