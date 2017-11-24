package bf.io.openshop.ux;

import com.android.volley.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import timber.log.*;

class SplashActivity$6 implements Listener<ShopResponse> {
    public void onResponse(@NonNull final ShopResponse shopResponse) {
        Timber.d("Get shops response: " + shopResponse.toString(), new Object[0]);
        SplashActivity.this.setSpinShops(shopResponse.getShopList());
        if (SplashActivity.access$000(SplashActivity.this) != null) {
            SplashActivity.access$000(SplashActivity.this).cancel();
        }
        SplashActivity.access$400(SplashActivity.this);
    }
}