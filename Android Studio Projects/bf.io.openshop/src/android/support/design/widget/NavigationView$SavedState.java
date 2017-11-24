package android.support.design.widget;

import android.view.*;
import android.support.v4.os.*;
import android.os.*;
import android.support.annotation.*;

public static class SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    public Bundle menuState;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
            @Override
            public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
            
            @Override
            public SavedState[] newArray(final int n) {
                return new SavedState[n];
            }
        });
    }
    
    public SavedState(final Parcel parcel, final ClassLoader classLoader) {
        super(parcel);
        this.menuState = parcel.readBundle(classLoader);
    }
    
    public SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public void writeToParcel(@NonNull final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeBundle(this.menuState);
    }
}
