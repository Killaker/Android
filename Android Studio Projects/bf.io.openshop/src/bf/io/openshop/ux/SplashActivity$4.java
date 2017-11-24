package bf.io.openshop.ux;

import android.view.*;
import bf.io.openshop.entities.*;
import android.os.*;
import timber.log.*;

class SplashActivity$4 implements View$OnClickListener {
    public void onClick(final View view) {
        final Shop shop = (Shop)SplashActivity.access$200(SplashActivity.this).getSelectedItem();
        if (shop != null && shop.getId() != -131L) {
            SplashActivity.access$100(SplashActivity.this, shop, null);
            return;
        }
        Timber.e("Cannot continue. Shop is not selected or is null.", new Object[0]);
    }
}