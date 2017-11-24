package android.support.v7.widget.helper;

import android.support.v7.widget.*;
import android.support.v4.animation.*;
import android.support.v4.view.*;

private class RecoverAnimation implements AnimatorListenerCompat
{
    final int mActionState;
    private final int mAnimationType;
    private boolean mEnded;
    private float mFraction;
    public boolean mIsPendingCleanup;
    boolean mOverridden;
    final float mStartDx;
    final float mStartDy;
    final float mTargetX;
    final float mTargetY;
    private final ValueAnimatorCompat mValueAnimator;
    final ViewHolder mViewHolder;
    float mX;
    float mY;
    
    public RecoverAnimation(final ViewHolder mViewHolder, final int mAnimationType, final int mActionState, final float mStartDx, final float mStartDy, final float mTargetX, final float mTargetY) {
        this.mOverridden = false;
        this.mEnded = false;
        this.mActionState = mActionState;
        this.mAnimationType = mAnimationType;
        this.mViewHolder = mViewHolder;
        this.mStartDx = mStartDx;
        this.mStartDy = mStartDy;
        this.mTargetX = mTargetX;
        this.mTargetY = mTargetY;
        (this.mValueAnimator = AnimatorCompatHelper.emptyValueAnimator()).addUpdateListener(new AnimatorUpdateListenerCompat() {
            @Override
            public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                RecoverAnimation.this.setFraction(valueAnimatorCompat.getAnimatedFraction());
            }
        });
        this.mValueAnimator.setTarget(mViewHolder.itemView);
        this.mValueAnimator.addListener(this);
        this.setFraction(0.0f);
    }
    
    public void cancel() {
        this.mValueAnimator.cancel();
    }
    
    @Override
    public void onAnimationCancel(final ValueAnimatorCompat valueAnimatorCompat) {
        this.setFraction(1.0f);
    }
    
    @Override
    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
        if (!this.mEnded) {
            this.mViewHolder.setIsRecyclable(true);
        }
        this.mEnded = true;
    }
    
    @Override
    public void onAnimationRepeat(final ValueAnimatorCompat valueAnimatorCompat) {
    }
    
    @Override
    public void onAnimationStart(final ValueAnimatorCompat valueAnimatorCompat) {
    }
    
    public void setDuration(final long duration) {
        this.mValueAnimator.setDuration(duration);
    }
    
    public void setFraction(final float mFraction) {
        this.mFraction = mFraction;
    }
    
    public void start() {
        this.mViewHolder.setIsRecyclable(false);
        this.mValueAnimator.start();
    }
    
    public void update() {
        if (this.mStartDx == this.mTargetX) {
            this.mX = ViewCompat.getTranslationX(this.mViewHolder.itemView);
        }
        else {
            this.mX = this.mStartDx + this.mFraction * (this.mTargetX - this.mStartDx);
        }
        if (this.mStartDy == this.mTargetY) {
            this.mY = ViewCompat.getTranslationY(this.mViewHolder.itemView);
            return;
        }
        this.mY = this.mStartDy + this.mFraction * (this.mTargetY - this.mStartDy);
    }
}
