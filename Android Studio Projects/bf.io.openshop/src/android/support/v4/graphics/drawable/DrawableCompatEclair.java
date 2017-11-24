package android.support.v4.graphics.drawable;

import android.graphics.drawable.*;

class DrawableCompatEclair
{
    public static Drawable wrapForTinting(Drawable drawable) {
        if (!(drawable instanceof DrawableWrapperEclair)) {
            drawable = new DrawableWrapperEclair(drawable);
        }
        return drawable;
    }
}
