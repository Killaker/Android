package android.support.v4.os;

import android.os.*;

static final class ResultReceiver$1 implements Parcelable$Creator<ResultReceiver> {
    public ResultReceiver createFromParcel(final Parcel parcel) {
        return new ResultReceiver(parcel);
    }
    
    public ResultReceiver[] newArray(final int n) {
        return new ResultReceiver[n];
    }
}