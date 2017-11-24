package android.support.v7.widget.helper;

import android.support.v4.animation.*;

class ItemTouchHelper$RecoverAnimation$1 implements AnimatorUpdateListenerCompat {
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        RecoverAnimation.this.setFraction(valueAnimatorCompat.getAnimatedFraction());
    }
}