package android.support.v7.app;

import android.content.*;
import android.graphics.drawable.*;
import android.support.annotation.*;

public interface Delegate
{
    Context getActionBarThemedContext();
    
    Drawable getThemeUpIndicator();
    
    boolean isNavigationVisible();
    
    void setActionBarDescription(@StringRes final int p0);
    
    void setActionBarUpIndicator(final Drawable p0, @StringRes final int p1);
}
