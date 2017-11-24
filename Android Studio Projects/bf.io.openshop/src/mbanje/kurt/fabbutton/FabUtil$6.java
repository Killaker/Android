package mbanje.kurt.fabbutton;

import android.animation.*;

static final class FabUtil$6 implements ValueAnimator$AnimatorUpdateListener {
    final /* synthetic */ OnFabValueCallback val$callback;
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        this.val$callback.onIndeterminateValuesChanged(-1.0f, (float)valueAnimator.getAnimatedValue(), -1.0f, -1.0f);
    }
}