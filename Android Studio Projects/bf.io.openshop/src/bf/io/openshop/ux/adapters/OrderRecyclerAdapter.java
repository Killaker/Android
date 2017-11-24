package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.content.*;
import bf.io.openshop.entities.order.*;
import timber.log.*;
import com.squareup.picasso.*;
import bf.io.openshop.entities.cart.*;
import bf.io.openshop.utils.*;
import android.view.*;
import android.widget.*;

public class OrderRecyclerAdapter extends Adapter<ViewHolder>
{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM_ORDER = 1;
    private Context context;
    LayoutInflater layoutInflater;
    private Order order;
    
    public OrderRecyclerAdapter(final Context context) {
        this.context = context;
    }
    
    public void addOrder(final Order order) {
        if (order != null) {
            this.order = order;
            ((RecyclerView.Adapter)this).notifyDataSetChanged();
            return;
        }
        Timber.e("Setting null order object.", new Object[0]);
    }
    
    @Override
    public int getItemCount() {
        if (this.order == null) {
            return 0;
        }
        if (this.order.getProducts() != null && this.order.getProducts().size() > 0) {
            return 1 + this.order.getProducts().size();
        }
        return 1;
    }
    
    @Override
    public int getItemViewType(final int n) {
        if (n == 0) {
            return 0;
        }
        return 1;
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        if (viewHolder instanceof ViewHolderOrderProduct) {
            Picasso.with(this.context).load(this.order.getProducts().get(n - 1).getVariant().getMainImage()).fit().centerInside().placeholder(2130837697).error(2130837696).into(((ViewHolderOrderProduct)viewHolder).productImage);
            return;
        }
        if (viewHolder instanceof ViewHolderHeader) {
            final ViewHolderHeader viewHolderHeader = (ViewHolderHeader)viewHolder;
            viewHolderHeader.orderId.setText((CharSequence)this.order.getRemoteId());
            viewHolderHeader.orderName.setText((CharSequence)this.order.getName());
            viewHolderHeader.orderDateCreated.setText((CharSequence)Utils.parseDate(this.order.getDateCreated()));
            viewHolderHeader.orderTotal.setText((CharSequence)this.order.getTotalFormatted());
            viewHolderHeader.orderShippingMethod.setText((CharSequence)this.order.getShippingName());
            viewHolderHeader.orderShippingPrice.setText((CharSequence)this.order.getShippingPriceFormatted());
            return;
        }
        Timber.e(new RuntimeException(), "Unknown holder type.", new Object[0]);
    }
    
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        if (n == 1) {
            return new ViewHolderOrderProduct(this.layoutInflater.inflate(2130968653, viewGroup, false));
        }
        return new ViewHolderHeader(this.layoutInflater.inflate(2130968651, viewGroup, false));
    }
    
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
    
    public static class ViewHolderOrderProduct extends ViewHolder
    {
        ImageView productImage;
        
        public ViewHolderOrderProduct(final View view) {
            super(view);
            this.productImage = (ImageView)view.findViewById(2131624331);
        }
    }
}
