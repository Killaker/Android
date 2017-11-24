package bf.io.openshop.ux.fragments;

import bf.io.openshop.utils.*;
import android.support.v7.widget.*;
import timber.log.*;

class BannersFragment$2 extends EndlessRecyclerScrollListener {
    @Override
    public void onLoadMore(final int n) {
        if (BannersFragment.access$000(BannersFragment.this) != null && BannersFragment.access$000(BannersFragment.this).getLinks() != null && BannersFragment.access$000(BannersFragment.this).getLinks().getNext() != null) {
            BannersFragment.access$100(BannersFragment.this, BannersFragment.access$000(BannersFragment.this).getLinks().getNext());
            return;
        }
        Timber.d("CustomLoadMoreDataFromApi NO MORE DATA", new Object[0]);
    }
}