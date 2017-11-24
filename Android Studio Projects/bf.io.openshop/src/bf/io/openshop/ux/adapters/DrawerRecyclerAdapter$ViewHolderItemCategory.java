package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.drawerMenu.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.listeners.*;
import android.view.*;

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
