package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import android.view.*;
import bf.io.openshop.entities.order.*;
import bf.io.openshop.ux.*;
import android.support.v4.app.*;

class OrdersHistoryFragment$1 implements OrdersRecyclerInterface {
    @Override
    public void onOrderSelected(final View view, final Order order) {
        final FragmentActivity activity = OrdersHistoryFragment.this.getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity)activity).onOrderSelected(order);
        }
    }
}