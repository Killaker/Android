package com.facebook.share.model;

import android.net.*;
import android.support.annotation.*;
import android.os.*;

public final class ShareLinkContent extends ShareContent<ShareLinkContent, Builder>
{
    public static final Parcelable$Creator<ShareLinkContent> CREATOR;
    private final String contentDescription;
    private final String contentTitle;
    private final Uri imageUrl;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareLinkContent>() {
            public ShareLinkContent createFromParcel(final Parcel parcel) {
                return new ShareLinkContent(parcel);
            }
            
            public ShareLinkContent[] newArray(final int n) {
                return new ShareLinkContent[n];
            }
        };
    }
    
    ShareLinkContent(final Parcel parcel) {
        super(parcel);
        this.contentDescription = parcel.readString();
        this.contentTitle = parcel.readString();
        this.imageUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
    }
    
    private ShareLinkContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.contentDescription = builder.contentDescription;
        this.contentTitle = builder.contentTitle;
        this.imageUrl = builder.imageUrl;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public String getContentDescription() {
        return this.contentDescription;
    }
    
    @Nullable
    public String getContentTitle() {
        return this.contentTitle;
    }
    
    @Nullable
    public Uri getImageUrl() {
        return this.imageUrl;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.contentDescription);
        parcel.writeString(this.contentTitle);
        parcel.writeParcelable((Parcelable)this.imageUrl, 0);
    }
    
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
}
