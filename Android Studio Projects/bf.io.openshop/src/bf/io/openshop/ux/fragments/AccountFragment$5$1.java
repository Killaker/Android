package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.ux.*;

class AccountFragment$5$1 implements LoginDialogInterface {
    @Override
    public void successfulLoginOrRegistration(final User user) {
        AccountFragment.access$000(OnSingleClickListener.this.this$0, user);
        MainActivity.updateCartCountNotification();
    }
}