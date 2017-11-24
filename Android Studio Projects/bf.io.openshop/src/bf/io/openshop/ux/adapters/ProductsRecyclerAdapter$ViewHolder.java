package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.product.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import android.view.*;

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
