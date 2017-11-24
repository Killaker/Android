package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.order.*;
import bf.io.openshop.interfaces.*;
import java.util.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import android.widget.*;
import android.view.*;

public class OrdersHistoryRecyclerAdapter extends Adapter<ViewHolder>
{
    LayoutInflater layoutInflater;
    private List<Order> orders;
    private final OrdersRecyclerInterface ordersRecyclerInterface;
    
    public OrdersHistoryRecyclerAdapter(final OrdersRecyclerInterface ordersRecyclerInterface) {
        this.orders = new ArrayList<Order>();
        this.ordersRecyclerInterface = ordersRecyclerInterface;
    }
    
    private Order getOrderItem(final int n) {
        return this.orders.get(n);
    }
    
    public void addOrders(final List<Order> list) {
        if (list != null && !list.isEmpty()) {
            this.orders.addAll(list);
            ((RecyclerView.Adapter)this).notifyDataSetChanged();
            return;
        }
        Timber.e("Adding empty orders list.", new Object[0]);
    }
    
    public void clear() {
        this.orders.clear();
    }
    
    @Override
    public int getItemCount() {
        return this.orders.size();
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final Order orderItem = this.getOrderItem(n);
        viewHolder.bindContent(orderItem);
        viewHolder.orderIdTv.setText((CharSequence)orderItem.getRemoteId());
        viewHolder.orderDateCreatedTv.setText((CharSequence)Utils.parseDate(orderItem.getDateCreated()));
        viewHolder.orderTotalPriceTv.setText((CharSequence)orderItem.getTotalFormatted());
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ViewHolder(this.layoutInflater.inflate(2130968654, viewGroup, false), this.ordersRecyclerInterface);
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private Order order;
        private TextView orderDateCreatedTv;
        private TextView orderIdTv;
        private TextView orderTotalPriceTv;
        
        public ViewHolder(final View view, final OrdersRecyclerInterface ordersRecyclerInterface) {
            super(view);
            this.orderIdTv = (TextView)view.findViewById(2131624332);
            this.orderDateCreatedTv = (TextView)view.findViewById(2131624333);
            this.orderTotalPriceTv = (TextView)view.findViewById(2131624334);
            view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    ordersRecyclerInterface.onOrderSelected(view, ViewHolder.this.order);
                }
            });
        }
        
        public void bindContent(final Order order) {
            this.order = order;
        }
    }
}
