package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.ux.*;
import android.support.v4.app.*;

class BannersFragment$1 implements BannersRecyclerInterface {
    @Override
    public void onBannerSelected(final Banner banner) {
        final FragmentActivity activity = BannersFragment.this.getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity)activity).onBannerSelected(banner);
        }
    }
}