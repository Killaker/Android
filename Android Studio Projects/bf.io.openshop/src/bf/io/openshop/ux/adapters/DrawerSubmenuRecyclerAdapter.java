package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.drawerMenu.*;
import bf.io.openshop.interfaces.*;
import java.util.*;
import android.widget.*;
import android.view.*;

public class DrawerSubmenuRecyclerAdapter extends Adapter<ViewHolder>
{
    private List<DrawerItemCategory> drawerItemCategoryList;
    private final DrawerSubmenuRecyclerInterface drawerSubmenuRecyclerInterface;
    LayoutInflater layoutInflater;
    
    public DrawerSubmenuRecyclerAdapter(final DrawerSubmenuRecyclerInterface drawerSubmenuRecyclerInterface) {
        this.drawerItemCategoryList = new ArrayList<DrawerItemCategory>();
        this.drawerSubmenuRecyclerInterface = drawerSubmenuRecyclerInterface;
    }
    
    private DrawerItemCategory getDrawerItem(final int n) {
        return this.drawerItemCategoryList.get(n);
    }
    
    public void changeDrawerItems(final List<DrawerItemCategory> list) {
        this.drawerItemCategoryList.clear();
        this.drawerItemCategoryList.addAll(list);
        ((RecyclerView.Adapter)this).notifyDataSetChanged();
    }
    
    @Override
    public int getItemCount() {
        return this.drawerItemCategoryList.size();
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final ViewHolderItemCategory viewHolderItemCategory = (ViewHolderItemCategory)viewHolder;
        final DrawerItemCategory drawerItem = this.getDrawerItem(n);
        viewHolderItemCategory.bindContent(drawerItem);
        viewHolderItemCategory.itemText.setText((CharSequence)drawerItem.getName());
        viewHolderItemCategory.subMenuIndicator.setVisibility(4);
    }
    
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ViewHolderItemCategory(this.layoutInflater.inflate(2130968646, viewGroup, false), this.drawerSubmenuRecyclerInterface);
    }
    
    public static class ViewHolderItemCategory extends ViewHolder
    {
        private DrawerItemCategory drawerItemCategory;
        public TextView itemText;
        public LinearLayout layout;
        public ImageView subMenuIndicator;
        
        public ViewHolderItemCategory(final View view, final DrawerSubmenuRecyclerInterface drawerSubmenuRecyclerInterface) {
            super(view);
            this.itemText = (TextView)view.findViewById(2131624315);
            this.subMenuIndicator = (ImageView)view.findViewById(2131624316);
            this.layout = (LinearLayout)view.findViewById(2131624314);
            view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    drawerSubmenuRecyclerInterface.onSubCategorySelected(view, ViewHolderItemCategory.this.drawerItemCategory);
                }
            });
        }
        
        public void bindContent(final DrawerItemCategory drawerItemCategory) {
            this.drawerItemCategory = drawerItemCategory;
        }
    }
}
