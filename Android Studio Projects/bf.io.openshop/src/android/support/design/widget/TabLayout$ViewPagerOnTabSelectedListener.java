package android.support.design.widget;

import android.support.v4.view.*;

public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener
{
    private final ViewPager mViewPager;
    
    public ViewPagerOnTabSelectedListener(final ViewPager mViewPager) {
        this.mViewPager = mViewPager;
    }
    
    @Override
    public void onTabReselected(final Tab tab) {
    }
    
    @Override
    public void onTabSelected(final Tab tab) {
        this.mViewPager.setCurrentItem(tab.getPosition());
    }
    
    @Override
    public void onTabUnselected(final Tab tab) {
    }
}
