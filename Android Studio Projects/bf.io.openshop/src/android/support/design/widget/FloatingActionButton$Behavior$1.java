package android.support.design.widget;

import android.support.v4.view.*;
import android.view.*;

class FloatingActionButton$Behavior$1 implements AnimatorUpdateListener {
    final /* synthetic */ FloatingActionButton val$fab;
    
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        ViewCompat.setTranslationY((View)this.val$fab, valueAnimatorCompat.getAnimatedFloatValue());
    }
}