package android.support.v7.widget;

import android.os.*;

public static class SavedState implements Parcelable
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    boolean mAnchorLayoutFromEnd;
    int mAnchorOffset;
    int mAnchorPosition;
    
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
    
    public SavedState() {
    }
    
    SavedState(final Parcel parcel) {
        boolean mAnchorLayoutFromEnd = true;
        this.mAnchorPosition = parcel.readInt();
        this.mAnchorOffset = parcel.readInt();
        if (parcel.readInt() != (mAnchorLayoutFromEnd ? 1 : 0)) {
            mAnchorLayoutFromEnd = false;
        }
        this.mAnchorLayoutFromEnd = mAnchorLayoutFromEnd;
    }
    
    public SavedState(final SavedState savedState) {
        this.mAnchorPosition = savedState.mAnchorPosition;
        this.mAnchorOffset = savedState.mAnchorOffset;
        this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
    }
    
    public int describeContents() {
        return 0;
    }
    
    boolean hasValidAnchor() {
        return this.mAnchorPosition >= 0;
    }
    
    void invalidateAnchor() {
        this.mAnchorPosition = -1;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.mAnchorPosition);
        parcel.writeInt(this.mAnchorOffset);
        int n2;
        if (this.mAnchorLayoutFromEnd) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        parcel.writeInt(n2);
    }
}
