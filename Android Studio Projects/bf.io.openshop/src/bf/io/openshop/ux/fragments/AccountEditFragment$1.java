package bf.io.openshop.ux.fragments;

import android.widget.*;
import android.view.*;

class AccountEditFragment$1 implements View$OnClickListener {
    final /* synthetic */ Button val$btnChangePassword;
    
    public void onClick(final View view) {
        if (AccountEditFragment.access$000(AccountEditFragment.this)) {
            AccountEditFragment.access$002(AccountEditFragment.this, false);
            AccountEditFragment.access$100(AccountEditFragment.this).setVisibility(8);
            AccountEditFragment.access$200(AccountEditFragment.this).setVisibility(0);
            this.val$btnChangePassword.setText((CharSequence)AccountEditFragment.this.getString(2131230798));
            return;
        }
        AccountEditFragment.access$002(AccountEditFragment.this, true);
        AccountEditFragment.access$100(AccountEditFragment.this).setVisibility(0);
        AccountEditFragment.access$200(AccountEditFragment.this).setVisibility(8);
        this.val$btnChangePassword.setText(2131230797);
    }
}