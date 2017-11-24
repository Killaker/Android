package android.support.design.internal;

import android.view.*;
import android.support.v7.view.menu.*;

class NavigationMenuPresenter$1 implements View$OnClickListener {
    public void onClick(final View view) {
        final NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView)view;
        NavigationMenuPresenter.this.setUpdateSuspended(true);
        final MenuItemImpl itemData = navigationMenuItemView.getItemData();
        final boolean performItemAction = NavigationMenuPresenter.access$000(NavigationMenuPresenter.this).performItemAction((MenuItem)itemData, NavigationMenuPresenter.this, 0);
        if (itemData != null && itemData.isCheckable() && performItemAction) {
            NavigationMenuPresenter.access$100(NavigationMenuPresenter.this).setCheckedItem(itemData);
        }
        NavigationMenuPresenter.this.setUpdateSuspended(false);
        NavigationMenuPresenter.this.updateMenuView(false);
    }
}