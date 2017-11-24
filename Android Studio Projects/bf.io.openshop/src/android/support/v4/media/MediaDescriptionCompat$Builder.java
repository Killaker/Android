package android.support.v4.media;

import android.os.*;
import android.graphics.*;
import android.net.*;
import android.support.annotation.*;

public static final class Builder
{
    private CharSequence mDescription;
    private Bundle mExtras;
    private Bitmap mIcon;
    private Uri mIconUri;
    private String mMediaId;
    private Uri mMediaUri;
    private CharSequence mSubtitle;
    private CharSequence mTitle;
    
    public MediaDescriptionCompat build() {
        return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri, null);
    }
    
    public Builder setDescription(@Nullable final CharSequence mDescription) {
        this.mDescription = mDescription;
        return this;
    }
    
    public Builder setExtras(@Nullable final Bundle mExtras) {
        this.mExtras = mExtras;
        return this;
    }
    
    public Builder setIconBitmap(@Nullable final Bitmap mIcon) {
        this.mIcon = mIcon;
        return this;
    }
    
    public Builder setIconUri(@Nullable final Uri mIconUri) {
        this.mIconUri = mIconUri;
        return this;
    }
    
    public Builder setMediaId(@Nullable final String mMediaId) {
        this.mMediaId = mMediaId;
        return this;
    }
    
    public Builder setMediaUri(@Nullable final Uri mMediaUri) {
        this.mMediaUri = mMediaUri;
        return this;
    }
    
    public Builder setSubtitle(@Nullable final CharSequence mSubtitle) {
        this.mSubtitle = mSubtitle;
        return this;
    }
    
    public Builder setTitle(@Nullable final CharSequence mTitle) {
        this.mTitle = mTitle;
        return this;
    }
}
