package android.support.v7.app;

import android.content.*;
import android.app.*;
import android.graphics.drawable.*;

private static class HoneycombDelegate implements Delegate
{
    final Activity mActivity;
    ActionBarDrawerToggleHoneycomb.SetIndicatorInfo mSetIndicatorInfo;
    
    private HoneycombDelegate(final Activity mActivity) {
        this.mActivity = mActivity;
    }
    
    @Override
    public Context getActionBarThemedContext() {
        final ActionBar actionBar = this.mActivity.getActionBar();
        if (actionBar != null) {
            return actionBar.getThemedContext();
        }
        return (Context)this.mActivity;
    }
    
    @Override
    public Drawable getThemeUpIndicator() {
        return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(this.mActivity);
    }
    
    @Override
    public boolean isNavigationVisible() {
        final ActionBar actionBar = this.mActivity.getActionBar();
        return actionBar != null && (0x4 & actionBar.getDisplayOptions()) != 0x0;
    }
    
    @Override
    public void setActionBarDescription(final int n) {
        this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, n);
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable drawable, final int n) {
        this.mActivity.getActionBar().setDisplayShowHomeEnabled(true);
        this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(this.mSetIndicatorInfo, this.mActivity, drawable, n);
        this.mActivity.getActionBar().setDisplayShowHomeEnabled(false);
    }
}
