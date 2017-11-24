package com.facebook.messenger;

import android.net.*;

public class ShareToMessengerParamsBuilder
{
    private Uri mExternalUri;
    private String mMetaData;
    private final String mMimeType;
    private final Uri mUri;
    
    ShareToMessengerParamsBuilder(final Uri mUri, final String mMimeType) {
        this.mUri = mUri;
        this.mMimeType = mMimeType;
    }
    
    public ShareToMessengerParams build() {
        return new ShareToMessengerParams(this);
    }
    
    public Uri getExternalUri() {
        return this.mExternalUri;
    }
    
    public String getMetaData() {
        return this.mMetaData;
    }
    
    public String getMimeType() {
        return this.mMimeType;
    }
    
    public Uri getUri() {
        return this.mUri;
    }
    
    public ShareToMessengerParamsBuilder setExternalUri(final Uri mExternalUri) {
        this.mExternalUri = mExternalUri;
        return this;
    }
    
    public ShareToMessengerParamsBuilder setMetaData(final String mMetaData) {
        this.mMetaData = mMetaData;
        return this;
    }
}
