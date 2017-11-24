package android.support.design.widget;

import android.support.v4.view.*;
import java.lang.ref.*;

public static class TabLayoutOnPageChangeListener implements OnPageChangeListener
{
    private int mPreviousScrollState;
    private int mScrollState;
    private final WeakReference<TabLayout> mTabLayoutRef;
    
    public TabLayoutOnPageChangeListener(final TabLayout tabLayout) {
        this.mTabLayoutRef = new WeakReference<TabLayout>(tabLayout);
    }
    
    private void reset() {
        this.mScrollState = 0;
        this.mPreviousScrollState = 0;
    }
    
    @Override
    public void onPageScrollStateChanged(final int mScrollState) {
        this.mPreviousScrollState = this.mScrollState;
        this.mScrollState = mScrollState;
    }
    
    @Override
    public void onPageScrolled(final int n, final float n2, final int n3) {
        final TabLayout tabLayout = this.mTabLayoutRef.get();
        if (tabLayout != null) {
            TabLayout.access$2700(tabLayout, n, n2, this.mScrollState != 2 || this.mPreviousScrollState == 1, this.mScrollState != 2 || this.mPreviousScrollState != 0);
        }
    }
    
    @Override
    public void onPageSelected(final int n) {
        final TabLayout tabLayout = this.mTabLayoutRef.get();
        if (tabLayout != null && tabLayout.getSelectedTabPosition() != n) {
            tabLayout.selectTab(tabLayout.getTabAt(n), this.mScrollState == 0 || (this.mScrollState == 2 && this.mPreviousScrollState == 0));
        }
    }
}
