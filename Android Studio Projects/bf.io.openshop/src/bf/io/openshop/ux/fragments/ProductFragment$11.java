package bf.io.openshop.ux.fragments;

import android.view.*;
import bf.io.openshop.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.entities.product.*;
import bf.io.openshop.interfaces.*;
import com.android.volley.*;
import android.support.design.widget.*;
import android.support.v4.content.*;
import android.content.*;
import bf.io.openshop.ux.*;
import android.widget.*;
import timber.log.*;
import bf.io.openshop.entities.*;

class ProductFragment$11 implements View$OnClickListener {
    private boolean running = false;
    
    public void onClick(final View view) {
        if (!this.running) {
            this.running = true;
            final User activeUser = SettingsMy.getActiveUser();
            if (activeUser == null) {
                this.running = false;
                new LoginExpiredDialogFragment().show(ProductFragment.this.getFragmentManager(), "loginExpiredDialogFragment");
                return;
            }
            if (!ProductFragment.access$1300(ProductFragment.this)) {
                ProductFragment.access$1302(ProductFragment.this, true);
                ProductFragment.access$700(ProductFragment.this).setIcon(2130837720, 2130837721);
                ProductFragment.access$700(ProductFragment.this).showProgress(true);
                WishlistFragment.addToWishList(ProductFragment.this.getActivity(), ProductFragment.access$300(ProductFragment.this).getVariants().get(0).getId(), activeUser, "product_requests", new RequestListener() {
                    @Override
                    public void requestFailed(final VolleyError volleyError) {
                        View$OnClickListener.this.running = false;
                        ProductFragment.access$700(ProductFragment.this).showProgress(false);
                    }
                    
                    @Override
                    public void requestSuccess(final long n) {
                        View$OnClickListener.this.running = false;
                        ProductFragment.access$700(ProductFragment.this).onProgressCompleted();
                        ProductFragment.access$1402(ProductFragment.this, n);
                        final Snackbar setAction = Snackbar.make((View)ProductFragment.access$1600(ProductFragment.this), ProductFragment.this.getString(2131230871), 0).setActionTextColor(ContextCompat.getColor((Context)ProductFragment.this.getActivity(), 2131558426)).setAction(2131230830, (View$OnClickListener)new View$OnClickListener() {
                            public void onClick(final View view) {
                                if (ProductFragment.this.getActivity() instanceof MainActivity) {
                                    ((MainActivity)ProductFragment.this.getActivity()).onWishlistSelected();
                                }
                            }
                        });
                        ((TextView)setAction.getView().findViewById(2131624091)).setTextColor(-1);
                        setAction.show();
                    }
                });
                return;
            }
            ProductFragment.access$1302(ProductFragment.this, false);
            ProductFragment.access$700(ProductFragment.this).setIcon(2130837721, 2130837720);
            ProductFragment.access$700(ProductFragment.this).showProgress(true);
            if (ProductFragment.access$1400(ProductFragment.this) == -131L) {
                this.running = false;
                ProductFragment.access$700(ProductFragment.this).showProgress(false);
                Timber.e(new RuntimeException(), "Trying remove product from wishlist with error consta", new Object[0]);
                return;
            }
            WishlistFragment.removeFromWishList(ProductFragment.this.getActivity(), ProductFragment.access$1400(ProductFragment.this), activeUser, "product_requests", new RequestListener() {
                @Override
                public void requestFailed(final VolleyError volleyError) {
                    View$OnClickListener.this.running = false;
                    ProductFragment.access$700(ProductFragment.this).showProgress(false);
                }
                
                @Override
                public void requestSuccess(final long n) {
                    View$OnClickListener.this.running = false;
                    ProductFragment.access$700(ProductFragment.this).onProgressCompleted();
                    ProductFragment.access$1402(ProductFragment.this, -131L);
                }
            });
        }
    }
}