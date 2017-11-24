package com.facebook.share.model;

import android.os.*;
import android.support.annotation.*;

public static final class Builder extends ShareContent.Builder<ShareVideoContent, Builder>
{
    private String contentDescription;
    private String contentTitle;
    private SharePhoto previewPhoto;
    private ShareVideo video;
    
    @Override
    public ShareVideoContent build() {
        return new ShareVideoContent(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((ShareVideoContent)parcel.readParcelable(ShareVideoContent.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final ShareVideoContent shareVideoContent) {
        if (shareVideoContent == null) {
            return this;
        }
        return super.readFrom(shareVideoContent).setContentDescription(shareVideoContent.getContentDescription()).setContentTitle(shareVideoContent.getContentTitle()).setPreviewPhoto(shareVideoContent.getPreviewPhoto()).setVideo(shareVideoContent.getVideo());
    }
    
    public Builder setContentDescription(@Nullable final String contentDescription) {
        this.contentDescription = contentDescription;
        return this;
    }
    
    public Builder setContentTitle(@Nullable final String contentTitle) {
        this.contentTitle = contentTitle;
        return this;
    }
    
    public Builder setPreviewPhoto(@Nullable final SharePhoto sharePhoto) {
        SharePhoto build;
        if (sharePhoto == null) {
            build = null;
        }
        else {
            build = new SharePhoto.Builder().readFrom(sharePhoto).build();
        }
        this.previewPhoto = build;
        return this;
    }
    
    public Builder setVideo(@Nullable final ShareVideo shareVideo) {
        if (shareVideo == null) {
            return this;
        }
        this.video = new ShareVideo.Builder().readFrom(shareVideo).build();
        return this;
    }
}
