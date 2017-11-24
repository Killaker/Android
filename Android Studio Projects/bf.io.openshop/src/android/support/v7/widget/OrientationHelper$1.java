package android.support.v7.widget;

import android.view.*;

static final class OrientationHelper$1 extends OrientationHelper {
    @Override
    public int getDecoratedEnd(final View view) {
        return this.mLayoutManager.getDecoratedRight(view) + ((RecyclerView.LayoutParams)view.getLayoutParams()).rightMargin;
    }
    
    @Override
    public int getDecoratedMeasurement(final View view) {
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        return this.mLayoutManager.getDecoratedMeasuredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
    }
    
    @Override
    public int getDecoratedMeasurementInOther(final View view) {
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        return this.mLayoutManager.getDecoratedMeasuredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
    }
    
    @Override
    public int getDecoratedStart(final View view) {
        return this.mLayoutManager.getDecoratedLeft(view) - ((RecyclerView.LayoutParams)view.getLayoutParams()).leftMargin;
    }
    
    @Override
    public int getEnd() {
        return this.mLayoutManager.getWidth();
    }
    
    @Override
    public int getEndAfterPadding() {
        return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingRight();
    }
    
    @Override
    public int getEndPadding() {
        return this.mLayoutManager.getPaddingRight();
    }
    
    @Override
    public int getMode() {
        return this.mLayoutManager.getWidthMode();
    }
    
    @Override
    public int getModeInOther() {
        return this.mLayoutManager.getHeightMode();
    }
    
    @Override
    public int getStartAfterPadding() {
        return this.mLayoutManager.getPaddingLeft();
    }
    
    @Override
    public int getTotalSpace() {
        return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingLeft() - this.mLayoutManager.getPaddingRight();
    }
    
    @Override
    public void offsetChild(final View view, final int n) {
        view.offsetLeftAndRight(n);
    }
    
    @Override
    public void offsetChildren(final int n) {
        this.mLayoutManager.offsetChildrenHorizontal(n);
    }
}