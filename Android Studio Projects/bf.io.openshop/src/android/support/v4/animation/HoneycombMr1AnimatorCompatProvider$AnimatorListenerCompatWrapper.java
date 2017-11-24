package android.support.v4.animation;

import android.animation.*;

static class AnimatorListenerCompatWrapper implements Animator$AnimatorListener
{
    final ValueAnimatorCompat mValueAnimatorCompat;
    final AnimatorListenerCompat mWrapped;
    
    public AnimatorListenerCompatWrapper(final AnimatorListenerCompat mWrapped, final ValueAnimatorCompat mValueAnimatorCompat) {
        this.mWrapped = mWrapped;
        this.mValueAnimatorCompat = mValueAnimatorCompat;
    }
    
    public void onAnimationCancel(final Animator animator) {
        this.mWrapped.onAnimationCancel(this.mValueAnimatorCompat);
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.mWrapped.onAnimationEnd(this.mValueAnimatorCompat);
    }
    
    public void onAnimationRepeat(final Animator animator) {
        this.mWrapped.onAnimationRepeat(this.mValueAnimatorCompat);
    }
    
    public void onAnimationStart(final Animator animator) {
        this.mWrapped.onAnimationStart(this.mValueAnimatorCompat);
    }
}
