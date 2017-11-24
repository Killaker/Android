package com.facebook.share.model;

import android.os.*;

public static class Builder implements ShareModelBuilder<AppInviteContent, Builder>
{
    private String applinkUrl;
    private String previewImageUrl;
    
    @Override
    public AppInviteContent build() {
        return new AppInviteContent(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((AppInviteContent)parcel.readParcelable(AppInviteContent.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final AppInviteContent appInviteContent) {
        if (appInviteContent == null) {
            return this;
        }
        return this.setApplinkUrl(appInviteContent.getApplinkUrl()).setPreviewImageUrl(appInviteContent.getPreviewImageUrl());
    }
    
    public Builder setApplinkUrl(final String applinkUrl) {
        this.applinkUrl = applinkUrl;
        return this;
    }
    
    public Builder setPreviewImageUrl(final String previewImageUrl) {
        this.previewImageUrl = previewImageUrl;
        return this;
    }
}
