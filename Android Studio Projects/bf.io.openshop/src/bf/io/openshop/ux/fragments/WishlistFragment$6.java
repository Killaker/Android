package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import android.support.annotation.*;
import bf.io.openshop.entities.wishlist.*;

class WishlistFragment$6 implements Listener<WishlistResponse> {
    public void onResponse(@NonNull final WishlistResponse wishlistResponse) {
        if (WishlistFragment.access$000(WishlistFragment.this) != null) {
            WishlistFragment.access$000(WishlistFragment.this).cancel();
        }
        for (int i = 0; i < wishlistResponse.getItems().size(); ++i) {
            WishlistFragment.access$100(WishlistFragment.this).add(i, wishlistResponse.getItems().get(i));
        }
        WishlistFragment.access$200(WishlistFragment.this);
    }
}