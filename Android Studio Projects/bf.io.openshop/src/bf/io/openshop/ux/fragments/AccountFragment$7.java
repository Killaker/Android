package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class AccountFragment$7 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (AccountFragment.access$100(AccountFragment.this) != null) {
            AccountFragment.access$100(AccountFragment.this).cancel();
        }
        MsgUtils.logAndShowErrorMessage(AccountFragment.this.getActivity(), volleyError);
    }
}