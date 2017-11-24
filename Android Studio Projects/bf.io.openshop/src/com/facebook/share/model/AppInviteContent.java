package com.facebook.share.model;

import android.os.*;

public final class AppInviteContent implements ShareModel
{
    public static final Parcelable$Creator<AppInviteContent> CREATOR;
    private final String applinkUrl;
    private final String previewImageUrl;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<AppInviteContent>() {
            public AppInviteContent createFromParcel(final Parcel parcel) {
                return new AppInviteContent(parcel);
            }
            
            public AppInviteContent[] newArray(final int n) {
                return new AppInviteContent[n];
            }
        };
    }
    
    AppInviteContent(final Parcel parcel) {
        this.applinkUrl = parcel.readString();
        this.previewImageUrl = parcel.readString();
    }
    
    private AppInviteContent(final Builder builder) {
        this.applinkUrl = builder.applinkUrl;
        this.previewImageUrl = builder.previewImageUrl;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getApplinkUrl() {
        return this.applinkUrl;
    }
    
    public String getPreviewImageUrl() {
        return this.previewImageUrl;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.applinkUrl);
        parcel.writeString(this.previewImageUrl);
    }
    
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
}
