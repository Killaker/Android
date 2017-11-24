package android.support.v4.widget;

import android.content.*;
import android.util.*;
import android.content.res.*;
import android.view.*;

private class ViewDragCallback extends Callback
{
    private final int mAbsGravity;
    private ViewDragHelper mDragger;
    private final Runnable mPeekRunnable;
    
    public ViewDragCallback(final int mAbsGravity) {
        this.mPeekRunnable = new Runnable() {
            @Override
            public void run() {
                ViewDragCallback.this.peekDrawer();
            }
        };
        this.mAbsGravity = mAbsGravity;
    }
    
    private void closeOtherDrawer() {
        int n = 3;
        if (this.mAbsGravity == n) {
            n = 5;
        }
        final View drawerWithGravity = DrawerLayout.this.findDrawerWithGravity(n);
        if (drawerWithGravity != null) {
            DrawerLayout.this.closeDrawer(drawerWithGravity);
        }
    }
    
    private void peekDrawer() {
        final int edgeSize = this.mDragger.getEdgeSize();
        boolean b;
        if (this.mAbsGravity == 3) {
            b = true;
        }
        else {
            b = false;
        }
        View view;
        int n2;
        if (b) {
            view = DrawerLayout.this.findDrawerWithGravity(3);
            int n = 0;
            if (view != null) {
                n = -view.getWidth();
            }
            n2 = n + edgeSize;
        }
        else {
            view = DrawerLayout.this.findDrawerWithGravity(5);
            n2 = DrawerLayout.this.getWidth() - edgeSize;
        }
        if (view != null && ((b && view.getLeft() < n2) || (!b && view.getLeft() > n2)) && DrawerLayout.this.getDrawerLockMode(view) == 0) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            this.mDragger.smoothSlideViewTo(view, n2, view.getTop());
            layoutParams.isPeeking = true;
            DrawerLayout.this.invalidate();
            this.closeOtherDrawer();
            DrawerLayout.this.cancelChildViewTouch();
        }
    }
    
    @Override
    public int clampViewPositionHorizontal(final View view, final int n, final int n2) {
        if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
            return Math.max(-view.getWidth(), Math.min(n, 0));
        }
        final int width = DrawerLayout.this.getWidth();
        return Math.max(width - view.getWidth(), Math.min(n, width));
    }
    
    @Override
    public int clampViewPositionVertical(final View view, final int n, final int n2) {
        return view.getTop();
    }
    
    @Override
    public int getViewHorizontalDragRange(final View view) {
        if (DrawerLayout.this.isDrawerView(view)) {
            return view.getWidth();
        }
        return 0;
    }
    
    @Override
    public void onEdgeDragStarted(final int n, final int n2) {
        View view;
        if ((n & 0x1) == 0x1) {
            view = DrawerLayout.this.findDrawerWithGravity(3);
        }
        else {
            view = DrawerLayout.this.findDrawerWithGravity(5);
        }
        if (view != null && DrawerLayout.this.getDrawerLockMode(view) == 0) {
            this.mDragger.captureChildView(view, n2);
        }
    }
    
    @Override
    public boolean onEdgeLock(final int n) {
        return false;
    }
    
    @Override
    public void onEdgeTouched(final int n, final int n2) {
        DrawerLayout.this.postDelayed(this.mPeekRunnable, 160L);
    }
    
    @Override
    public void onViewCaptured(final View view, final int n) {
        ((LayoutParams)view.getLayoutParams()).isPeeking = false;
        this.closeOtherDrawer();
    }
    
    @Override
    public void onViewDragStateChanged(final int n) {
        DrawerLayout.this.updateDrawerState(this.mAbsGravity, n, this.mDragger.getCapturedView());
    }
    
    @Override
    public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
        final int width = view.getWidth();
        float n5;
        if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
            n5 = (width + n) / width;
        }
        else {
            n5 = (DrawerLayout.this.getWidth() - n) / width;
        }
        DrawerLayout.this.setDrawerViewOffset(view, n5);
        int visibility;
        if (n5 == 0.0f) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        view.setVisibility(visibility);
        DrawerLayout.this.invalidate();
    }
    
    @Override
    public void onViewReleased(final View view, final float n, final float n2) {
        final float drawerViewOffset = DrawerLayout.this.getDrawerViewOffset(view);
        final int width = view.getWidth();
        int n3;
        if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
            if (n > 0.0f || (n == 0.0f && drawerViewOffset > 0.5f)) {
                n3 = 0;
            }
            else {
                n3 = -width;
            }
        }
        else {
            final int width2 = DrawerLayout.this.getWidth();
            if (n < 0.0f || (n == 0.0f && drawerViewOffset > 0.5f)) {
                n3 = width2 - width;
            }
            else {
                n3 = width2;
            }
        }
        this.mDragger.settleCapturedViewAt(n3, view.getTop());
        DrawerLayout.this.invalidate();
    }
    
    public void removeCallbacks() {
        DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
    }
    
    public void setDragger(final ViewDragHelper mDragger) {
        this.mDragger = mDragger;
    }
    
    @Override
    public boolean tryCaptureView(final View view, final int n) {
        return DrawerLayout.this.isDrawerView(view) && DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, this.mAbsGravity) && DrawerLayout.this.getDrawerLockMode(view) == 0;
    }
}
