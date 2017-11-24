package bf.io.openshop.ux.fragments;

import android.support.v4.app.*;
import bf.io.openshop.ux.adapters.*;
import android.app.*;
import bf.io.openshop.entities.order.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.entities.*;
import android.os.*;
import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import timber.log.*;
import bf.io.openshop.utils.*;

public class OrderFragment extends Fragment
{
    public static final String ORDER_ID = "order_id";
    private OrderRecyclerAdapter orderRecyclerAdapter;
    private ProgressDialog progressDialog;
    
    private void loadOrderDetail(final long n) {
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            final String format = String.format(EndPoints.ORDERS_SINGLE, SettingsMy.getActualNonNullShop(this.getActivity()).getId(), n);
            this.progressDialog.show();
            final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, Order.class, new Response.Listener<Order>() {
                public void onResponse(final Order order) {
                    OrderFragment.this.orderRecyclerAdapter.addOrder(order);
                    if (OrderFragment.this.progressDialog != null) {
                        OrderFragment.this.progressDialog.cancel();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (OrderFragment.this.progressDialog != null) {
                        OrderFragment.this.progressDialog.cancel();
                    }
                    MsgUtils.logAndShowErrorMessage(OrderFragment.this.getActivity(), volleyError);
                }
            }, this.getFragmentManager(), activeUser.getAccessToken());
            gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            gsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "orders_detail_requests");
            return;
        }
        new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    public static OrderFragment newInstance(final long n) {
        final Bundle arguments = new Bundle();
        arguments.putLong("order_id", n);
        final OrderFragment orderFragment = new OrderFragment();
        orderFragment.setArguments(arguments);
        return orderFragment;
    }
    
    private void prepareOrder(final View view) {
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(2131624231);
        recyclerView.setAdapter((RecyclerView.Adapter)(this.orderRecyclerAdapter = new OrderRecyclerAdapter((Context)this.getActivity())));
        recyclerView.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator((RecyclerView.ItemAnimator)new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration((RecyclerView.ItemDecoration)new RecyclerMarginDecorator(this.getResources().getDimensionPixelSize(2131361868)));
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968635, viewGroup, false);
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.prepareOrder(inflate);
        if (this.getArguments() != null) {
            this.loadOrderDetail(this.getArguments().getLong("order_id"));
            return inflate;
        }
        Timber.e("Missing arguments with orderId", new Object[0]);
        return inflate;
    }
    
    @Override
    public void onStop() {
        if (this.progressDialog != null) {
            this.progressDialog.cancel();
        }
        MyApplication.getInstance().cancelPendingRequests("orders_history_requests");
        super.onStop();
    }
}
