package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import org.json.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;

class AccountEditFragment$5 implements Listener<JSONObject> {
    public void onResponse(final JSONObject jsonObject) {
        Timber.d("Change password successful: " + jsonObject.toString(), new Object[0]);
        MsgUtils.showToast(AccountEditFragment.this.getActivity(), 1, AccountEditFragment.this.getString(2131230854), MsgUtils.ToastLength.SHORT);
        if (AccountEditFragment.access$700(AccountEditFragment.this) != null) {
            AccountEditFragment.access$700(AccountEditFragment.this).cancel();
        }
        AccountEditFragment.this.getFragmentManager().popBackStackImmediate();
    }
}