package android.support.design.widget;

import android.view.*;
import android.support.v4.view.*;

private class ApplyInsetsListener implements OnApplyWindowInsetsListener
{
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return CoordinatorLayout.access$200(CoordinatorLayout.this, windowInsetsCompat);
    }
}
