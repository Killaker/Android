package bf.io.openshop.ux.dialogs;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class UpdateCartItemDialogFragment$4 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        UpdateCartItemDialogFragment.access$400(UpdateCartItemDialogFragment.this, false);
        MsgUtils.logAndShowErrorMessage(UpdateCartItemDialogFragment.this.getActivity(), volleyError);
    }
}