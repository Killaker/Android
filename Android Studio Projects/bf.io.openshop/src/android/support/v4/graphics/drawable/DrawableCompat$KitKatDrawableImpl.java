package android.support.v4.graphics.drawable;

import android.graphics.drawable.*;

static class KitKatDrawableImpl extends JellybeanMr1DrawableImpl
{
    @Override
    public int getAlpha(final Drawable drawable) {
        return DrawableCompatKitKat.getAlpha(drawable);
    }
    
    @Override
    public boolean isAutoMirrored(final Drawable drawable) {
        return DrawableCompatKitKat.isAutoMirrored(drawable);
    }
    
    @Override
    public void setAutoMirrored(final Drawable drawable, final boolean b) {
        DrawableCompatKitKat.setAutoMirrored(drawable, b);
    }
    
    @Override
    public Drawable wrap(final Drawable drawable) {
        return DrawableCompatKitKat.wrapForTinting(drawable);
    }
}
