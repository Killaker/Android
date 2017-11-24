package android.support.v4.widget;

import android.view.animation.*;

class MaterialProgressDrawable$2 implements Animation$AnimationListener {
    final /* synthetic */ Ring val$ring;
    
    public void onAnimationEnd(final Animation animation) {
    }
    
    public void onAnimationRepeat(final Animation animation) {
        this.val$ring.storeOriginals();
        this.val$ring.goToNextColor();
        this.val$ring.setStartTrim(this.val$ring.getEndTrim());
        if (MaterialProgressDrawable.this.mFinishing) {
            MaterialProgressDrawable.this.mFinishing = false;
            animation.setDuration(1332L);
            this.val$ring.setShowArrow(false);
            return;
        }
        MaterialProgressDrawable.access$402(MaterialProgressDrawable.this, (1.0f + MaterialProgressDrawable.access$400(MaterialProgressDrawable.this)) % 5.0f);
    }
    
    public void onAnimationStart(final Animation animation) {
        MaterialProgressDrawable.access$402(MaterialProgressDrawable.this, 0.0f);
    }
}