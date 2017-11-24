package android.support.v4.view;

import android.view.*;

static class IcsViewConfigurationVersionImpl extends HoneycombViewConfigurationVersionImpl
{
    @Override
    public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
        return ViewConfigurationCompatICS.hasPermanentMenuKey(viewConfiguration);
    }
}
