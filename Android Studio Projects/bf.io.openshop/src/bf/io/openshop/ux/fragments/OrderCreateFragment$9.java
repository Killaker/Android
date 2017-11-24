package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.order.*;
import bf.io.openshop.entities.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.ux.*;
import bf.io.openshop.ux.dialogs.*;

class OrderCreateFragment$9 implements Listener<Order> {
    final /* synthetic */ User val$user;
    
    public void onResponse(final Order order) {
        Timber.d("response:" + order.toString(), new Object[0]);
        OrderCreateFragment.access$1800(OrderCreateFragment.this).cancel();
        Analytics.logOrderCreatedEvent(OrderCreateFragment.access$2200(OrderCreateFragment.this), order.getRemoteId(), OrderCreateFragment.access$2300(OrderCreateFragment.this), OrderCreateFragment.access$700(OrderCreateFragment.this));
        OrderCreateFragment.access$2400(OrderCreateFragment.this, this.val$user, order);
        MainActivity.updateCartCountNotification();
        OrderCreateSuccessDialogFragment.newInstance(false).show(OrderCreateFragment.this.getFragmentManager(), OrderCreateSuccessDialogFragment.class.getSimpleName());
    }
}