package android.support.v4.media.session;

import android.os.*;

static final class ParcelableVolumeInfo$1 implements Parcelable$Creator<ParcelableVolumeInfo> {
    public ParcelableVolumeInfo createFromParcel(final Parcel parcel) {
        return new ParcelableVolumeInfo(parcel);
    }
    
    public ParcelableVolumeInfo[] newArray(final int n) {
        return new ParcelableVolumeInfo[n];
    }
}