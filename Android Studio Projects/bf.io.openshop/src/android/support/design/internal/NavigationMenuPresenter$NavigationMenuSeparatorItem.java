package android.support.design.internal;

private static class NavigationMenuSeparatorItem implements NavigationMenuItem
{
    private final int mPaddingBottom;
    private final int mPaddingTop;
    
    public NavigationMenuSeparatorItem(final int mPaddingTop, final int mPaddingBottom) {
        this.mPaddingTop = mPaddingTop;
        this.mPaddingBottom = mPaddingBottom;
    }
    
    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }
    
    public int getPaddingTop() {
        return this.mPaddingTop;
    }
}
