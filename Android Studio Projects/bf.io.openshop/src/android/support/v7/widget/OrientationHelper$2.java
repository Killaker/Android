package android.support.v7.widget;

import android.view.*;

static final class OrientationHelper$2 extends OrientationHelper {
    @Override
    public int getDecoratedEnd(final View view) {
        return this.mLayoutManager.getDecoratedBottom(view) + ((RecyclerView.LayoutParams)view.getLayoutParams()).bottomMargin;
    }
    
    @Override
    public int getDecoratedMeasurement(final View view) {
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        return this.mLayoutManager.getDecoratedMeasuredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
    }
    
    @Override
    public int getDecoratedMeasurementInOther(final View view) {
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        return this.mLayoutManager.getDecoratedMeasuredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
    }
    
    @Override
    public int getDecoratedStart(final View view) {
        return this.mLayoutManager.getDecoratedTop(view) - ((RecyclerView.LayoutParams)view.getLayoutParams()).topMargin;
    }
    
    @Override
    public int getEnd() {
        return this.mLayoutManager.getHeight();
    }
    
    @Override
    public int getEndAfterPadding() {
        return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
    }
    
    @Override
    public int getEndPadding() {
        return this.mLayoutManager.getPaddingBottom();
    }
    
    @Override
    public int getMode() {
        return this.mLayoutManager.getHeightMode();
    }
    
    @Override
    public int getModeInOther() {
        return this.mLayoutManager.getWidthMode();
    }
    
    @Override
    public int getStartAfterPadding() {
        return this.mLayoutManager.getPaddingTop();
    }
    
    @Override
    public int getTotalSpace() {
        return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingTop() - this.mLayoutManager.getPaddingBottom();
    }
    
    @Override
    public void offsetChild(final View view, final int n) {
        view.offsetTopAndBottom(n);
    }
    
    @Override
    public void offsetChildren(final int n) {
        this.mLayoutManager.offsetChildrenVertical(n);
    }
}