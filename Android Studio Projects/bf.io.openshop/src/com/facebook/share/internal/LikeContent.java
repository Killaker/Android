package com.facebook.share.internal;

import android.os.*;
import com.facebook.share.model.*;

public class LikeContent implements ShareModel
{
    public static final Parcelable$Creator<LikeContent> CREATOR;
    private final String objectId;
    private final String objectType;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<LikeContent>() {
            public LikeContent createFromParcel(final Parcel parcel) {
                return new LikeContent(parcel);
            }
            
            public LikeContent[] newArray(final int n) {
                return new LikeContent[n];
            }
        };
    }
    
    LikeContent(final Parcel parcel) {
        this.objectId = parcel.readString();
        this.objectType = parcel.readString();
    }
    
    private LikeContent(final Builder builder) {
        this.objectId = builder.objectId;
        this.objectType = builder.objectType;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getObjectId() {
        return this.objectId;
    }
    
    public String getObjectType() {
        return this.objectType;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.objectId);
        parcel.writeString(this.objectType);
    }
    
    public static class Builder implements ShareModelBuilder<LikeContent, Builder>
    {
        private String objectId;
        private String objectType;
        
        @Override
        public LikeContent build() {
            return new LikeContent(this, null);
        }
        
        @Override
        public Builder readFrom(final Parcel parcel) {
            return this.readFrom((LikeContent)parcel.readParcelable(LikeContent.class.getClassLoader()));
        }
        
        @Override
        public Builder readFrom(final LikeContent likeContent) {
            if (likeContent == null) {
                return this;
            }
            return this.setObjectId(likeContent.getObjectId()).setObjectType(likeContent.getObjectType());
        }
        
        public Builder setObjectId(final String objectId) {
            this.objectId = objectId;
            return this;
        }
        
        public Builder setObjectType(final String objectType) {
            this.objectType = objectType;
            return this;
        }
    }
}
