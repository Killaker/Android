package com.facebook.share.model;

import android.graphics.*;
import android.net.*;
import android.os.*;
import java.util.*;
import android.support.annotation.*;

public static final class Builder extends ShareMedia.Builder<SharePhoto, Builder>
{
    private Bitmap bitmap;
    private String caption;
    private Uri imageUrl;
    private boolean userGenerated;
    
    public static List<SharePhoto> readListFrom(final Parcel parcel) {
        final ArrayList<SharePhoto> list = new ArrayList<SharePhoto>();
        parcel.readTypedList((List)list, (Parcelable$Creator)SharePhoto.CREATOR);
        return list;
    }
    
    public static void writeListTo(final Parcel parcel, final List<SharePhoto> list) {
        final ArrayList<SharePhoto> list2 = new ArrayList<SharePhoto>();
        final Iterator<SharePhoto> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(iterator.next());
        }
        parcel.writeTypedList((List)list2);
    }
    
    @Override
    public SharePhoto build() {
        return new SharePhoto(this, null);
    }
    
    Bitmap getBitmap() {
        return this.bitmap;
    }
    
    Uri getImageUrl() {
        return this.imageUrl;
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((SharePhoto)parcel.readParcelable(SharePhoto.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final SharePhoto sharePhoto) {
        if (sharePhoto == null) {
            return this;
        }
        return super.readFrom(sharePhoto).setBitmap(sharePhoto.getBitmap()).setImageUrl(sharePhoto.getImageUrl()).setUserGenerated(sharePhoto.getUserGenerated()).setCaption(sharePhoto.getCaption());
    }
    
    public Builder setBitmap(@Nullable final Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }
    
    public Builder setCaption(@Nullable final String caption) {
        this.caption = caption;
        return this;
    }
    
    public Builder setImageUrl(@Nullable final Uri imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
    
    public Builder setUserGenerated(final boolean userGenerated) {
        this.userGenerated = userGenerated;
        return this;
    }
}
