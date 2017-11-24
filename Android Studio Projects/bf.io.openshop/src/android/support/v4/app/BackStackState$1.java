package android.support.v4.app;

import android.os.*;

static final class BackStackState$1 implements Parcelable$Creator<BackStackState> {
    public BackStackState createFromParcel(final Parcel parcel) {
        return new BackStackState(parcel);
    }
    
    public BackStackState[] newArray(final int n) {
        return new BackStackState[n];
    }
}