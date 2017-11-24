package bf.io.openshop.ux.fragments;

import bf.io.openshop.entities.wishlist.*;
import bf.io.openshop.*;
import bf.io.openshop.interfaces.*;
import com.android.volley.*;
import android.support.design.widget.*;
import android.support.v4.content.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.os.*;
import android.transition.*;
import bf.io.openshop.ux.*;

class WishlistFragment$5 implements WishlistInterface {
    @Override
    public void onRemoveItemFromWishList(final View view, final WishlistItem wishlistItem, final int n) {
        if (wishlistItem != null) {
            WishlistFragment.access$000(WishlistFragment.this).show();
            WishlistFragment.removeFromWishList(WishlistFragment.this.getActivity(), wishlistItem.getId(), SettingsMy.getActiveUser(), "wishlist_requests", new RequestListener() {
                @Override
                public void requestFailed(final VolleyError volleyError) {
                    WishlistFragment.access$000(WishlistFragment.this).hide();
                }
                
                @Override
                public void requestSuccess(final long n) {
                    WishlistFragment.access$000(WishlistFragment.this).hide();
                    WishlistFragment.access$100(WishlistFragment.this).remove(n);
                    WishlistFragment.access$200(WishlistFragment.this);
                    final Snackbar setAction = Snackbar.make(WishlistFragment.access$300(WishlistFragment.this), 2131230872, 0).setActionTextColor(ContextCompat.getColor((Context)WishlistFragment.this.getActivity(), 2131558426)).setAction(2131230913, (View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            WishlistFragment.access$000(WishlistFragment.this).show();
                            WishlistFragment.addToWishList(WishlistFragment.this.getActivity(), wishlistItem.getVariant().getId(), SettingsMy.getActiveUser(), "wishlist_requests", new RequestListener() {
                                @Override
                                public void requestFailed(final VolleyError volleyError) {
                                    WishlistFragment.access$000(WishlistFragment.this).hide();
                                }
                                
                                @Override
                                public void requestSuccess(final long id) {
                                    WishlistFragment.access$000(WishlistFragment.this).hide();
                                    wishlistItem.setId(id);
                                    WishlistFragment.access$100(WishlistFragment.this).add(n, wishlistItem);
                                    WishlistFragment.access$200(WishlistFragment.this);
                                }
                            });
                        }
                    });
                    ((TextView)setAction.getView().findViewById(2131624091)).setTextColor(ContextCompat.getColor((Context)WishlistFragment.this.getActivity(), 2131558530));
                    setAction.show();
                }
            });
        }
    }
    
    @Override
    public void onWishlistItemSelected(final View view, final WishlistItem wishlistItem) {
        if (Build$VERSION.SDK_INT > 21) {
            WishlistFragment.this.setReenterTransition(TransitionInflater.from((Context)WishlistFragment.this.getActivity()).inflateTransition(17760258));
        }
        if (WishlistFragment.this.getActivity() != null) {
            ((MainActivity)WishlistFragment.this.getActivity()).onProductSelected(wishlistItem.getVariant().getProductId());
        }
    }
}