package com.facebook.share.model;

import android.os.*;
import android.support.annotation.*;

public static final class Builder extends ShareContent.Builder<ShareOpenGraphContent, Builder>
{
    private ShareOpenGraphAction action;
    private String previewPropertyName;
    
    @Override
    public ShareOpenGraphContent build() {
        return new ShareOpenGraphContent(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((ShareOpenGraphContent)parcel.readParcelable(ShareOpenGraphContent.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final ShareOpenGraphContent shareOpenGraphContent) {
        if (shareOpenGraphContent == null) {
            return this;
        }
        return super.readFrom(shareOpenGraphContent).setAction(shareOpenGraphContent.getAction()).setPreviewPropertyName(shareOpenGraphContent.getPreviewPropertyName());
    }
    
    public Builder setAction(@Nullable final ShareOpenGraphAction shareOpenGraphAction) {
        ShareOpenGraphAction build;
        if (shareOpenGraphAction == null) {
            build = null;
        }
        else {
            build = new ShareOpenGraphAction.Builder().readFrom(shareOpenGraphAction).build();
        }
        this.action = build;
        return this;
    }
    
    public Builder setPreviewPropertyName(@Nullable final String previewPropertyName) {
        this.previewPropertyName = previewPropertyName;
        return this;
    }
}
