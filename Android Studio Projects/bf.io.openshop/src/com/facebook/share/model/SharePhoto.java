package com.facebook.share.model;

import android.graphics.*;
import android.net.*;
import android.support.annotation.*;
import android.os.*;
import java.util.*;

public final class SharePhoto extends ShareMedia
{
    public static final Parcelable$Creator<SharePhoto> CREATOR;
    private final Bitmap bitmap;
    private final String caption;
    private final Uri imageUrl;
    private final boolean userGenerated;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<SharePhoto>() {
            public SharePhoto createFromParcel(final Parcel parcel) {
                return new SharePhoto(parcel);
            }
            
            public SharePhoto[] newArray(final int n) {
                return new SharePhoto[n];
            }
        };
    }
    
    SharePhoto(final Parcel parcel) {
        super(parcel);
        this.bitmap = (Bitmap)parcel.readParcelable(Bitmap.class.getClassLoader());
        this.imageUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.userGenerated = (parcel.readByte() != 0);
        this.caption = parcel.readString();
    }
    
    private SharePhoto(final Builder builder) {
        super((ShareMedia.Builder)builder);
        this.bitmap = builder.bitmap;
        this.imageUrl = builder.imageUrl;
        this.userGenerated = builder.userGenerated;
        this.caption = builder.caption;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public Bitmap getBitmap() {
        return this.bitmap;
    }
    
    public String getCaption() {
        return this.caption;
    }
    
    @Nullable
    public Uri getImageUrl() {
        return this.imageUrl;
    }
    
    public boolean getUserGenerated() {
        return this.userGenerated;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeParcelable((Parcelable)this.bitmap, 0);
        parcel.writeParcelable((Parcelable)this.imageUrl, 0);
        final boolean userGenerated = this.userGenerated;
        boolean b = false;
        if (userGenerated) {
            b = true;
        }
        parcel.writeByte((byte)(byte)(b ? 1 : 0));
        parcel.writeString(this.caption);
    }
    
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
}
