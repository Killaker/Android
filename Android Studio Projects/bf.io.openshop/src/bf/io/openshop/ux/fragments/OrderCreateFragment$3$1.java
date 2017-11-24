package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.delivery.*;

class OrderCreateFragment$3$1 implements ShippingDialogInterface {
    @Override
    public void onShippingSelected(final Shipping shipping) {
        OrderCreateFragment.access$702(View$OnClickListener.this.this$0, shipping);
        OrderCreateFragment.access$1300(View$OnClickListener.this.this$0, shipping);
        OrderCreateFragment.access$802(View$OnClickListener.this.this$0, null);
        OrderCreateFragment.access$1400(View$OnClickListener.this.this$0).setText((CharSequence)View$OnClickListener.this.this$0.getString(2131230801));
        OrderCreateFragment.access$1500(View$OnClickListener.this.this$0).setText((CharSequence)"");
        OrderCreateFragment.access$1600(View$OnClickListener.this.this$0).performClick();
    }
}