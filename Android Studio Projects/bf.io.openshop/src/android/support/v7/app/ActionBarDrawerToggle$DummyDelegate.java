package android.support.v7.app;

import android.app.*;
import android.content.*;
import android.graphics.drawable.*;
import android.support.annotation.*;

static class DummyDelegate implements Delegate
{
    final Activity mActivity;
    
    DummyDelegate(final Activity mActivity) {
        this.mActivity = mActivity;
    }
    
    @Override
    public Context getActionBarThemedContext() {
        return (Context)this.mActivity;
    }
    
    @Override
    public Drawable getThemeUpIndicator() {
        return null;
    }
    
    @Override
    public boolean isNavigationVisible() {
        return true;
    }
    
    @Override
    public void setActionBarDescription(@StringRes final int n) {
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable drawable, @StringRes final int n) {
    }
}
