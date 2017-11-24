package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.product.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import android.view.*;

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
