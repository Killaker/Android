package android.support.design.internal;

import android.support.v7.view.menu.*;

private static class NavigationMenuTextItem implements NavigationMenuItem
{
    private final MenuItemImpl mMenuItem;
    
    private NavigationMenuTextItem(final MenuItemImpl mMenuItem) {
        this.mMenuItem = mMenuItem;
    }
    
    public MenuItemImpl getMenuItem() {
        return this.mMenuItem;
    }
}
