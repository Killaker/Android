package com.facebook.appevents;

import java.io.*;
import com.facebook.*;
import com.facebook.internal.*;

private static class AccessTokenAppIdPair implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final String accessTokenString;
    private final String applicationId;
    
    AccessTokenAppIdPair(final AccessToken accessToken) {
        this(accessToken.getToken(), FacebookSdk.getApplicationId());
    }
    
    AccessTokenAppIdPair(String accessTokenString, final String applicationId) {
        if (Utility.isNullOrEmpty(accessTokenString)) {
            accessTokenString = null;
        }
        this.accessTokenString = accessTokenString;
        this.applicationId = applicationId;
    }
    
    private Object writeReplace() {
        return new SerializationProxyV1(this.accessTokenString, this.applicationId);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof AccessTokenAppIdPair) {
            final AccessTokenAppIdPair accessTokenAppIdPair = (AccessTokenAppIdPair)o;
            if (Utility.areObjectsEqual(accessTokenAppIdPair.accessTokenString, this.accessTokenString) && Utility.areObjectsEqual(accessTokenAppIdPair.applicationId, this.applicationId)) {
                return true;
            }
        }
        return false;
    }
    
    String getAccessTokenString() {
        return this.accessTokenString;
    }
    
    String getApplicationId() {
        return this.applicationId;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.accessTokenString == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.accessTokenString.hashCode();
        }
        final String applicationId = this.applicationId;
        int hashCode2 = 0;
        if (applicationId != null) {
            hashCode2 = this.applicationId.hashCode();
        }
        return hashCode ^ hashCode2;
    }
    
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
}
