package android.support.v7.widget;

import java.util.*;
import android.os.*;

static class LazySpanLookup
{
    private static final int MIN_SIZE = 10;
    int[] mData;
    List<FullSpanItem> mFullSpanItems;
    
    private int invalidateFullSpansAfter(final int n) {
        if (this.mFullSpanItems != null) {
            final FullSpanItem fullSpanItem = this.getFullSpanItem(n);
            if (fullSpanItem != null) {
                this.mFullSpanItems.remove(fullSpanItem);
            }
            int n2 = -1;
            for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
                if (this.mFullSpanItems.get(i).mPosition >= n) {
                    n2 = i;
                    break;
                }
            }
            if (n2 != -1) {
                final FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(n2);
                this.mFullSpanItems.remove(n2);
                return fullSpanItem2.mPosition;
            }
        }
        return -1;
    }
    
    private void offsetFullSpansForAddition(final int n, final int n2) {
        if (this.mFullSpanItems != null) {
            for (int i = -1 + this.mFullSpanItems.size(); i >= 0; --i) {
                final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                if (fullSpanItem.mPosition >= n) {
                    fullSpanItem.mPosition += n2;
                }
            }
        }
    }
    
    private void offsetFullSpansForRemoval(final int n, final int n2) {
        if (this.mFullSpanItems != null) {
            final int n3 = n + n2;
            for (int i = -1 + this.mFullSpanItems.size(); i >= 0; --i) {
                final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                if (fullSpanItem.mPosition >= n) {
                    if (fullSpanItem.mPosition < n3) {
                        this.mFullSpanItems.remove(i);
                    }
                    else {
                        fullSpanItem.mPosition -= n2;
                    }
                }
            }
        }
    }
    
    public void addFullSpanItem(final FullSpanItem fullSpanItem) {
        if (this.mFullSpanItems == null) {
            this.mFullSpanItems = new ArrayList<FullSpanItem>();
        }
        for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
            final FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(i);
            if (fullSpanItem2.mPosition == fullSpanItem.mPosition) {
                this.mFullSpanItems.remove(i);
            }
            if (fullSpanItem2.mPosition >= fullSpanItem.mPosition) {
                this.mFullSpanItems.add(i, fullSpanItem);
                return;
            }
        }
        this.mFullSpanItems.add(fullSpanItem);
    }
    
    void clear() {
        if (this.mData != null) {
            Arrays.fill(this.mData, -1);
        }
        this.mFullSpanItems = null;
    }
    
    void ensureSize(final int n) {
        if (this.mData == null) {
            Arrays.fill(this.mData = new int[1 + Math.max(n, 10)], -1);
        }
        else if (n >= this.mData.length) {
            final int[] mData = this.mData;
            System.arraycopy(mData, 0, this.mData = new int[this.sizeForPosition(n)], 0, mData.length);
            Arrays.fill(this.mData, mData.length, this.mData.length, -1);
        }
    }
    
    int forceInvalidateAfter(final int n) {
        if (this.mFullSpanItems != null) {
            for (int i = -1 + this.mFullSpanItems.size(); i >= 0; --i) {
                if (this.mFullSpanItems.get(i).mPosition >= n) {
                    this.mFullSpanItems.remove(i);
                }
            }
        }
        return this.invalidateAfter(n);
    }
    
    public FullSpanItem getFirstFullSpanItemInRange(final int n, final int n2, final int n3, final boolean b) {
        if (this.mFullSpanItems != null) {
            for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
                final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                if (fullSpanItem.mPosition >= n2) {
                    return null;
                }
                if (fullSpanItem.mPosition >= n && (n3 == 0 || fullSpanItem.mGapDir == n3 || (b && fullSpanItem.mHasUnwantedGapAfter))) {
                    return fullSpanItem;
                }
            }
            return null;
        }
        return null;
    }
    
    public FullSpanItem getFullSpanItem(final int n) {
        if (this.mFullSpanItems != null) {
            for (int i = -1 + this.mFullSpanItems.size(); i >= 0; --i) {
                final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                if (fullSpanItem.mPosition == n) {
                    return fullSpanItem;
                }
            }
            return null;
        }
        return null;
    }
    
    int getSpan(final int n) {
        if (this.mData == null || n >= this.mData.length) {
            return -1;
        }
        return this.mData[n];
    }
    
    int invalidateAfter(final int n) {
        if (this.mData == null || n >= this.mData.length) {
            return -1;
        }
        final int invalidateFullSpansAfter = this.invalidateFullSpansAfter(n);
        if (invalidateFullSpansAfter == -1) {
            Arrays.fill(this.mData, n, this.mData.length, -1);
            return this.mData.length;
        }
        Arrays.fill(this.mData, n, invalidateFullSpansAfter + 1, -1);
        return invalidateFullSpansAfter + 1;
    }
    
    void offsetForAddition(final int n, final int n2) {
        if (this.mData == null || n >= this.mData.length) {
            return;
        }
        this.ensureSize(n + n2);
        System.arraycopy(this.mData, n, this.mData, n + n2, this.mData.length - n - n2);
        Arrays.fill(this.mData, n, n + n2, -1);
        this.offsetFullSpansForAddition(n, n2);
    }
    
    void offsetForRemoval(final int n, final int n2) {
        if (this.mData == null || n >= this.mData.length) {
            return;
        }
        this.ensureSize(n + n2);
        System.arraycopy(this.mData, n + n2, this.mData, n, this.mData.length - n - n2);
        Arrays.fill(this.mData, this.mData.length - n2, this.mData.length, -1);
        this.offsetFullSpansForRemoval(n, n2);
    }
    
    void setSpan(final int n, final Span span) {
        this.ensureSize(n);
        this.mData[n] = span.mIndex;
    }
    
    int sizeForPosition(final int n) {
        int i;
        for (i = this.mData.length; i <= n; i *= 2) {}
        return i;
    }
    
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
}
