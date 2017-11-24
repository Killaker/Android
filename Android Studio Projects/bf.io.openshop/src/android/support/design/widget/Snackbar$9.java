package android.support.design.widget;

import android.support.v4.view.*;
import android.view.*;

class Snackbar$9 extends ViewPropertyAnimatorListenerAdapter {
    boolean mEndCalled = false;
    final /* synthetic */ int val$event;
    
    @Override
    public void onAnimationEnd(final View view) {
        Snackbar.access$300(Snackbar.this, this.val$event);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        if (!Snackbar.access$600(Snackbar.this).isEnabled()) {
            Snackbar.access$500(Snackbar.this).animateChildrenOut(0, 180);
        }
    }
}