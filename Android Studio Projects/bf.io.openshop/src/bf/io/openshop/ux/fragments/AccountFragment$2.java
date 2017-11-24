package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.ux.*;

class AccountFragment$2 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (AccountFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)AccountFragment.this.getActivity()).onOrdersHistory();
        }
    }
}