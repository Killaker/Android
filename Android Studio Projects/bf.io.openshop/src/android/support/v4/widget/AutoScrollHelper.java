package android.support.v4.widget;

import android.content.res.*;
import android.util.*;
import android.os.*;
import android.view.*;
import android.support.v4.view.*;
import android.view.animation.*;

public abstract class AutoScrollHelper implements View$OnTouchListener
{
    private static final int DEFAULT_ACTIVATION_DELAY = 0;
    private static final int DEFAULT_EDGE_TYPE = 1;
    private static final float DEFAULT_MAXIMUM_EDGE = Float.MAX_VALUE;
    private static final int DEFAULT_MAXIMUM_VELOCITY_DIPS = 1575;
    private static final int DEFAULT_MINIMUM_VELOCITY_DIPS = 315;
    private static final int DEFAULT_RAMP_DOWN_DURATION = 500;
    private static final int DEFAULT_RAMP_UP_DURATION = 500;
    private static final float DEFAULT_RELATIVE_EDGE = 0.2f;
    private static final float DEFAULT_RELATIVE_VELOCITY = 1.0f;
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    private static final int HORIZONTAL = 0;
    public static final float NO_MAX = Float.MAX_VALUE;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;
    private static final int VERTICAL = 1;
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    private boolean mAnimating;
    private final Interpolator mEdgeInterpolator;
    private int mEdgeType;
    private boolean mEnabled;
    private boolean mExclusive;
    private float[] mMaximumEdges;
    private float[] mMaximumVelocity;
    private float[] mMinimumVelocity;
    private boolean mNeedsCancel;
    private boolean mNeedsReset;
    private float[] mRelativeEdges;
    private float[] mRelativeVelocity;
    private Runnable mRunnable;
    private final ClampedScroller mScroller;
    private final View mTarget;
    
    public AutoScrollHelper(final View mTarget) {
        this.mScroller = new ClampedScroller();
        this.mEdgeInterpolator = (Interpolator)new AccelerateInterpolator();
        this.mRelativeEdges = new float[] { 0.0f, 0.0f };
        this.mMaximumEdges = new float[] { Float.MAX_VALUE, Float.MAX_VALUE };
        this.mRelativeVelocity = new float[] { 0.0f, 0.0f };
        this.mMinimumVelocity = new float[] { 0.0f, 0.0f };
        this.mMaximumVelocity = new float[] { Float.MAX_VALUE, Float.MAX_VALUE };
        this.mTarget = mTarget;
        final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        final int n = (int)(0.5f + 1575.0f * displayMetrics.density);
        final int n2 = (int)(0.5f + 315.0f * displayMetrics.density);
        this.setMaximumVelocity(n, n);
        this.setMinimumVelocity(n2, n2);
        this.setEdgeType(1);
        this.setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
        this.setRelativeEdges(0.2f, 0.2f);
        this.setRelativeVelocity(1.0f, 1.0f);
        this.setActivationDelay(AutoScrollHelper.DEFAULT_ACTIVATION_DELAY);
        this.setRampUpDuration(500);
        this.setRampDownDuration(500);
    }
    
    private void cancelTargetTouch() {
        final long uptimeMillis = SystemClock.uptimeMillis();
        final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.mTarget.onTouchEvent(obtain);
        obtain.recycle();
    }
    
    private float computeTargetVelocity(final int n, final float n2, final float n3, final float n4) {
        final float edgeValue = this.getEdgeValue(this.mRelativeEdges[n], n3, this.mMaximumEdges[n], n2);
        if (edgeValue == 0.0f) {
            return 0.0f;
        }
        final float n5 = this.mRelativeVelocity[n];
        final float n6 = this.mMinimumVelocity[n];
        final float n7 = this.mMaximumVelocity[n];
        final float n8 = n5 * n4;
        if (edgeValue > 0.0f) {
            return constrain(edgeValue * n8, n6, n7);
        }
        return -constrain(n8 * -edgeValue, n6, n7);
    }
    
    private static float constrain(final float n, final float n2, final float n3) {
        if (n > n3) {
            return n3;
        }
        if (n < n2) {
            return n2;
        }
        return n;
    }
    
    private static int constrain(final int n, final int n2, final int n3) {
        if (n > n3) {
            return n3;
        }
        if (n < n2) {
            return n2;
        }
        return n;
    }
    
    private float constrainEdgeValue(final float n, final float n2) {
        if (n2 != 0.0f) {
            switch (this.mEdgeType) {
                default: {
                    return 0.0f;
                }
                case 0:
                case 1: {
                    if (n >= n2) {
                        break;
                    }
                    if (n >= 0.0f) {
                        return 1.0f - n / n2;
                    }
                    if (this.mAnimating && this.mEdgeType == 1) {
                        return 1.0f;
                    }
                    break;
                }
                case 2: {
                    if (n < 0.0f) {
                        return n / -n2;
                    }
                    break;
                }
            }
        }
        return 0.0f;
    }
    
    private float getEdgeValue(final float n, final float n2, final float n3, final float n4) {
        final float constrain = constrain(n * n2, 0.0f, n3);
        final float n5 = this.constrainEdgeValue(n2 - n4, constrain) - this.constrainEdgeValue(n4, constrain);
        float interpolation;
        if (n5 < 0.0f) {
            interpolation = -this.mEdgeInterpolator.getInterpolation(-n5);
        }
        else {
            final float n6 = fcmpl(n5, 0.0f);
            final float constrain2 = 0.0f;
            if (n6 <= 0) {
                return constrain2;
            }
            interpolation = this.mEdgeInterpolator.getInterpolation(n5);
        }
        return constrain(interpolation, -1.0f, 1.0f);
    }
    
    private void requestStop() {
        if (this.mNeedsReset) {
            this.mAnimating = false;
            return;
        }
        this.mScroller.requestStop();
    }
    
    private boolean shouldAnimate() {
        final ClampedScroller mScroller = this.mScroller;
        final int verticalDirection = mScroller.getVerticalDirection();
        final int horizontalDirection = mScroller.getHorizontalDirection();
        return (verticalDirection != 0 && this.canTargetScrollVertically(verticalDirection)) || (horizontalDirection != 0 && this.canTargetScrollHorizontally(horizontalDirection));
    }
    
    private void startAnimating() {
        if (this.mRunnable == null) {
            this.mRunnable = new ScrollAnimationRunnable();
        }
        this.mAnimating = true;
        this.mNeedsReset = true;
        if (!this.mAlreadyDelayed && this.mActivationDelay > 0) {
            ViewCompat.postOnAnimationDelayed(this.mTarget, this.mRunnable, this.mActivationDelay);
        }
        else {
            this.mRunnable.run();
        }
        this.mAlreadyDelayed = true;
    }
    
    public abstract boolean canTargetScrollHorizontally(final int p0);
    
    public abstract boolean canTargetScrollVertically(final int p0);
    
    public boolean isEnabled() {
        return this.mEnabled;
    }
    
    public boolean isExclusive() {
        return this.mExclusive;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        int mNeedsCancel = 1;
        if (!this.mEnabled) {
            return false;
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0: {
                this.mNeedsCancel = (mNeedsCancel != 0);
                this.mAlreadyDelayed = false;
            }
            case 2: {
                this.mScroller.setTargetVelocity(this.computeTargetVelocity(0, motionEvent.getX(), view.getWidth(), this.mTarget.getWidth()), this.computeTargetVelocity(mNeedsCancel, motionEvent.getY(), view.getHeight(), this.mTarget.getHeight()));
                if (!this.mAnimating && this.shouldAnimate()) {
                    this.startAnimating();
                    break;
                }
                break;
            }
            case 1:
            case 3: {
                this.requestStop();
                break;
            }
        }
        if (!this.mExclusive || !this.mAnimating) {
            mNeedsCancel = 0;
        }
        return mNeedsCancel != 0;
    }
    
    public abstract void scrollTargetBy(final int p0, final int p1);
    
    public AutoScrollHelper setActivationDelay(final int mActivationDelay) {
        this.mActivationDelay = mActivationDelay;
        return this;
    }
    
    public AutoScrollHelper setEdgeType(final int mEdgeType) {
        this.mEdgeType = mEdgeType;
        return this;
    }
    
    public AutoScrollHelper setEnabled(final boolean mEnabled) {
        if (this.mEnabled && !mEnabled) {
            this.requestStop();
        }
        this.mEnabled = mEnabled;
        return this;
    }
    
    public AutoScrollHelper setExclusive(final boolean mExclusive) {
        this.mExclusive = mExclusive;
        return this;
    }
    
    public AutoScrollHelper setMaximumEdges(final float n, final float n2) {
        this.mMaximumEdges[0] = n;
        this.mMaximumEdges[1] = n2;
        return this;
    }
    
    public AutoScrollHelper setMaximumVelocity(final float n, final float n2) {
        this.mMaximumVelocity[0] = n / 1000.0f;
        this.mMaximumVelocity[1] = n2 / 1000.0f;
        return this;
    }
    
    public AutoScrollHelper setMinimumVelocity(final float n, final float n2) {
        this.mMinimumVelocity[0] = n / 1000.0f;
        this.mMinimumVelocity[1] = n2 / 1000.0f;
        return this;
    }
    
    public AutoScrollHelper setRampDownDuration(final int rampDownDuration) {
        this.mScroller.setRampDownDuration(rampDownDuration);
        return this;
    }
    
    public AutoScrollHelper setRampUpDuration(final int rampUpDuration) {
        this.mScroller.setRampUpDuration(rampUpDuration);
        return this;
    }
    
    public AutoScrollHelper setRelativeEdges(final float n, final float n2) {
        this.mRelativeEdges[0] = n;
        this.mRelativeEdges[1] = n2;
        return this;
    }
    
    public AutoScrollHelper setRelativeVelocity(final float n, final float n2) {
        this.mRelativeVelocity[0] = n / 1000.0f;
        this.mRelativeVelocity[1] = n2 / 1000.0f;
        return this;
    }
    
    private static class ClampedScroller
    {
        private long mDeltaTime;
        private int mDeltaX;
        private int mDeltaY;
        private int mEffectiveRampDown;
        private int mRampDownDuration;
        private int mRampUpDuration;
        private long mStartTime;
        private long mStopTime;
        private float mStopValue;
        private float mTargetVelocityX;
        private float mTargetVelocityY;
        
        public ClampedScroller() {
            this.mStartTime = Long.MIN_VALUE;
            this.mStopTime = -1L;
            this.mDeltaTime = 0L;
            this.mDeltaX = 0;
            this.mDeltaY = 0;
        }
        
        private float getValueAt(final long n) {
            if (n < this.mStartTime) {
                return 0.0f;
            }
            if (this.mStopTime < 0L || n < this.mStopTime) {
                return 0.5f * constrain((n - this.mStartTime) / this.mRampUpDuration, 0.0f, 1.0f);
            }
            return 1.0f - this.mStopValue + this.mStopValue * constrain((n - this.mStopTime) / this.mEffectiveRampDown, 0.0f, 1.0f);
        }
        
        private float interpolateValue(final float n) {
            return n * (-4.0f * n) + 4.0f * n;
        }
        
        public void computeScrollDelta() {
            if (this.mDeltaTime == 0L) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            final long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            final float interpolateValue = this.interpolateValue(this.getValueAt(currentAnimationTimeMillis));
            final long n = currentAnimationTimeMillis - this.mDeltaTime;
            this.mDeltaTime = currentAnimationTimeMillis;
            this.mDeltaX = (int)(interpolateValue * n * this.mTargetVelocityX);
            this.mDeltaY = (int)(interpolateValue * n * this.mTargetVelocityY);
        }
        
        public int getDeltaX() {
            return this.mDeltaX;
        }
        
        public int getDeltaY() {
            return this.mDeltaY;
        }
        
        public int getHorizontalDirection() {
            return (int)(this.mTargetVelocityX / Math.abs(this.mTargetVelocityX));
        }
        
        public int getVerticalDirection() {
            return (int)(this.mTargetVelocityY / Math.abs(this.mTargetVelocityY));
        }
        
        public boolean isFinished() {
            return this.mStopTime > 0L && AnimationUtils.currentAnimationTimeMillis() > this.mStopTime + this.mEffectiveRampDown;
        }
        
        public void requestStop() {
            final long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mEffectiveRampDown = constrain((int)(currentAnimationTimeMillis - this.mStartTime), 0, this.mRampDownDuration);
            this.mStopValue = this.getValueAt(currentAnimationTimeMillis);
            this.mStopTime = currentAnimationTimeMillis;
        }
        
        public void setRampDownDuration(final int mRampDownDuration) {
            this.mRampDownDuration = mRampDownDuration;
        }
        
        public void setRampUpDuration(final int mRampUpDuration) {
            this.mRampUpDuration = mRampUpDuration;
        }
        
        public void setTargetVelocity(final float mTargetVelocityX, final float mTargetVelocityY) {
            this.mTargetVelocityX = mTargetVelocityX;
            this.mTargetVelocityY = mTargetVelocityY;
        }
        
        public void start() {
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.mStopTime = -1L;
            this.mDeltaTime = this.mStartTime;
            this.mStopValue = 0.5f;
            this.mDeltaX = 0;
            this.mDeltaY = 0;
        }
    }
    
    private class ScrollAnimationRunnable implements Runnable
    {
        @Override
        public void run() {
            if (!AutoScrollHelper.this.mAnimating) {
                return;
            }
            if (AutoScrollHelper.this.mNeedsReset) {
                AutoScrollHelper.this.mNeedsReset = false;
                AutoScrollHelper.this.mScroller.start();
            }
            final ClampedScroller access$300 = AutoScrollHelper.this.mScroller;
            if (access$300.isFinished() || !AutoScrollHelper.this.shouldAnimate()) {
                AutoScrollHelper.this.mAnimating = false;
                return;
            }
            if (AutoScrollHelper.this.mNeedsCancel) {
                AutoScrollHelper.this.mNeedsCancel = false;
                AutoScrollHelper.this.cancelTargetTouch();
            }
            access$300.computeScrollDelta();
            AutoScrollHelper.this.scrollTargetBy(access$300.getDeltaX(), access$300.getDeltaY());
            ViewCompat.postOnAnimation(AutoScrollHelper.this.mTarget, this);
        }
    }
}