package android.support.v4.graphics.drawable;

import android.graphics.drawable.*;

static class HoneycombDrawableImpl extends EclairDrawableImpl
{
    @Override
    public void jumpToCurrentState(final Drawable drawable) {
        DrawableCompatHoneycomb.jumpToCurrentState(drawable);
    }
    
    @Override
    public Drawable wrap(final Drawable drawable) {
        return DrawableCompatHoneycomb.wrapForTinting(drawable);
    }
}
