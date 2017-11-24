package bf.io.openshop.ux.fragments;

import android.support.v4.app.*;
import android.support.design.widget.*;
import bf.io.openshop.entities.order.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.api.*;
import android.app.*;
import android.support.annotation.*;
import timber.log.*;
import bf.io.openshop.ux.*;
import bf.io.openshop.*;
import com.android.volley.*;
import bf.io.openshop.utils.*;
import org.json.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.ux.dialogs.*;
import android.view.*;
import bf.io.openshop.entities.cart.*;
import android.support.v4.content.*;
import bf.io.openshop.entities.delivery.*;
import java.util.*;
import android.os.*;
import android.content.*;
import android.text.*;
import bf.io.openshop.listeners.*;
import android.widget.*;
import android.view.inputmethod.*;

public class OrderCreateFragment extends Fragment
{
    private Cart cart;
    private LinearLayout cartItemsLayout;
    private TextView cartItemsTotalPrice;
    private TextInputLayout cityInputWrapper;
    private Delivery delivery;
    private View deliveryPaymentLayout;
    private ProgressBar deliveryProgressBar;
    private View deliveryShippingLayout;
    private TextInputLayout emailInputWrapper;
    private TextInputLayout houseNumberInputWrapper;
    private TextInputLayout nameInputWrapper;
    private TextInputLayout noteInputWrapper;
    private double orderTotalPrice;
    private TextView orderTotalPriceTv;
    private TextInputLayout phoneInputWrapper;
    private GsonRequest<Order> postOrderRequest;
    private ProgressDialog progressDialog;
    private ScrollView scrollLayout;
    private Payment selectedPayment;
    private TextView selectedPaymentNameTv;
    private TextView selectedPaymentPriceTv;
    private Shipping selectedShipping;
    private TextView selectedShippingNameTv;
    private TextView selectedShippingPriceTv;
    private TextInputLayout streetInputWrapper;
    private TextInputLayout zipInputWrapper;
    
    private void getUserCart() {
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            final String format = String.format(EndPoints.CART, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
            this.progressDialog.show();
            final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, Cart.class, new Response.Listener<Cart>() {
                public void onResponse(@NonNull final Cart cart) {
                    if (OrderCreateFragment.this.progressDialog != null) {
                        OrderCreateFragment.this.progressDialog.cancel();
                    }
                    OrderCreateFragment.this.refreshScreenContent(cart, activeUser);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (OrderCreateFragment.this.progressDialog != null) {
                        OrderCreateFragment.this.progressDialog.cancel();
                    }
                    Timber.e("Get request cart error: " + volleyError.getMessage(), new Object[0]);
                    MsgUtils.logAndShowErrorMessage(OrderCreateFragment.this.getActivity(), volleyError);
                    if (OrderCreateFragment.this.getActivity() instanceof MainActivity) {
                        ((MainActivity)OrderCreateFragment.this.getActivity()).onDrawerBannersSelected();
                    }
                }
            }, this.getFragmentManager(), activeUser.getAccessToken());
            gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            gsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "order_create_requests");
            return;
        }
        new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    private boolean isRequiredFieldsOk() {
        final String string = this.getString(2131230880);
        final boolean checkTextInputLayoutValueRequirement = Utils.checkTextInputLayoutValueRequirement(this.nameInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement2 = Utils.checkTextInputLayoutValueRequirement(this.streetInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement3 = Utils.checkTextInputLayoutValueRequirement(this.houseNumberInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement4 = Utils.checkTextInputLayoutValueRequirement(this.cityInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement5 = Utils.checkTextInputLayoutValueRequirement(this.zipInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement6 = Utils.checkTextInputLayoutValueRequirement(this.phoneInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement7 = Utils.checkTextInputLayoutValueRequirement(this.emailInputWrapper, string);
        if (checkTextInputLayoutValueRequirement && checkTextInputLayoutValueRequirement2 && checkTextInputLayoutValueRequirement3 && checkTextInputLayoutValueRequirement4 && checkTextInputLayoutValueRequirement5 && checkTextInputLayoutValueRequirement6 && checkTextInputLayoutValueRequirement7) {
            if (this.selectedShipping == null) {
                MsgUtils.showToast(this.getActivity(), 1, this.getString(2131230802), MsgUtils.ToastLength.SHORT);
                this.scrollLayout.smoothScrollTo(0, this.deliveryShippingLayout.getTop());
            }
            else {
                if (this.selectedPayment == null) {
                    MsgUtils.showToast(this.getActivity(), 1, this.getString(2131230801), MsgUtils.ToastLength.SHORT);
                    this.scrollLayout.smoothScrollTo(0, this.deliveryShippingLayout.getTop());
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    private void postOrder(final Order order) {
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            try {
                final JSONObject orderJson = JsonUtils.createOrderJson(order);
                Timber.d("Post order jo: " + orderJson.toString(), new Object[0]);
                final String format = String.format(EndPoints.ORDERS, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
                this.progressDialog.show();
                (this.postOrderRequest = new GsonRequest<Order>(1, format, orderJson.toString(), (Class<Object>)Order.class, (Response.Listener<Object>)new Response.Listener<Order>() {
                    public void onResponse(final Order order) {
                        Timber.d("response:" + order.toString(), new Object[0]);
                        OrderCreateFragment.this.progressDialog.cancel();
                        Analytics.logOrderCreatedEvent(OrderCreateFragment.this.cart, order.getRemoteId(), OrderCreateFragment.this.orderTotalPrice, OrderCreateFragment.this.selectedShipping);
                        OrderCreateFragment.this.updateUserData(activeUser, order);
                        MainActivity.updateCartCountNotification();
                        OrderCreateSuccessDialogFragment.newInstance(false).show(OrderCreateFragment.this.getFragmentManager(), OrderCreateSuccessDialogFragment.class.getSimpleName());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError volleyError) {
                        OrderCreateFragment.this.progressDialog.cancel();
                        if (OrderCreateFragment.this.postOrderRequest != null && OrderCreateFragment.this.postOrderRequest.getStatusCode() == 501) {
                            OrderCreateSuccessDialogFragment.newInstance(true).show(OrderCreateFragment.this.getFragmentManager(), OrderCreateSuccessDialogFragment.class.getSimpleName());
                            return;
                        }
                        MsgUtils.logAndShowErrorMessage(OrderCreateFragment.this.getActivity(), volleyError);
                    }
                }, this.getFragmentManager(), activeUser.getAccessToken())).setRetryPolicy(MyApplication.getDefaultRetryPolice());
                this.postOrderRequest.setShouldCache(false);
                MyApplication.getInstance().addToRequestQueue(this.postOrderRequest, "order_create_requests");
                return;
            }
            catch (JSONException ex) {
                Timber.e((Throwable)ex, "Post order Json exception.", new Object[0]);
                MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
                return;
            }
        }
        new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    private void prepareDeliveryLayout(final View view) {
        this.deliveryProgressBar = (ProgressBar)view.findViewById(2131624241);
        this.deliveryShippingLayout = view.findViewById(2131624235);
        this.deliveryPaymentLayout = view.findViewById(2131624238);
        this.selectedShippingNameTv = (TextView)view.findViewById(2131624236);
        this.selectedShippingPriceTv = (TextView)view.findViewById(2131624237);
        this.selectedPaymentNameTv = (TextView)view.findViewById(2131624239);
        this.selectedPaymentPriceTv = (TextView)view.findViewById(2131624240);
        this.deliveryShippingLayout.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ShippingDialogFragment.newInstance(OrderCreateFragment.this.delivery, OrderCreateFragment.this.selectedShipping, new ShippingDialogInterface() {
                    @Override
                    public void onShippingSelected(final Shipping shipping) {
                        OrderCreateFragment.this.selectedShipping = shipping;
                        OrderCreateFragment.this.showSelectedShipping(shipping);
                        OrderCreateFragment.this.selectedPayment = null;
                        OrderCreateFragment.this.selectedPaymentNameTv.setText((CharSequence)OrderCreateFragment.this.getString(2131230801));
                        OrderCreateFragment.this.selectedPaymentPriceTv.setText((CharSequence)"");
                        OrderCreateFragment.this.deliveryPaymentLayout.performClick();
                    }
                }).show(OrderCreateFragment.this.getFragmentManager(), ShippingDialogFragment.class.getSimpleName());
            }
        });
        this.deliveryPaymentLayout.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                PaymentDialogFragment.newInstance(OrderCreateFragment.this.selectedShipping, OrderCreateFragment.this.selectedPayment, new PaymentDialogInterface() {
                    @Override
                    public void onPaymentSelected(final Payment payment) {
                        OrderCreateFragment.this.selectedPayment = payment;
                        OrderCreateFragment.this.showSelectedPayment(payment);
                    }
                }).show(OrderCreateFragment.this.getFragmentManager(), "PaymentDialog");
            }
        });
    }
    
    private void prepareFields(final View view) {
        this.nameInputWrapper = (TextInputLayout)view.findViewById(2131624242);
        this.streetInputWrapper = (TextInputLayout)view.findViewById(2131624245);
        this.houseNumberInputWrapper = (TextInputLayout)view.findViewById(2131624247);
        this.cityInputWrapper = (TextInputLayout)view.findViewById(2131624249);
        this.zipInputWrapper = (TextInputLayout)view.findViewById(2131624251);
        this.phoneInputWrapper = (TextInputLayout)view.findViewById(2131624254);
        this.emailInputWrapper = (TextInputLayout)view.findViewById(2131624253);
        this.noteInputWrapper = (TextInputLayout)view.findViewById(2131624256);
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            Utils.setTextToInputLayout(this.nameInputWrapper, activeUser.getName());
            Utils.setTextToInputLayout(this.streetInputWrapper, activeUser.getStreet());
            Utils.setTextToInputLayout(this.houseNumberInputWrapper, activeUser.getHouseNumber());
            Utils.setTextToInputLayout(this.cityInputWrapper, activeUser.getCity());
            Utils.setTextToInputLayout(this.zipInputWrapper, activeUser.getZip());
            Utils.setTextToInputLayout(this.emailInputWrapper, activeUser.getEmail());
            Utils.setTextToInputLayout(this.phoneInputWrapper, activeUser.getPhone());
            return;
        }
        new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    private void refreshScreenContent(@NonNull final Cart cart, final User user) {
        this.cart = cart;
        final List<CartProductItem> items = cart.getItems();
        if (items == null || items.size() < 1) {
            Timber.e(new RuntimeException(), "Received null cart during order creation.", new Object[0]);
            if (this.getActivity() instanceof MainActivity) {
                ((MainActivity)this.getActivity()).onDrawerBannersSelected();
            }
            return;
        }
        final LayoutInflater layoutInflater = (LayoutInflater)this.getActivity().getSystemService("layout_inflater");
        for (int i = 0; i < items.size(); ++i) {
            final LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(2130968677, (ViewGroup)this.cartItemsLayout, false);
            ((TextView)linearLayout.findViewById(2131624377)).setText((CharSequence)items.get(i).getVariant().getName());
            ((TextView)linearLayout.findViewById(2131624380)).setText((CharSequence)items.get(i).getTotalItemPriceFormatted());
            ((TextView)linearLayout.findViewById(2131624379)).setText((CharSequence)this.getString(2131230940, items.get(i).getQuantity()));
            ((TextView)linearLayout.findViewById(2131624378)).setText((CharSequence)this.getString(2131230942, items.get(i).getVariant().getColor().getValue(), items.get(i).getVariant().getSize().getValue()));
            this.cartItemsLayout.addView((View)linearLayout);
        }
        if (cart.getDiscounts() != null) {
            for (int j = 0; j < cart.getDiscounts().size(); ++j) {
                final LinearLayout linearLayout2 = (LinearLayout)layoutInflater.inflate(2130968677, (ViewGroup)this.cartItemsLayout, false);
                final TextView textView = (TextView)linearLayout2.findViewById(2131624377);
                final TextView textView2 = (TextView)linearLayout2.findViewById(2131624380);
                textView.setText((CharSequence)cart.getDiscounts().get(j).getDiscount().getName());
                textView2.setText((CharSequence)cart.getDiscounts().get(j).getDiscount().getValueFormatted());
                textView2.setTextColor(ContextCompat.getColor(this.getContext(), 2131558426));
                this.cartItemsLayout.addView((View)linearLayout2);
            }
        }
        this.cartItemsTotalPrice.setText((CharSequence)cart.getTotalPriceFormatted());
        this.orderTotalPriceTv.setText((CharSequence)cart.getTotalPriceFormatted());
        final String format = String.format(EndPoints.CART_DELIVERY_INFO, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
        this.deliveryProgressBar.setVisibility(0);
        final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, (Class<Object>)DeliveryRequest.class, (Response.Listener<Object>)new Response.Listener<DeliveryRequest>() {
            public void onResponse(@NonNull final DeliveryRequest deliveryRequest) {
                Timber.d("GetDelivery:" + deliveryRequest.toString(), new Object[0]);
                OrderCreateFragment.this.delivery = deliveryRequest.getDelivery();
                OrderCreateFragment.this.deliveryProgressBar.setVisibility(8);
                OrderCreateFragment.this.deliveryShippingLayout.setVisibility(0);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Timber.e("Get request cart error: " + volleyError.getMessage(), new Object[0]);
                MsgUtils.logAndShowErrorMessage(OrderCreateFragment.this.getActivity(), volleyError);
                OrderCreateFragment.this.deliveryProgressBar.setVisibility(8);
                if (OrderCreateFragment.this.getActivity() instanceof MainActivity) {
                    ((MainActivity)OrderCreateFragment.this.getActivity()).onDrawerBannersSelected();
                }
            }
        }, this.getFragmentManager(), user.getAccessToken());
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "order_create_requests");
    }
    
    private void showSelectedPayment(final Payment payment) {
        if (payment != null && this.selectedPaymentNameTv != null && this.selectedPaymentPriceTv != null) {
            this.selectedPaymentNameTv.setText((CharSequence)payment.getName());
            if (payment.getPrice() != 0.0) {
                this.selectedPaymentPriceTv.setText((CharSequence)payment.getPriceFormatted());
            }
            else {
                this.selectedPaymentPriceTv.setText(this.getText(2131230944));
            }
            this.orderTotalPrice = payment.getTotalPrice();
            this.orderTotalPriceTv.setText((CharSequence)payment.getTotalPriceFormatted());
            return;
        }
        Timber.e("Showing selected payment with null values.", new Object[0]);
    }
    
    private void showSelectedShipping(final Shipping shipping) {
        if (shipping != null && this.selectedShippingNameTv != null && this.selectedShippingPriceTv != null) {
            this.selectedShippingNameTv.setText((CharSequence)shipping.getName());
            if (shipping.getPrice() != 0) {
                this.selectedShippingPriceTv.setText((CharSequence)shipping.getPriceFormatted());
            }
            else {
                this.selectedShippingPriceTv.setText(this.getText(2131230944));
            }
            this.orderTotalPrice = shipping.getTotalPrice();
            this.orderTotalPriceTv.setText((CharSequence)shipping.getTotalPriceFormatted());
            this.deliveryPaymentLayout.setVisibility(0);
            return;
        }
        Timber.e("Showing selected shipping with null values.", new Object[0]);
    }
    
    private void updateUserData(final User activeUser, final Order order) {
        if (activeUser != null) {
            if (order.getName() != null && !order.getName().isEmpty()) {
                activeUser.setName(order.getName());
            }
            activeUser.setEmail(order.getEmail());
            activeUser.setPhone(order.getPhone());
            activeUser.setCity(order.getCity());
            activeUser.setStreet(order.getStreet());
            activeUser.setZip(order.getZip());
            activeUser.setHouseNumber(order.getHouseNumber());
            SettingsMy.setActiveUser(activeUser);
            return;
        }
        Timber.e(new NullPointerException(), "Null user after successful order.", new Object[0]);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " onCreateView", new Object[0]);
        MainActivity.setActionBarTitle(this.getString(2131230861));
        final View inflate = layoutInflater.inflate(2130968636, viewGroup, false);
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.scrollLayout = (ScrollView)inflate.findViewById(2131624232);
        this.cartItemsLayout = (LinearLayout)inflate.findViewById(2131624233);
        this.cartItemsTotalPrice = (TextView)inflate.findViewById(2131624234);
        this.orderTotalPriceTv = (TextView)inflate.findViewById(2131624259);
        final TextView textView = (TextView)inflate.findViewById(2131624258);
        textView.setText((CharSequence)Html.fromHtml(this.getString(2131230805)));
        textView.setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                if (OrderCreateFragment.this.getActivity() instanceof MainActivity) {
                    ((MainActivity)OrderCreateFragment.this.getActivity()).onTermsAndConditionsSelected();
                }
            }
        });
        this.prepareFields(inflate);
        this.prepareDeliveryLayout(inflate);
        ((Button)inflate.findViewById(2131624260)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                if (OrderCreateFragment.this.isRequiredFieldsOk()) {
                    final Order order = new Order();
                    order.setName(Utils.getTextFromInputLayout(OrderCreateFragment.this.nameInputWrapper));
                    order.setCity(Utils.getTextFromInputLayout(OrderCreateFragment.this.cityInputWrapper));
                    order.setStreet(Utils.getTextFromInputLayout(OrderCreateFragment.this.streetInputWrapper));
                    order.setHouseNumber(Utils.getTextFromInputLayout(OrderCreateFragment.this.houseNumberInputWrapper));
                    order.setZip(Utils.getTextFromInputLayout(OrderCreateFragment.this.zipInputWrapper));
                    order.setEmail(Utils.getTextFromInputLayout(OrderCreateFragment.this.emailInputWrapper));
                    order.setShippingType(OrderCreateFragment.this.selectedShipping.getId());
                    if (OrderCreateFragment.this.selectedPayment != null) {
                        order.setPaymentType(OrderCreateFragment.this.selectedPayment.getId());
                    }
                    else {
                        order.setPaymentType(-1L);
                    }
                    order.setPhone(Utils.getTextFromInputLayout(OrderCreateFragment.this.phoneInputWrapper));
                    order.setNote(Utils.getTextFromInputLayout(OrderCreateFragment.this.noteInputWrapper));
                    view.clearFocus();
                    ((InputMethodManager)OrderCreateFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                    OrderCreateFragment.this.postOrder(order);
                }
            }
        });
        this.showSelectedShipping(this.selectedShipping);
        this.showSelectedPayment(this.selectedPayment);
        this.getUserCart();
        return inflate;
    }
    
    @Override
    public void onStop() {
        super.onStop();
        MyApplication.getInstance().cancelPendingRequests("order_create_requests");
        if (this.progressDialog != null) {
            this.progressDialog.cancel();
        }
        if (this.deliveryProgressBar != null) {
            this.deliveryProgressBar.setVisibility(8);
        }
    }
}
