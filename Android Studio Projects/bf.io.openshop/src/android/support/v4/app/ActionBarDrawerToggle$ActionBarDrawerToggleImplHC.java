package android.support.v4.app;

import android.app.*;
import android.graphics.drawable.*;

private static class ActionBarDrawerToggleImplHC implements ActionBarDrawerToggleImpl
{
    @Override
    public Drawable getThemeUpIndicator(final Activity activity) {
        return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(activity);
    }
    
    @Override
    public Object setActionBarDescription(final Object o, final Activity activity, final int n) {
        return ActionBarDrawerToggleHoneycomb.setActionBarDescription(o, activity, n);
    }
    
    @Override
    public Object setActionBarUpIndicator(final Object o, final Activity activity, final Drawable drawable, final int n) {
        return ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(o, activity, drawable, n);
    }
}
