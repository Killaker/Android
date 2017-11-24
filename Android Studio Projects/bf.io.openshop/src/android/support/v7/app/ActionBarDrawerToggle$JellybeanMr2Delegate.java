package android.support.v7.app;

import android.content.*;
import android.app.*;
import android.graphics.drawable.*;
import android.util.*;
import android.content.res.*;

private static class JellybeanMr2Delegate implements Delegate
{
    final Activity mActivity;
    
    private JellybeanMr2Delegate(final Activity mActivity) {
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
        final TypedArray obtainStyledAttributes = this.getActionBarThemedContext().obtainStyledAttributes((AttributeSet)null, new int[] { 16843531 }, 16843470, 0);
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }
    
    @Override
    public boolean isNavigationVisible() {
        final ActionBar actionBar = this.mActivity.getActionBar();
        return actionBar != null && (0x4 & actionBar.getDisplayOptions()) != 0x0;
    }
    
    @Override
    public void setActionBarDescription(final int homeActionContentDescription) {
        final ActionBar actionBar = this.mActivity.getActionBar();
        if (actionBar != null) {
            actionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable homeAsUpIndicator, final int homeActionContentDescription) {
        final ActionBar actionBar = this.mActivity.getActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(homeAsUpIndicator);
            actionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
}
