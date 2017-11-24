package android.support.v7.widget;

import android.view.*;

class AnchorInfo
{
    int mCoordinate;
    boolean mLayoutFromEnd;
    int mPosition;
    
    private boolean isViewValidAsAnchor(final View view, final State state) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        return !layoutParams.isItemRemoved() && layoutParams.getViewLayoutPosition() >= 0 && layoutParams.getViewLayoutPosition() < state.getItemCount();
    }
    
    void assignCoordinateFromPadding() {
        int mCoordinate;
        if (this.mLayoutFromEnd) {
            mCoordinate = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding();
        }
        else {
            mCoordinate = LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
        }
        this.mCoordinate = mCoordinate;
    }
    
    public void assignFromView(final View view) {
        if (this.mLayoutFromEnd) {
            this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(view) + LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
        }
        else {
            this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
        }
        this.mPosition = ((RecyclerView.LayoutManager)LinearLayoutManager.this).getPosition(view);
    }
    
    public void assignFromViewAndKeepVisibleRect(final View view) {
        final int totalSpaceChange = LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
        if (totalSpaceChange >= 0) {
            this.assignFromView(view);
        }
        else {
            this.mPosition = ((RecyclerView.LayoutManager)LinearLayoutManager.this).getPosition(view);
            if (this.mLayoutFromEnd) {
                final int n = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange - LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(view);
                this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - n;
                if (n > 0) {
                    final int n2 = this.mCoordinate - LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(view);
                    final int startAfterPadding = LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
                    final int n3 = n2 - (startAfterPadding + Math.min(LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(view) - startAfterPadding, 0));
                    if (n3 < 0) {
                        this.mCoordinate += Math.min(n, -n3);
                    }
                }
            }
            else {
                final int decoratedStart = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
                final int n4 = decoratedStart - LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
                this.mCoordinate = decoratedStart;
                if (n4 > 0) {
                    final int n5 = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - Math.min(0, LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange - LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(view)) - (decoratedStart + LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(view));
                    if (n5 < 0) {
                        this.mCoordinate -= Math.min(n4, -n5);
                    }
                }
            }
        }
    }
    
    void reset() {
        this.mPosition = -1;
        this.mCoordinate = Integer.MIN_VALUE;
        this.mLayoutFromEnd = false;
    }
    
    @Override
    public String toString() {
        return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + '}';
    }
}
