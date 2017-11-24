package android.support.v4.view;

import android.view.*;

static class ViewGroupCompatHCImpl extends ViewGroupCompatStubImpl
{
    @Override
    public void setMotionEventSplittingEnabled(final ViewGroup viewGroup, final boolean b) {
        ViewGroupCompatHC.setMotionEventSplittingEnabled(viewGroup, b);
    }
}
