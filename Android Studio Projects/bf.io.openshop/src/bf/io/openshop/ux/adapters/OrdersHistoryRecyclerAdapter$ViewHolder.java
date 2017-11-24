package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.order.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import android.view.*;

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
