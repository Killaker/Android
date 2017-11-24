package bf.io.openshop.interfaces;

import android.view.*;
import bf.io.openshop.entities.wishlist.*;

public interface WishlistInterface
{
    void onRemoveItemFromWishList(final View p0, final WishlistItem p1, final int p2);
    
    void onWishlistItemSelected(final View p0, final WishlistItem p1);
}
