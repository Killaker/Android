package com.facebook.share.model;

import android.os.*;

public static final class Builder extends ShareOpenGraphValueContainer.Builder<ShareOpenGraphObject, Builder>
{
    public Builder() {
        this.putBoolean("fbsdk:create_object", true);
    }
    
    @Override
    public ShareOpenGraphObject build() {
        return new ShareOpenGraphObject(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return ((ShareOpenGraphValueContainer.Builder<ShareOpenGraphObject, Builder>)this).readFrom((ShareOpenGraphObject)parcel.readParcelable(ShareOpenGraphObject.class.getClassLoader()));
    }
}
