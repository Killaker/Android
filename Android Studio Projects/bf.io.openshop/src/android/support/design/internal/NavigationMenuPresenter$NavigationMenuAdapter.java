package android.support.design.internal;

import android.support.v7.widget.*;
import android.support.v7.view.menu.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import java.util.*;
import android.widget.*;
import android.view.*;

private class NavigationMenuAdapter extends Adapter<NavigationMenuPresenter.ViewHolder>
{
    private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
    private static final String STATE_CHECKED_ITEM = "android:menu:checked";
    private static final int VIEW_TYPE_HEADER = 3;
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_SEPARATOR = 2;
    private static final int VIEW_TYPE_SUBHEADER = 1;
    private MenuItemImpl mCheckedItem;
    private final ArrayList<NavigationMenuItem> mItems;
    private ColorDrawable mTransparentIcon;
    private boolean mUpdateSuspended;
    
    NavigationMenuAdapter() {
        this.mItems = new ArrayList<NavigationMenuItem>();
        this.prepareMenuItems();
    }
    
    private void appendTransparentIconIfMissing(final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            final MenuItemImpl menuItem = ((NavigationMenuTextItem)this.mItems.get(i)).getMenuItem();
            if (((MenuItem)menuItem).getIcon() == null) {
                if (this.mTransparentIcon == null) {
                    this.mTransparentIcon = new ColorDrawable(0);
                }
                ((MenuItem)menuItem).setIcon((Drawable)this.mTransparentIcon);
            }
        }
    }
    
    private void prepareMenuItems() {
        if (this.mUpdateSuspended) {
            return;
        }
        this.mUpdateSuspended = true;
        this.mItems.clear();
        this.mItems.add(new NavigationMenuHeaderItem());
        int n = -1;
        int size = 0;
        int n2 = 0;
        for (int i = 0; i < NavigationMenuPresenter.access$000(NavigationMenuPresenter.this).getVisibleItems().size(); ++i) {
            final MenuItemImpl menuItemImpl = NavigationMenuPresenter.access$000(NavigationMenuPresenter.this).getVisibleItems().get(i);
            if (menuItemImpl.isChecked()) {
                this.setCheckedItem(menuItemImpl);
            }
            if (menuItemImpl.isCheckable()) {
                menuItemImpl.setExclusiveCheckable(false);
            }
            if (menuItemImpl.hasSubMenu()) {
                final SubMenu subMenu = menuItemImpl.getSubMenu();
                if (subMenu.hasVisibleItems()) {
                    if (i != 0) {
                        this.mItems.add(new NavigationMenuSeparatorItem(NavigationMenuPresenter.access$1100(NavigationMenuPresenter.this), 0));
                    }
                    this.mItems.add(new NavigationMenuTextItem(menuItemImpl));
                    int n3 = 0;
                    final int size2 = this.mItems.size();
                    for (int j = 0; j < subMenu.size(); ++j) {
                        final MenuItemImpl menuItemImpl2 = (MenuItemImpl)subMenu.getItem(j);
                        if (menuItemImpl2.isVisible()) {
                            if (n3 == 0 && menuItemImpl2.getIcon() != null) {
                                n3 = 1;
                            }
                            if (menuItemImpl2.isCheckable()) {
                                menuItemImpl2.setExclusiveCheckable(false);
                            }
                            if (menuItemImpl.isChecked()) {
                                this.setCheckedItem(menuItemImpl);
                            }
                            this.mItems.add(new NavigationMenuTextItem(menuItemImpl2));
                        }
                    }
                    if (n3 != 0) {
                        this.appendTransparentIconIfMissing(size2, this.mItems.size());
                    }
                }
            }
            else {
                final int groupId = menuItemImpl.getGroupId();
                if (groupId != n) {
                    size = this.mItems.size();
                    if (menuItemImpl.getIcon() != null) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    if (i != 0) {
                        ++size;
                        this.mItems.add(new NavigationMenuSeparatorItem(NavigationMenuPresenter.access$1100(NavigationMenuPresenter.this), NavigationMenuPresenter.access$1100(NavigationMenuPresenter.this)));
                    }
                }
                else if (n2 == 0 && menuItemImpl.getIcon() != null) {
                    n2 = 1;
                    this.appendTransparentIconIfMissing(size, this.mItems.size());
                }
                if (n2 != 0 && menuItemImpl.getIcon() == null) {
                    menuItemImpl.setIcon(17170445);
                }
                this.mItems.add(new NavigationMenuTextItem(menuItemImpl));
                n = groupId;
            }
        }
        this.mUpdateSuspended = false;
    }
    
    public Bundle createInstanceState() {
        final Bundle bundle = new Bundle();
        if (this.mCheckedItem != null) {
            bundle.putInt("android:menu:checked", this.mCheckedItem.getItemId());
        }
        final SparseArray sparseArray = new SparseArray();
        for (final NavigationMenuItem navigationMenuItem : this.mItems) {
            if (navigationMenuItem instanceof NavigationMenuTextItem) {
                final MenuItemImpl menuItem = ((NavigationMenuTextItem)navigationMenuItem).getMenuItem();
                View actionView;
                if (menuItem != null) {
                    actionView = menuItem.getActionView();
                }
                else {
                    actionView = null;
                }
                if (actionView == null) {
                    continue;
                }
                final ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                actionView.saveHierarchyState((SparseArray)parcelableSparseArray);
                sparseArray.put(menuItem.getItemId(), (Object)parcelableSparseArray);
            }
        }
        bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
        return bundle;
    }
    
    @Override
    public int getItemCount() {
        return this.mItems.size();
    }
    
    @Override
    public long getItemId(final int n) {
        return n;
    }
    
    @Override
    public int getItemViewType(final int n) {
        final NavigationMenuItem navigationMenuItem = this.mItems.get(n);
        if (navigationMenuItem instanceof NavigationMenuSeparatorItem) {
            return 2;
        }
        if (navigationMenuItem instanceof NavigationMenuHeaderItem) {
            return 3;
        }
        if (!(navigationMenuItem instanceof NavigationMenuTextItem)) {
            throw new RuntimeException("Unknown item type.");
        }
        if (((NavigationMenuTextItem)navigationMenuItem).getMenuItem().hasSubMenu()) {
            return 1;
        }
        return 0;
    }
    
    public void onBindViewHolder(final NavigationMenuPresenter.ViewHolder viewHolder, final int n) {
        switch (this.getItemViewType(n)) {
            default: {}
            case 0: {
                final NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView)viewHolder.itemView;
                navigationMenuItemView.setIconTintList(NavigationMenuPresenter.access$500(NavigationMenuPresenter.this));
                if (NavigationMenuPresenter.access$600(NavigationMenuPresenter.this)) {
                    navigationMenuItemView.setTextAppearance(navigationMenuItemView.getContext(), NavigationMenuPresenter.access$700(NavigationMenuPresenter.this));
                }
                if (NavigationMenuPresenter.access$800(NavigationMenuPresenter.this) != null) {
                    navigationMenuItemView.setTextColor(NavigationMenuPresenter.access$800(NavigationMenuPresenter.this));
                }
                Drawable drawable;
                if (NavigationMenuPresenter.access$900(NavigationMenuPresenter.this) != null) {
                    drawable = NavigationMenuPresenter.access$900(NavigationMenuPresenter.this).getConstantState().newDrawable();
                }
                else {
                    drawable = null;
                }
                navigationMenuItemView.setBackgroundDrawable(drawable);
                navigationMenuItemView.initialize(((NavigationMenuTextItem)this.mItems.get(n)).getMenuItem(), 0);
            }
            case 1: {
                ((TextView)viewHolder.itemView).setText(((NavigationMenuTextItem)this.mItems.get(n)).getMenuItem().getTitle());
            }
            case 2: {
                final NavigationMenuSeparatorItem navigationMenuSeparatorItem = this.mItems.get(n);
                viewHolder.itemView.setPadding(0, navigationMenuSeparatorItem.getPaddingTop(), 0, navigationMenuSeparatorItem.getPaddingBottom());
            }
        }
    }
    
    public NavigationMenuPresenter.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return new NormalViewHolder(NavigationMenuPresenter.access$200(NavigationMenuPresenter.this), viewGroup, NavigationMenuPresenter.access$300(NavigationMenuPresenter.this));
            }
            case 1: {
                return new SubheaderViewHolder(NavigationMenuPresenter.access$200(NavigationMenuPresenter.this), viewGroup);
            }
            case 2: {
                return new SeparatorViewHolder(NavigationMenuPresenter.access$200(NavigationMenuPresenter.this), viewGroup);
            }
            case 3: {
                return new HeaderViewHolder((View)NavigationMenuPresenter.access$400(NavigationMenuPresenter.this));
            }
        }
    }
    
    public void onViewRecycled(final NavigationMenuPresenter.ViewHolder viewHolder) {
        if (viewHolder instanceof NormalViewHolder) {
            ((NavigationMenuItemView)viewHolder.itemView).recycle();
        }
    }
    
    public void restoreInstanceState(final Bundle bundle) {
        final int int1 = bundle.getInt("android:menu:checked", 0);
        if (int1 != 0) {
            this.mUpdateSuspended = true;
            for (final NavigationMenuItem navigationMenuItem : this.mItems) {
                if (navigationMenuItem instanceof NavigationMenuTextItem) {
                    final MenuItemImpl menuItem = ((NavigationMenuTextItem)navigationMenuItem).getMenuItem();
                    if (menuItem != null && menuItem.getItemId() == int1) {
                        this.setCheckedItem(menuItem);
                        break;
                    }
                    continue;
                }
            }
            this.mUpdateSuspended = false;
            this.prepareMenuItems();
        }
        final SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
        for (final NavigationMenuItem navigationMenuItem2 : this.mItems) {
            if (navigationMenuItem2 instanceof NavigationMenuTextItem) {
                final MenuItemImpl menuItem2 = ((NavigationMenuTextItem)navigationMenuItem2).getMenuItem();
                View actionView;
                if (menuItem2 != null) {
                    actionView = menuItem2.getActionView();
                }
                else {
                    actionView = null;
                }
                if (actionView == null) {
                    continue;
                }
                actionView.restoreHierarchyState((SparseArray)sparseParcelableArray.get(menuItem2.getItemId()));
            }
        }
    }
    
    public void setCheckedItem(final MenuItemImpl mCheckedItem) {
        if (this.mCheckedItem == mCheckedItem || !mCheckedItem.isCheckable()) {
            return;
        }
        if (this.mCheckedItem != null) {
            this.mCheckedItem.setChecked(false);
        }
        (this.mCheckedItem = mCheckedItem).setChecked(true);
    }
    
    public void setUpdateSuspended(final boolean mUpdateSuspended) {
        this.mUpdateSuspended = mUpdateSuspended;
    }
    
    public void update() {
        this.prepareMenuItems();
        ((RecyclerView.Adapter)this).notifyDataSetChanged();
    }
}
