package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class ProductFragment$14 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (ProductFragment.access$1800(ProductFragment.this) != null) {
            ProductFragment.access$1800(ProductFragment.this).setVisibility(0);
        }
        if (ProductFragment.access$1900(ProductFragment.this) != null) {
            ProductFragment.access$1900(ProductFragment.this).setVisibility(4);
        }
        MsgUtils.logAndShowErrorMessage(ProductFragment.this.getActivity(), volleyError);
    }
}