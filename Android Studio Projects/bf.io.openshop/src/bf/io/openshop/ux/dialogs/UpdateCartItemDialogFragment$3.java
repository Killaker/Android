package bf.io.openshop.ux.dialogs;

import com.android.volley.*;
import bf.io.openshop.entities.product.*;
import android.support.annotation.*;

class UpdateCartItemDialogFragment$3 implements Listener<Product> {
    public void onResponse(@NonNull final Product product) {
        UpdateCartItemDialogFragment.access$400(UpdateCartItemDialogFragment.this, false);
        UpdateCartItemDialogFragment.access$500(UpdateCartItemDialogFragment.this, product);
    }
}