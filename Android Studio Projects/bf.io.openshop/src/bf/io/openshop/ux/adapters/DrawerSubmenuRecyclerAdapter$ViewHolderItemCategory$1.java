package bf.io.openshop.ux.adapters;

import bf.io.openshop.interfaces.*;
import android.view.*;

class DrawerSubmenuRecyclerAdapter$ViewHolderItemCategory$1 implements View$OnClickListener {
    final /* synthetic */ DrawerSubmenuRecyclerInterface val$drawerSubmenuRecyclerInterface;
    
    public void onClick(final View view) {
        this.val$drawerSubmenuRecyclerInterface.onSubCategorySelected(view, ViewHolderItemCategory.access$000(ViewHolderItemCategory.this));
    }
}