package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.drawerMenu.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import android.view.*;

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
