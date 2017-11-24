package android.support.design.widget;

import android.view.*;
import android.os.*;

protected static class SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    final int state;
    
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
        this.state = parcel.readInt();
    }
    
    public SavedState(final Parcelable parcelable, final int state) {
        super(parcelable);
        this.state = state;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.state);
    }
}
