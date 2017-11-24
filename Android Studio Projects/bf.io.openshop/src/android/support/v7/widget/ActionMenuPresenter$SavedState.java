package android.support.v7.widget;

import android.os.*;

private static class SavedState implements Parcelable
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    public int openSubMenuId;
    
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
    
    SavedState() {
    }
    
    SavedState(final Parcel parcel) {
        this.openSubMenuId = parcel.readInt();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.openSubMenuId);
    }
}
