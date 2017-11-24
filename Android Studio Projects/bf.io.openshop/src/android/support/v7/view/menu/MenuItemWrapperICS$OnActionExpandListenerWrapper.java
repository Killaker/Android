package android.support.v7.view.menu;

import android.support.v4.view.*;
import android.view.*;

private class OnActionExpandListenerWrapper extends BaseWrapper<MenuItem$OnActionExpandListener> implements OnActionExpandListener
{
    OnActionExpandListenerWrapper(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        super(menuItem$OnActionExpandListener);
    }
    
    @Override
    public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
        return ((MenuItem$OnActionExpandListener)this.mWrappedObject).onMenuItemActionCollapse(MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
    }
    
    @Override
    public boolean onMenuItemActionExpand(final MenuItem menuItem) {
        return ((MenuItem$OnActionExpandListener)this.mWrappedObject).onMenuItemActionExpand(MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
    }
}
