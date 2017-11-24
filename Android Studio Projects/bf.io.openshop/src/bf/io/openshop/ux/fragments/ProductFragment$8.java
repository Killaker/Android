package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.*;
import bf.io.openshop.utils.*;
import android.app.*;

class ProductFragment$8 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        ProductFragment.access$1100(ProductFragment.this, CONST.VISIBLE.EMPTY);
        MsgUtils.logAndShowErrorMessage(ProductFragment.this.getActivity(), volleyError);
    }
}