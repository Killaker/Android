package bf.io.openshop.ux.adapters;

import bf.io.openshop.interfaces.*;
import android.view.*;

class DrawerRecyclerAdapter$ViewHolderItemPage$1 implements View$OnClickListener {
    final /* synthetic */ DrawerRecyclerInterface val$drawerRecyclerInterface;
    
    public void onClick(final View view) {
        this.val$drawerRecyclerInterface.onPageSelected(view, ViewHolderItemPage.access$100(ViewHolderItemPage.this));
    }
}