package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.order.*;

class OrdersHistoryFragment$3 implements Listener<OrderResponse> {
    public void onResponse(final OrderResponse orderResponse) {
        OrdersHistoryFragment.access$002(OrdersHistoryFragment.this, orderResponse.getMetadata());
        OrdersHistoryFragment.access$200(OrdersHistoryFragment.this).addOrders(orderResponse.getOrders());
        if (OrdersHistoryFragment.access$200(OrdersHistoryFragment.this).getItemCount() > 0) {
            OrdersHistoryFragment.access$300(OrdersHistoryFragment.this).setVisibility(8);
            OrdersHistoryFragment.access$400(OrdersHistoryFragment.this).setVisibility(0);
        }
        else {
            OrdersHistoryFragment.access$300(OrdersHistoryFragment.this).setVisibility(0);
            OrdersHistoryFragment.access$400(OrdersHistoryFragment.this).setVisibility(8);
        }
        if (OrdersHistoryFragment.access$500(OrdersHistoryFragment.this) != null) {
            OrdersHistoryFragment.access$500(OrdersHistoryFragment.this).cancel();
        }
    }
}