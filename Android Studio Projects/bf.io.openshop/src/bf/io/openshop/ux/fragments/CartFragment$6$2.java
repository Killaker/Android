package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import org.json.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;

class CartFragment$6$2 implements Listener<JSONObject> {
    public void onResponse(final JSONObject jsonObject) {
        Timber.d("Delete item from cart: " + jsonObject.toString(), new Object[0]);
        CartFragment.access$000(CartRecyclerInterface.this.this$0);
        MsgUtils.showToast(CartRecyclerInterface.this.this$0.getActivity(), 1, CartRecyclerInterface.this.this$0.getString(2131230908), MsgUtils.ToastLength.LONG);
        if (CartFragment.access$100(CartRecyclerInterface.this.this$0) != null) {
            CartFragment.access$100(CartRecyclerInterface.this.this$0).cancel();
        }
    }
}