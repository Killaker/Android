package android.support.v7.view.menu;

import android.widget.*;
import java.util.*;
import android.view.*;

private class MenuAdapter extends BaseAdapter
{
    private MenuBuilder mAdapterMenu;
    private int mExpandedIndex;
    
    public MenuAdapter(final MenuBuilder mAdapterMenu) {
        this.mExpandedIndex = -1;
        this.mAdapterMenu = mAdapterMenu;
        this.findExpandedIndex();
    }
    
    void findExpandedIndex() {
        final MenuItemImpl expandedItem = MenuPopupHelper.access$300(MenuPopupHelper.this).getExpandedItem();
        if (expandedItem != null) {
            final ArrayList<MenuItemImpl> nonActionItems = MenuPopupHelper.access$300(MenuPopupHelper.this).getNonActionItems();
            for (int size = nonActionItems.size(), i = 0; i < size; ++i) {
                if (nonActionItems.get(i) == expandedItem) {
                    this.mExpandedIndex = i;
                    return;
                }
            }
        }
        this.mExpandedIndex = -1;
    }
    
    public int getCount() {
        ArrayList<MenuItemImpl> list;
        if (MenuPopupHelper.access$100(MenuPopupHelper.this)) {
            list = this.mAdapterMenu.getNonActionItems();
        }
        else {
            list = this.mAdapterMenu.getVisibleItems();
        }
        if (this.mExpandedIndex < 0) {
            return list.size();
        }
        return -1 + list.size();
    }
    
    public MenuItemImpl getItem(int n) {
        ArrayList<MenuItemImpl> list;
        if (MenuPopupHelper.access$100(MenuPopupHelper.this)) {
            list = this.mAdapterMenu.getNonActionItems();
        }
        else {
            list = this.mAdapterMenu.getVisibleItems();
        }
        if (this.mExpandedIndex >= 0 && n >= this.mExpandedIndex) {
            ++n;
        }
        return list.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        if (inflate == null) {
            inflate = MenuPopupHelper.access$200(MenuPopupHelper.this).inflate(MenuPopupHelper.ITEM_LAYOUT, viewGroup, false);
        }
        final MenuView.ItemView itemView = (MenuView.ItemView)inflate;
        if (MenuPopupHelper.this.mForceShowIcon) {
            ((ListMenuItemView)inflate).setForceShowIcon(true);
        }
        itemView.initialize(this.getItem(n), 0);
        return inflate;
    }
    
    public void notifyDataSetChanged() {
        this.findExpandedIndex();
        super.notifyDataSetChanged();
    }
}
