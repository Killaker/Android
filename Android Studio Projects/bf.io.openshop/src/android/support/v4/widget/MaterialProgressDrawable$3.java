package android.support.v4.widget;

import android.graphics.drawable.*;

class MaterialProgressDrawable$3 implements Drawable$Callback {
    public void invalidateDrawable(final Drawable drawable) {
        MaterialProgressDrawable.this.invalidateSelf();
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        MaterialProgressDrawable.this.scheduleSelf(runnable, n);
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        MaterialProgressDrawable.this.unscheduleSelf(runnable);
    }
}