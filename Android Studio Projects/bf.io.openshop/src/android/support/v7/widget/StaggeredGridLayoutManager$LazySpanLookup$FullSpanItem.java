package android.support.v7.widget;

import android.os.*;
import java.util.*;

static class FullSpanItem implements Parcelable
{
    public static final Parcelable$Creator<FullSpanItem> CREATOR;
    int mGapDir;
    int[] mGapPerSpan;
    boolean mHasUnwantedGapAfter;
    int mPosition;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<FullSpanItem>() {
            public FullSpanItem createFromParcel(final Parcel parcel) {
                return new FullSpanItem(parcel);
            }
            
            public FullSpanItem[] newArray(final int n) {
                return new FullSpanItem[n];
            }
        };
    }
    
    public FullSpanItem() {
    }
    
    public FullSpanItem(final Parcel parcel) {
        boolean mHasUnwantedGapAfter = true;
        this.mPosition = parcel.readInt();
        this.mGapDir = parcel.readInt();
        if (parcel.readInt() != (mHasUnwantedGapAfter ? 1 : 0)) {
            mHasUnwantedGapAfter = false;
        }
        this.mHasUnwantedGapAfter = mHasUnwantedGapAfter;
        final int int1 = parcel.readInt();
        if (int1 > 0) {
            parcel.readIntArray(this.mGapPerSpan = new int[int1]);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    int getGapForSpan(final int n) {
        if (this.mGapPerSpan == null) {
            return 0;
        }
        return this.mGapPerSpan[n];
    }
    
    @Override
    public String toString() {
        return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.mPosition);
        parcel.writeInt(this.mGapDir);
        int n2;
        if (this.mHasUnwantedGapAfter) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        parcel.writeInt(n2);
        if (this.mGapPerSpan != null && this.mGapPerSpan.length > 0) {
            parcel.writeInt(this.mGapPerSpan.length);
            parcel.writeIntArray(this.mGapPerSpan);
            return;
        }
        parcel.writeInt(0);
    }
}
