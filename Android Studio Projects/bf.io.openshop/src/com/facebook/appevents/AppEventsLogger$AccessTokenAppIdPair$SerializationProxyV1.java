package com.facebook.appevents;

import java.io.*;

private static class SerializationProxyV1 implements Serializable
{
    private static final long serialVersionUID = -2488473066578201069L;
    private final String accessTokenString;
    private final String appId;
    
    private SerializationProxyV1(final String accessTokenString, final String appId) {
        this.accessTokenString = accessTokenString;
        this.appId = appId;
    }
    
    private Object readResolve() {
        return new AccessTokenAppIdPair(this.accessTokenString, this.appId);
    }
}
