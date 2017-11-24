package android.support.v7.widget;

import android.view.*;
import android.support.v4.view.*;

class DefaultItemAnimator$5 extends VpaListenerAdapter {
    final /* synthetic */ ViewPropertyAnimatorCompat val$animation;
    final /* synthetic */ ViewHolder val$holder;
    
    @Override
    public void onAnimationCancel(final View view) {
        ViewCompat.setAlpha(view, 1.0f);
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$animation.setListener(null);
        DefaultItemAnimator.this.dispatchAddFinished(this.val$holder);
        DefaultItemAnimator.access$900(DefaultItemAnimator.this).remove(this.val$holder);
        DefaultItemAnimator.access$800(DefaultItemAnimator.this);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        DefaultItemAnimator.this.dispatchAddStarting(this.val$holder);
    }
}