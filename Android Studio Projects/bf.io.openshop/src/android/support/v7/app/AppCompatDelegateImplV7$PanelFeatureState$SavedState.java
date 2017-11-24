package android.support.v7.app;

import android.os.*;
import android.support.v4.os.*;

private static class SavedState implements Parcelable
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    int featureId;
    boolean isOpen;
    Bundle menuState;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
            @Override
            public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                return readFromParcel(parcel, classLoader);
            }
            
            @Override
            public SavedState[] newArray(final int n) {
                return new SavedState[n];
            }
        });
    }
    
    private static SavedState readFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        boolean isOpen = true;
        final SavedState savedState = new SavedState();
        savedState.featureId = parcel.readInt();
        if (parcel.readInt() != (isOpen ? 1 : 0)) {
            isOpen = false;
        }
        savedState.isOpen = isOpen;
        if (savedState.isOpen) {
            savedState.menuState = parcel.readBundle(classLoader);
        }
        return savedState;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.featureId);
        int n2;
        if (this.isOpen) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        parcel.writeInt(n2);
        if (this.isOpen) {
            parcel.writeBundle(this.menuState);
        }
    }
}
