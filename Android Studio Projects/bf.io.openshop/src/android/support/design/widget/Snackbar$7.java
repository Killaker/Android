package android.support.design.widget;

import android.support.v4.view.*;
import android.view.*;

class Snackbar$7 extends ViewPropertyAnimatorListenerAdapter {
    @Override
    public void onAnimationEnd(final View view) {
        Snackbar.access$700(Snackbar.this);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        if (!Snackbar.access$600(Snackbar.this).isEnabled()) {
            Snackbar.access$500(Snackbar.this).animateChildrenIn(70, 180);
        }
    }
}