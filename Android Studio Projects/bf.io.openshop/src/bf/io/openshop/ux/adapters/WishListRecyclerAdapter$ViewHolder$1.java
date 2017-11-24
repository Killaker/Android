package bf.io.openshop.ux.adapters;

import android.view.*;
import android.support.v7.widget.*;

class WishListRecyclerAdapter$ViewHolder$1 implements View$OnClickListener {
    public void onClick(final View view) {
        ViewHolder.this.viewHolderClickListenerThis.onRemoveProductFromWishList(view, ViewHolder.access$100(ViewHolder.this), ((RecyclerView.ViewHolder)ViewHolder.this).getAdapterPosition());
    }
}