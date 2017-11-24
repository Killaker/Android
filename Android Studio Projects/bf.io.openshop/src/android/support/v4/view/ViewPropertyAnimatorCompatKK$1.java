package android.support.v4.view;

import android.view.*;
import android.animation.*;

static final class ViewPropertyAnimatorCompatKK$1 implements ValueAnimator$AnimatorUpdateListener {
    final /* synthetic */ ViewPropertyAnimatorUpdateListener val$listener;
    final /* synthetic */ View val$view;
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        this.val$listener.onAnimationUpdate(this.val$view);
    }
}