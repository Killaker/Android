package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import com.android.volley.*;

class WishlistFragment$5$1$1$1 implements RequestListener {
    @Override
    public void requestFailed(final VolleyError volleyError) {
        WishlistFragment.access$000(View$OnClickListener.this.this$2.this$1.this$0).hide();
    }
    
    @Override
    public void requestSuccess(final long id) {
        WishlistFragment.access$000(View$OnClickListener.this.this$2.this$1.this$0).hide();
        View$OnClickListener.this.this$2.val$wishlistItem.setId(id);
        WishlistFragment.access$100(View$OnClickListener.this.this$2.this$1.this$0).add(View$OnClickListener.this.this$2.val$adapterPosition, View$OnClickListener.this.this$2.val$wishlistItem);
        WishlistFragment.access$200(View$OnClickListener.this.this$2.this$1.this$0);
    }
}