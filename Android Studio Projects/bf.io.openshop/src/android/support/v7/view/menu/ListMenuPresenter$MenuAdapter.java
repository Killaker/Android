package android.support.v7.view.menu;

import android.widget.*;
import java.util.*;
import android.view.*;

private class MenuAdapter extends BaseAdapter
{
    private int mExpandedIndex;
    
    public MenuAdapter() {
        this.mExpandedIndex = -1;
        this.findExpandedIndex();
    }
    
    void findExpandedIndex() {
        final MenuItemImpl expandedItem = ListMenuPresenter.this.mMenu.getExpandedItem();
        if (expandedItem != null) {
            final ArrayList<MenuItemImpl> nonActionItems = ListMenuPresenter.this.mMenu.getNonActionItems();
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
        final int n = ListMenuPresenter.this.mMenu.getNonActionItems().size() - ListMenuPresenter.access$000(ListMenuPresenter.this);
        if (this.mExpandedIndex < 0) {
            return n;
        }
        return n - 1;
    }
    
    public MenuItemImpl getItem(final int n) {
        final ArrayList<MenuItemImpl> nonActionItems = ListMenuPresenter.this.mMenu.getNonActionItems();
        int n2 = n + ListMenuPresenter.access$000(ListMenuPresenter.this);
        if (this.mExpandedIndex >= 0 && n2 >= this.mExpandedIndex) {
            ++n2;
        }
        return nonActionItems.get(n2);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        if (inflate == null) {
            inflate = ListMenuPresenter.this.mInflater.inflate(ListMenuPresenter.this.mItemLayoutRes, viewGroup, false);
        }
        ((MenuView.ItemView)inflate).initialize(this.getItem(n), 0);
        return inflate;
    }
    
    public void notifyDataSetChanged() {
        this.findExpandedIndex();
        super.notifyDataSetChanged();
    }
}
