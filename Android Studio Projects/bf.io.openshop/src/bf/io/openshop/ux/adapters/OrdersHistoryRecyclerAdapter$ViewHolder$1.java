package bf.io.openshop.ux.adapters;

import bf.io.openshop.interfaces.*;
import android.view.*;

class OrdersHistoryRecyclerAdapter$ViewHolder$1 implements View$OnClickListener {
    final /* synthetic */ OrdersRecyclerInterface val$ordersRecyclerInterface;
    
    public void onClick(final View view) {
        this.val$ordersRecyclerInterface.onOrderSelected(view, ViewHolder.access$300(ViewHolder.this));
    }
}