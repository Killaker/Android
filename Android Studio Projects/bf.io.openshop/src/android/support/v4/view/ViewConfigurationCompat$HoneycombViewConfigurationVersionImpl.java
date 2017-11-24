package android.support.v4.view;

import android.view.*;

static class HoneycombViewConfigurationVersionImpl extends FroyoViewConfigurationVersionImpl
{
    @Override
    public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
        return false;
    }
}
