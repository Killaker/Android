package android.support.v4.widget;

import android.view.*;
import android.os.*;

static class SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    boolean isOpen;
    
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
    
    private SavedState(final Parcel parcel) {
        super(parcel);
        this.isOpen = (parcel.readInt() != 0);
    }
    
    SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        int n2;
        if (this.isOpen) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        parcel.writeInt(n2);
    }
}
