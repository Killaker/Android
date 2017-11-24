package android.support.v4.view;

import android.support.v4.os.*;
import android.os.*;

static final class ViewPager$SavedState$1 implements ParcelableCompatCreatorCallbacks<SavedState> {
    @Override
    public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return new SavedState(parcel, classLoader);
    }
    
    @Override
    public SavedState[] newArray(final int n) {
        return new SavedState[n];
    }
}