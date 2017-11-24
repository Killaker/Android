package com.facebook.share.model;

import android.support.annotation.*;
import java.util.*;
import android.os.*;

public static class Builder extends ShareContent.Builder<SharePhotoContent, Builder>
{
    private final List<SharePhoto> photos;
    
    public Builder() {
        this.photos = new ArrayList<SharePhoto>();
    }
    
    public Builder addPhoto(@Nullable final SharePhoto sharePhoto) {
        if (sharePhoto != null) {
            this.photos.add(new SharePhoto.Builder().readFrom(sharePhoto).build());
        }
        return this;
    }
    
    public Builder addPhotos(@Nullable final List<SharePhoto> list) {
        if (list != null) {
            final Iterator<SharePhoto> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.addPhoto(iterator.next());
            }
        }
        return this;
    }
    
    @Override
    public SharePhotoContent build() {
        return new SharePhotoContent(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((SharePhotoContent)parcel.readParcelable(SharePhotoContent.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final SharePhotoContent sharePhotoContent) {
        if (sharePhotoContent == null) {
            return this;
        }
        return super.readFrom(sharePhotoContent).addPhotos(sharePhotoContent.getPhotos());
    }
    
    public Builder setPhotos(@Nullable final List<SharePhoto> list) {
        this.photos.clear();
        this.addPhotos(list);
        return this;
    }
}
