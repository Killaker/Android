package android.support.v4.media.session;

import android.os.*;

static final class MediaSessionCompat$QueueItem$1 implements Parcelable$Creator<QueueItem> {
    public QueueItem createFromParcel(final Parcel parcel) {
        return new QueueItem(parcel);
    }
    
    public QueueItem[] newArray(final int n) {
        return new QueueItem[n];
    }
}