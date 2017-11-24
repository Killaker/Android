package android.support.graphics.drawable;

import android.graphics.drawable.*;
import android.content.res.*;

private static class AnimatedVectorDrawableDelegateState extends Drawable$ConstantState
{
    private final Drawable$ConstantState mDelegateState;
    
    public AnimatedVectorDrawableDelegateState(final Drawable$ConstantState mDelegateState) {
        this.mDelegateState = mDelegateState;
    }
    
    public boolean canApplyTheme() {
        return this.mDelegateState.canApplyTheme();
    }
    
    public int getChangingConfigurations() {
        return this.mDelegateState.getChangingConfigurations();
    }
    
    public Drawable newDrawable() {
        final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat((AnimatedVectorDrawableCompat$1)null);
        (animatedVectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable()).setCallback(AnimatedVectorDrawableCompat.access$100(animatedVectorDrawableCompat));
        return animatedVectorDrawableCompat;
    }
    
    public Drawable newDrawable(final Resources resources) {
        final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat((AnimatedVectorDrawableCompat$1)null);
        (animatedVectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(resources)).setCallback(AnimatedVectorDrawableCompat.access$100(animatedVectorDrawableCompat));
        return animatedVectorDrawableCompat;
    }
    
    public Drawable newDrawable(final Resources resources, final Resources$Theme resources$Theme) {
        final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat((AnimatedVectorDrawableCompat$1)null);
        (animatedVectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(resources, resources$Theme)).setCallback(AnimatedVectorDrawableCompat.access$100(animatedVectorDrawableCompat));
        return animatedVectorDrawableCompat;
    }
}
