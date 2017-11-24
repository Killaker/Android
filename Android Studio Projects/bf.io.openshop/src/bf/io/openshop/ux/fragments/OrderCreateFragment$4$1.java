package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.delivery.*;

class OrderCreateFragment$4$1 implements PaymentDialogInterface {
    @Override
    public void onPaymentSelected(final Payment payment) {
        OrderCreateFragment.access$802(View$OnClickListener.this.this$0, payment);
        OrderCreateFragment.access$1700(View$OnClickListener.this.this$0, payment);
    }
}