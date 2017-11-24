package android.support.design.widget;

private class ResetElevationAnimation extends BaseShadowAnimation
{
    @Override
    protected float getTargetShadowSize() {
        return FloatingActionButtonEclairMr1.this.mElevation;
    }
}
