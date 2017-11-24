package bf.io.openshop.ux.dialogs;

import com.android.volley.*;
import org.json.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;

class DiscountDialogFragment$4 implements Listener<JSONObject> {
    public void onResponse(final JSONObject jsonObject) {
        Timber.d("Update item in cart: " + jsonObject.toString(), new Object[0]);
        MsgUtils.showToast(DiscountDialogFragment.this.getActivity(), 1, DiscountDialogFragment.this.getString(2131230854), MsgUtils.ToastLength.SHORT);
        if (DiscountDialogFragment.this.requestListener != null) {
            DiscountDialogFragment.this.requestListener.requestSuccess(0L);
        }
        DiscountDialogFragment.this.dismiss();
    }
}