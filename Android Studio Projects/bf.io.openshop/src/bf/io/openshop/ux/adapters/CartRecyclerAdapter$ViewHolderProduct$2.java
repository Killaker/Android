package bf.io.openshop.ux.adapters;

import bf.io.openshop.listeners.*;
import bf.io.openshop.interfaces.*;
import android.view.*;

class CartRecyclerAdapter$ViewHolderProduct$2 extends OnSingleClickListener {
    final /* synthetic */ CartRecyclerInterface val$cartRecyclerInterface;
    
    @Override
    public void onSingleClick(final View view) {
        this.val$cartRecyclerInterface.onProductUpdate(ViewHolderProduct.this.cartProductItem);
    }
}