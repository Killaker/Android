package android.support.v7.view.menu;

import android.view.*;

public interface Callback
{
    boolean onMenuItemSelected(final MenuBuilder p0, final MenuItem p1);
    
    void onMenuModeChange(final MenuBuilder p0);
}
