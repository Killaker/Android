package android.support.v7.widget;

import android.support.v7.view.*;
import android.view.*;
import android.content.*;
import android.os.*;
import android.support.v7.view.menu.*;

private class ExpandedActionViewMenuPresenter implements MenuPresenter
{
    MenuItemImpl mCurrentExpandedItem;
    MenuBuilder mMenu;
    
    @Override
    public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
            ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewCollapsed();
        }
        Toolbar.this.removeView(Toolbar.this.mExpandedActionView);
        Toolbar.this.removeView((View)Toolbar.access$300(Toolbar.this));
        Toolbar.this.mExpandedActionView = null;
        Toolbar.this.addChildrenForExpandedActionView();
        this.mCurrentExpandedItem = null;
        Toolbar.this.requestLayout();
        menuItemImpl.setActionViewExpanded(false);
        return true;
    }
    
    @Override
    public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl mCurrentExpandedItem) {
        Toolbar.access$200(Toolbar.this);
        if (Toolbar.access$300(Toolbar.this).getParent() != Toolbar.this) {
            Toolbar.this.addView((View)Toolbar.access$300(Toolbar.this));
        }
        Toolbar.this.mExpandedActionView = mCurrentExpandedItem.getActionView();
        this.mCurrentExpandedItem = mCurrentExpandedItem;
        if (Toolbar.this.mExpandedActionView.getParent() != Toolbar.this) {
            final LayoutParams generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = (0x800003 | (0x70 & Toolbar.access$400(Toolbar.this)));
            generateDefaultLayoutParams.mViewType = 2;
            Toolbar.this.mExpandedActionView.setLayoutParams((ViewGroup$LayoutParams)generateDefaultLayoutParams);
            Toolbar.this.addView(Toolbar.this.mExpandedActionView);
        }
        Toolbar.this.removeChildrenForExpandedActionView();
        Toolbar.this.requestLayout();
        mCurrentExpandedItem.setActionViewExpanded(true);
        if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
            ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewExpanded();
        }
        return true;
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    @Override
    public int getId() {
        return 0;
    }
    
    @Override
    public MenuView getMenuView(final ViewGroup viewGroup) {
        return null;
    }
    
    @Override
    public void initForMenu(final Context context, final MenuBuilder mMenu) {
        if (this.mMenu != null && this.mCurrentExpandedItem != null) {
            this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
        }
        this.mMenu = mMenu;
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }
    
    @Override
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        return false;
    }
    
    @Override
    public void setCallback(final Callback callback) {
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        if (this.mCurrentExpandedItem != null) {
            final MenuBuilder mMenu = this.mMenu;
            boolean b2 = false;
            if (mMenu != null) {
                final int size = this.mMenu.size();
                int n = 0;
                while (true) {
                    b2 = false;
                    if (n >= size) {
                        break;
                    }
                    if (this.mMenu.getItem(n) == this.mCurrentExpandedItem) {
                        b2 = true;
                        break;
                    }
                    ++n;
                }
            }
            if (!b2) {
                this.collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
            }
        }
    }
}
