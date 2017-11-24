package android.support.v7.widget;

import android.content.*;
import android.support.v7.appcompat.*;
import android.support.v7.view.menu.*;
import android.view.*;

private class ActionButtonSubmenu extends MenuPopupHelper
{
    private SubMenuBuilder mSubMenu;
    
    public ActionButtonSubmenu(final Context context, final SubMenuBuilder mSubMenu) {
        super(context, mSubMenu, null, false, R.attr.actionOverflowMenuStyle);
        this.mSubMenu = mSubMenu;
        if (!((MenuItemImpl)mSubMenu.getItem()).isActionButton()) {
            Object access$600;
            if (ActionMenuPresenter.access$600(ActionMenuPresenter.this) == null) {
                access$600 = ActionMenuPresenter.access$700(ActionMenuPresenter.this);
            }
            else {
                access$600 = ActionMenuPresenter.access$600(ActionMenuPresenter.this);
            }
            this.setAnchorView((View)access$600);
        }
        this.setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        final int size = mSubMenu.size();
        int n = 0;
        boolean forceShowIcon;
        while (true) {
            forceShowIcon = false;
            if (n >= size) {
                break;
            }
            final MenuItem item = mSubMenu.getItem(n);
            if (item.isVisible() && item.getIcon() != null) {
                forceShowIcon = true;
                break;
            }
            ++n;
        }
        this.setForceShowIcon(forceShowIcon);
    }
    
    @Override
    public void onDismiss() {
        super.onDismiss();
        ActionMenuPresenter.access$802(ActionMenuPresenter.this, null);
        ActionMenuPresenter.this.mOpenSubMenuId = 0;
    }
}
