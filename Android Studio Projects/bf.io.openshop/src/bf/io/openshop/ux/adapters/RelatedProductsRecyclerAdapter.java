package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.content.*;
import bf.io.openshop.entities.product.*;
import bf.io.openshop.interfaces.*;
import java.util.*;
import android.support.v4.content.*;
import com.squareup.picasso.*;
import timber.log.*;
import android.widget.*;
import android.view.*;

public class RelatedProductsRecyclerAdapter extends Adapter<ViewHolder>
{
    private final Context context;
    private LayoutInflater layoutInflater;
    private List<Product> relatedProducts;
    private final RelatedProductsRecyclerInterface relatedProductsRecyclerInterface;
    
    public RelatedProductsRecyclerAdapter(final Context context, final RelatedProductsRecyclerInterface relatedProductsRecyclerInterface) {
        this.context = context;
        this.relatedProductsRecyclerInterface = relatedProductsRecyclerInterface;
        this.relatedProducts = new ArrayList<Product>();
    }
    
    private Product getItem(final int n) {
        return this.relatedProducts.get(n);
    }
    
    public void add(final int n, final Product product) {
        this.relatedProducts.add(n, product);
        ((RecyclerView.Adapter)this).notifyItemInserted(n);
    }
    
    public void addLast(final Product product) {
        this.relatedProducts.add(this.relatedProducts.size(), product);
        ((RecyclerView.Adapter)this).notifyItemInserted(this.relatedProducts.size());
    }
    
    @Override
    public int getItemCount() {
        return this.relatedProducts.size();
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Product item = this.getItem(position);
        if (item != null) {
            viewHolder.setPosition(position);
            viewHolder.setProduct(item);
            viewHolder.productName.setText((CharSequence)item.getName());
            final double price = viewHolder.product.getPrice();
            final double discountPrice = viewHolder.product.getDiscountPrice();
            if (price == discountPrice || Math.abs(price - discountPrice) / Math.max(Math.abs(price), Math.abs(discountPrice)) < 1.0E-6) {
                viewHolder.productPrice.setVisibility(0);
                viewHolder.productDiscount.setVisibility(8);
                viewHolder.productPrice.setText((CharSequence)viewHolder.product.getPriceFormatted());
                viewHolder.productPrice.setPaintFlags(0xFFFFFFEF & viewHolder.productPrice.getPaintFlags());
                viewHolder.productPrice.setTextColor(ContextCompat.getColor(this.context, 2131558531));
            }
            else {
                viewHolder.productPrice.setVisibility(0);
                viewHolder.productDiscount.setVisibility(0);
                viewHolder.productPrice.setText((CharSequence)viewHolder.product.getPriceFormatted());
                viewHolder.productPrice.setPaintFlags(17);
                viewHolder.productPrice.setTextColor(ContextCompat.getColor(this.context, 2131558532));
                viewHolder.productDiscount.setText((CharSequence)viewHolder.product.getDiscountPriceFormatted());
            }
            Picasso.with(this.context).load(item.getMainImage()).fit().centerInside().placeholder(2130837697).error(2130837696).into(viewHolder.productImage);
            return;
        }
        Timber.e(RelatedProductsRecyclerAdapter.class.getSimpleName() + " tried setting null product!", new Object[0]);
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ViewHolder(this.layoutInflater.inflate(2130968658, viewGroup, false), this.relatedProductsRecyclerInterface);
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        int position;
        Product product;
        TextView productDiscount;
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        
        public ViewHolder(final View view, final RelatedProductsRecyclerInterface relatedProductsRecyclerInterface) {
            super(view);
            this.productImage = (ImageView)view.findViewById(2131624345);
            this.productName = (TextView)view.findViewById(2131624346);
            this.productPrice = (TextView)view.findViewById(2131624347);
            this.productDiscount = (TextView)view.findViewById(2131624348);
            view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    if (relatedProductsRecyclerInterface != null) {
                        relatedProductsRecyclerInterface.onRelatedProductSelected(view, ViewHolder.this.position, ViewHolder.this.product);
                    }
                }
            });
        }
        
        public void setPosition(final int position) {
            this.position = position;
        }
        
        public void setProduct(final Product product) {
            this.product = product;
        }
    }
}
