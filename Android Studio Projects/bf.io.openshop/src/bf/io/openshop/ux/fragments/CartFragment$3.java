package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.ux.*;

class CartFragment$3 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (CartFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)CartFragment.this.getActivity()).onOrderCreateSelected();
        }
    }
}