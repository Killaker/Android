package android.support.v7.widget;

import android.view.*;
import android.os.*;

static class SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    boolean isIconified;
    
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
    
    public SavedState(final Parcel parcel) {
        super(parcel);
        this.isIconified = (boolean)parcel.readValue((ClassLoader)null);
    }
    
    SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public String toString() {
        return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.isIconified + "}";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeValue((Object)this.isIconified);
    }
}
