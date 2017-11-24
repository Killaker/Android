package bf.io.openshop.ux.fragments;

import bf.io.openshop.ux.adapters.*;
import android.app.*;
import android.support.annotation.*;
import bf.io.openshop.ux.*;
import timber.log.*;
import bf.io.openshop.*;
import com.android.volley.*;
import bf.io.openshop.entities.*;
import android.content.*;
import android.support.v7.widget.*;
import org.json.*;
import bf.io.openshop.api.*;
import bf.io.openshop.entities.cart.*;
import bf.io.openshop.interfaces.*;
import android.os.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.listeners.*;
import android.support.v4.app.*;
import android.view.*;
import bf.io.openshop.ux.dialogs.*;
import android.widget.*;

public class CartFragment extends Fragment
{
    private View cartFooter;
    private TextView cartItemCountTv;
    private RecyclerView cartRecycler;
    private CartRecyclerAdapter cartRecyclerAdapter;
    private TextView cartTotalPriceTv;
    private View emptyCart;
    private ProgressDialog progressDialog;
    
    private void getCartContent() {
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            final String format = String.format(EndPoints.CART, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
            this.progressDialog.show();
            final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, Cart.class, new Response.Listener<Cart>() {
                public void onResponse(@NonNull final Cart cart) {
                    if (CartFragment.this.progressDialog != null) {
                        CartFragment.this.progressDialog.cancel();
                    }
                    MainActivity.updateCartCountNotification();
                    if (cart.getItems() == null || cart.getItems().size() == 0) {
                        CartFragment.this.setCartVisibility(false);
                        return;
                    }
                    CartFragment.this.setCartVisibility(true);
                    CartFragment.this.cartRecyclerAdapter.refreshItems(cart);
                    CartFragment.this.cartItemCountTv.setText((CharSequence)CartFragment.this.getString(2131230940, cart.getProductCount()));
                    CartFragment.this.cartTotalPriceTv.setText((CharSequence)cart.getTotalPriceFormatted());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (CartFragment.this.progressDialog != null) {
                        CartFragment.this.progressDialog.cancel();
                    }
                    CartFragment.this.setCartVisibility(false);
                    Timber.e("Get request cart error: " + volleyError.getMessage(), new Object[0]);
                    MsgUtils.logAndShowErrorMessage(CartFragment.this.getActivity(), volleyError);
                }
            }, this.getFragmentManager(), activeUser.getAccessToken());
            gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            gsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "cart_requests");
            return;
        }
        new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    private void prepareCartRecycler(final View view) {
        (this.cartRecycler = (RecyclerView)view.findViewById(2131624205)).addItemDecoration((RecyclerView.ItemDecoration)new RecyclerDividerDecorator((Context)this.getActivity()));
        this.cartRecycler.setItemAnimator((RecyclerView.ItemAnimator)new DefaultItemAnimator());
        this.cartRecycler.setHasFixedSize(true);
        this.cartRecycler.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager((Context)this.getActivity()));
        this.cartRecyclerAdapter = new CartRecyclerAdapter((Context)this.getActivity(), new CartRecyclerInterface() {
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
                    CartFragment.this.progressDialog.show();
                    final JsonRequest jsonRequest = new JsonRequest(3, s, null, new Response.Listener<JSONObject>() {
                        public void onResponse(final JSONObject jsonObject) {
                            Timber.d("Delete item from cart: " + jsonObject.toString(), new Object[0]);
                            CartFragment.this.getCartContent();
                            MsgUtils.showToast(CartFragment.this.getActivity(), 1, CartFragment.this.getString(2131230908), MsgUtils.ToastLength.LONG);
                            if (CartFragment.this.progressDialog != null) {
                                CartFragment.this.progressDialog.cancel();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError volleyError) {
                            if (CartFragment.this.progressDialog != null) {
                                CartFragment.this.progressDialog.cancel();
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
                        CartFragment.this.getCartContent();
                    }
                });
                if (instance != null) {
                    instance.show(CartFragment.this.getFragmentManager(), UpdateCartItemDialogFragment.class.getSimpleName());
                }
            }
        });
        this.cartRecycler.setAdapter((RecyclerView.Adapter)this.cartRecyclerAdapter);
    }
    
    private void setCartVisibility(final boolean b) {
        if (b) {
            if (this.emptyCart != null) {
                this.emptyCart.setVisibility(8);
            }
            if (this.cartRecycler != null) {
                this.cartRecycler.setVisibility(0);
            }
            if (this.cartFooter != null) {
                this.cartFooter.setVisibility(0);
            }
        }
        else {
            if (this.cartRecyclerAdapter != null) {
                this.cartRecyclerAdapter.cleatCart();
            }
            if (this.emptyCart != null) {
                this.emptyCart.setVisibility(0);
            }
            if (this.cartRecycler != null) {
                this.cartRecycler.setVisibility(8);
            }
            if (this.cartFooter != null) {
                this.cartFooter.setVisibility(8);
            }
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " onCreateView", new Object[0]);
        MainActivity.setActionBarTitle(this.getString(2131230900));
        final View inflate = layoutInflater.inflate(2130968631, viewGroup, false);
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.prepareCartRecycler(inflate);
        this.emptyCart = inflate.findViewById(2131624203);
        inflate.findViewById(2131624204).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                final FragmentActivity activity = CartFragment.this.getActivity();
                if (activity instanceof MainActivity) {
                    final MainActivity mainActivity = (MainActivity)activity;
                    if (mainActivity.drawerFragment != null) {
                        mainActivity.drawerFragment.toggleDrawerMenu();
                    }
                }
            }
        });
        this.cartFooter = inflate.findViewById(2131624206);
        this.cartItemCountTv = (TextView)inflate.findViewById(2131624208);
        this.cartTotalPriceTv = (TextView)inflate.findViewById(2131624209);
        inflate.findViewById(2131624207).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                final DiscountDialogFragment instance = DiscountDialogFragment.newInstance(new RequestListener() {
                    @Override
                    public void requestFailed(final VolleyError volleyError) {
                        MsgUtils.logAndShowErrorMessage(CartFragment.this.getActivity(), volleyError);
                    }
                    
                    @Override
                    public void requestSuccess(final long n) {
                        CartFragment.this.getCartContent();
                    }
                });
                if (instance != null) {
                    instance.show(CartFragment.this.getFragmentManager(), DiscountDialogFragment.class.getSimpleName());
                }
            }
        });
        ((Button)inflate.findViewById(2131624210)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                if (CartFragment.this.getActivity() instanceof MainActivity) {
                    ((MainActivity)CartFragment.this.getActivity()).onOrderCreateSelected();
                }
            }
        });
        this.getCartContent();
        return inflate;
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().cancelPendingRequests("cart_requests");
        if (this.progressDialog != null) {
            this.progressDialog.cancel();
        }
        super.onStop();
    }
}
