package android.support.design.widget;

import android.animation.*;

class ValueAnimatorCompatImplHoneycombMr1$2 extends android.animation.AnimatorListenerAdapter {
    final /* synthetic */ AnimatorListenerProxy val$listener;
    
    public void onAnimationCancel(final Animator animator) {
        this.val$listener.onAnimationCancel();
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.val$listener.onAnimationEnd();
    }
    
    public void onAnimationStart(final Animator animator) {
        this.val$listener.onAnimationStart();
    }
}