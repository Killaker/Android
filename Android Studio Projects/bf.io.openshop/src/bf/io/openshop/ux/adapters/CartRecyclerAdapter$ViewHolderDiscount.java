package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import bf.io.openshop.entities.cart.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.listeners.*;
import android.view.*;

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
