package android.support.v4.view;

import android.view.*;

static class GingerbreadMotionEventVersionImpl extends EclairMotionEventVersionImpl
{
    @Override
    public int getSource(final MotionEvent motionEvent) {
        return MotionEventCompatGingerbread.getSource(motionEvent);
    }
}
