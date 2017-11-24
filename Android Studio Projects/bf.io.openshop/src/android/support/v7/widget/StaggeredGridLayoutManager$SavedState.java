package android.support.v7.widget;

import java.util.*;
import android.os.*;

public static class SavedState implements Parcelable
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    boolean mAnchorLayoutFromEnd;
    int mAnchorPosition;
    List<FullSpanItem> mFullSpanItems;
    boolean mLastLayoutRTL;
    boolean mReverseLayout;
    int[] mSpanLookup;
    int mSpanLookupSize;
    int[] mSpanOffsets;
    int mSpanOffsetsSize;
    int mVisibleAnchorPosition;
    
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
        boolean mLastLayoutRTL = true;
        this.mAnchorPosition = parcel.readInt();
        this.mVisibleAnchorPosition = parcel.readInt();
        this.mSpanOffsetsSize = parcel.readInt();
        if (this.mSpanOffsetsSize > 0) {
            parcel.readIntArray(this.mSpanOffsets = new int[this.mSpanOffsetsSize]);
        }
        this.mSpanLookupSize = parcel.readInt();
        if (this.mSpanLookupSize > 0) {
            parcel.readIntArray(this.mSpanLookup = new int[this.mSpanLookupSize]);
        }
        this.mReverseLayout = (parcel.readInt() == (mLastLayoutRTL ? 1 : 0) && mLastLayoutRTL);
        this.mAnchorLayoutFromEnd = (parcel.readInt() == (mLastLayoutRTL ? 1 : 0) && mLastLayoutRTL);
        if (parcel.readInt() != (mLastLayoutRTL ? 1 : 0)) {
            mLastLayoutRTL = false;
        }
        this.mLastLayoutRTL = mLastLayoutRTL;
        this.mFullSpanItems = (List<FullSpanItem>)parcel.readArrayList(FullSpanItem.class.getClassLoader());
    }
    
    public SavedState(final SavedState savedState) {
        this.mSpanOffsetsSize = savedState.mSpanOffsetsSize;
        this.mAnchorPosition = savedState.mAnchorPosition;
        this.mVisibleAnchorPosition = savedState.mVisibleAnchorPosition;
        this.mSpanOffsets = savedState.mSpanOffsets;
        this.mSpanLookupSize = savedState.mSpanLookupSize;
        this.mSpanLookup = savedState.mSpanLookup;
        this.mReverseLayout = savedState.mReverseLayout;
        this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
        this.mLastLayoutRTL = savedState.mLastLayoutRTL;
        this.mFullSpanItems = savedState.mFullSpanItems;
    }
    
    public int describeContents() {
        return 0;
    }
    
    void invalidateAnchorPositionInfo() {
        this.mSpanOffsets = null;
        this.mSpanOffsetsSize = 0;
        this.mAnchorPosition = -1;
        this.mVisibleAnchorPosition = -1;
    }
    
    void invalidateSpanInfo() {
        this.mSpanOffsets = null;
        this.mSpanOffsetsSize = 0;
        this.mSpanLookupSize = 0;
        this.mSpanLookup = null;
        this.mFullSpanItems = null;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        int n2 = 1;
        parcel.writeInt(this.mAnchorPosition);
        parcel.writeInt(this.mVisibleAnchorPosition);
        parcel.writeInt(this.mSpanOffsetsSize);
        if (this.mSpanOffsetsSize > 0) {
            parcel.writeIntArray(this.mSpanOffsets);
        }
        parcel.writeInt(this.mSpanLookupSize);
        if (this.mSpanLookupSize > 0) {
            parcel.writeIntArray(this.mSpanLookup);
        }
        int n3;
        if (this.mReverseLayout) {
            n3 = n2;
        }
        else {
            n3 = 0;
        }
        parcel.writeInt(n3);
        int n4;
        if (this.mAnchorLayoutFromEnd) {
            n4 = n2;
        }
        else {
            n4 = 0;
        }
        parcel.writeInt(n4);
        if (!this.mLastLayoutRTL) {
            n2 = 0;
        }
        parcel.writeInt(n2);
        parcel.writeList((List)this.mFullSpanItems);
    }
}
