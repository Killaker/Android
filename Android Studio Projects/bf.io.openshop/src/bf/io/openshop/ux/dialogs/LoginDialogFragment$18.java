package bf.io.openshop.ux.dialogs;

import com.android.volley.*;
import org.json.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;

class LoginDialogFragment$18 implements Listener<JSONObject> {
    public void onResponse(final JSONObject jsonObject) {
        Timber.d("Reset password on url success. Response:" + jsonObject.toString(), new Object[0]);
        LoginDialogFragment.access$800(LoginDialogFragment.this).cancel();
        MsgUtils.showToast(LoginDialogFragment.this.getActivity(), 1, LoginDialogFragment.this.getString(2131230800), MsgUtils.ToastLength.LONG);
        LoginDialogFragment.access$200(LoginDialogFragment.this, false);
    }
}