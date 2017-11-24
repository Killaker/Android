package android.support.v4.app;

import android.os.*;

static final class FragmentState$1 implements Parcelable$Creator<FragmentState> {
    public FragmentState createFromParcel(final Parcel parcel) {
        return new FragmentState(parcel);
    }
    
    public FragmentState[] newArray(final int n) {
        return new FragmentState[n];
    }
}