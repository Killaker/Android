package android.support.v7.view.menu;

import android.view.*;

private class OnMenuItemClickListenerWrapper extends BaseWrapper<MenuItem$OnMenuItemClickListener> implements MenuItem$OnMenuItemClickListener
{
    OnMenuItemClickListenerWrapper(final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
        super(menuItem$OnMenuItemClickListener);
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        return ((MenuItem$OnMenuItemClickListener)this.mWrappedObject).onMenuItemClick(MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
    }
}
