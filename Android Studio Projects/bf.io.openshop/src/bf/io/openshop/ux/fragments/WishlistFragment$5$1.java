package bf.io.openshop.ux.fragments;

import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.wishlist.*;
import com.android.volley.*;
import android.support.design.widget.*;
import android.support.v4.content.*;
import android.content.*;
import android.view.*;
import bf.io.openshop.*;
import android.widget.*;

class WishlistFragment$5$1 implements RequestListener {
    final /* synthetic */ int val$adapterPosition;
    final /* synthetic */ WishlistItem val$wishlistItem;
    
    @Override
    public void requestFailed(final VolleyError volleyError) {
        WishlistFragment.access$000(WishlistInterface.this.this$0).hide();
    }
    
    @Override
    public void requestSuccess(final long n) {
        WishlistFragment.access$000(WishlistInterface.this.this$0).hide();
        WishlistFragment.access$100(WishlistInterface.this.this$0).remove(this.val$adapterPosition);
        WishlistFragment.access$200(WishlistInterface.this.this$0);
        final Snackbar setAction = Snackbar.make(WishlistFragment.access$300(WishlistInterface.this.this$0), 2131230872, 0).setActionTextColor(ContextCompat.getColor((Context)WishlistInterface.this.this$0.getActivity(), 2131558426)).setAction(2131230913, (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                WishlistFragment.access$000(WishlistInterface.this.this$0).show();
                WishlistFragment.addToWishList(WishlistInterface.this.this$0.getActivity(), RequestListener.this.val$wishlistItem.getVariant().getId(), SettingsMy.getActiveUser(), "wishlist_requests", new RequestListener() {
                    @Override
                    public void requestFailed(final VolleyError volleyError) {
                        WishlistFragment.access$000(WishlistInterface.this.this$0).hide();
                    }
                    
                    @Override
                    public void requestSuccess(final long id) {
                        WishlistFragment.access$000(WishlistInterface.this.this$0).hide();
                        RequestListener.this.val$wishlistItem.setId(id);
                        WishlistFragment.access$100(WishlistInterface.this.this$0).add(RequestListener.this.val$adapterPosition, RequestListener.this.val$wishlistItem);
                        WishlistFragment.access$200(WishlistInterface.this.this$0);
                    }
                });
            }
        });
        ((TextView)setAction.getView().findViewById(2131624091)).setTextColor(ContextCompat.getColor((Context)WishlistInterface.this.this$0.getActivity(), 2131558530));
        setAction.show();
    }
}