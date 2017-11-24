package com.facebook.share.internal;

import android.os.*;
import com.facebook.share.model.*;

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
