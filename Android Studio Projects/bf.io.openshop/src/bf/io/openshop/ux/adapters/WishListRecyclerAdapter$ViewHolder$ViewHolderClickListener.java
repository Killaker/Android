package bf.io.openshop.ux.adapters;

import android.view.*;
import bf.io.openshop.entities.wishlist.*;

public interface ViewHolderClickListener
{
    void onProductSelected(final View p0, final WishlistItem p1);
    
    void onRemoveProductFromWishList(final View p0, final WishlistItem p1, final int p2);
}
