package android.support.v4.view;

import android.view.*;

static class ICSMr1ViewCompatImpl extends ICSViewCompatImpl
{
    @Override
    public boolean hasOnClickListeners(final View view) {
        return ViewCompatICSMr1.hasOnClickListeners(view);
    }
}
