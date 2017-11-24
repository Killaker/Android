package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;
import bf.io.openshop.ux.*;

class OrderCreateFragment$8 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        Timber.e("Get request cart error: " + volleyError.getMessage(), new Object[0]);
        MsgUtils.logAndShowErrorMessage(OrderCreateFragment.this.getActivity(), volleyError);
        OrderCreateFragment.access$2000(OrderCreateFragment.this).setVisibility(8);
        if (OrderCreateFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)OrderCreateFragment.this.getActivity()).onDrawerBannersSelected();
        }
    }
}