package bf.io.openshop.ux.fragments;

import android.app.*;
import org.json.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.entities.cart.*;
import bf.io.openshop.ux.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.ux.dialogs.*;

class CartFragment$6 implements CartRecyclerInterface {
    private void deleteItemFromCart(final long n, final boolean b) {
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            String s;
            if (b) {
                s = String.format(EndPoints.CART_DISCOUNTS_SINGLE, SettingsMy.getActualNonNullShop(CartFragment.this.getActivity()).getId(), n);
            }
            else {
                s = String.format(EndPoints.CART_ITEM, SettingsMy.getActualNonNullShop(CartFragment.this.getActivity()).getId(), n);
            }
            CartFragment.access$100(CartFragment.this).show();
            final JsonRequest jsonRequest = new JsonRequest(3, s, null, new Response.Listener<JSONObject>() {
                public void onResponse(final JSONObject jsonObject) {
                    Timber.d("Delete item from cart: " + jsonObject.toString(), new Object[0]);
                    CartFragment.access$000(CartFragment.this);
                    MsgUtils.showToast(CartFragment.this.getActivity(), 1, CartFragment.this.getString(2131230908), MsgUtils.ToastLength.LONG);
                    if (CartFragment.access$100(CartFragment.this) != null) {
                        CartFragment.access$100(CartFragment.this).cancel();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (CartFragment.access$100(CartFragment.this) != null) {
                        CartFragment.access$100(CartFragment.this).cancel();
                    }
                    MsgUtils.logAndShowErrorMessage(CartFragment.this.getActivity(), volleyError);
                }
            }, CartFragment.this.getFragmentManager(), activeUser.getAccessToken());
            jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            jsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, "cart_requests");
            return;
        }
        new LoginExpiredDialogFragment().show(CartFragment.this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    @Override
    public void onDiscountDelete(final CartDiscountItem cartDiscountItem) {
        if (cartDiscountItem != null) {
            this.deleteItemFromCart(cartDiscountItem.getId(), true);
            return;
        }
        Timber.e("Trying delete null cart discount.", new Object[0]);
    }
    
    @Override
    public void onProductDelete(final CartProductItem cartProductItem) {
        if (cartProductItem != null) {
            this.deleteItemFromCart(cartProductItem.getId(), false);
            return;
        }
        Timber.e("Trying delete null cart item.", new Object[0]);
    }
    
    @Override
    public void onProductSelect(final long n) {
        if (CartFragment.this.getActivity() instanceof MainActivity) {
            ((MainActivity)CartFragment.this.getActivity()).onProductSelected(n);
        }
    }
    
    @Override
    public void onProductUpdate(final CartProductItem cartProductItem) {
        final UpdateCartItemDialogFragment instance = UpdateCartItemDialogFragment.newInstance(cartProductItem, new RequestListener() {
            @Override
            public void requestFailed(final VolleyError volleyError) {
                MsgUtils.logAndShowErrorMessage(CartFragment.this.getActivity(), volleyError);
            }
            
            @Override
            public void requestSuccess(final long n) {
                CartFragment.access$000(CartFragment.this);
            }
        });
        if (instance != null) {
            instance.show(CartFragment.this.getFragmentManager(), UpdateCartItemDialogFragment.class.getSimpleName());
        }
    }
}