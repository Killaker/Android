package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.content.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.wishlist.*;
import java.util.*;
import timber.log.*;
import com.squareup.picasso.*;
import android.support.v4.content.*;
import android.os.*;
import android.widget.*;
import android.view.*;

public class WishListRecyclerAdapter extends Adapter<ViewHolder>
{
    private final Context context;
    private final WishlistInterface wishlistInterface;
    private final List<WishlistItem> wishlistItems;
    
    public WishListRecyclerAdapter(final Context context, final WishlistInterface wishlistInterface) {
        this.wishlistItems = new ArrayList<WishlistItem>();
        this.context = context;
        this.wishlistInterface = wishlistInterface;
    }
    
    public void add(final int n, final WishlistItem wishlistItem) {
        ((RecyclerView.Adapter)this).notifyItemInserted(n);
        this.wishlistItems.add(n, wishlistItem);
    }
    
    public WishlistItem getItem(final int n) {
        return this.wishlistItems.get(n);
    }
    
    @Override
    public int getItemCount() {
        return this.wishlistItems.size();
    }
    
    public boolean isEmpty() {
        return this.wishlistItems.size() <= 0;
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final WishlistItem item = this.getItem(n);
        if (item.getVariant() == null) {
            Timber.e(new RuntimeException(), "Show wishlist item with null variant", new Object[0]);
            return;
        }
        viewHolder.bindContent(item);
        viewHolder.tvProductName.setText((CharSequence)viewHolder.wishlistItem.getVariant().getName());
        Picasso.with(this.context).load(viewHolder.wishlistItem.getVariant().getMainImage()).fit().centerInside().placeholder(2130837697).error(2130837696).into(viewHolder.ivThumb);
        final double price = viewHolder.wishlistItem.getVariant().getPrice();
        final double discountPrice = viewHolder.wishlistItem.getVariant().getDiscountPrice();
        if (price == discountPrice || Math.abs(price - discountPrice) / Math.max(Math.abs(price), Math.abs(discountPrice)) < 1.0E-6) {
            viewHolder.tvProductPrice.setVisibility(0);
            viewHolder.tvProductPriceDiscount.setVisibility(8);
            viewHolder.tvProductPrice.setText((CharSequence)viewHolder.wishlistItem.getVariant().getPriceFormatted());
            viewHolder.tvProductPrice.setPaintFlags(0xFFFFFFEF & viewHolder.tvProductPrice.getPaintFlags());
            viewHolder.tvProductPrice.setTextColor(ContextCompat.getColor(this.context, 2131558531));
            return;
        }
        viewHolder.tvProductPrice.setVisibility(0);
        viewHolder.tvProductPriceDiscount.setVisibility(0);
        viewHolder.tvProductPrice.setText((CharSequence)viewHolder.wishlistItem.getVariant().getPriceFormatted());
        viewHolder.tvProductPrice.setPaintFlags(17);
        viewHolder.tvProductPrice.setTextColor(ContextCompat.getColor(this.context, 2131558532));
        viewHolder.tvProductPriceDiscount.setText((CharSequence)viewHolder.wishlistItem.getVariant().getDiscountPriceFormatted());
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2130968662, viewGroup, false), (ViewHolderClickListener)new ViewHolderClickListener() {
            @Override
            public void onProductSelected(final View view, final WishlistItem wishlistItem) {
                Timber.d("Product click: " + wishlistItem.getVariant().getName(), new Object[0]);
                new Handler().postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        WishListRecyclerAdapter.this.wishlistInterface.onWishlistItemSelected(view, wishlistItem);
                    }
                }, 200L);
            }
            
            @Override
            public void onRemoveProductFromWishList(final View view, final WishlistItem wishlistItem, final int n) {
                WishListRecyclerAdapter.this.wishlistInterface.onRemoveItemFromWishList(view, wishlistItem, n);
            }
        });
    }
    
    public void remove(final int n) {
        if (this.wishlistItems.size() > n) {
            ((RecyclerView.Adapter)this).notifyItemRemoved(n);
            this.wishlistItems.remove(n);
            return;
        }
        Timber.e(new RuntimeException(), "Removing wishlist item at non existing position.", new Object[0]);
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ivThumb;
        public TextView tvProductName;
        public TextView tvProductPrice;
        public TextView tvProductPriceDiscount;
        public ViewHolderClickListener viewHolderClickListenerThis;
        public ImageView wishList;
        private WishlistItem wishlistItem;
        
        public ViewHolder(final View view, final ViewHolderClickListener viewHolderClickListenerThis) {
            super(view);
            this.wishlistItem = new WishlistItem();
            this.viewHolderClickListenerThis = viewHolderClickListenerThis;
            this.tvProductName = (TextView)view.findViewById(2131624358);
            this.tvProductPrice = (TextView)view.findViewById(2131624360);
            this.tvProductPriceDiscount = (TextView)view.findViewById(2131624361);
            this.ivThumb = (ImageView)view.findViewById(2131624357);
            (this.wishList = (ImageView)view.findViewById(2131624359)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    ViewHolder.this.viewHolderClickListenerThis.onRemoveProductFromWishList(view, ViewHolder.this.wishlistItem, ((RecyclerView.ViewHolder)ViewHolder.this).getAdapterPosition());
                }
            });
            view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    ViewHolder.this.viewHolderClickListenerThis.onProductSelected(view, ViewHolder.this.wishlistItem);
                }
            });
        }
        
        public void bindContent(final WishlistItem wishlistItem) {
            this.wishlistItem = wishlistItem;
        }
        
        public interface ViewHolderClickListener
        {
            void onProductSelected(final View p0, final WishlistItem p1);
            
            void onRemoveProductFromWishList(final View p0, final WishlistItem p1, final int p2);
        }
    }
}
