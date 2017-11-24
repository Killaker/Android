package android.support.v7.widget;

import android.view.*;
import android.support.v4.view.*;

class DefaultItemAnimator$8 extends VpaListenerAdapter {
    final /* synthetic */ ChangeInfo val$changeInfo;
    final /* synthetic */ View val$newView;
    final /* synthetic */ ViewPropertyAnimatorCompat val$newViewAnimation;
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$newViewAnimation.setListener(null);
        ViewCompat.setAlpha(this.val$newView, 1.0f);
        ViewCompat.setTranslationX(this.val$newView, 0.0f);
        ViewCompat.setTranslationY(this.val$newView, 0.0f);
        DefaultItemAnimator.this.dispatchChangeFinished(this.val$changeInfo.newHolder, false);
        DefaultItemAnimator.access$1300(DefaultItemAnimator.this).remove(this.val$changeInfo.newHolder);
        DefaultItemAnimator.access$800(DefaultItemAnimator.this);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        DefaultItemAnimator.this.dispatchChangeStarting(this.val$changeInfo.newHolder, false);
    }
}