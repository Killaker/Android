package android.support.v4.graphics.drawable;

import android.graphics.drawable.*;

static class EclairDrawableImpl extends BaseDrawableImpl
{
    @Override
    public Drawable wrap(final Drawable drawable) {
        return DrawableCompatEclair.wrapForTinting(drawable);
    }
}
