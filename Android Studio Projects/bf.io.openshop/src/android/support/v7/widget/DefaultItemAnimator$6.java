package android.support.v7.widget;

import android.view.*;
import android.support.v4.view.*;

class DefaultItemAnimator$6 extends VpaListenerAdapter {
    final /* synthetic */ ViewPropertyAnimatorCompat val$animation;
    final /* synthetic */ int val$deltaX;
    final /* synthetic */ int val$deltaY;
    final /* synthetic */ ViewHolder val$holder;
    
    @Override
    public void onAnimationCancel(final View view) {
        if (this.val$deltaX != 0) {
            ViewCompat.setTranslationX(view, 0.0f);
        }
        if (this.val$deltaY != 0) {
            ViewCompat.setTranslationY(view, 0.0f);
        }
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$animation.setListener(null);
        DefaultItemAnimator.this.dispatchMoveFinished(this.val$holder);
        DefaultItemAnimator.access$1100(DefaultItemAnimator.this).remove(this.val$holder);
        DefaultItemAnimator.access$800(DefaultItemAnimator.this);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        DefaultItemAnimator.this.dispatchMoveStarting(this.val$holder);
    }
}