package bf.io.openshop.ux.adapters;

import android.view.*;
import bf.io.openshop.entities.wishlist.*;
import timber.log.*;
import android.os.*;

class WishListRecyclerAdapter$1 implements ViewHolderClickListener {
    @Override
    public void onProductSelected(final View view, final WishlistItem wishlistItem) {
        Timber.d("Product click: " + wishlistItem.getVariant().getName(), new Object[0]);
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                WishListRecyclerAdapter.access$000(WishListRecyclerAdapter.this).onWishlistItemSelected(view, wishlistItem);
            }
        }, 200L);
    }
    
    @Override
    public void onRemoveProductFromWishList(final View view, final WishlistItem wishlistItem, final int n) {
        WishListRecyclerAdapter.access$000(WishListRecyclerAdapter.this).onRemoveItemFromWishList(view, wishlistItem, n);
    }
}