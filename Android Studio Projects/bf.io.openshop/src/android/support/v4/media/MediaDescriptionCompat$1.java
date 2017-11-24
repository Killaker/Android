package android.support.v4.media;

import android.os.*;

static final class MediaDescriptionCompat$1 implements Parcelable$Creator<MediaDescriptionCompat> {
    public MediaDescriptionCompat createFromParcel(final Parcel parcel) {
        if (Build$VERSION.SDK_INT < 21) {
            return new MediaDescriptionCompat(parcel, null);
        }
        return MediaDescriptionCompat.fromMediaDescription(MediaDescriptionCompatApi21.fromParcel(parcel));
    }
    
    public MediaDescriptionCompat[] newArray(final int n) {
        return new MediaDescriptionCompat[n];
    }
}