package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.utils.*;
import android.app.*;

class OrderCreateFragment$10 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        OrderCreateFragment.access$1800(OrderCreateFragment.this).cancel();
        if (OrderCreateFragment.access$2500(OrderCreateFragment.this) != null && OrderCreateFragment.access$2500(OrderCreateFragment.this).getStatusCode() == 501) {
            OrderCreateSuccessDialogFragment.newInstance(true).show(OrderCreateFragment.this.getFragmentManager(), OrderCreateSuccessDialogFragment.class.getSimpleName());
            return;
        }
        MsgUtils.logAndShowErrorMessage(OrderCreateFragment.this.getActivity(), volleyError);
    }
}