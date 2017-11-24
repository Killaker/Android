package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.interfaces.*;
import android.content.*;
import bf.io.openshop.entities.product.*;
import java.util.*;
import bf.io.openshop.*;
import timber.log.*;
import com.squareup.picasso.*;
import android.support.v4.content.*;
import android.widget.*;
import android.view.*;

public class ProductsRecyclerAdapter extends Adapter<ViewHolder>
{
    private final CategoryRecyclerInterface categoryRecyclerInterface;
    private final Context context;
    private LayoutInflater layoutInflater;
    private boolean loadHighRes;
    private List<Product> products;
    
    public ProductsRecyclerAdapter(final Context context, final CategoryRecyclerInterface categoryRecyclerInterface) {
        this.products = new ArrayList<Product>();
        this.loadHighRes = false;
        this.context = context;
        this.categoryRecyclerInterface = categoryRecyclerInterface;
        this.defineImagesQuality(false);
    }
    
    public void addProducts(final List<Product> list) {
        this.products.addAll(list);
        ((RecyclerView.Adapter)this).notifyDataSetChanged();
    }
    
    public void clear() {
        this.products.clear();
    }
    
    public void defineImagesQuality(final boolean b) {
        boolean b2 = true;
        final int densityDpi = this.context.getResources().getDisplayMetrics().densityDpi;
        if (b) {
            if ((0xF & this.context.getResources().getConfiguration().screenLayout) < 2 || densityDpi < 320) {
                b2 = false;
            }
            this.loadHighRes = b2;
        }
        else {
            if ((0xF & this.context.getResources().getConfiguration().screenLayout) < 4 || densityDpi < 240 || !MyApplication.getInstance().isWiFiConnection()) {
                b2 = false;
            }
            this.loadHighRes = b2;
        }
        Timber.d("Image high quality selected: " + this.loadHighRes, new Object[0]);
        ((RecyclerView.Adapter)this).notifyDataSetChanged();
    }
    
    public Product getItem(final int n) {
        return this.products.get(n);
    }
    
    @Override
    public int getItemCount() {
        return this.products.size();
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final Product item = this.getItem(n);
        viewHolder.bindContent(item);
        viewHolder.productNameTV.setText((CharSequence)viewHolder.product.getName());
        if (this.loadHighRes && item.getMainImageHighRes() != null) {
            Picasso.with(this.context).load(item.getMainImageHighRes()).fit().centerInside().placeholder(2130837697).error(2130837696).into(viewHolder.productImage);
        }
        else {
            Picasso.with(this.context).load(viewHolder.product.getMainImage()).fit().centerInside().placeholder(2130837697).error(2130837696).into(viewHolder.productImage);
        }
        final double price = viewHolder.product.getPrice();
        final double discountPrice = viewHolder.product.getDiscountPrice();
        if (price == discountPrice || Math.abs(price - discountPrice) / Math.max(Math.abs(price), Math.abs(discountPrice)) < 1.0E-6) {
            viewHolder.productPriceTV.setVisibility(0);
            viewHolder.productPriceDiscountTV.setVisibility(8);
            viewHolder.productPriceTV.setText((CharSequence)viewHolder.product.getPriceFormatted());
            viewHolder.productPriceTV.setPaintFlags(0xFFFFFFEF & viewHolder.productPriceTV.getPaintFlags());
            viewHolder.productPriceTV.setTextColor(ContextCompat.getColor(this.context, 2131558531));
            return;
        }
        viewHolder.productPriceTV.setVisibility(0);
        viewHolder.productPriceDiscountTV.setVisibility(0);
        viewHolder.productPriceTV.setText((CharSequence)viewHolder.product.getPriceFormatted());
        viewHolder.productPriceTV.setPaintFlags(17);
        viewHolder.productPriceTV.setTextColor(ContextCompat.getColor(this.context, 2131558532));
        viewHolder.productPriceDiscountTV.setText((CharSequence)viewHolder.product.getDiscountPriceFormatted());
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ViewHolder(this.layoutInflater.inflate(2130968657, viewGroup, false), this.categoryRecyclerInterface);
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private Product product;
        public ImageView productImage;
        public TextView productNameTV;
        public TextView productPriceDiscountTV;
        public TextView productPriceTV;
        
        public ViewHolder(final View view, final CategoryRecyclerInterface categoryRecyclerInterface) {
            super(view);
            this.productNameTV = (TextView)view.findViewById(2131624342);
            this.productPriceTV = (TextView)view.findViewById(2131624343);
            this.productPriceDiscountTV = (TextView)view.findViewById(2131624344);
            this.productImage = (ImageView)view.findViewById(2131624340);
            view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    categoryRecyclerInterface.onProductSelected(view, ViewHolder.this.product);
                }
            });
        }
        
        public void bindContent(final Product product) {
            this.product = product;
        }
    }
}
