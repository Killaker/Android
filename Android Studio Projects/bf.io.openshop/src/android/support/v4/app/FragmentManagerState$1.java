package android.support.v4.app;

import android.os.*;

static final class FragmentManagerState$1 implements Parcelable$Creator<FragmentManagerState> {
    public FragmentManagerState createFromParcel(final Parcel parcel) {
        return new FragmentManagerState(parcel);
    }
    
    public FragmentManagerState[] newArray(final int n) {
        return new FragmentManagerState[n];
    }
}