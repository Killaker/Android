package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;

class CartFragment$5 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (CartFragment.access$100(CartFragment.this) != null) {
            CartFragment.access$100(CartFragment.this).cancel();
        }
        CartFragment.access$200(CartFragment.this, false);
        Timber.e("Get request cart error: " + volleyError.getMessage(), new Object[0]);
        MsgUtils.logAndShowErrorMessage(CartFragment.this.getActivity(), volleyError);
    }
}