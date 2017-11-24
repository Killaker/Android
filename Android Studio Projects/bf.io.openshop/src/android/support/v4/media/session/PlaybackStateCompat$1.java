package android.support.v4.media.session;

import android.os.*;

static final class PlaybackStateCompat$1 implements Parcelable$Creator<PlaybackStateCompat> {
    public PlaybackStateCompat createFromParcel(final Parcel parcel) {
        return new PlaybackStateCompat(parcel, null);
    }
    
    public PlaybackStateCompat[] newArray(final int n) {
        return new PlaybackStateCompat[n];
    }
}