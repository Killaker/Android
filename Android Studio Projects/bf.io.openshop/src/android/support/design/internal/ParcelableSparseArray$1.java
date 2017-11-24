package android.support.design.internal;

import android.support.v4.os.*;
import android.os.*;

static final class ParcelableSparseArray$1 implements ParcelableCompatCreatorCallbacks<ParcelableSparseArray> {
    @Override
    public ParcelableSparseArray createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return new ParcelableSparseArray(parcel, classLoader);
    }
    
    @Override
    public ParcelableSparseArray[] newArray(final int n) {
        return new ParcelableSparseArray[n];
    }
}