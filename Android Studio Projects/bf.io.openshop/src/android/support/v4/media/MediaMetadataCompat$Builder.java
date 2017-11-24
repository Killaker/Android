package android.support.v4.media;

import android.graphics.*;
import android.os.*;

public static final class Builder
{
    private final Bundle mBundle;
    
    public Builder() {
        this.mBundle = new Bundle();
    }
    
    public Builder(final MediaMetadataCompat mediaMetadataCompat) {
        this.mBundle = new Bundle(MediaMetadataCompat.access$100(mediaMetadataCompat));
    }
    
    public MediaMetadataCompat build() {
        return new MediaMetadataCompat(this.mBundle, null);
    }
    
    public Builder putBitmap(final String s, final Bitmap bitmap) {
        if (MediaMetadataCompat.access$200().containsKey(s) && (int)MediaMetadataCompat.access$200().get(s) != 2) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a Bitmap");
        }
        this.mBundle.putParcelable(s, (Parcelable)bitmap);
        return this;
    }
    
    public Builder putLong(final String s, final long n) {
        if (MediaMetadataCompat.access$200().containsKey(s) && (int)MediaMetadataCompat.access$200().get(s) != 0) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a long");
        }
        this.mBundle.putLong(s, n);
        return this;
    }
    
    public Builder putRating(final String s, final RatingCompat ratingCompat) {
        if (MediaMetadataCompat.access$200().containsKey(s) && (int)MediaMetadataCompat.access$200().get(s) != 3) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a Rating");
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mBundle.putParcelable(s, (Parcelable)ratingCompat.getRating());
            return this;
        }
        this.mBundle.putParcelable(s, (Parcelable)ratingCompat);
        return this;
    }
    
    public Builder putString(final String s, final String s2) {
        if (MediaMetadataCompat.access$200().containsKey(s) && (int)MediaMetadataCompat.access$200().get(s) != 1) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a String");
        }
        this.mBundle.putCharSequence(s, (CharSequence)s2);
        return this;
    }
    
    public Builder putText(final String s, final CharSequence charSequence) {
        if (MediaMetadataCompat.access$200().containsKey(s) && (int)MediaMetadataCompat.access$200().get(s) != 1) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a CharSequence");
        }
        this.mBundle.putCharSequence(s, charSequence);
        return this;
    }
}
