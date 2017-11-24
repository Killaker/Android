package bf.io.openshop.ux.fragments;

import android.view.*;
import bf.io.openshop.ux.*;
import android.support.v4.app.*;

class AccountFragment$3 implements View$OnClickListener {
    public void onClick(final View view) {
        final FragmentActivity activity = AccountFragment.this.getActivity();
        if (activity != null && activity instanceof MainActivity) {
            ((MainActivity)AccountFragment.this.getActivity()).startSettingsFragment();
        }
    }
}