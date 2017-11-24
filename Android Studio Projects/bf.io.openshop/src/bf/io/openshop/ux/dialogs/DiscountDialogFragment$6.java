package bf.io.openshop.ux.dialogs;

import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;

class DiscountDialogFragment$6 implements Runnable {
    final /* synthetic */ JsonRequest val$req;
    
    @Override
    public void run() {
        MyApplication.getInstance().addToRequestQueue((Request<Object>)this.val$req, "cart_discounts_requests");
    }
}