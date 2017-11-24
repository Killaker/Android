package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class CategoryFragment$10 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (CategoryFragment.access$1300(CategoryFragment.this) != null) {
            CategoryFragment.access$1300(CategoryFragment.this).setVisibility(8);
        }
        CategoryFragment.access$1200(CategoryFragment.this);
        MsgUtils.logAndShowErrorMessage(CategoryFragment.this.getActivity(), volleyError);
    }
}