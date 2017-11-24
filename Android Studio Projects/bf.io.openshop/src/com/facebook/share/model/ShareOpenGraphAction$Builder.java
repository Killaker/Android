package com.facebook.share.model;

import android.os.*;

public static final class Builder extends ShareOpenGraphValueContainer.Builder<ShareOpenGraphAction, Builder>
{
    private static final String ACTION_TYPE_KEY = "og:type";
    
    @Override
    public ShareOpenGraphAction build() {
        return new ShareOpenGraphAction(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((ShareOpenGraphAction)parcel.readParcelable(ShareOpenGraphAction.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final ShareOpenGraphAction shareOpenGraphAction) {
        if (shareOpenGraphAction == null) {
            return this;
        }
        return super.readFrom(shareOpenGraphAction).setActionType(shareOpenGraphAction.getActionType());
    }
    
    public Builder setActionType(final String s) {
        this.putString("og:type", s);
        return this;
    }
}
