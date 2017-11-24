package android.support.design.widget;

import android.os.*;

static final class ViewUtils$1 implements Creator {
    @Override
    public ValueAnimatorCompat createAnimator() {
        Impl impl;
        if (Build$VERSION.SDK_INT >= 12) {
            impl = new ValueAnimatorCompatImplHoneycombMr1();
        }
        else {
            impl = new ValueAnimatorCompatImplEclairMr1();
        }
        return new ValueAnimatorCompat(impl);
    }
}