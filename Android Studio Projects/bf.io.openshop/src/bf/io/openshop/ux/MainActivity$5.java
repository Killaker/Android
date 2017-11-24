package bf.io.openshop.ux;

import com.android.volley.*;
import bf.io.openshop.entities.cart.*;
import timber.log.*;

class MainActivity$5 implements Listener<CartInfo> {
    public void onResponse(final CartInfo cartInfo) {
        Timber.d("getCartCount: " + cartInfo.toString(), new Object[0]);
        MainActivity.access$000(MainActivity.this, cartInfo.getProductCount());
    }
}