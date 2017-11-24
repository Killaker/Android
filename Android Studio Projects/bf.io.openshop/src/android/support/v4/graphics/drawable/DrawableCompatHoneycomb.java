package android.support.v4.graphics.drawable;

import android.graphics.drawable.*;

class DrawableCompatHoneycomb
{
    public static void jumpToCurrentState(final Drawable drawable) {
        drawable.jumpToCurrentState();
    }
    
    public static Drawable wrapForTinting(Drawable drawable) {
        if (!(drawable instanceof DrawableWrapperHoneycomb)) {
            drawable = new DrawableWrapperHoneycomb(drawable);
        }
        return drawable;
    }
}
