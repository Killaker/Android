package android.support.v4.view;

static class HoneycombKeyEventVersionImpl extends EclairKeyEventVersionImpl
{
    @Override
    public boolean metaStateHasModifiers(final int n, final int n2) {
        return KeyEventCompatHoneycomb.metaStateHasModifiers(n, n2);
    }
    
    @Override
    public boolean metaStateHasNoModifiers(final int n) {
        return KeyEventCompatHoneycomb.metaStateHasNoModifiers(n);
    }
    
    @Override
    public int normalizeMetaState(final int n) {
        return KeyEventCompatHoneycomb.normalizeMetaState(n);
    }
}
