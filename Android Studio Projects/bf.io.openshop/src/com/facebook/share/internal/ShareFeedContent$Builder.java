package com.facebook.share.internal;

import android.os.*;
import com.facebook.share.model.*;

public static final class Builder extends ShareContent.Builder<ShareFeedContent, Builder>
{
    private String link;
    private String linkCaption;
    private String linkDescription;
    private String linkName;
    private String mediaSource;
    private String picture;
    private String toId;
    
    @Override
    public ShareFeedContent build() {
        return new ShareFeedContent(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((ShareFeedContent)parcel.readParcelable(ShareFeedContent.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final ShareFeedContent shareFeedContent) {
        if (shareFeedContent == null) {
            return this;
        }
        return super.readFrom(shareFeedContent).setToId(shareFeedContent.getToId()).setLink(shareFeedContent.getLink()).setLinkName(shareFeedContent.getLinkName()).setLinkCaption(shareFeedContent.getLinkCaption()).setLinkDescription(shareFeedContent.getLinkDescription()).setPicture(shareFeedContent.getPicture()).setMediaSource(shareFeedContent.getMediaSource());
    }
    
    public Builder setLink(final String link) {
        this.link = link;
        return this;
    }
    
    public Builder setLinkCaption(final String linkCaption) {
        this.linkCaption = linkCaption;
        return this;
    }
    
    public Builder setLinkDescription(final String linkDescription) {
        this.linkDescription = linkDescription;
        return this;
    }
    
    public Builder setLinkName(final String linkName) {
        this.linkName = linkName;
        return this;
    }
    
    public Builder setMediaSource(final String mediaSource) {
        this.mediaSource = mediaSource;
        return this;
    }
    
    public Builder setPicture(final String picture) {
        this.picture = picture;
        return this;
    }
    
    public Builder setToId(final String toId) {
        this.toId = toId;
        return this;
    }
}
