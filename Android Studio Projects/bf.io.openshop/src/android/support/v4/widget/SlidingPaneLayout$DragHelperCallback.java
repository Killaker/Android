package android.support.v4.widget;

import android.view.*;

private class DragHelperCallback extends Callback
{
    @Override
    public int clampViewPositionHorizontal(final View view, final int n, final int n2) {
        final LayoutParams layoutParams = (LayoutParams)SlidingPaneLayout.access$400(SlidingPaneLayout.this).getLayoutParams();
        if (SlidingPaneLayout.access$700(SlidingPaneLayout.this)) {
            final int n3 = SlidingPaneLayout.this.getWidth() - (SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin + SlidingPaneLayout.access$400(SlidingPaneLayout.this).getWidth());
            return Math.max(Math.min(n, n3), n3 - SlidingPaneLayout.access$800(SlidingPaneLayout.this));
        }
        final int n4 = SlidingPaneLayout.this.getPaddingLeft() + layoutParams.leftMargin;
        return Math.min(Math.max(n, n4), n4 + SlidingPaneLayout.access$800(SlidingPaneLayout.this));
    }
    
    @Override
    public int clampViewPositionVertical(final View view, final int n, final int n2) {
        return view.getTop();
    }
    
    @Override
    public int getViewHorizontalDragRange(final View view) {
        return SlidingPaneLayout.access$800(SlidingPaneLayout.this);
    }
    
    @Override
    public void onEdgeDragStarted(final int n, final int n2) {
        SlidingPaneLayout.access$200(SlidingPaneLayout.this).captureChildView(SlidingPaneLayout.access$400(SlidingPaneLayout.this), n2);
    }
    
    @Override
    public void onViewCaptured(final View view, final int n) {
        SlidingPaneLayout.this.setAllChildrenVisible();
    }
    
    @Override
    public void onViewDragStateChanged(final int n) {
        if (SlidingPaneLayout.access$200(SlidingPaneLayout.this).getViewDragState() == 0) {
            if (SlidingPaneLayout.access$300(SlidingPaneLayout.this) != 0.0f) {
                SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.access$400(SlidingPaneLayout.this));
                SlidingPaneLayout.access$502(SlidingPaneLayout.this, true);
                return;
            }
            SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.access$400(SlidingPaneLayout.this));
            SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.access$400(SlidingPaneLayout.this));
            SlidingPaneLayout.access$502(SlidingPaneLayout.this, false);
        }
    }
    
    @Override
    public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
        SlidingPaneLayout.access$600(SlidingPaneLayout.this, n);
        SlidingPaneLayout.this.invalidate();
    }
    
    @Override
    public void onViewReleased(final View view, final float n, final float n2) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        int n4;
        if (SlidingPaneLayout.access$700(SlidingPaneLayout.this)) {
            int n3 = SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin;
            if (n < 0.0f || (n == 0.0f && SlidingPaneLayout.access$300(SlidingPaneLayout.this) > 0.5f)) {
                n3 += SlidingPaneLayout.access$800(SlidingPaneLayout.this);
            }
            n4 = SlidingPaneLayout.this.getWidth() - n3 - SlidingPaneLayout.access$400(SlidingPaneLayout.this).getWidth();
        }
        else {
            n4 = SlidingPaneLayout.this.getPaddingLeft() + layoutParams.leftMargin;
            if (n > 0.0f || (n == 0.0f && SlidingPaneLayout.access$300(SlidingPaneLayout.this) > 0.5f)) {
                n4 += SlidingPaneLayout.access$800(SlidingPaneLayout.this);
            }
        }
        SlidingPaneLayout.access$200(SlidingPaneLayout.this).settleCapturedViewAt(n4, view.getTop());
        SlidingPaneLayout.this.invalidate();
    }
    
    @Override
    public boolean tryCaptureView(final View view, final int n) {
        return !SlidingPaneLayout.access$100(SlidingPaneLayout.this) && ((LayoutParams)view.getLayoutParams()).slideable;
    }
}
