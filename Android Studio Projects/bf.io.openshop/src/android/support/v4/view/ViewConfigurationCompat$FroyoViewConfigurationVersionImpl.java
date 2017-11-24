package android.support.v4.view;

import android.view.*;

static class FroyoViewConfigurationVersionImpl extends BaseViewConfigurationVersionImpl
{
    @Override
    public int getScaledPagingTouchSlop(final ViewConfiguration viewConfiguration) {
        return ViewConfigurationCompatFroyo.getScaledPagingTouchSlop(viewConfiguration);
    }
}
