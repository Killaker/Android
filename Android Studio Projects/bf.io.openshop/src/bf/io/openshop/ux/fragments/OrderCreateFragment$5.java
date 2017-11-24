package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.cart.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;

class OrderCreateFragment$5 implements Listener<Cart> {
    final /* synthetic */ User val$user;
    
    public void onResponse(@NonNull final Cart cart) {
        if (OrderCreateFragment.access$1800(OrderCreateFragment.this) != null) {
            OrderCreateFragment.access$1800(OrderCreateFragment.this).cancel();
        }
        OrderCreateFragment.access$1900(OrderCreateFragment.this, cart, this.val$user);
    }
}