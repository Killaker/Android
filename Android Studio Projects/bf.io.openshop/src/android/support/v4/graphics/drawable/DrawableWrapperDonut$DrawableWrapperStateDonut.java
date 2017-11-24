package android.support.v4.graphics.drawable;

import android.support.annotation.*;
import android.content.res.*;
import android.graphics.drawable.*;

private static class DrawableWrapperStateDonut extends DrawableWrapperState
{
    DrawableWrapperStateDonut(@Nullable final DrawableWrapperState drawableWrapperState, @Nullable final Resources resources) {
        super(drawableWrapperState, resources);
    }
    
    @Override
    public Drawable newDrawable(@Nullable final Resources resources) {
        return new DrawableWrapperDonut((DrawableWrapperState)this, resources);
    }
}
