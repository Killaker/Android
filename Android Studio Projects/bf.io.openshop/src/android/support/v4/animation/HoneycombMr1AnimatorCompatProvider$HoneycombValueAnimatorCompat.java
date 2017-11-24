package android.support.v4.animation;

import android.animation.*;
import android.view.*;

static class HoneycombValueAnimatorCompat implements ValueAnimatorCompat
{
    final Animator mWrapped;
    
    public HoneycombValueAnimatorCompat(final Animator mWrapped) {
        this.mWrapped = mWrapped;
    }
    
    @Override
    public void addListener(final AnimatorListenerCompat animatorListenerCompat) {
        this.mWrapped.addListener((Animator$AnimatorListener)new AnimatorListenerCompatWrapper(animatorListenerCompat, this));
    }
    
    @Override
    public void addUpdateListener(final AnimatorUpdateListenerCompat animatorUpdateListenerCompat) {
        if (this.mWrapped instanceof ValueAnimator) {
            ((ValueAnimator)this.mWrapped).addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
                public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                    animatorUpdateListenerCompat.onAnimationUpdate(HoneycombValueAnimatorCompat.this);
                }
            });
        }
    }
    
    @Override
    public void cancel() {
        this.mWrapped.cancel();
    }
    
    @Override
    public float getAnimatedFraction() {
        return ((ValueAnimator)this.mWrapped).getAnimatedFraction();
    }
    
    @Override
    public void setDuration(final long duration) {
        this.mWrapped.setDuration(duration);
    }
    
    @Override
    public void setTarget(final View target) {
        this.mWrapped.setTarget((Object)target);
    }
    
    @Override
    public void start() {
        this.mWrapped.start();
    }
}
