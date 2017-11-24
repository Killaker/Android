package android.support.v4.view;

import android.database.*;

private class PageListener extends DataSetObserver implements OnPageChangeListener, OnAdapterChangeListener
{
    private int mScrollState;
    
    public void onAdapterChanged(final PagerAdapter pagerAdapter, final PagerAdapter pagerAdapter2) {
        PagerTitleStrip.this.updateAdapter(pagerAdapter, pagerAdapter2);
    }
    
    public void onChanged() {
        PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
        final float n = fcmpl(PagerTitleStrip.access$100(PagerTitleStrip.this), 0.0f);
        float access$100 = 0.0f;
        if (n >= 0) {
            access$100 = PagerTitleStrip.access$100(PagerTitleStrip.this);
        }
        PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), access$100, true);
    }
    
    public void onPageScrollStateChanged(final int mScrollState) {
        this.mScrollState = mScrollState;
    }
    
    public void onPageScrolled(int n, final float n2, final int n3) {
        if (n2 > 0.5f) {
            ++n;
        }
        PagerTitleStrip.this.updateTextPositions(n, n2, false);
    }
    
    public void onPageSelected(final int n) {
        if (this.mScrollState == 0) {
            PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
            final float n2 = fcmpl(PagerTitleStrip.access$100(PagerTitleStrip.this), 0.0f);
            float access$100 = 0.0f;
            if (n2 >= 0) {
                access$100 = PagerTitleStrip.access$100(PagerTitleStrip.this);
            }
            PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), access$100, true);
        }
    }
}
