package com.facebook.share.model;

import android.net.*;
import android.os.*;
import android.support.annotation.*;

public static final class Builder extends ShareMedia.Builder<ShareVideo, Builder>
{
    private Uri localUrl;
    
    @Override
    public ShareVideo build() {
        return new ShareVideo(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((ShareVideo)parcel.readParcelable(ShareVideo.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final ShareVideo shareVideo) {
        if (shareVideo == null) {
            return this;
        }
        return super.readFrom(shareVideo).setLocalUrl(shareVideo.getLocalUrl());
    }
    
    public Builder setLocalUrl(@Nullable final Uri localUrl) {
        this.localUrl = localUrl;
        return this;
    }
}
