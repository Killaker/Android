package bf.io.openshop.ux.adapters;

import bf.io.openshop.interfaces.*;
import android.view.*;

class ProductImagesRecyclerAdapter$ViewHolder$1 implements View$OnClickListener {
    final /* synthetic */ ProductImagesRecyclerInterface val$productImagesRecyclerInterface;
    
    public void onClick(final View view) {
        if (this.val$productImagesRecyclerInterface != null) {
            this.val$productImagesRecyclerInterface.onImageSelected(view, ViewHolder.this.position);
        }
    }
}