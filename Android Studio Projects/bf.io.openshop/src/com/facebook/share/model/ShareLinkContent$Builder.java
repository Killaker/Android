package com.facebook.share.model;

import android.net.*;
import android.os.*;
import android.support.annotation.*;

public static final class Builder extends ShareContent.Builder<ShareLinkContent, Builder>
{
    private String contentDescription;
    private String contentTitle;
    private Uri imageUrl;
    
    @Override
    public ShareLinkContent build() {
        return new ShareLinkContent(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((ShareLinkContent)parcel.readParcelable(ShareLinkContent.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final ShareLinkContent shareLinkContent) {
        if (shareLinkContent == null) {
            return this;
        }
        return super.readFrom(shareLinkContent).setContentDescription(shareLinkContent.getContentDescription()).setImageUrl(shareLinkContent.getImageUrl()).setContentTitle(shareLinkContent.getContentTitle());
    }
    
    public Builder setContentDescription(@Nullable final String contentDescription) {
        this.contentDescription = contentDescription;
        return this;
    }
    
    public Builder setContentTitle(@Nullable final String contentTitle) {
        this.contentTitle = contentTitle;
        return this;
    }
    
    public Builder setImageUrl(@Nullable final Uri imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
