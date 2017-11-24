package android.support.v4.widget;

static class EdgeEffectLollipopImpl extends EdgeEffectIcsImpl
{
    @Override
    public boolean onPull(final Object o, final float n, final float n2) {
        return EdgeEffectCompatLollipop.onPull(o, n, n2);
    }
}
