package android.support.design.widget;

import android.view.animation.*;

static class Tuple
{
    final Animation mAnimation;
    final int[] mSpecs;
    
    private Tuple(final int[] mSpecs, final Animation mAnimation) {
        this.mSpecs = mSpecs;
        this.mAnimation = mAnimation;
    }
    
    Animation getAnimation() {
        return this.mAnimation;
    }
    
    int[] getSpecs() {
        return this.mSpecs;
    }
}
