package bf.io.openshop.ux.dialogs;

import com.android.volley.*;

class DiscountDialogFragment$5 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (DiscountDialogFragment.this.requestListener != null) {
            DiscountDialogFragment.this.requestListener.requestFailed(volleyError);
        }
        DiscountDialogFragment.this.dismiss();
    }
}