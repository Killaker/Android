package android.support.graphics.drawable;

import android.graphics.drawable.*;

class AnimatedVectorDrawableCompat$1 implements Drawable$Callback {
    public void invalidateDrawable(final Drawable drawable) {
        AnimatedVectorDrawableCompat.this.invalidateSelf();
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        AnimatedVectorDrawableCompat.this.scheduleSelf(runnable, n);
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        AnimatedVectorDrawableCompat.this.unscheduleSelf(runnable);
    }
}