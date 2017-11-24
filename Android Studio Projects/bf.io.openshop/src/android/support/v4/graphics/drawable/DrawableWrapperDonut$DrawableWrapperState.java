package android.support.v4.graphics.drawable;

import android.graphics.*;
import android.support.annotation.*;
import android.content.res.*;
import android.graphics.drawable.*;

protected abstract static class DrawableWrapperState extends Drawable$ConstantState
{
    int mChangingConfigurations;
    Drawable$ConstantState mDrawableState;
    ColorStateList mTint;
    PorterDuff$Mode mTintMode;
    
    DrawableWrapperState(@Nullable final DrawableWrapperState drawableWrapperState, @Nullable final Resources resources) {
        this.mTint = null;
        this.mTintMode = DrawableWrapperDonut.DEFAULT_TINT_MODE;
        if (drawableWrapperState != null) {
            this.mChangingConfigurations = drawableWrapperState.mChangingConfigurations;
            this.mDrawableState = drawableWrapperState.mDrawableState;
            this.mTint = drawableWrapperState.mTint;
            this.mTintMode = drawableWrapperState.mTintMode;
        }
    }
    
    boolean canConstantState() {
        return this.mDrawableState != null;
    }
    
    public int getChangingConfigurations() {
        final int mChangingConfigurations = this.mChangingConfigurations;
        int changingConfigurations;
        if (this.mDrawableState != null) {
            changingConfigurations = this.mDrawableState.getChangingConfigurations();
        }
        else {
            changingConfigurations = 0;
        }
        return changingConfigurations | mChangingConfigurations;
    }
    
    public Drawable newDrawable() {
        return this.newDrawable(null);
    }
    
    public abstract Drawable newDrawable(@Nullable final Resources p0);
}
