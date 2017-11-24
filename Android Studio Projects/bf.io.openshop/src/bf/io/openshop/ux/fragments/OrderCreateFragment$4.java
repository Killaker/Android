package bf.io.openshop.ux.fragments;

import android.view.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.delivery.*;
import bf.io.openshop.ux.dialogs.*;

class OrderCreateFragment$4 implements View$OnClickListener {
    public void onClick(final View view) {
        PaymentDialogFragment.newInstance(OrderCreateFragment.access$700(OrderCreateFragment.this), OrderCreateFragment.access$800(OrderCreateFragment.this), new PaymentDialogInterface() {
            @Override
            public void onPaymentSelected(final Payment payment) {
                OrderCreateFragment.access$802(OrderCreateFragment.this, payment);
                OrderCreateFragment.access$1700(OrderCreateFragment.this, payment);
            }
        }).show(OrderCreateFragment.this.getFragmentManager(), "PaymentDialog");
    }
}