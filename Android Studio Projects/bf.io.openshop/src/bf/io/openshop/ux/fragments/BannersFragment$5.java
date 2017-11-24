package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class BannersFragment$5 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (BannersFragment.access$500(BannersFragment.this) != null) {
            BannersFragment.access$500(BannersFragment.this).cancel();
        }
        MsgUtils.logAndShowErrorMessage(BannersFragment.this.getActivity(), volleyError);
    }
}