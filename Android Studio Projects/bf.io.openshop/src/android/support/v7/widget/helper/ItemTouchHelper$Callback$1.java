package android.support.v7.widget.helper;

import android.view.animation.*;

static final class ItemTouchHelper$Callback$1 implements Interpolator {
    public float getInterpolation(final float n) {
        return n * (n * (n * (n * n)));
    }
}