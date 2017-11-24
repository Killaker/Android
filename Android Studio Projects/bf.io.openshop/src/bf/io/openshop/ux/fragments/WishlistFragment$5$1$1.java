package bf.io.openshop.ux.fragments;

import android.view.*;
import bf.io.openshop.*;
import bf.io.openshop.interfaces.*;
import com.android.volley.*;

class WishlistFragment$5$1$1 implements View$OnClickListener {
    public void onClick(final View view) {
        WishlistFragment.access$000(RequestListener.this.this$1.this$0).show();
        WishlistFragment.addToWishList(RequestListener.this.this$1.this$0.getActivity(), RequestListener.this.val$wishlistItem.getVariant().getId(), SettingsMy.getActiveUser(), "wishlist_requests", new RequestListener() {
            @Override
            public void requestFailed(final VolleyError volleyError) {
                WishlistFragment.access$000(RequestListener.this.this$1.this$0).hide();
            }
            
            @Override
            public void requestSuccess(final long id) {
                WishlistFragment.access$000(RequestListener.this.this$1.this$0).hide();
                RequestListener.this.val$wishlistItem.setId(id);
                WishlistFragment.access$100(RequestListener.this.this$1.this$0).add(RequestListener.this.val$adapterPosition, RequestListener.this.val$wishlistItem);
                WishlistFragment.access$200(RequestListener.this.this$1.this$0);
            }
        });
    }
}