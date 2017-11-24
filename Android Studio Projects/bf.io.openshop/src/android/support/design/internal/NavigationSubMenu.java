package android.support.design.internal;

import android.content.*;
import android.support.v7.view.menu.*;

public class NavigationSubMenu extends SubMenuBuilder
{
    public NavigationSubMenu(final Context context, final NavigationMenu navigationMenu, final MenuItemImpl menuItemImpl) {
        super(context, navigationMenu, menuItemImpl);
    }
    
    @Override
    public void onItemsChanged(final boolean b) {
        super.onItemsChanged(b);
        ((MenuBuilder)this.getParentMenu()).onItemsChanged(b);
    }
}
