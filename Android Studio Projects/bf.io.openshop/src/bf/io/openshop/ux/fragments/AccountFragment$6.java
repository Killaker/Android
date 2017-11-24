package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import timber.log.*;
import bf.io.openshop.*;

class AccountFragment$6 implements Listener<User> {
    public void onResponse(@NonNull final User activeUser) {
        Timber.d("response:" + activeUser.toString(), new Object[0]);
        SettingsMy.setActiveUser(activeUser);
        AccountFragment.access$000(AccountFragment.this, SettingsMy.getActiveUser());
        if (AccountFragment.access$100(AccountFragment.this) != null) {
            AccountFragment.access$100(AccountFragment.this).cancel();
        }
    }
}