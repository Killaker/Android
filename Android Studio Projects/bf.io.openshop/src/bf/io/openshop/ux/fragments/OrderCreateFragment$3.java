package bf.io.openshop.ux.fragments;

import android.view.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.delivery.*;
import bf.io.openshop.ux.dialogs.*;

class OrderCreateFragment$3 implements View$OnClickListener {
    public void onClick(final View view) {
        ShippingDialogFragment.newInstance(OrderCreateFragment.access$1200(OrderCreateFragment.this), OrderCreateFragment.access$700(OrderCreateFragment.this), new ShippingDialogInterface() {
            @Override
            public void onShippingSelected(final Shipping shipping) {
                OrderCreateFragment.access$702(OrderCreateFragment.this, shipping);
                OrderCreateFragment.access$1300(OrderCreateFragment.this, shipping);
                OrderCreateFragment.access$802(OrderCreateFragment.this, null);
                OrderCreateFragment.access$1400(OrderCreateFragment.this).setText((CharSequence)OrderCreateFragment.this.getString(2131230801));
                OrderCreateFragment.access$1500(OrderCreateFragment.this).setText((CharSequence)"");
                OrderCreateFragment.access$1600(OrderCreateFragment.this).performClick();
            }
        }).show(OrderCreateFragment.this.getFragmentManager(), ShippingDialogFragment.class.getSimpleName());
    }
}