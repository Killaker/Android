package bf.io.openshop.ux.dialogs;

import com.android.volley.*;
import org.json.*;
import timber.log.*;

class UpdateCartItemDialogFragment$6 implements Listener<JSONObject> {
    public void onResponse(final JSONObject jsonObject) {
        Timber.d("Update item in cart: " + jsonObject.toString(), new Object[0]);
        if (UpdateCartItemDialogFragment.access$700(UpdateCartItemDialogFragment.this) != null) {
            UpdateCartItemDialogFragment.access$700(UpdateCartItemDialogFragment.this).requestSuccess(0L);
        }
        UpdateCartItemDialogFragment.access$400(UpdateCartItemDialogFragment.this, false);
        UpdateCartItemDialogFragment.this.dismiss();
    }
}