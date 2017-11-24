package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.delivery.*;
import android.support.annotation.*;
import timber.log.*;

class OrderCreateFragment$7 implements Listener<DeliveryRequest> {
    public void onResponse(@NonNull final DeliveryRequest deliveryRequest) {
        Timber.d("GetDelivery:" + deliveryRequest.toString(), new Object[0]);
        OrderCreateFragment.access$1202(OrderCreateFragment.this, deliveryRequest.getDelivery());
        OrderCreateFragment.access$2000(OrderCreateFragment.this).setVisibility(8);
        OrderCreateFragment.access$2100(OrderCreateFragment.this).setVisibility(0);
    }
}