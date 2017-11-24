package android.support.v4.os;

import android.os.*;

static class CompatCreator<T> implements Parcelable$Creator<T>
{
    final ParcelableCompatCreatorCallbacks<T> mCallbacks;
    
    public CompatCreator(final ParcelableCompatCreatorCallbacks<T> mCallbacks) {
        this.mCallbacks = mCallbacks;
    }
    
    public T createFromParcel(final Parcel parcel) {
        return this.mCallbacks.createFromParcel(parcel, null);
    }
    
    public T[] newArray(final int n) {
        return this.mCallbacks.newArray(n);
    }
}
