package android.support.v4.media;

import android.os.*;

static final class MediaBrowserCompat$MediaItem$1 implements Parcelable$Creator<MediaItem> {
    public MediaItem createFromParcel(final Parcel parcel) {
        return new MediaItem(parcel);
    }
    
    public MediaItem[] newArray(final int n) {
        return new MediaItem[n];
    }
}