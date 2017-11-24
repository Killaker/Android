package bf.io.openshop.ux.fragments;

import bf.io.openshop.utils.*;
import android.support.v7.widget.*;
import timber.log.*;

class CategoryFragment$4 extends EndlessRecyclerScrollListener {
    @Override
    public void onLoadMore(final int n) {
        Timber.e("Load more", new Object[0]);
        if (CategoryFragment.access$500(CategoryFragment.this) != null && CategoryFragment.access$500(CategoryFragment.this).getLinks() != null && CategoryFragment.access$500(CategoryFragment.this).getLinks().getNext() != null) {
            CategoryFragment.access$400(CategoryFragment.this, CategoryFragment.access$500(CategoryFragment.this).getLinks().getNext());
            return;
        }
        Timber.d("CustomLoadMoreDataFromApi NO MORE DATA", new Object[0]);
    }
}