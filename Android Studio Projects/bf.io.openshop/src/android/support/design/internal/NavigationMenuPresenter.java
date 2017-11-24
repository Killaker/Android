package android.support.design.internal;

import android.content.res.*;
import android.support.design.*;
import android.support.v7.widget.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.support.v7.view.menu.*;
import android.support.annotation.*;
import android.graphics.drawable.*;
import android.view.*;
import java.util.*;
import android.widget.*;

public class NavigationMenuPresenter implements MenuPresenter
{
    private static final String STATE_ADAPTER = "android:menu:adapter";
    private static final String STATE_HIERARCHY = "android:menu:list";
    private NavigationMenuAdapter mAdapter;
    private Callback mCallback;
    private LinearLayout mHeaderLayout;
    private ColorStateList mIconTintList;
    private int mId;
    private Drawable mItemBackground;
    private LayoutInflater mLayoutInflater;
    private MenuBuilder mMenu;
    private NavigationMenuView mMenuView;
    private final View$OnClickListener mOnClickListener;
    private int mPaddingSeparator;
    private int mPaddingTopDefault;
    private int mTextAppearance;
    private boolean mTextAppearanceSet;
    private ColorStateList mTextColor;
    
    public NavigationMenuPresenter() {
        this.mOnClickListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView)view;
                NavigationMenuPresenter.this.setUpdateSuspended(true);
                final MenuItemImpl itemData = navigationMenuItemView.getItemData();
                final boolean performItemAction = NavigationMenuPresenter.this.mMenu.performItemAction((MenuItem)itemData, NavigationMenuPresenter.this, 0);
                if (itemData != null && itemData.isCheckable() && performItemAction) {
                    NavigationMenuPresenter.this.mAdapter.setCheckedItem(itemData);
                }
                NavigationMenuPresenter.this.setUpdateSuspended(false);
                NavigationMenuPresenter.this.updateMenuView(false);
            }
        };
    }
    
    public void addHeaderView(@NonNull final View view) {
        this.mHeaderLayout.addView(view);
        this.mMenuView.setPadding(0, 0, 0, this.mMenuView.getPaddingBottom());
    }
    
    @Override
    public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    @Override
    public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    public int getHeaderCount() {
        return this.mHeaderLayout.getChildCount();
    }
    
    public View getHeaderView(final int n) {
        return this.mHeaderLayout.getChildAt(n);
    }
    
    @Override
    public int getId() {
        return this.mId;
    }
    
    @Nullable
    public Drawable getItemBackground() {
        return this.mItemBackground;
    }
    
    @Nullable
    public ColorStateList getItemTextColor() {
        return this.mTextColor;
    }
    
    @Nullable
    public ColorStateList getItemTintList() {
        return this.mIconTintList;
    }
    
    @Override
    public MenuView getMenuView(final ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (NavigationMenuView)this.mLayoutInflater.inflate(R.layout.design_navigation_menu, viewGroup, false);
            if (this.mAdapter == null) {
                this.mAdapter = new NavigationMenuAdapter();
            }
            this.mHeaderLayout = (LinearLayout)this.mLayoutInflater.inflate(R.layout.design_navigation_item_header, (ViewGroup)this.mMenuView, false);
            this.mMenuView.setAdapter((RecyclerView.Adapter)this.mAdapter);
        }
        return this.mMenuView;
    }
    
    public View inflateHeaderView(@LayoutRes final int n) {
        final View inflate = this.mLayoutInflater.inflate(n, (ViewGroup)this.mHeaderLayout, false);
        this.addHeaderView(inflate);
        return inflate;
    }
    
    @Override
    public void initForMenu(final Context context, final MenuBuilder mMenu) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mMenu = mMenu;
        this.mPaddingSeparator = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (this.mCallback != null) {
            this.mCallback.onCloseMenu(menuBuilder, b);
        }
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final Bundle bundle = (Bundle)parcelable;
        final SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.mMenuView.restoreHierarchyState(sparseParcelableArray);
        }
        final Bundle bundle2 = bundle.getBundle("android:menu:adapter");
        if (bundle2 != null) {
            this.mAdapter.restoreInstanceState(bundle2);
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        if (this.mMenuView != null) {
            final SparseArray sparseArray = new SparseArray();
            this.mMenuView.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        if (this.mAdapter != null) {
            bundle.putBundle("android:menu:adapter", this.mAdapter.createInstanceState());
        }
        return (Parcelable)bundle;
    }
    
    @Override
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        return false;
    }
    
    public void removeHeaderView(@NonNull final View view) {
        this.mHeaderLayout.removeView(view);
        if (this.mHeaderLayout.getChildCount() == 0) {
            this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
        }
    }
    
    @Override
    public void setCallback(final Callback mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setCheckedItem(final MenuItemImpl checkedItem) {
        this.mAdapter.setCheckedItem(checkedItem);
    }
    
    public void setId(final int mId) {
        this.mId = mId;
    }
    
    public void setItemBackground(@Nullable final Drawable mItemBackground) {
        this.mItemBackground = mItemBackground;
        this.updateMenuView(false);
    }
    
    public void setItemIconTintList(@Nullable final ColorStateList mIconTintList) {
        this.mIconTintList = mIconTintList;
        this.updateMenuView(false);
    }
    
    public void setItemTextAppearance(@StyleRes final int mTextAppearance) {
        this.mTextAppearance = mTextAppearance;
        this.mTextAppearanceSet = true;
        this.updateMenuView(false);
    }
    
    public void setItemTextColor(@Nullable final ColorStateList mTextColor) {
        this.mTextColor = mTextColor;
        this.updateMenuView(false);
    }
    
    public void setPaddingTopDefault(final int mPaddingTopDefault) {
        if (this.mPaddingTopDefault != mPaddingTopDefault) {
            this.mPaddingTopDefault = mPaddingTopDefault;
            if (this.mHeaderLayout.getChildCount() == 0) {
                this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
            }
        }
    }
    
    public void setUpdateSuspended(final boolean updateSuspended) {
        if (this.mAdapter != null) {
            this.mAdapter.setUpdateSuspended(updateSuspended);
        }
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        if (this.mAdapter != null) {
            this.mAdapter.update();
        }
    }
    
    private static class HeaderViewHolder extends ViewHolder
    {
        public HeaderViewHolder(final View view) {
            super(view);
        }
    }
    
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
            for (int i = 0; i < NavigationMenuPresenter.this.mMenu.getVisibleItems().size(); ++i) {
                final MenuItemImpl menuItemImpl = NavigationMenuPresenter.this.mMenu.getVisibleItems().get(i);
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
                            this.mItems.add(new NavigationMenuSeparatorItem(NavigationMenuPresenter.this.mPaddingSeparator, 0));
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
                            this.mItems.add(new NavigationMenuSeparatorItem(NavigationMenuPresenter.this.mPaddingSeparator, NavigationMenuPresenter.this.mPaddingSeparator));
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
                    navigationMenuItemView.setIconTintList(NavigationMenuPresenter.this.mIconTintList);
                    if (NavigationMenuPresenter.this.mTextAppearanceSet) {
                        navigationMenuItemView.setTextAppearance(navigationMenuItemView.getContext(), NavigationMenuPresenter.this.mTextAppearance);
                    }
                    if (NavigationMenuPresenter.this.mTextColor != null) {
                        navigationMenuItemView.setTextColor(NavigationMenuPresenter.this.mTextColor);
                    }
                    Drawable drawable;
                    if (NavigationMenuPresenter.this.mItemBackground != null) {
                        drawable = NavigationMenuPresenter.this.mItemBackground.getConstantState().newDrawable();
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
                    return new NormalViewHolder(NavigationMenuPresenter.this.mLayoutInflater, viewGroup, NavigationMenuPresenter.this.mOnClickListener);
                }
                case 1: {
                    return new SubheaderViewHolder(NavigationMenuPresenter.this.mLayoutInflater, viewGroup);
                }
                case 2: {
                    return new SeparatorViewHolder(NavigationMenuPresenter.this.mLayoutInflater, viewGroup);
                }
                case 3: {
                    return new HeaderViewHolder((View)NavigationMenuPresenter.this.mHeaderLayout);
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
    
    private static class NavigationMenuHeaderItem implements NavigationMenuItem
    {
    }
    
    private interface NavigationMenuItem
    {
    }
    
    private static class NavigationMenuSeparatorItem implements NavigationMenuItem
    {
        private final int mPaddingBottom;
        private final int mPaddingTop;
        
        public NavigationMenuSeparatorItem(final int mPaddingTop, final int mPaddingBottom) {
            this.mPaddingTop = mPaddingTop;
            this.mPaddingBottom = mPaddingBottom;
        }
        
        public int getPaddingBottom() {
            return this.mPaddingBottom;
        }
        
        public int getPaddingTop() {
            return this.mPaddingTop;
        }
    }
    
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
    
    private static class NormalViewHolder extends ViewHolder
    {
        public NormalViewHolder(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final View$OnClickListener onClickListener) {
            super(layoutInflater.inflate(R.layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }
    
    private static class SeparatorViewHolder extends ViewHolder
    {
        public SeparatorViewHolder(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }
    
    private static class SubheaderViewHolder extends ViewHolder
    {
        public SubheaderViewHolder(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }
    
    private abstract static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(final View view) {
            super(view);
        }
    }
}
