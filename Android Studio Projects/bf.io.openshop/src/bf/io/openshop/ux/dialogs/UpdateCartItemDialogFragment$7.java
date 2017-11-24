package bf.io.openshop.ux.dialogs;

import com.android.volley.*;

class UpdateCartItemDialogFragment$7 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        UpdateCartItemDialogFragment.access$400(UpdateCartItemDialogFragment.this, false);
        if (UpdateCartItemDialogFragment.access$700(UpdateCartItemDialogFragment.this) != null) {
            UpdateCartItemDialogFragment.access$700(UpdateCartItemDialogFragment.this).requestFailed(volleyError);
        }
        UpdateCartItemDialogFragment.this.dismiss();
    }
}