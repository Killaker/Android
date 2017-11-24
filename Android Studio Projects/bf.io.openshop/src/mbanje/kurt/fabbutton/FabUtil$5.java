package mbanje.kurt.fabbutton;

import android.view.*;
import android.animation.*;

static final class FabUtil$5 implements ValueAnimator$AnimatorUpdateListener {
    final /* synthetic */ OnFabValueCallback val$callback;
    final /* synthetic */ float val$start;
    final /* synthetic */ View val$view;
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        final float floatValue = (float)valueAnimator.getAnimatedValue();
        this.val$callback.onIndeterminateValuesChanged(285.0f - floatValue + this.val$start, -1.0f, floatValue, -1.0f);
        this.val$view.invalidate();
    }
}