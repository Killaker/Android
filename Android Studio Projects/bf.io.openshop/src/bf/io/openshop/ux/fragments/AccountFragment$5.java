package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.ux.*;

class AccountFragment$5 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (SettingsMy.getActiveUser() != null) {
            LoginDialogFragment.logoutUser();
            AccountFragment.access$000(AccountFragment.this, null);
            return;
        }
        LoginDialogFragment.newInstance(new LoginDialogInterface() {
            @Override
            public void successfulLoginOrRegistration(final User user) {
                AccountFragment.access$000(AccountFragment.this, user);
                MainActivity.updateCartCountNotification();
            }
        }).show(AccountFragment.this.getFragmentManager(), LoginDialogFragment.class.getSimpleName());
    }
}