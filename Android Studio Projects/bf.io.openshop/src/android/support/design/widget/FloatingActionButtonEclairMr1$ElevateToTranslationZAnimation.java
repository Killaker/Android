package android.support.design.widget;

private class ElevateToTranslationZAnimation extends BaseShadowAnimation
{
    @Override
    protected float getTargetShadowSize() {
        return FloatingActionButtonEclairMr1.this.mElevation + FloatingActionButtonEclairMr1.this.mPressedTranslationZ;
    }
}
