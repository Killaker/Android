package android.support.v4.graphics.drawable;

import android.support.annotation.*;
import android.content.res.*;
import android.graphics.drawable.*;

private static class DrawableWrapperStateKitKat extends DrawableWrapperState
{
    DrawableWrapperStateKitKat(@Nullable final DrawableWrapperState drawableWrapperState, @Nullable final Resources resources) {
        super(drawableWrapperState, resources);
    }
    
    @Override
    public Drawable newDrawable(@Nullable final Resources resources) {
        return new DrawableWrapperKitKat(this, resources);
    }
}
