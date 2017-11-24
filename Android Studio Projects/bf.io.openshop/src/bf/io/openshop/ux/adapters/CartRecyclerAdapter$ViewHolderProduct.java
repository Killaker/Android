package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.widget.*;
import bf.io.openshop.entities.cart.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.listeners.*;
import android.view.*;

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
