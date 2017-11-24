package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import android.view.inputmethod.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.app.*;

class AccountEditFragment$2 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        while (true) {
            Label_0104: {
                if (AccountEditFragment.access$000(AccountEditFragment.this)) {
                    break Label_0104;
                }
                try {
                    AccountEditFragment.access$400(AccountEditFragment.this, AccountEditFragment.access$300(AccountEditFragment.this));
                    if (AccountEditFragment.this.getActivity().getCurrentFocus() != null) {
                        ((InputMethodManager)AccountEditFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(AccountEditFragment.this.getActivity().getCurrentFocus().getWindowToken(), 0);
                    }
                    return;
                }
                catch (Exception ex) {
                    Timber.e(ex, "Update user information exception.", new Object[0]);
                    MsgUtils.showToast(AccountEditFragment.this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
                    continue;
                }
            }
            AccountEditFragment.access$500(AccountEditFragment.this);
            continue;
        }
    }
}