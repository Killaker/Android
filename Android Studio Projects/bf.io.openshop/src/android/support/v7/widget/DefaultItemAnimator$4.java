package android.support.v7.widget;

import android.view.*;
import android.support.v4.view.*;

class DefaultItemAnimator$4 extends VpaListenerAdapter {
    final /* synthetic */ ViewPropertyAnimatorCompat val$animation;
    final /* synthetic */ ViewHolder val$holder;
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$animation.setListener(null);
        ViewCompat.setAlpha(view, 1.0f);
        DefaultItemAnimator.this.dispatchRemoveFinished(this.val$holder);
        DefaultItemAnimator.access$700(DefaultItemAnimator.this).remove(this.val$holder);
        DefaultItemAnimator.access$800(DefaultItemAnimator.this);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        DefaultItemAnimator.this.dispatchRemoveStarting(this.val$holder);
    }
}