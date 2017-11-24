package bf.io.openshop.ux.dialogs;

import com.android.volley.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;

class ShippingDialogFragment$4 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        Timber.e("Get branches error: " + volleyError.getMessage(), new Object[0]);
        ShippingDialogFragment.access$000(ShippingDialogFragment.this, true);
        MsgUtils.logAndShowErrorMessage(ShippingDialogFragment.this.getActivity(), volleyError);
    }
}