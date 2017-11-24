package android.support.v7.widget;

import android.view.*;
import android.os.*;

public static class SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    Parcelable mLayoutState;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
            public SavedState createFromParcel(final Parcel parcel) {
                return new SavedState(parcel);
            }
            
            public SavedState[] newArray(final int n) {
                return new SavedState[n];
            }
        };
    }
    
    SavedState(final Parcel parcel) {
        super(parcel);
        this.mLayoutState = parcel.readParcelable(LayoutManager.class.getClassLoader());
    }
    
    SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    private void copyFrom(final SavedState savedState) {
        this.mLayoutState = savedState.mLayoutState;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeParcelable(this.mLayoutState, 0);
    }
}
