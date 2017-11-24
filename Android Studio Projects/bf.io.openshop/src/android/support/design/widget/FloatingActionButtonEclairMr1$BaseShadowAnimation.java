package android.support.design.widget;

import android.view.animation.*;

private abstract class BaseShadowAnimation extends Animation
{
    private float mShadowSizeDiff;
    private float mShadowSizeStart;
    
    protected void applyTransformation(final float n, final Transformation transformation) {
        FloatingActionButtonEclairMr1.this.mShadowDrawable.setShadowSize(this.mShadowSizeStart + n * this.mShadowSizeDiff);
    }
    
    protected abstract float getTargetShadowSize();
    
    public void reset() {
        super.reset();
        this.mShadowSizeStart = FloatingActionButtonEclairMr1.this.mShadowDrawable.getShadowSize();
        this.mShadowSizeDiff = this.getTargetShadowSize() - this.mShadowSizeStart;
    }
}
