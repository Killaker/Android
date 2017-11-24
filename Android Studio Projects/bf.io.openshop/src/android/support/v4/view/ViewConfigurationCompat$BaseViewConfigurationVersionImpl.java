package android.support.v4.view;

import android.view.*;

static class BaseViewConfigurationVersionImpl implements ViewConfigurationVersionImpl
{
    @Override
    public int getScaledPagingTouchSlop(final ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledTouchSlop();
    }
    
    @Override
    public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
        return true;
    }
}
