package bf.io.openshop.ux;

import com.android.volley.*;
import bf.io.openshop.entities.*;
import android.os.*;
import bf.io.openshop.*;
import bf.io.openshop.ux.dialogs.*;

class SplashActivity$2 implements Listener<Shop> {
    final /* synthetic */ String val$target;
    final /* synthetic */ String val$title;
    
    public void onResponse(final Shop shop) {
        SplashActivity.access$000(SplashActivity.this).cancel();
        final Bundle bundle = new Bundle();
        bundle.putString("target", this.val$target);
        bundle.putString("title", this.val$title);
        final Shop actualShop = SettingsMy.getActualShop();
        if (actualShop != null && shop.getId() != actualShop.getId()) {
            LoginDialogFragment.logoutUser();
        }
        SplashActivity.access$100(SplashActivity.this, shop, bundle);
    }
}