package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.widget.*;
import android.view.*;

public static class ViewHolderOrderProduct extends ViewHolder
{
    ImageView productImage;
    
    public ViewHolderOrderProduct(final View view) {
        super(view);
        this.productImage = (ImageView)view.findViewById(2131624331);
    }
}
