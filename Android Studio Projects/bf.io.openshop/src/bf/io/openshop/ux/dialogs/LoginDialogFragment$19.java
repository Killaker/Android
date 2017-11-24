package bf.io.openshop.ux.dialogs;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class LoginDialogFragment$19 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (LoginDialogFragment.access$800(LoginDialogFragment.this) != null) {
            LoginDialogFragment.access$800(LoginDialogFragment.this).cancel();
        }
        MsgUtils.logAndShowErrorMessage(LoginDialogFragment.this.getActivity(), volleyError);
    }
}