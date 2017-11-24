package android.support.graphics.drawable;

import android.graphics.drawable.*;
import android.content.res.*;

private static class VectorDrawableDelegateState extends Drawable$ConstantState
{
    private final Drawable$ConstantState mDelegateState;
    
    public VectorDrawableDelegateState(final Drawable$ConstantState mDelegateState) {
        this.mDelegateState = mDelegateState;
    }
    
    public boolean canApplyTheme() {
        return this.mDelegateState.canApplyTheme();
    }
    
    public int getChangingConfigurations() {
        return this.mDelegateState.getChangingConfigurations();
    }
    
    public Drawable newDrawable() {
        final VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat((VectorDrawableCompat$1)null);
        vectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable();
        return vectorDrawableCompat;
    }
    
    public Drawable newDrawable(final Resources resources) {
        final VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat((VectorDrawableCompat$1)null);
        vectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(resources);
        return vectorDrawableCompat;
    }
    
    public Drawable newDrawable(final Resources resources, final Resources$Theme resources$Theme) {
        final VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat((VectorDrawableCompat$1)null);
        vectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(resources, resources$Theme);
        return vectorDrawableCompat;
    }
}
