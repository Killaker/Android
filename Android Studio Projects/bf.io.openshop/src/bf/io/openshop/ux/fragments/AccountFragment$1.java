package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.ux.*;

class AccountFragment$1 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (AccountFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)AccountFragment.this.getActivity()).onAccountEditSelected();
        }
    }
}