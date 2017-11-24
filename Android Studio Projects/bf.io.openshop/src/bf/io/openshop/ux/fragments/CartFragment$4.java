package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.cart.*;
import android.support.annotation.*;
import bf.io.openshop.ux.*;

class CartFragment$4 implements Listener<Cart> {
    public void onResponse(@NonNull final Cart cart) {
        if (CartFragment.access$100(CartFragment.this) != null) {
            CartFragment.access$100(CartFragment.this).cancel();
        }
        MainActivity.updateCartCountNotification();
        if (cart.getItems() == null || cart.getItems().size() == 0) {
            CartFragment.access$200(CartFragment.this, false);
            return;
        }
        CartFragment.access$200(CartFragment.this, true);
        CartFragment.access$300(CartFragment.this).refreshItems(cart);
        CartFragment.access$400(CartFragment.this).setText((CharSequence)CartFragment.this.getString(2131230940, cart.getProductCount()));
        CartFragment.access$500(CartFragment.this).setText((CharSequence)cart.getTotalPriceFormatted());
    }
}