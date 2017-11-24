package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.content.*;
import bf.io.openshop.entities.drawerMenu.*;
import bf.io.openshop.interfaces.*;
import android.view.animation.*;
import java.util.*;
import android.support.v4.content.*;
import android.graphics.drawable.*;
import bf.io.openshop.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.listeners.*;
import android.view.*;
import android.widget.*;

public class DrawerRecyclerAdapter extends Adapter<ViewHolder>
{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM_CATEGORY = 1;
    private static final int TYPE_ITEM_PAGE = 2;
    private Context context;
    private List<DrawerItemCategory> drawerItemCategoryList;
    private List<DrawerItemPage> drawerItemPageList;
    private final DrawerRecyclerInterface drawerRecyclerInterface;
    LayoutInflater layoutInflater;
    
    public DrawerRecyclerAdapter(final Context context, final DrawerRecyclerInterface drawerRecyclerInterface) {
        this.drawerItemCategoryList = new ArrayList<DrawerItemCategory>();
        this.drawerItemPageList = new ArrayList<DrawerItemPage>();
        this.context = context;
        this.drawerRecyclerInterface = drawerRecyclerInterface;
    }
    
    private DrawerItemCategory getDrawerItem(final int n) {
        return this.drawerItemCategoryList.get(n - 1);
    }
    
    private DrawerItemPage getPageItem(final int n) {
        return this.drawerItemPageList.get(-1 + (n - this.drawerItemCategoryList.size()));
    }
    
    private void setAnimation(final View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this.context, 17432578));
    }
    
    public void addDrawerItem(final DrawerItemCategory drawerItemCategory) {
        this.drawerItemCategoryList.add(drawerItemCategory);
    }
    
    public void addDrawerItemList(final List<DrawerItemCategory> list) {
        if (list != null) {
            this.drawerItemCategoryList.addAll(list);
        }
    }
    
    public void addPageItemList(final List<DrawerItemPage> list) {
        if (list != null) {
            this.drawerItemPageList.addAll(list);
        }
    }
    
    @Override
    public int getItemCount() {
        return 1 + (this.drawerItemCategoryList.size() + this.drawerItemPageList.size());
    }
    
    @Override
    public int getItemViewType(final int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= this.drawerItemCategoryList.size()) {
            return 1;
        }
        return 2;
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        if (viewHolder instanceof ViewHolderItemCategory) {
            final ViewHolderItemCategory viewHolderItemCategory = (ViewHolderItemCategory)viewHolder;
            final DrawerItemCategory drawerItem = this.getDrawerItem(n);
            viewHolderItemCategory.bindContent(drawerItem);
            viewHolderItemCategory.itemText.setText((CharSequence)drawerItem.getName());
            if (n == 1) {
                viewHolderItemCategory.itemText.setTextColor(ContextCompat.getColor(this.context, 2131558426));
                viewHolderItemCategory.itemText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.context, 2130837716), (Drawable)null, (Drawable)null, (Drawable)null);
                viewHolderItemCategory.divider.setVisibility(0);
            }
            else {
                viewHolderItemCategory.itemText.setTextColor(ContextCompat.getColor(this.context, 2131558531));
                viewHolderItemCategory.itemText.setCompoundDrawablesWithIntrinsicBounds((Drawable)null, (Drawable)null, (Drawable)null, (Drawable)null);
                viewHolderItemCategory.divider.setVisibility(8);
            }
            if (drawerItem.getChildren() != null && !drawerItem.getChildren().isEmpty()) {
                viewHolderItemCategory.subMenuIndicator.setVisibility(0);
                return;
            }
            viewHolderItemCategory.subMenuIndicator.setVisibility(4);
        }
        else {
            if (viewHolder instanceof ViewHolderItemPage) {
                final ViewHolderItemPage viewHolderItemPage = (ViewHolderItemPage)viewHolder;
                final DrawerItemPage pageItem = this.getPageItem(n);
                viewHolderItemPage.bindContent(pageItem);
                viewHolderItemPage.itemText.setText((CharSequence)pageItem.getName());
                return;
            }
            if (viewHolder instanceof ViewHolderHeader) {
                final ViewHolderHeader viewHolderHeader = (ViewHolderHeader)viewHolder;
                final User activeUser = SettingsMy.getActiveUser();
                if (activeUser != null) {
                    viewHolderHeader.userName.setText((CharSequence)activeUser.getEmail());
                    return;
                }
                viewHolderHeader.userName.setText((CharSequence)this.context.getString(2131230914));
            }
        }
    }
    
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        if (n == 1) {
            return new ViewHolderItemCategory(this.layoutInflater.inflate(2130968646, viewGroup, false), this.drawerRecyclerInterface);
        }
        if (n == 2) {
            return new ViewHolderItemPage(this.layoutInflater.inflate(2130968648, viewGroup, false), this.drawerRecyclerInterface);
        }
        return new ViewHolderHeader(this.layoutInflater.inflate(2130968647, viewGroup, false), this.drawerRecyclerInterface);
    }
    
    @Override
    public void onViewAttachedToWindow(final ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof ViewHolderItemCategory) {
            this.setAnimation((View)((ViewHolderItemCategory)viewHolder).layout);
        }
        else if (viewHolder instanceof ViewHolderItemPage) {
            this.setAnimation(((ViewHolderItemPage)viewHolder).layout);
        }
    }
    
    @Override
    public void onViewDetachedFromWindow(final ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if (viewHolder instanceof ViewHolderItemCategory) {
            ((ViewHolderItemCategory)viewHolder).layout.clearAnimation();
        }
        else if (viewHolder instanceof ViewHolderItemPage) {
            ((ViewHolderItemPage)viewHolder).layout.clearAnimation();
        }
    }
    
    public static class ViewHolderHeader extends ViewHolder
    {
        public TextView userName;
        
        public ViewHolderHeader(final View view, final DrawerRecyclerInterface drawerRecyclerInterface) {
            super(view);
            this.userName = (TextView)view.findViewById(2131624319);
            view.setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
                @Override
                public void onSingleClick(final View view) {
                    drawerRecyclerInterface.onHeaderSelected();
                }
            });
        }
    }
    
    public static class ViewHolderItemCategory extends ViewHolder
    {
        private View divider;
        private DrawerItemCategory drawerItemCategory;
        public TextView itemText;
        public LinearLayout layout;
        public ImageView subMenuIndicator;
        
        public ViewHolderItemCategory(final View view, final DrawerRecyclerInterface drawerRecyclerInterface) {
            super(view);
            this.itemText = (TextView)view.findViewById(2131624315);
            this.subMenuIndicator = (ImageView)view.findViewById(2131624316);
            this.layout = (LinearLayout)view.findViewById(2131624314);
            this.divider = view.findViewById(2131624317);
            view.setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
                @Override
                public void onSingleClick(final View view) {
                    drawerRecyclerInterface.onCategorySelected(view, ViewHolderItemCategory.this.drawerItemCategory);
                }
            });
        }
        
        public void bindContent(final DrawerItemCategory drawerItemCategory) {
            this.drawerItemCategory = drawerItemCategory;
        }
    }
    
    public static class ViewHolderItemPage extends ViewHolder
    {
        private DrawerItemPage drawerItemPage;
        public TextView itemText;
        public View layout;
        
        public ViewHolderItemPage(final View view, final DrawerRecyclerInterface drawerRecyclerInterface) {
            super(view);
            this.itemText = (TextView)view.findViewById(2131624315);
            this.layout = view.findViewById(2131624314);
            view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    drawerRecyclerInterface.onPageSelected(view, ViewHolderItemPage.this.drawerItemPage);
                }
            });
        }
        
        public void bindContent(final DrawerItemPage drawerItemPage) {
            this.drawerItemPage = drawerItemPage;
        }
    }
}
