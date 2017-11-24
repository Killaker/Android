package bf.io.openshop.ux.fragments;

import bf.io.openshop.ux.adapters.*;
import android.support.v4.app.*;
import bf.io.openshop.entities.*;
import org.json.*;
import android.app.*;
import timber.log.*;
import bf.io.openshop.*;
import com.android.volley.*;
import android.support.annotation.*;
import bf.io.openshop.entities.wishlist.*;
import bf.io.openshop.api.*;
import android.content.*;
import android.support.v7.widget.*;
import bf.io.openshop.interfaces.*;
import android.support.design.widget.*;
import android.support.v4.content.*;
import android.widget.*;
import android.transition.*;
import bf.io.openshop.ux.*;
import android.view.*;
import android.os.*;
import bf.io.openshop.utils.*;

public class WishlistFragment extends Fragment
{
    private View contentLayout;
    private View emptyLayout;
    private ProgressDialog progressDialog;
    private GridLayoutManager recyclerLayoutManaged;
    private Parcelable recyclerState;
    private View rootLayout;
    private WishListRecyclerAdapter wishlistAdapter;
    
    public static void addToWishList(final FragmentActivity fragmentActivity, final long n, final User user, final String s, final RequestListener requestListener) {
        if (fragmentActivity != null && n != 0L && user != null && s != null && requestListener != null) {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("product_variant_id", n);
                final JsonRequest jsonRequest = new JsonRequest(1, String.format(EndPoints.WISHLIST, SettingsMy.getActualNonNullShop(fragmentActivity).getId()), jsonObject, new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject jsonObject) {
                        Timber.d("AddToWishlist response" + jsonObject.toString(), new Object[0]);
                        try {
                            new Handler().postDelayed((Runnable)new Runnable() {
                                final /* synthetic */ long val$responseId = jsonObject.getLong("id");
                                
                                @Override
                                public void run() {
                                    requestListener.requestSuccess(this.val$responseId);
                                }
                            }, 500L);
                        }
                        catch (Exception ex) {
                            Timber.e(ex, "Parsing addToWishList response failed. Response: " + jsonObject, new Object[0]);
                            new Handler().postDelayed((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    requestListener.requestFailed(null);
                                }
                            }, 500L);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError volleyError) {
                        new Handler().postDelayed((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                requestListener.requestFailed(volleyError);
                            }
                        }, 500L);
                        MsgUtils.logAndShowErrorMessage(fragmentActivity, volleyError);
                    }
                }, fragmentActivity.getSupportFragmentManager(), user.getAccessToken());
                jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                jsonRequest.setShouldCache(false);
                MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, s);
                return;
            }
            catch (Exception ex) {
                requestListener.requestFailed(null);
                Timber.e(ex, "Add to wishlist null product.", new Object[0]);
                return;
            }
        }
        if (requestListener != null) {
            requestListener.requestFailed(null);
        }
        Timber.e(new RuntimeException(), "Add to wishlist product with null parameters.", new Object[0]);
    }
    
    private void checkIfEmpty() {
        if (this.wishlistAdapter != null && !this.wishlistAdapter.isEmpty()) {
            this.emptyLayout.setVisibility(8);
            this.contentLayout.setVisibility(0);
            return;
        }
        this.emptyLayout.setVisibility(0);
        this.contentLayout.setVisibility(8);
    }
    
    private void getWishlistContent(@NonNull final User user) {
        final String format = String.format(EndPoints.WISHLIST, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
        this.progressDialog.show();
        final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, WishlistResponse.class, new Response.Listener<WishlistResponse>() {
            public void onResponse(@NonNull final WishlistResponse wishlistResponse) {
                if (WishlistFragment.this.progressDialog != null) {
                    WishlistFragment.this.progressDialog.cancel();
                }
                for (int i = 0; i < wishlistResponse.getItems().size(); ++i) {
                    WishlistFragment.this.wishlistAdapter.add(i, wishlistResponse.getItems().get(i));
                }
                WishlistFragment.this.checkIfEmpty();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                if (WishlistFragment.this.progressDialog != null) {
                    WishlistFragment.this.progressDialog.cancel();
                }
                MsgUtils.logAndShowErrorMessage(WishlistFragment.this.getActivity(), volleyError);
            }
        }, this.getFragmentManager(), user.getAccessToken());
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "cart_requests");
    }
    
    private void prepareWishlistRecycler(final View view) {
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(2131624300);
        recyclerView.addItemDecoration((RecyclerView.ItemDecoration)new RecyclerMarginDecorator((Context)this.getActivity(), RecyclerMarginDecorator.ORIENTATION.BOTH));
        recyclerView.setItemAnimator((RecyclerView.ItemAnimator)new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager)(this.recyclerLayoutManaged = new GridLayoutManager((Context)this.getActivity(), 2)));
        if (this.recyclerState != null) {
            this.recyclerLayoutManaged.onRestoreInstanceState(this.recyclerState);
        }
        recyclerView.setAdapter((RecyclerView.Adapter)(this.wishlistAdapter = new WishListRecyclerAdapter((Context)this.getActivity(), new WishlistInterface() {
            @Override
            public void onRemoveItemFromWishList(final View view, final WishlistItem wishlistItem, final int n) {
                if (wishlistItem != null) {
                    WishlistFragment.this.progressDialog.show();
                    WishlistFragment.removeFromWishList(WishlistFragment.this.getActivity(), wishlistItem.getId(), SettingsMy.getActiveUser(), "wishlist_requests", new RequestListener() {
                        @Override
                        public void requestFailed(final VolleyError volleyError) {
                            WishlistFragment.this.progressDialog.hide();
                        }
                        
                        @Override
                        public void requestSuccess(final long n) {
                            WishlistFragment.this.progressDialog.hide();
                            WishlistFragment.this.wishlistAdapter.remove(n);
                            WishlistFragment.this.checkIfEmpty();
                            final Snackbar setAction = Snackbar.make(WishlistFragment.this.rootLayout, 2131230872, 0).setActionTextColor(ContextCompat.getColor((Context)WishlistFragment.this.getActivity(), 2131558426)).setAction(2131230913, (View$OnClickListener)new View$OnClickListener() {
                                public void onClick(final View view) {
                                    WishlistFragment.this.progressDialog.show();
                                    WishlistFragment.addToWishList(WishlistFragment.this.getActivity(), wishlistItem.getVariant().getId(), SettingsMy.getActiveUser(), "wishlist_requests", new RequestListener() {
                                        @Override
                                        public void requestFailed(final VolleyError volleyError) {
                                            WishlistFragment.this.progressDialog.hide();
                                        }
                                        
                                        @Override
                                        public void requestSuccess(final long id) {
                                            WishlistFragment.this.progressDialog.hide();
                                            wishlistItem.setId(id);
                                            WishlistFragment.this.wishlistAdapter.add(n, wishlistItem);
                                            WishlistFragment.this.checkIfEmpty();
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
        })));
    }
    
    public static void removeFromWishList(final FragmentActivity fragmentActivity, final long n, final User user, final String s, final RequestListener requestListener) {
        if (fragmentActivity != null && n != 0L && user != null && s != null && requestListener != null) {
            final JsonRequest jsonRequest = new JsonRequest(3, String.format(EndPoints.WISHLIST_SINGLE, SettingsMy.getActualNonNullShop(fragmentActivity).getId(), n), null, new Response.Listener<JSONObject>() {
                public void onResponse(final JSONObject jsonObject) {
                    Timber.d("RemoveFromWishlist response" + jsonObject.toString(), new Object[0]);
                    new Handler().postDelayed((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            requestListener.requestSuccess(0L);
                        }
                    }, 500L);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    new Handler().postDelayed((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            requestListener.requestFailed(volleyError);
                        }
                    }, 500L);
                    MsgUtils.logAndShowErrorMessage(fragmentActivity, volleyError);
                }
            }, fragmentActivity.getSupportFragmentManager(), user.getAccessToken());
            jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            jsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, s);
            return;
        }
        if (requestListener != null) {
            requestListener.requestFailed(null);
        }
        Timber.e(new RuntimeException(), "Remove from wishlist product with null parameters.", new Object[0]);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " onCreateView", new Object[0]);
        MainActivity.setActionBarTitle(this.getString(2131230918));
        final View inflate = layoutInflater.inflate(2130968641, viewGroup, false);
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.rootLayout = inflate.findViewById(2131624297);
        this.emptyLayout = inflate.findViewById(2131624298);
        this.contentLayout = inflate.findViewById(2131624299);
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            this.prepareWishlistRecycler(inflate);
            this.getWishlistContent(activeUser);
            return inflate;
        }
        Timber.e(new RuntimeException(), "Wishlist fragment created with no logged user. ", new Object[0]);
        this.getFragmentManager().popBackStackImmediate();
        return inflate;
    }
    
    @Override
    public void onPause() {
        super.onPause();
        if (this.recyclerLayoutManaged != null) {
            this.recyclerState = this.recyclerLayoutManaged.onSaveInstanceState();
        }
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().cancelPendingRequests("wishlist_requests");
        if (this.progressDialog != null) {
            this.progressDialog.cancel();
        }
        super.onStop();
    }
}
