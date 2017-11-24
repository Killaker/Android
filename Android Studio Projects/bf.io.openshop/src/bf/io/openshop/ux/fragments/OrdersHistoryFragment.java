package bf.io.openshop.ux.fragments;

import bf.io.openshop.ux.adapters.*;
import android.app.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.order.*;
import bf.io.openshop.ux.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import timber.log.*;
import android.view.*;
import android.os.*;
import bf.io.openshop.utils.*;
import android.content.*;
import android.support.annotation.*;

public class OrdersHistoryFragment extends Fragment
{
    private View content;
    private View empty;
    private EndlessRecyclerScrollListener endlessRecyclerScrollListener;
    private OrdersHistoryRecyclerAdapter ordersHistoryRecyclerAdapter;
    private Metadata ordersMetadata;
    private RecyclerView ordersRecycler;
    private ProgressDialog progressDialog;
    
    private void loadOrders(String format) {
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            this.progressDialog.show();
            if (format == null) {
                this.ordersHistoryRecyclerAdapter.clear();
                format = String.format(EndPoints.ORDERS, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
            }
            final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, (Class<Object>)OrderResponse.class, (Response.Listener<Object>)new Response.Listener<OrderResponse>() {
                public void onResponse(final OrderResponse orderResponse) {
                    OrdersHistoryFragment.this.ordersMetadata = orderResponse.getMetadata();
                    OrdersHistoryFragment.this.ordersHistoryRecyclerAdapter.addOrders(orderResponse.getOrders());
                    if (OrdersHistoryFragment.this.ordersHistoryRecyclerAdapter.getItemCount() > 0) {
                        OrdersHistoryFragment.this.empty.setVisibility(8);
                        OrdersHistoryFragment.this.content.setVisibility(0);
                    }
                    else {
                        OrdersHistoryFragment.this.empty.setVisibility(0);
                        OrdersHistoryFragment.this.content.setVisibility(8);
                    }
                    if (OrdersHistoryFragment.this.progressDialog != null) {
                        OrdersHistoryFragment.this.progressDialog.cancel();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (OrdersHistoryFragment.this.progressDialog != null) {
                        OrdersHistoryFragment.this.progressDialog.cancel();
                    }
                    MsgUtils.logAndShowErrorMessage(OrdersHistoryFragment.this.getActivity(), volleyError);
                }
            }, this.getFragmentManager(), activeUser.getAccessToken());
            gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            gsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "orders_history_requests");
            return;
        }
        new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    private void prepareOrdersHistoryRecycler(final View view) {
        this.ordersRecycler = (RecyclerView)view.findViewById(2131624263);
        this.ordersHistoryRecyclerAdapter = new OrdersHistoryRecyclerAdapter(new OrdersRecyclerInterface() {
            @Override
            public void onOrderSelected(final View view, final Order order) {
                final FragmentActivity activity = OrdersHistoryFragment.this.getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity)activity).onOrderSelected(order);
                }
            }
        });
        this.ordersRecycler.setAdapter((RecyclerView.Adapter)this.ordersHistoryRecyclerAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.ordersRecycler.getContext());
        this.ordersRecycler.setLayoutManager((RecyclerView.LayoutManager)layoutManager);
        this.ordersRecycler.setItemAnimator((RecyclerView.ItemAnimator)new DefaultItemAnimator());
        this.ordersRecycler.setHasFixedSize(true);
        this.ordersRecycler.addItemDecoration((RecyclerView.ItemDecoration)new RecyclerMarginDecorator(this.getResources().getDimensionPixelSize(2131361868)));
        this.endlessRecyclerScrollListener = new EndlessRecyclerScrollListener(layoutManager) {
            @Override
            public void onLoadMore(final int n) {
                if (OrdersHistoryFragment.this.ordersMetadata != null && OrdersHistoryFragment.this.ordersMetadata.getLinks() != null && OrdersHistoryFragment.this.ordersMetadata.getLinks().getNext() != null) {
                    OrdersHistoryFragment.this.loadOrders(OrdersHistoryFragment.this.ordersMetadata.getLinks().getNext());
                    return;
                }
                Timber.d("CustomLoadMoreDataFromApi NO MORE DATA", new Object[0]);
            }
        };
        this.ordersRecycler.addOnScrollListener((RecyclerView.OnScrollListener)this.endlessRecyclerScrollListener);
    }
    
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " onCreateView", new Object[0]);
        MainActivity.setActionBarTitle(this.getString(2131230860));
        final View inflate = layoutInflater.inflate(2130968637, viewGroup, false);
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.empty = inflate.findViewById(2131624261);
        this.content = inflate.findViewById(2131624262);
        this.prepareOrdersHistoryRecycler(inflate);
        this.loadOrders(null);
        return inflate;
    }
    
    @Override
    public void onDestroyView() {
        if (this.ordersRecycler != null) {
            this.ordersRecycler.clearOnScrollListeners();
        }
        super.onDestroyView();
    }
    
    @Override
    public void onStop() {
        if (this.progressDialog != null) {
            if (this.progressDialog.isShowing() && this.endlessRecyclerScrollListener != null) {
                this.endlessRecyclerScrollListener.resetLoading();
            }
            this.progressDialog.cancel();
        }
        MyApplication.getInstance().cancelPendingRequests("orders_history_requests");
        super.onStop();
    }
}
