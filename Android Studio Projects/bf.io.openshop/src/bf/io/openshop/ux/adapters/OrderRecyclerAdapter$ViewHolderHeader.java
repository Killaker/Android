package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.widget.*;
import android.view.*;

public static class ViewHolderHeader extends ViewHolder
{
    public TextView orderDateCreated;
    public TextView orderId;
    public TextView orderName;
    public TextView orderShippingMethod;
    public TextView orderShippingPrice;
    public TextView orderTotal;
    
    public ViewHolderHeader(final View view) {
        super(view);
        this.orderId = (TextView)view.findViewById(2131624325);
        this.orderName = (TextView)view.findViewById(2131624326);
        this.orderDateCreated = (TextView)view.findViewById(2131624327);
        this.orderTotal = (TextView)view.findViewById(2131624328);
        this.orderShippingMethod = (TextView)view.findViewById(2131624329);
        this.orderShippingPrice = (TextView)view.findViewById(2131624330);
    }
}
