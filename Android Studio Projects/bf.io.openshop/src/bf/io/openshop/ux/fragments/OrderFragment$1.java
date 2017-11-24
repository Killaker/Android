package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.order.*;

class OrderFragment$1 implements Listener<Order> {
    public void onResponse(final Order order) {
        OrderFragment.access$000(OrderFragment.this).addOrder(order);
        if (OrderFragment.access$100(OrderFragment.this) != null) {
            OrderFragment.access$100(OrderFragment.this).cancel();
        }
    }
}