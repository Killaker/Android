package android.support.v7.widget;

private class AnchorInfo
{
    boolean mInvalidateOffsets;
    boolean mLayoutFromEnd;
    int mOffset;
    int mPosition;
    
    void assignCoordinateFromPadding() {
        int mOffset;
        if (this.mLayoutFromEnd) {
            mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
        }
        else {
            mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
        }
        this.mOffset = mOffset;
    }
    
    void assignCoordinateFromPadding(final int n) {
        if (this.mLayoutFromEnd) {
            this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - n;
            return;
        }
        this.mOffset = n + StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
    }
    
    void reset() {
        this.mPosition = -1;
        this.mOffset = Integer.MIN_VALUE;
        this.mLayoutFromEnd = false;
        this.mInvalidateOffsets = false;
    }
}
