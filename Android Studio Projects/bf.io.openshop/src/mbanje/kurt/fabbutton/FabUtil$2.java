package mbanje.kurt.fabbutton;

import android.view.*;
import android.animation.*;

static final class FabUtil$2 implements ValueAnimator$AnimatorUpdateListener {
    final /* synthetic */ OnFabValueCallback val$callback;
    final /* synthetic */ View val$view;
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        this.val$callback.onIndeterminateValuesChanged(-1.0f, -1.0f, -1.0f, (float)valueAnimator.getAnimatedValue());
        this.val$view.invalidate();
    }
}