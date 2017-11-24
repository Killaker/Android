package android.support.v4.view;

import android.view.*;
import android.view.animation.*;

static class JBMr2ViewPropertyAnimatorCompatImpl extends JBViewPropertyAnimatorCompatImpl
{
    @Override
    public Interpolator getInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return ViewPropertyAnimatorCompatJellybeanMr2.getInterpolator(view);
    }
}
