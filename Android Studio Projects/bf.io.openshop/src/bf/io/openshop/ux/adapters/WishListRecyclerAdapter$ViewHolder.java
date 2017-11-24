package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.widget.*;
import bf.io.openshop.entities.wishlist.*;
import android.view.*;

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
