package android.support.v4.media;

import android.os.*;

static final class RatingCompat$1 implements Parcelable$Creator<RatingCompat> {
    public RatingCompat createFromParcel(final Parcel parcel) {
        return new RatingCompat(parcel.readInt(), parcel.readFloat(), null);
    }
    
    public RatingCompat[] newArray(final int n) {
        return new RatingCompat[n];
    }
}