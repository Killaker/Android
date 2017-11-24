package android.support.v7.app;

import android.content.*;
import android.graphics.drawable.*;
import android.support.v7.appcompat.*;
import android.support.v7.widget.*;
import android.util.*;

private class ActionBarDrawableToggleImpl implements Delegate
{
    @Override
    public Context getActionBarThemedContext() {
        return AppCompatDelegateImplBase.this.getActionBarThemedContext();
    }
    
    @Override
    public Drawable getThemeUpIndicator() {
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.getActionBarThemedContext(), null, new int[] { R.attr.homeAsUpIndicator });
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }
    
    @Override
    public boolean isNavigationVisible() {
        final ActionBar supportActionBar = AppCompatDelegateImplBase.this.getSupportActionBar();
        return supportActionBar != null && (0x4 & supportActionBar.getDisplayOptions()) != 0x0;
    }
    
    @Override
    public void setActionBarDescription(final int homeActionContentDescription) {
        final ActionBar supportActionBar = AppCompatDelegateImplBase.this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable homeAsUpIndicator, final int homeActionContentDescription) {
        final ActionBar supportActionBar = AppCompatDelegateImplBase.this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(homeAsUpIndicator);
            supportActionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
    }
}
