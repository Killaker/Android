package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.ux.*;

class OrderCreateFragment$1 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (OrderCreateFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)OrderCreateFragment.this.getActivity()).onTermsAndConditionsSelected();
        }
    }
}