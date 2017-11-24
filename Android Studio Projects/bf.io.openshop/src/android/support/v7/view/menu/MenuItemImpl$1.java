package android.support.v7.view.menu;

import android.support.v4.view.*;

class MenuItemImpl$1 implements VisibilityListener {
    @Override
    public void onActionProviderVisibilityChanged(final boolean b) {
        MenuItemImpl.access$000(MenuItemImpl.this).onItemVisibleChanged(MenuItemImpl.this);
    }
}