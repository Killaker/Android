package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;

class CartFragment$2$1 implements RequestListener {
    @Override
    public void requestFailed(final VolleyError volleyError) {
        MsgUtils.logAndShowErrorMessage(OnSingleClickListener.this.this$0.getActivity(), volleyError);
    }
    
    @Override
    public void requestSuccess(final long n) {
        CartFragment.access$000(OnSingleClickListener.this.this$0);
    }
}