package bf.io.openshop.ux.adapters;

import bf.io.openshop.interfaces.*;
import android.view.*;

class RelatedProductsRecyclerAdapter$ViewHolder$1 implements View$OnClickListener {
    final /* synthetic */ RelatedProductsRecyclerInterface val$relatedProductsRecyclerInterface;
    
    public void onClick(final View view) {
        if (this.val$relatedProductsRecyclerInterface != null) {
            this.val$relatedProductsRecyclerInterface.onRelatedProductSelected(view, ViewHolder.this.position, ViewHolder.this.product);
        }
    }
}