package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.entities.order.*;
import bf.io.openshop.utils.*;
import android.view.inputmethod.*;

class OrderCreateFragment$2 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (OrderCreateFragment.access$000(OrderCreateFragment.this)) {
            final Order order = new Order();
            order.setName(Utils.getTextFromInputLayout(OrderCreateFragment.access$100(OrderCreateFragment.this)));
            order.setCity(Utils.getTextFromInputLayout(OrderCreateFragment.access$200(OrderCreateFragment.this)));
            order.setStreet(Utils.getTextFromInputLayout(OrderCreateFragment.access$300(OrderCreateFragment.this)));
            order.setHouseNumber(Utils.getTextFromInputLayout(OrderCreateFragment.access$400(OrderCreateFragment.this)));
            order.setZip(Utils.getTextFromInputLayout(OrderCreateFragment.access$500(OrderCreateFragment.this)));
            order.setEmail(Utils.getTextFromInputLayout(OrderCreateFragment.access$600(OrderCreateFragment.this)));
            order.setShippingType(OrderCreateFragment.access$700(OrderCreateFragment.this).getId());
            if (OrderCreateFragment.access$800(OrderCreateFragment.this) != null) {
                order.setPaymentType(OrderCreateFragment.access$800(OrderCreateFragment.this).getId());
            }
            else {
                order.setPaymentType(-1L);
            }
            order.setPhone(Utils.getTextFromInputLayout(OrderCreateFragment.access$900(OrderCreateFragment.this)));
            order.setNote(Utils.getTextFromInputLayout(OrderCreateFragment.access$1000(OrderCreateFragment.this)));
            view.clearFocus();
            ((InputMethodManager)OrderCreateFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            OrderCreateFragment.access$1100(OrderCreateFragment.this, order);
        }
    }
}