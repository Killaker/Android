package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.interfaces.*;
import android.content.*;
import com.squareup.picasso.*;
import timber.log.*;
import bf.io.openshop.entities.cart.*;
import java.util.*;
import bf.io.openshop.listeners.*;
import android.view.*;
import android.widget.*;

public class CartRecyclerAdapter extends Adapter<ViewHolder>
{
    private static final int TYPE_ITEM_DISCOUNT = 1;
    private static final int TYPE_ITEM_PRODUCT;
    private final List<CartDiscountItem> cartDiscountItems;
    private final List<CartProductItem> cartProductItems;
    private final CartRecyclerInterface cartRecyclerInterface;
    private final Context context;
    LayoutInflater layoutInflater;
    
    public CartRecyclerAdapter(final Context context, final CartRecyclerInterface cartRecyclerInterface) {
        this.cartProductItems = new ArrayList<CartProductItem>();
        this.cartDiscountItems = new ArrayList<CartDiscountItem>();
        this.context = context;
        this.cartRecyclerInterface = cartRecyclerInterface;
    }
    
    private CartDiscountItem getCartDiscountItem(final int n) {
        return this.cartDiscountItems.get(n - this.cartProductItems.size());
    }
    
    private CartProductItem getCartProductItem(final int n) {
        return this.cartProductItems.get(n);
    }
    
    public void cleatCart() {
        this.cartProductItems.clear();
        this.cartDiscountItems.clear();
        ((RecyclerView.Adapter)this).notifyDataSetChanged();
    }
    
    @Override
    public int getItemCount() {
        return this.cartProductItems.size() + this.cartDiscountItems.size();
    }
    
    @Override
    public int getItemViewType(final int n) {
        if (n < this.cartProductItems.size()) {
            return 0;
        }
        return 1;
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        if (viewHolder instanceof ViewHolderProduct) {
            final ViewHolderProduct viewHolderProduct = (ViewHolderProduct)viewHolder;
            final CartProductItem cartProductItem = this.getCartProductItem(n);
            viewHolderProduct.bindContent(cartProductItem);
            viewHolderProduct.cartProductName.setText((CharSequence)cartProductItem.getVariant().getName());
            viewHolderProduct.cartProductPrice.setText((CharSequence)cartProductItem.getTotalItemPriceFormatted());
            viewHolderProduct.cartProductDetails.setText((CharSequence)this.context.getString(2131230942, new Object[] { cartProductItem.getVariant().getColor().getValue(), cartProductItem.getVariant().getSize().getValue() }));
            viewHolderProduct.cartProductQuantity.setText((CharSequence)this.context.getString(2131230940, new Object[] { cartProductItem.getQuantity() }));
            Picasso.with(this.context).load(cartProductItem.getVariant().getMainImage()).fit().centerInside().placeholder(2130837697).error(2130837696).into(viewHolderProduct.cartProductImage);
            return;
        }
        if (viewHolder instanceof ViewHolderDiscount) {
            final ViewHolderDiscount viewHolderDiscount = (ViewHolderDiscount)viewHolder;
            final CartDiscountItem cartDiscountItem = this.getCartDiscountItem(n);
            viewHolderDiscount.bindContent(cartDiscountItem);
            viewHolderDiscount.cartDiscountName.setText((CharSequence)cartDiscountItem.getDiscount().getName());
            viewHolderDiscount.cartDiscountValue.setText((CharSequence)cartDiscountItem.getDiscount().getValueFormatted());
            return;
        }
        Timber.e(new RuntimeException(), "Unknown ViewHolder in class: " + CartRecyclerAdapter.class.getSimpleName(), new Object[0]);
    }
    
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        if (n == 1) {
            return new ViewHolderDiscount(this.layoutInflater.inflate(2130968644, viewGroup, false), this.cartRecyclerInterface);
        }
        return new ViewHolderProduct(this.layoutInflater.inflate(2130968645, viewGroup, false), this.cartRecyclerInterface);
    }
    
    public void refreshItems(final Cart cart) {
        if (cart != null) {
            this.cartProductItems.clear();
            this.cartDiscountItems.clear();
            this.cartProductItems.addAll(cart.getItems());
            this.cartDiscountItems.addAll(cart.getDiscounts());
            ((RecyclerView.Adapter)this).notifyDataSetChanged();
            return;
        }
        Timber.e("Setting cart content with null cart", new Object[0]);
    }
    
    public static class ViewHolderDiscount extends ViewHolder
    {
        private CartDiscountItem cartDiscountItem;
        public TextView cartDiscountName;
        public TextView cartDiscountValue;
        
        public ViewHolderDiscount(final View view, final CartRecyclerInterface cartRecyclerInterface) {
            super(view);
            this.cartDiscountName = (TextView)view.findViewById(2131624303);
            this.cartDiscountValue = (TextView)view.findViewById(2131624304);
            view.findViewById(2131624305).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
                @Override
                public void onSingleClick(final View view) {
                    cartRecyclerInterface.onDiscountDelete(ViewHolderDiscount.this.cartDiscountItem);
                }
            });
        }
        
        public void bindContent(final CartDiscountItem cartDiscountItem) {
            this.cartDiscountItem = cartDiscountItem;
        }
    }
    
    public static class ViewHolderProduct extends ViewHolder
    {
        TextView cartProductDetails;
        ImageView cartProductImage;
        CartProductItem cartProductItem;
        TextView cartProductName;
        TextView cartProductPrice;
        TextView cartProductQuantity;
        
        public ViewHolderProduct(final View view, final CartRecyclerInterface cartRecyclerInterface) {
            super(view);
            this.cartProductImage = (ImageView)view.findViewById(2131624306);
            this.cartProductQuantity = (TextView)view.findViewById(2131624309);
            this.cartProductName = (TextView)view.findViewById(2131624307);
            this.cartProductPrice = (TextView)view.findViewById(2131624308);
            this.cartProductDetails = (TextView)view.findViewById(2131624310);
            view.findViewById(2131624312).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
                @Override
                public void onSingleClick(final View view) {
                    cartRecyclerInterface.onProductDelete(ViewHolderProduct.this.cartProductItem);
                }
            });
            view.findViewById(2131624313).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
                @Override
                public void onSingleClick(final View view) {
                    cartRecyclerInterface.onProductUpdate(ViewHolderProduct.this.cartProductItem);
                }
            });
            view.setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
                @Override
                public void onSingleClick(final View view) {
                    cartRecyclerInterface.onProductSelect(ViewHolderProduct.this.cartProductItem.getVariant().getProductId());
                }
            });
        }
        
        public void bindContent(final CartProductItem cartProductItem) {
            this.cartProductItem = cartProductItem;
        }
    }
}
