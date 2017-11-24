package android.support.v7.widget;

import android.view.*;
import android.support.v4.view.*;

class DefaultItemAnimator$7 extends VpaListenerAdapter {
    final /* synthetic */ ChangeInfo val$changeInfo;
    final /* synthetic */ ViewPropertyAnimatorCompat val$oldViewAnim;
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$oldViewAnim.setListener(null);
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.setTranslationX(view, 0.0f);
        ViewCompat.setTranslationY(view, 0.0f);
        DefaultItemAnimator.this.dispatchChangeFinished(this.val$changeInfo.oldHolder, true);
        DefaultItemAnimator.access$1300(DefaultItemAnimator.this).remove(this.val$changeInfo.oldHolder);
        DefaultItemAnimator.access$800(DefaultItemAnimator.this);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        DefaultItemAnimator.this.dispatchChangeStarting(this.val$changeInfo.oldHolder, true);
    }
}