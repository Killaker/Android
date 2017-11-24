package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.interfaces.*;
import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.app.*;
import bf.io.openshop.ux.dialogs.*;

class CartFragment$2 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        final DiscountDialogFragment instance = DiscountDialogFragment.newInstance(new RequestListener() {
            @Override
            public void requestFailed(final VolleyError volleyError) {
                MsgUtils.logAndShowErrorMessage(CartFragment.this.getActivity(), volleyError);
            }
            
            @Override
            public void requestSuccess(final long n) {
                CartFragment.access$000(CartFragment.this);
            }
        });
        if (instance != null) {
            instance.show(CartFragment.this.getFragmentManager(), DiscountDialogFragment.class.getSimpleName());
        }
    }
}