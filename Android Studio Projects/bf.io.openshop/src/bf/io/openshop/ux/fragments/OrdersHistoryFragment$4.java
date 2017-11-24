package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class OrdersHistoryFragment$4 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (OrdersHistoryFragment.access$500(OrdersHistoryFragment.this) != null) {
            OrdersHistoryFragment.access$500(OrdersHistoryFragment.this).cancel();
        }
        MsgUtils.logAndShowErrorMessage(OrdersHistoryFragment.this.getActivity(), volleyError);
    }
}