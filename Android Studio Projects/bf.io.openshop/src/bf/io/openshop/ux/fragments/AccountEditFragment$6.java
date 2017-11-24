package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class AccountEditFragment$6 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (AccountEditFragment.access$700(AccountEditFragment.this) != null) {
            AccountEditFragment.access$700(AccountEditFragment.this).cancel();
        }
        MsgUtils.logAndShowErrorMessage(AccountEditFragment.this.getActivity(), volleyError);
    }
}