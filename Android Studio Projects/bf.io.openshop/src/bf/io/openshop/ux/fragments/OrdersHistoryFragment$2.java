package bf.io.openshop.ux.fragments;

import bf.io.openshop.utils.*;
import android.support.v7.widget.*;
import timber.log.*;

class OrdersHistoryFragment$2 extends EndlessRecyclerScrollListener {
    @Override
    public void onLoadMore(final int n) {
        if (OrdersHistoryFragment.access$000(OrdersHistoryFragment.this) != null && OrdersHistoryFragment.access$000(OrdersHistoryFragment.this).getLinks() != null && OrdersHistoryFragment.access$000(OrdersHistoryFragment.this).getLinks().getNext() != null) {
            OrdersHistoryFragment.access$100(OrdersHistoryFragment.this, OrdersHistoryFragment.access$000(OrdersHistoryFragment.this).getLinks().getNext());
            return;
        }
        Timber.d("CustomLoadMoreDataFromApi NO MORE DATA", new Object[0]);
    }
}