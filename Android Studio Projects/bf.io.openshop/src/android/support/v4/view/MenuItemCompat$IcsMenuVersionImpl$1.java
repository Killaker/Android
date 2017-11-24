package android.support.v4.view;

import android.view.*;

class MenuItemCompat$IcsMenuVersionImpl$1 implements SupportActionExpandProxy {
    final /* synthetic */ OnActionExpandListener val$listener;
    
    @Override
    public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
        return this.val$listener.onMenuItemActionCollapse(menuItem);
    }
    
    @Override
    public boolean onMenuItemActionExpand(final MenuItem menuItem) {
        return this.val$listener.onMenuItemActionExpand(menuItem);
    }
}