package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import timber.log.*;
import java.util.*;

class SettingsFragment$2 implements Listener<ShopResponse> {
    public void onResponse(@NonNull final ShopResponse shopResponse) {
        Timber.d("Available shops response:" + shopResponse.toString(), new Object[0]);
        SettingsFragment.access$000(SettingsFragment.this, shopResponse.getShopList());
        if (SettingsFragment.access$100(SettingsFragment.this) != null) {
            SettingsFragment.access$100(SettingsFragment.this).cancel();
        }
    }
}