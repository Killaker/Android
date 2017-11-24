package android.support.design.widget;

import android.support.v7.view.menu.*;
import android.view.*;

class NavigationView$1 implements Callback {
    @Override
    public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        return NavigationView.access$000(NavigationView.this) != null && NavigationView.access$000(NavigationView.this).onNavigationItemSelected(menuItem);
    }
    
    @Override
    public void onMenuModeChange(final MenuBuilder menuBuilder) {
    }
}