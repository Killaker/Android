package android.support.design.widget;

import android.support.v4.widget.*;
import android.support.v4.view.*;
import android.view.*;

class SwipeDismissBehavior$1 extends Callback {
    private int mOriginalCapturedViewLeft;
    
    private boolean shouldDismiss(final View view, final float n) {
        if (n != 0.0f) {
            final boolean b = ViewCompat.getLayoutDirection(view) == 1;
            if (SwipeDismissBehavior.access$200(SwipeDismissBehavior.this) != 2) {
                if (SwipeDismissBehavior.access$200(SwipeDismissBehavior.this) == 0) {
                    if (b) {
                        if (n >= 0.0f) {
                            return false;
                        }
                    }
                    else if (n <= 0.0f) {
                        return false;
                    }
                }
                else {
                    if (SwipeDismissBehavior.access$200(SwipeDismissBehavior.this) != 1) {
                        return false;
                    }
                    if (b) {
                        if (n <= 0.0f) {
                            return false;
                        }
                    }
                    else if (n >= 0.0f) {
                        return false;
                    }
                }
            }
        }
        else if (Math.abs(view.getLeft() - this.mOriginalCapturedViewLeft) < Math.round(view.getWidth() * SwipeDismissBehavior.access$300(SwipeDismissBehavior.this))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int clampViewPositionHorizontal(final View view, final int n, final int n2) {
        boolean b;
        if (ViewCompat.getLayoutDirection(view) == 1) {
            b = true;
        }
        else {
            b = false;
        }
        int n3;
        int n4;
        if (SwipeDismissBehavior.access$200(SwipeDismissBehavior.this) == 0) {
            if (b) {
                n3 = this.mOriginalCapturedViewLeft - view.getWidth();
                n4 = this.mOriginalCapturedViewLeft;
            }
            else {
                n3 = this.mOriginalCapturedViewLeft;
                n4 = this.mOriginalCapturedViewLeft + view.getWidth();
            }
        }
        else if (SwipeDismissBehavior.access$200(SwipeDismissBehavior.this) == 1) {
            if (b) {
                n3 = this.mOriginalCapturedViewLeft;
                n4 = this.mOriginalCapturedViewLeft + view.getWidth();
            }
            else {
                n3 = this.mOriginalCapturedViewLeft - view.getWidth();
                n4 = this.mOriginalCapturedViewLeft;
            }
        }
        else {
            n3 = this.mOriginalCapturedViewLeft - view.getWidth();
            n4 = this.mOriginalCapturedViewLeft + view.getWidth();
        }
        return SwipeDismissBehavior.access$400(n3, n, n4);
    }
    
    @Override
    public int clampViewPositionVertical(final View view, final int n, final int n2) {
        return view.getTop();
    }
    
    @Override
    public int getViewHorizontalDragRange(final View view) {
        return view.getWidth();
    }
    
    @Override
    public void onViewCaptured(final View view, final int n) {
        this.mOriginalCapturedViewLeft = view.getLeft();
        final ViewParent parent = view.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }
    
    @Override
    public void onViewDragStateChanged(final int n) {
        if (SwipeDismissBehavior.access$000(SwipeDismissBehavior.this) != null) {
            SwipeDismissBehavior.access$000(SwipeDismissBehavior.this).onDragStateChanged(n);
        }
    }
    
    @Override
    public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
        final float n5 = this.mOriginalCapturedViewLeft + view.getWidth() * SwipeDismissBehavior.access$500(SwipeDismissBehavior.this);
        final float n6 = this.mOriginalCapturedViewLeft + view.getWidth() * SwipeDismissBehavior.access$600(SwipeDismissBehavior.this);
        if (n <= n5) {
            ViewCompat.setAlpha(view, 1.0f);
            return;
        }
        if (n >= n6) {
            ViewCompat.setAlpha(view, 0.0f);
            return;
        }
        ViewCompat.setAlpha(view, SwipeDismissBehavior.access$700(0.0f, 1.0f - SwipeDismissBehavior.fraction(n5, n6, n), 1.0f));
    }
    
    @Override
    public void onViewReleased(final View view, final float n, final float n2) {
        final ViewParent parent = view.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        final int width = view.getWidth();
        int mOriginalCapturedViewLeft;
        boolean b;
        if (this.shouldDismiss(view, n)) {
            if (view.getLeft() < this.mOriginalCapturedViewLeft) {
                mOriginalCapturedViewLeft = this.mOriginalCapturedViewLeft - width;
            }
            else {
                mOriginalCapturedViewLeft = width + this.mOriginalCapturedViewLeft;
            }
            b = true;
        }
        else {
            mOriginalCapturedViewLeft = this.mOriginalCapturedViewLeft;
            b = false;
        }
        if (SwipeDismissBehavior.access$100(SwipeDismissBehavior.this).settleCapturedViewAt(mOriginalCapturedViewLeft, view.getTop())) {
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, b));
        }
        else if (b && SwipeDismissBehavior.access$000(SwipeDismissBehavior.this) != null) {
            SwipeDismissBehavior.access$000(SwipeDismissBehavior.this).onDismiss(view);
        }
    }
    
    @Override
    public boolean tryCaptureView(final View view, final int n) {
        return SwipeDismissBehavior.this.canSwipeDismissView(view);
    }
}