package bf.io.openshop.ux.adapters;

import bf.io.openshop.interfaces.*;
import android.view.*;

class ProductsRecyclerAdapter$ViewHolder$1 implements View$OnClickListener {
    final /* synthetic */ CategoryRecyclerInterface val$categoryRecyclerInterface;
    
    public void onClick(final View view) {
        this.val$categoryRecyclerInterface.onProductSelected(view, ViewHolder.access$000(ViewHolder.this));
    }
}