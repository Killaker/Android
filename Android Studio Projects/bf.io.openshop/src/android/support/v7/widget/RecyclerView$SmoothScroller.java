package android.support.v7.widget;

import android.view.animation.*;
import android.content.*;
import android.util.*;
import android.support.v7.recyclerview.*;
import android.content.res.*;
import android.graphics.*;
import android.view.*;
import android.support.v4.view.*;
import java.util.*;
import android.support.annotation.*;
import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;
import android.os.*;

public abstract static class SmoothScroller
{
    private LayoutManager mLayoutManager;
    private boolean mPendingInitialRun;
    private RecyclerView mRecyclerView;
    private final Action mRecyclingAction;
    private boolean mRunning;
    private int mTargetPosition;
    private View mTargetView;
    
    public SmoothScroller() {
        this.mTargetPosition = -1;
        this.mRecyclingAction = new Action(0, 0);
    }
    
    private void onAnimation(final int n, final int n2) {
        final RecyclerView mRecyclerView = this.mRecyclerView;
        if (!this.mRunning || this.mTargetPosition == -1 || mRecyclerView == null) {
            this.stop();
        }
        this.mPendingInitialRun = false;
        if (this.mTargetView != null) {
            if (this.getChildPosition(this.mTargetView) == this.mTargetPosition) {
                this.onTargetFound(this.mTargetView, mRecyclerView.mState, this.mRecyclingAction);
                this.mRecyclingAction.runIfNecessary(mRecyclerView);
                this.stop();
            }
            else {
                Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                this.mTargetView = null;
            }
        }
        if (this.mRunning) {
            this.onSeekTargetStep(n, n2, mRecyclerView.mState, this.mRecyclingAction);
            final boolean hasJumpTarget = this.mRecyclingAction.hasJumpTarget();
            this.mRecyclingAction.runIfNecessary(mRecyclerView);
            if (hasJumpTarget) {
                if (!this.mRunning) {
                    this.stop();
                    return;
                }
                this.mPendingInitialRun = true;
                RecyclerView.access$5900(mRecyclerView).postOnAnimation();
            }
        }
    }
    
    public View findViewByPosition(final int n) {
        return this.mRecyclerView.mLayout.findViewByPosition(n);
    }
    
    public int getChildCount() {
        return this.mRecyclerView.mLayout.getChildCount();
    }
    
    public int getChildPosition(final View view) {
        return this.mRecyclerView.getChildLayoutPosition(view);
    }
    
    @Nullable
    public LayoutManager getLayoutManager() {
        return this.mLayoutManager;
    }
    
    public int getTargetPosition() {
        return this.mTargetPosition;
    }
    
    @Deprecated
    public void instantScrollToPosition(final int n) {
        this.mRecyclerView.scrollToPosition(n);
    }
    
    public boolean isPendingInitialRun() {
        return this.mPendingInitialRun;
    }
    
    public boolean isRunning() {
        return this.mRunning;
    }
    
    protected void normalize(final PointF pointF) {
        final double sqrt = Math.sqrt(pointF.x * pointF.x + pointF.y * pointF.y);
        pointF.x /= (float)sqrt;
        pointF.y /= (float)sqrt;
    }
    
    protected void onChildAttachedToWindow(final View mTargetView) {
        if (this.getChildPosition(mTargetView) == this.getTargetPosition()) {
            this.mTargetView = mTargetView;
        }
    }
    
    protected abstract void onSeekTargetStep(final int p0, final int p1, final State p2, final Action p3);
    
    protected abstract void onStart();
    
    protected abstract void onStop();
    
    protected abstract void onTargetFound(final View p0, final State p1, final Action p2);
    
    public void setTargetPosition(final int mTargetPosition) {
        this.mTargetPosition = mTargetPosition;
    }
    
    void start(final RecyclerView mRecyclerView, final LayoutManager mLayoutManager) {
        this.mRecyclerView = mRecyclerView;
        this.mLayoutManager = mLayoutManager;
        if (this.mTargetPosition == -1) {
            throw new IllegalArgumentException("Invalid target position");
        }
        this.mRecyclerView.mState.mTargetPosition = this.mTargetPosition;
        this.mRunning = true;
        this.mPendingInitialRun = true;
        this.mTargetView = this.findViewByPosition(this.getTargetPosition());
        this.onStart();
        RecyclerView.access$5900(this.mRecyclerView).postOnAnimation();
    }
    
    protected final void stop() {
        if (!this.mRunning) {
            return;
        }
        this.onStop();
        this.mRecyclerView.mState.mTargetPosition = -1;
        this.mTargetView = null;
        this.mTargetPosition = -1;
        this.mPendingInitialRun = false;
        this.mRunning = false;
        this.mLayoutManager.onSmoothScrollerStopped(this);
        this.mLayoutManager = null;
        this.mRecyclerView = null;
    }
    
    public static class Action
    {
        public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
        private boolean changed;
        private int consecutiveUpdates;
        private int mDuration;
        private int mDx;
        private int mDy;
        private Interpolator mInterpolator;
        private int mJumpToPosition;
        
        public Action(final int n, final int n2) {
            this(n, n2, Integer.MIN_VALUE, null);
        }
        
        public Action(final int n, final int n2, final int n3) {
            this(n, n2, n3, null);
        }
        
        public Action(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
            this.mJumpToPosition = -1;
            this.changed = false;
            this.consecutiveUpdates = 0;
            this.mDx = mDx;
            this.mDy = mDy;
            this.mDuration = mDuration;
            this.mInterpolator = mInterpolator;
        }
        
        private void runIfNecessary(final RecyclerView recyclerView) {
            if (this.mJumpToPosition >= 0) {
                final int mJumpToPosition = this.mJumpToPosition;
                this.mJumpToPosition = -1;
                RecyclerView.access$6200(recyclerView, mJumpToPosition);
                this.changed = false;
                return;
            }
            if (this.changed) {
                this.validate();
                if (this.mInterpolator == null) {
                    if (this.mDuration == Integer.MIN_VALUE) {
                        RecyclerView.access$5900(recyclerView).smoothScrollBy(this.mDx, this.mDy);
                    }
                    else {
                        RecyclerView.access$5900(recyclerView).smoothScrollBy(this.mDx, this.mDy, this.mDuration);
                    }
                }
                else {
                    RecyclerView.access$5900(recyclerView).smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                }
                ++this.consecutiveUpdates;
                if (this.consecutiveUpdates > 10) {
                    Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                }
                this.changed = false;
                return;
            }
            this.consecutiveUpdates = 0;
        }
        
        private void validate() {
            if (this.mInterpolator != null && this.mDuration < 1) {
                throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
            }
            if (this.mDuration < 1) {
                throw new IllegalStateException("Scroll duration must be a positive number");
            }
        }
        
        public int getDuration() {
            return this.mDuration;
        }
        
        public int getDx() {
            return this.mDx;
        }
        
        public int getDy() {
            return this.mDy;
        }
        
        public Interpolator getInterpolator() {
            return this.mInterpolator;
        }
        
        boolean hasJumpTarget() {
            return this.mJumpToPosition >= 0;
        }
        
        public void jumpTo(final int mJumpToPosition) {
            this.mJumpToPosition = mJumpToPosition;
        }
        
        public void setDuration(final int mDuration) {
            this.changed = true;
            this.mDuration = mDuration;
        }
        
        public void setDx(final int mDx) {
            this.changed = true;
            this.mDx = mDx;
        }
        
        public void setDy(final int mDy) {
            this.changed = true;
            this.mDy = mDy;
        }
        
        public void setInterpolator(final Interpolator mInterpolator) {
            this.changed = true;
            this.mInterpolator = mInterpolator;
        }
        
        public void update(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
            this.mDx = mDx;
            this.mDy = mDy;
            this.mDuration = mDuration;
            this.mInterpolator = mInterpolator;
            this.changed = true;
        }
    }
}
