package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import bf.io.openshop.*;
import bf.io.openshop.utils.*;
import android.app.*;

class AccountEditFragment$3 implements Listener<User> {
    public void onResponse(@NonNull final User activeUser) {
        SettingsMy.setActiveUser(activeUser);
        AccountEditFragment.access$600(AccountEditFragment.this, activeUser);
        AccountEditFragment.access$700(AccountEditFragment.this).cancel();
        MsgUtils.showToast(AccountEditFragment.this.getActivity(), 1, AccountEditFragment.this.getString(2131230854), MsgUtils.ToastLength.SHORT);
        AccountEditFragment.this.getFragmentManager().popBackStackImmediate();
    }
}