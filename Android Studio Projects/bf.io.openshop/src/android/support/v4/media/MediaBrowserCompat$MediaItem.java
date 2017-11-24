package android.support.v4.media;

import android.os.*;
import android.support.annotation.*;
import android.text.*;
import java.lang.annotation.*;

public static class MediaItem implements Parcelable
{
    public static final Parcelable$Creator<MediaItem> CREATOR;
    public static final int FLAG_BROWSABLE = 1;
    public static final int FLAG_PLAYABLE = 2;
    private final MediaDescriptionCompat mDescription;
    private final int mFlags;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<MediaItem>() {
            public MediaItem createFromParcel(final Parcel parcel) {
                return new MediaItem(parcel);
            }
            
            public MediaItem[] newArray(final int n) {
                return new MediaItem[n];
            }
        };
    }
    
    private MediaItem(final Parcel parcel) {
        this.mFlags = parcel.readInt();
        this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
    }
    
    public MediaItem(@NonNull final MediaDescriptionCompat mDescription, final int mFlags) {
        if (mDescription == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (TextUtils.isEmpty((CharSequence)mDescription.getMediaId())) {
            throw new IllegalArgumentException("description must have a non-empty media id");
        }
        this.mFlags = mFlags;
        this.mDescription = mDescription;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @NonNull
    public MediaDescriptionCompat getDescription() {
        return this.mDescription;
    }
    
    public int getFlags() {
        return this.mFlags;
    }
    
    @NonNull
    public String getMediaId() {
        return this.mDescription.getMediaId();
    }
    
    public boolean isBrowsable() {
        return (0x1 & this.mFlags) != 0x0;
    }
    
    public boolean isPlayable() {
        return (0x2 & this.mFlags) != 0x0;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MediaItem{");
        sb.append("mFlags=").append(this.mFlags);
        sb.append(", mDescription=").append(this.mDescription);
        sb.append('}');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.mFlags);
        this.mDescription.writeToParcel(parcel, n);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }
}
