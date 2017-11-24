package bf.io.openshop.ux.dialogs;

import android.support.v4.app.*;
import bf.io.openshop.entities.cart.*;
import bf.io.openshop.interfaces.*;
import android.support.annotation.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.*;
import com.android.volley.*;
import timber.log.*;
import bf.io.openshop.entities.product.*;
import android.content.*;
import java.util.*;
import bf.io.openshop.api.*;
import org.json.*;
import bf.io.openshop.entities.*;
import android.os.*;
import android.app.*;
import android.graphics.drawable.*;
import android.widget.*;
import android.view.*;
import bf.io.openshop.ux.adapters.*;

public class UpdateCartItemDialogFragment extends DialogFragment
{
    private static final int QUANTITY_MAX = 15;
    private CartProductItem cartProductItem;
    private View dialogContent;
    private View dialogProgress;
    private Spinner itemColorsSpinner;
    private Spinner itemSizesSpinner;
    private Spinner quantitySpinner;
    private RequestListener requestListener;
    
    private void getProductDetail(final CartProductItem cartProductItem) {
        final String format = String.format(EndPoints.PRODUCTS_SINGLE, SettingsMy.getActualNonNullShop(this.getActivity()).getId(), cartProductItem.getVariant().getProductId());
        this.setProgressActive(true);
        final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, Product.class, new Response.Listener<Product>() {
            public void onResponse(@NonNull final Product product) {
                UpdateCartItemDialogFragment.this.setProgressActive(false);
                UpdateCartItemDialogFragment.this.setSpinners(product);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                UpdateCartItemDialogFragment.this.setProgressActive(false);
                MsgUtils.logAndShowErrorMessage(UpdateCartItemDialogFragment.this.getActivity(), volleyError);
            }
        });
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "update_cart_item_requests");
    }
    
    private List<ProductQuantity> getQuantities() {
        final ArrayList<ProductQuantity> list = new ArrayList<ProductQuantity>();
        for (int i = 1; i <= 15; ++i) {
            list.add(new ProductQuantity(i, i + "x"));
        }
        return list;
    }
    
    public static UpdateCartItemDialogFragment newInstance(final CartProductItem cartProductItem, final RequestListener requestListener) {
        if (cartProductItem == null) {
            Timber.e(new RuntimeException(), "Created UpdateCartItemDialogFragment with null parameters.", new Object[0]);
            return null;
        }
        final UpdateCartItemDialogFragment updateCartItemDialogFragment = new UpdateCartItemDialogFragment();
        updateCartItemDialogFragment.cartProductItem = cartProductItem;
        updateCartItemDialogFragment.requestListener = requestListener;
        return updateCartItemDialogFragment;
    }
    
    private void setProgressActive(final boolean b) {
        if (b) {
            this.dialogProgress.setVisibility(0);
            this.dialogContent.setVisibility(4);
            return;
        }
        this.dialogProgress.setVisibility(8);
        this.dialogContent.setVisibility(0);
    }
    
    private void setSpinners(final Product product) {
        if (product == null || product.getVariants() == null || product.getVariants().size() <= 0) {
            Timber.e("Setting spinners for null product variants.", new Object[0]);
            MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
            return;
        }
        final ArrayList<ProductColor> list = new ArrayList<ProductColor>();
        final Iterator<ProductVariant> iterator = product.getVariants().iterator();
        while (iterator.hasNext()) {
            final ProductColor color = iterator.next().getColor();
            if (!list.contains(color)) {
                list.add(color);
            }
        }
        if (list.size() > 1) {
            this.itemColorsSpinner.setVisibility(0);
            this.itemColorsSpinner.setAdapter((SpinnerAdapter)new CartColorTextSpinnerAdapter((Context)this.getActivity(), list));
            final ProductColor color2 = this.cartProductItem.getVariant().getColor();
            if (color2 != null) {
                final int index = list.indexOf(color2);
                Timber.d("SetSpinners selectedColor: " + color2.toString(), new Object[0]);
                if (index == -1) {
                    this.itemColorsSpinner.setSelection(0);
                    Timber.e(new NullPointerException(), "Actual item color didn't match server received item colors", new Object[0]);
                }
                else {
                    this.itemColorsSpinner.setSelection(index);
                }
            }
            this.itemColorsSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    final ProductColor productColor = (ProductColor)adapterView.getItemAtPosition(n);
                    if (productColor != null) {
                        Timber.d("ColorPicker selected color: " + productColor.toString(), new Object[0]);
                        UpdateCartItemDialogFragment.this.updateSizeSpinner(product, productColor);
                        return;
                    }
                    Timber.e("Retrieved null color from spinner.", new Object[0]);
                }
                
                public void onNothingSelected(final AdapterView<?> adapterView) {
                    Timber.d("Nothing selected in product colors spinner.", new Object[0]);
                }
            });
        }
        else {
            this.itemColorsSpinner.setVisibility(8);
            this.updateSizeSpinner(product, product.getVariants().get(0).getColor());
        }
        int selection = -1 + this.cartProductItem.getQuantity();
        if (selection < 0) {
            selection = 0;
        }
        if (selection > -1 + this.quantitySpinner.getCount()) {
            Timber.e(new RuntimeException(), "More item quantity that can be. Quantity:" + (selection + 1) + ", max:" + this.quantitySpinner.getCount(), new Object[0]);
            return;
        }
        this.quantitySpinner.setSelection(selection);
    }
    
    private void updateProductInCart(final long n, final long n2, final int n3) {
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("quantity", n3);
                jsonObject.put("product_variant_id", n2);
                Timber.d("update product: " + jsonObject.toString(), new Object[0]);
                final String format = String.format(EndPoints.CART_ITEM, SettingsMy.getActualNonNullShop(this.getActivity()).getId(), n);
                this.setProgressActive(true);
                final JsonRequest jsonRequest = new JsonRequest(2, format, jsonObject, new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject jsonObject) {
                        Timber.d("Update item in cart: " + jsonObject.toString(), new Object[0]);
                        if (UpdateCartItemDialogFragment.this.requestListener != null) {
                            UpdateCartItemDialogFragment.this.requestListener.requestSuccess(0L);
                        }
                        UpdateCartItemDialogFragment.this.setProgressActive(false);
                        UpdateCartItemDialogFragment.this.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError volleyError) {
                        UpdateCartItemDialogFragment.this.setProgressActive(false);
                        if (UpdateCartItemDialogFragment.this.requestListener != null) {
                            UpdateCartItemDialogFragment.this.requestListener.requestFailed(volleyError);
                        }
                        UpdateCartItemDialogFragment.this.dismiss();
                    }
                }, this.getFragmentManager(), activeUser.getAccessToken());
                jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                jsonRequest.setShouldCache(false);
                MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, "update_cart_item_requests");
                return;
            }
            catch (JSONException ex) {
                Timber.e((Throwable)ex, "Create update object exception", new Object[0]);
                MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
                return;
            }
        }
        new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    private void updateSizeSpinner(final Product product, final ProductColor productColor) {
        if (product != null) {
            final ArrayList<ProductVariant> list = new ArrayList<ProductVariant>();
            for (final ProductVariant productVariant : product.getVariants()) {
                if (productVariant.getColor().equals(productColor)) {
                    list.add(productVariant);
                }
            }
            this.itemSizesSpinner.setAdapter((SpinnerAdapter)new CartSizeSpinnerAdapter((Context)this.getActivity(), list));
            if (list.size() > 0) {
                int selection = 0;
                for (int i = 0; i < list.size(); ++i) {
                    if (list.get(i).getId() == this.cartProductItem.getVariant().getId()) {
                        selection = i;
                    }
                }
                this.itemSizesSpinner.setSelection(selection);
            }
            return;
        }
        Timber.e("UpdateImagesAndSizeSpinner with null product.", new Object[0]);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setStyle(1, 16973942);
    }
    
    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        final Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0));
        onCreateDialog.getWindow().setWindowAnimations(2131427706);
        return onCreateDialog;
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130968627, viewGroup, false);
        this.dialogProgress = inflate.findViewById(2131624164);
        this.dialogContent = inflate.findViewById(2131624158);
        this.itemColorsSpinner = (Spinner)inflate.findViewById(2131624159);
        this.itemSizesSpinner = (Spinner)inflate.findViewById(2131624160);
        ((TextView)inflate.findViewById(2131624157)).setText((CharSequence)this.cartProductItem.getVariant().getName());
        inflate.findViewById(2131624163).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (UpdateCartItemDialogFragment.this.quantitySpinner == null || UpdateCartItemDialogFragment.this.itemSizesSpinner == null) {
                    Timber.e(new NullPointerException(), "Null spinners in editing item in cart", new Object[0]);
                    MsgUtils.showToast(UpdateCartItemDialogFragment.this.getActivity(), 1, UpdateCartItemDialogFragment.this.getString(2131230834), MsgUtils.ToastLength.SHORT);
                    UpdateCartItemDialogFragment.this.dismiss();
                    return;
                }
                final ProductVariant productVariant = (ProductVariant)UpdateCartItemDialogFragment.this.itemSizesSpinner.getSelectedItem();
                final ProductQuantity productQuantity = (ProductQuantity)UpdateCartItemDialogFragment.this.quantitySpinner.getSelectedItem();
                Timber.d("Selected: " + productVariant + ". Quantity: " + productQuantity, new Object[0]);
                if (productVariant != null && productVariant.getSize() != null && productQuantity != null) {
                    UpdateCartItemDialogFragment.this.updateProductInCart(UpdateCartItemDialogFragment.this.cartProductItem.getId(), productVariant.getId(), productQuantity.getQuantity());
                    return;
                }
                Timber.e(new RuntimeException(), "Cannot obtain info about edited cart item.", new Object[0]);
                MsgUtils.showToast(UpdateCartItemDialogFragment.this.getActivity(), 1, UpdateCartItemDialogFragment.this.getString(2131230834), MsgUtils.ToastLength.SHORT);
                UpdateCartItemDialogFragment.this.dismiss();
            }
        });
        inflate.findViewById(2131624162).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                UpdateCartItemDialogFragment.this.dismiss();
            }
        });
        (this.quantitySpinner = (Spinner)inflate.findViewById(2131624161)).setAdapter((SpinnerAdapter)new QuantitySpinnerAdapter((Context)this.getActivity(), this.getQuantities()));
        this.getProductDetail(this.cartProductItem);
        return inflate;
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().getRequestQueue().cancelAll("update_cart_item_requests");
        super.onStop();
    }
}
