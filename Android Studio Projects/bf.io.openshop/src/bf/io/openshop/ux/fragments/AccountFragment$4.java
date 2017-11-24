package bf.io.openshop.ux.fragments;

import android.view.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.delivery.*;
import bf.io.openshop.ux.dialogs.*;

class AccountFragment$4 implements View$OnClickListener {
    public void onClick(final View view) {
        ShippingDialogFragment.newInstance(new ShippingDialogInterface() {
            @Override
            public void onShippingSelected(final Shipping shipping) {
            }
        }).show(AccountFragment.this.getFragmentManager(), "shippingDialogFragment");
    }
}