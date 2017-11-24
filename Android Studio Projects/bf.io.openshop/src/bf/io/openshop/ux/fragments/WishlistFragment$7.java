package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class WishlistFragment$7 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (WishlistFragment.access$000(WishlistFragment.this) != null) {
            WishlistFragment.access$000(WishlistFragment.this).cancel();
        }
        MsgUtils.logAndShowErrorMessage(WishlistFragment.this.getActivity(), volleyError);
    }
}