package android.support.v4.view;

import android.view.*;
import android.animation.*;

static final class ViewPropertyAnimatorCompatICS$1 extends AnimatorListenerAdapter {
    final /* synthetic */ ViewPropertyAnimatorListener val$listener;
    final /* synthetic */ View val$view;
    
    public void onAnimationCancel(final Animator animator) {
        this.val$listener.onAnimationCancel(this.val$view);
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.val$listener.onAnimationEnd(this.val$view);
    }
    
    public void onAnimationStart(final Animator animator) {
        this.val$listener.onAnimationStart(this.val$view);
    }
}