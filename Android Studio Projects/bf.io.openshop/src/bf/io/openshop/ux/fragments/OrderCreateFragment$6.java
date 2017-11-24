package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;
import bf.io.openshop.ux.*;

class OrderCreateFragment$6 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (OrderCreateFragment.access$1800(OrderCreateFragment.this) != null) {
            OrderCreateFragment.access$1800(OrderCreateFragment.this).cancel();
        }
        Timber.e("Get request cart error: " + volleyError.getMessage(), new Object[0]);
        MsgUtils.logAndShowErrorMessage(OrderCreateFragment.this.getActivity(), volleyError);
        if (OrderCreateFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)OrderCreateFragment.this.getActivity()).onDrawerBannersSelected();
        }
    }
}