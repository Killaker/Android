package android.support.v4.media;

import android.os.*;

static final class MediaMetadataCompat$1 implements Parcelable$Creator<MediaMetadataCompat> {
    public MediaMetadataCompat createFromParcel(final Parcel parcel) {
        return new MediaMetadataCompat(parcel, null);
    }
    
    public MediaMetadataCompat[] newArray(final int n) {
        return new MediaMetadataCompat[n];
    }
}