package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class ProductFragment$10 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        MsgUtils.logAndShowErrorMessage(ProductFragment.this.getActivity(), volleyError);
    }
}