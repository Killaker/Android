package android.support.v4.app;

import android.app.*;
import android.graphics.drawable.*;

private static class ActionBarDrawerToggleImplBase implements ActionBarDrawerToggleImpl
{
    @Override
    public Drawable getThemeUpIndicator(final Activity activity) {
        return null;
    }
    
    @Override
    public Object setActionBarDescription(final Object o, final Activity activity, final int n) {
        return o;
    }
    
    @Override
    public Object setActionBarUpIndicator(final Object o, final Activity activity, final Drawable drawable, final int n) {
        return o;
    }
}
