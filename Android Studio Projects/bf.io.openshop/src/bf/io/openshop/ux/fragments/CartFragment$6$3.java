package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class CartFragment$6$3 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (CartFragment.access$100(CartRecyclerInterface.this.this$0) != null) {
            CartFragment.access$100(CartRecyclerInterface.this.this$0).cancel();
        }
        MsgUtils.logAndShowErrorMessage(CartRecyclerInterface.this.this$0.getActivity(), volleyError);
    }
}