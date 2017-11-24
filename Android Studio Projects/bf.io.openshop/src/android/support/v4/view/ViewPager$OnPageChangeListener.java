package android.support.v4.view;

public interface OnPageChangeListener
{
    void onPageScrollStateChanged(final int p0);
    
    void onPageScrolled(final int p0, final float p1, final int p2);
    
    void onPageSelected(final int p0);
}
