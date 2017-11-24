package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.drawerMenu.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import android.view.*;

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
