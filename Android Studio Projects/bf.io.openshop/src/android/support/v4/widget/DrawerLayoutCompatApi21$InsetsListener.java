package android.support.v4.widget;

import android.view.*;

static class InsetsListener implements View$OnApplyWindowInsetsListener
{
    public WindowInsets onApplyWindowInsets(final View view, final WindowInsets windowInsets) {
        ((DrawerLayoutImpl)view).setChildInsets(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
        return windowInsets.consumeSystemWindowInsets();
    }
}
