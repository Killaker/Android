package bf.io.openshop.ux.adapters;

import android.view.*;
import bf.io.openshop.entities.wishlist.*;

class WishListRecyclerAdapter$1$1 implements Runnable {
    final /* synthetic */ View val$caller;
    final /* synthetic */ WishlistItem val$wishlistItem;
    
    @Override
    public void run() {
        WishListRecyclerAdapter.access$000(ViewHolderClickListener.this.this$0).onWishlistItemSelected(this.val$caller, this.val$wishlistItem);
    }
}