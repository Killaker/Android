package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class OrderFragment$2 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (OrderFragment.access$100(OrderFragment.this) != null) {
            OrderFragment.access$100(OrderFragment.this).cancel();
        }
        MsgUtils.logAndShowErrorMessage(OrderFragment.this.getActivity(), volleyError);
    }
}