package android.support.v7.app;

import android.graphics.drawable.*;
import android.support.v7.widget.*;
import android.content.*;
import android.support.annotation.*;

static class ToolbarCompatDelegate implements Delegate
{
    final CharSequence mDefaultContentDescription;
    final Drawable mDefaultUpIndicator;
    final Toolbar mToolbar;
    
    ToolbarCompatDelegate(final Toolbar mToolbar) {
        this.mToolbar = mToolbar;
        this.mDefaultUpIndicator = mToolbar.getNavigationIcon();
        this.mDefaultContentDescription = mToolbar.getNavigationContentDescription();
    }
    
    @Override
    public Context getActionBarThemedContext() {
        return this.mToolbar.getContext();
    }
    
    @Override
    public Drawable getThemeUpIndicator() {
        return this.mDefaultUpIndicator;
    }
    
    @Override
    public boolean isNavigationVisible() {
        return true;
    }
    
    @Override
    public void setActionBarDescription(@StringRes final int navigationContentDescription) {
        if (navigationContentDescription == 0) {
            this.mToolbar.setNavigationContentDescription(this.mDefaultContentDescription);
            return;
        }
        this.mToolbar.setNavigationContentDescription(navigationContentDescription);
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable navigationIcon, @StringRes final int actionBarDescription) {
        this.mToolbar.setNavigationIcon(navigationIcon);
        this.setActionBarDescription(actionBarDescription);
    }
}
