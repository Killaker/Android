package android.support.v7.widget;

import android.view.animation.*;
import android.support.v4.widget.*;
import android.support.v4.view.*;
import android.view.*;
import android.support.v4.os.*;
import android.util.*;
import android.support.annotation.*;
import android.graphics.*;

private class ViewFlinger implements Runnable
{
    private boolean mEatRunOnAnimationRequest;
    private Interpolator mInterpolator;
    private int mLastFlingX;
    private int mLastFlingY;
    private boolean mReSchedulePostAnimationCallback;
    private ScrollerCompat mScroller;
    
    public ViewFlinger() {
        this.mInterpolator = RecyclerView.access$3000();
        this.mEatRunOnAnimationRequest = false;
        this.mReSchedulePostAnimationCallback = false;
        this.mScroller = ScrollerCompat.create(RecyclerView.this.getContext(), RecyclerView.access$3000());
    }
    
    private int computeScrollDuration(final int n, final int n2, final int n3, final int n4) {
        int abs = Math.abs(n);
        final int abs2 = Math.abs(n2);
        boolean b;
        if (abs > abs2) {
            b = true;
        }
        else {
            b = false;
        }
        final int n5 = (int)Math.sqrt(n3 * n3 + n4 * n4);
        final int n6 = (int)Math.sqrt(n * n + n2 * n2);
        int n7;
        if (b) {
            n7 = RecyclerView.this.getWidth();
        }
        else {
            n7 = RecyclerView.this.getHeight();
        }
        final int n8 = n7 / 2;
        final float n9 = n8 + n8 * this.distanceInfluenceForSnapDuration(Math.min(1.0f, 1.0f * n6 / n7));
        int n10;
        if (n5 > 0) {
            n10 = 4 * Math.round(1000.0f * Math.abs(n9 / n5));
        }
        else {
            if (!b) {
                abs = abs2;
            }
            n10 = (int)(300.0f * (1.0f + abs / n7));
        }
        return Math.min(n10, 2000);
    }
    
    private void disableRunOnAnimationRequests() {
        this.mReSchedulePostAnimationCallback = false;
        this.mEatRunOnAnimationRequest = true;
    }
    
    private float distanceInfluenceForSnapDuration(final float n) {
        return (float)Math.sin((float)(0.4712389167638204 * (n - 0.5f)));
    }
    
    private void enableRunOnAnimationRequests() {
        this.mEatRunOnAnimationRequest = false;
        if (this.mReSchedulePostAnimationCallback) {
            this.postOnAnimation();
        }
    }
    
    public void fling(final int n, final int n2) {
        RecyclerView.access$3900(RecyclerView.this, 2);
        this.mLastFlingY = 0;
        this.mLastFlingX = 0;
        this.mScroller.fling(0, 0, n, n2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        this.postOnAnimation();
    }
    
    void postOnAnimation() {
        if (this.mEatRunOnAnimationRequest) {
            this.mReSchedulePostAnimationCallback = true;
            return;
        }
        RecyclerView.this.removeCallbacks((Runnable)this);
        ViewCompat.postOnAnimation((View)RecyclerView.this, this);
    }
    
    @Override
    public void run() {
        if (RecyclerView.this.mLayout == null) {
            this.stop();
            return;
        }
        this.disableRunOnAnimationRequests();
        RecyclerView.access$400(RecyclerView.this);
        final ScrollerCompat mScroller = this.mScroller;
        final SmoothScroller mSmoothScroller = RecyclerView.this.mLayout.mSmoothScroller;
        if (mScroller.computeScrollOffset()) {
            final int currX = mScroller.getCurrX();
            final int currY = mScroller.getCurrY();
            final int n = currX - this.mLastFlingX;
            final int n2 = currY - this.mLastFlingY;
            this.mLastFlingX = currX;
            this.mLastFlingY = currY;
            final Adapter access$3100 = RecyclerView.access$3100(RecyclerView.this);
            int scrollHorizontallyBy = 0;
            int n3 = 0;
            int n4 = 0;
            int scrollVerticallyBy = 0;
            if (access$3100 != null) {
                RecyclerView.this.eatRequestLayout();
                RecyclerView.access$3200(RecyclerView.this);
                TraceCompat.beginSection("RV Scroll");
                scrollHorizontallyBy = 0;
                n3 = 0;
                if (n != 0) {
                    scrollHorizontallyBy = RecyclerView.this.mLayout.scrollHorizontallyBy(n, RecyclerView.this.mRecycler, RecyclerView.this.mState);
                    n3 = n - scrollHorizontallyBy;
                }
                n4 = 0;
                scrollVerticallyBy = 0;
                if (n2 != 0) {
                    scrollVerticallyBy = RecyclerView.this.mLayout.scrollVerticallyBy(n2, RecyclerView.this.mRecycler, RecyclerView.this.mState);
                    n4 = n2 - scrollVerticallyBy;
                }
                TraceCompat.endSection();
                RecyclerView.access$3300(RecyclerView.this);
                RecyclerView.access$3400(RecyclerView.this);
                RecyclerView.this.resumeRequestLayout(false);
                if (mSmoothScroller != null && !mSmoothScroller.isPendingInitialRun() && mSmoothScroller.isRunning()) {
                    final int itemCount = RecyclerView.this.mState.getItemCount();
                    if (itemCount == 0) {
                        mSmoothScroller.stop();
                    }
                    else if (mSmoothScroller.getTargetPosition() >= itemCount) {
                        mSmoothScroller.setTargetPosition(itemCount - 1);
                        mSmoothScroller.onAnimation(n - n3, n2 - n4);
                    }
                    else {
                        mSmoothScroller.onAnimation(n - n3, n2 - n4);
                    }
                }
            }
            if (!RecyclerView.access$3600(RecyclerView.this).isEmpty()) {
                RecyclerView.this.invalidate();
            }
            if (ViewCompat.getOverScrollMode((View)RecyclerView.this) != 2) {
                RecyclerView.access$3700(RecyclerView.this, n, n2);
            }
            if (n3 != 0 || n4 != 0) {
                final int n5 = (int)mScroller.getCurrVelocity();
                int n6 = 0;
                if (n3 != currX) {
                    if (n3 < 0) {
                        n6 = -n5;
                    }
                    else if (n3 > 0) {
                        n6 = n5;
                    }
                    else {
                        n6 = 0;
                    }
                }
                int n7 = 0;
                if (n4 != currY) {
                    if (n4 < 0) {
                        n7 = -n5;
                    }
                    else if (n4 > 0) {
                        n7 = n5;
                    }
                    else {
                        n7 = 0;
                    }
                }
                if (ViewCompat.getOverScrollMode((View)RecyclerView.this) != 2) {
                    RecyclerView.this.absorbGlows(n6, n7);
                }
                if ((n6 != 0 || n3 == currX || mScroller.getFinalX() == 0) && (n7 != 0 || n4 == currY || mScroller.getFinalY() == 0)) {
                    mScroller.abortAnimation();
                }
            }
            if (scrollHorizontallyBy != 0 || scrollVerticallyBy != 0) {
                RecyclerView.this.dispatchOnScrolled(scrollHorizontallyBy, scrollVerticallyBy);
            }
            if (!RecyclerView.access$3800(RecyclerView.this)) {
                RecyclerView.this.invalidate();
            }
            boolean b;
            if (n2 != 0 && RecyclerView.this.mLayout.canScrollVertically() && scrollVerticallyBy == n2) {
                b = true;
            }
            else {
                b = false;
            }
            boolean b2;
            if (n != 0 && RecyclerView.this.mLayout.canScrollHorizontally() && scrollHorizontallyBy == n) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            boolean b3;
            if ((n == 0 && n2 == 0) || b2 || b) {
                b3 = true;
            }
            else {
                b3 = false;
            }
            if (mScroller.isFinished() || !b3) {
                RecyclerView.access$3900(RecyclerView.this, 0);
            }
            else {
                this.postOnAnimation();
            }
        }
        if (mSmoothScroller != null) {
            if (mSmoothScroller.isPendingInitialRun()) {
                mSmoothScroller.onAnimation(0, 0);
            }
            if (!this.mReSchedulePostAnimationCallback) {
                mSmoothScroller.stop();
            }
        }
        this.enableRunOnAnimationRequests();
    }
    
    public void smoothScrollBy(final int n, final int n2) {
        this.smoothScrollBy(n, n2, 0, 0);
    }
    
    public void smoothScrollBy(final int n, final int n2, final int n3) {
        this.smoothScrollBy(n, n2, n3, RecyclerView.access$3000());
    }
    
    public void smoothScrollBy(final int n, final int n2, final int n3, final int n4) {
        this.smoothScrollBy(n, n2, this.computeScrollDuration(n, n2, n3, n4));
    }
    
    public void smoothScrollBy(final int n, final int n2, final int n3, final Interpolator mInterpolator) {
        if (this.mInterpolator != mInterpolator) {
            this.mInterpolator = mInterpolator;
            this.mScroller = ScrollerCompat.create(RecyclerView.this.getContext(), mInterpolator);
        }
        RecyclerView.access$3900(RecyclerView.this, 2);
        this.mLastFlingY = 0;
        this.mLastFlingX = 0;
        this.mScroller.startScroll(0, 0, n, n2, n3);
        this.postOnAnimation();
    }
    
    public void stop() {
        RecyclerView.this.removeCallbacks((Runnable)this);
        this.mScroller.abortAnimation();
    }
}
