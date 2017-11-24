package android.support.v4.media.session;

import android.os.*;

static final class MediaSessionCompat$ResultReceiverWrapper$1 implements Parcelable$Creator<ResultReceiverWrapper> {
    public ResultReceiverWrapper createFromParcel(final Parcel parcel) {
        return new ResultReceiverWrapper(parcel);
    }
    
    public ResultReceiverWrapper[] newArray(final int n) {
        return new ResultReceiverWrapper[n];
    }
}