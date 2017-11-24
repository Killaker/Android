package bf.io.openshop.ux.dialogs;

import android.support.v4.app.*;
import android.support.design.widget.*;
import bf.io.openshop.interfaces.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import org.json.*;
import bf.io.openshop.entities.*;
import android.os.*;
import android.app.*;
import android.content.*;
import android.view.inputmethod.*;
import android.graphics.drawable.*;
import android.support.annotation.*;
import android.widget.*;
import android.view.*;

public class DiscountDialogFragment extends DialogFragment
{
    private TextInputLayout discountCodeInput;
    private View progressLayout;
    public RequestListener requestListener;
    
    public static DiscountDialogFragment newInstance(final RequestListener requestListener) {
        final DiscountDialogFragment discountDialogFragment = new DiscountDialogFragment();
        discountDialogFragment.requestListener = requestListener;
        return discountDialogFragment;
    }
    
    private void sendDiscountCode(final EditText editText) {
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            final String format = String.format(EndPoints.CART_DISCOUNTS, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("code", (Object)editText.getText().toString().trim());
                Timber.d("Sending discount code: " + jsonObject.toString(), new Object[0]);
                this.progressLayout.setVisibility(0);
                final JsonRequest jsonRequest = new JsonRequest(1, format, jsonObject, new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject jsonObject) {
                        Timber.d("Update item in cart: " + jsonObject.toString(), new Object[0]);
                        MsgUtils.showToast(DiscountDialogFragment.this.getActivity(), 1, DiscountDialogFragment.this.getString(2131230854), MsgUtils.ToastLength.SHORT);
                        if (DiscountDialogFragment.this.requestListener != null) {
                            DiscountDialogFragment.this.requestListener.requestSuccess(0L);
                        }
                        DiscountDialogFragment.this.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError volleyError) {
                        if (DiscountDialogFragment.this.requestListener != null) {
                            DiscountDialogFragment.this.requestListener.requestFailed(volleyError);
                        }
                        DiscountDialogFragment.this.dismiss();
                    }
                }, this.getFragmentManager(), activeUser.getAccessToken());
                jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                jsonRequest.setShouldCache(false);
                new Handler().postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, "cart_discounts_requests");
                    }
                }, 150L);
                return;
            }
            catch (JSONException ex) {
                Timber.e((Throwable)ex, "Creating code json failed", new Object[0]);
                MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
                return;
            }
        }
        new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    public boolean isRequiredFieldsOk() {
        if (this.discountCodeInput.getEditText() == null || this.discountCodeInput.getEditText().getText().toString().equalsIgnoreCase("")) {
            this.discountCodeInput.setErrorEnabled(true);
            this.discountCodeInput.setError(this.getString(2131230880));
            return false;
        }
        Timber.d("Some fields are required.", new Object[0]);
        this.discountCodeInput.setErrorEnabled(false);
        return true;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setStyle(1, 2131427708);
    }
    
    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        final Dialog dialog = new Dialog(this.getActivity(), this.getTheme()) {
            public void dismiss() {
                if (DiscountDialogFragment.this.getActivity() != null && DiscountDialogFragment.this.getView() != null) {
                    ((InputMethodManager)DiscountDialogFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(DiscountDialogFragment.this.getView().getWindowToken(), 0);
                }
                DiscountDialogFragment.this.requestListener = null;
                super.dismiss();
            }
        };
        dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(2131427706);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(DiscountDialogFragment.class.getSimpleName() + " onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968620, viewGroup, false);
        this.progressLayout = inflate.findViewById(2131624102);
        this.discountCodeInput = (TextInputLayout)inflate.findViewById(2131624100);
        ((Button)inflate.findViewById(2131624101)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (DiscountDialogFragment.this.isRequiredFieldsOk()) {
                    DiscountDialogFragment.this.sendDiscountCode(DiscountDialogFragment.this.discountCodeInput.getEditText());
                }
            }
        });
        inflate.findViewById(2131624099).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                DiscountDialogFragment.this.dismiss();
            }
        });
        return inflate;
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().getRequestQueue().cancelAll("cart_discounts_requests");
        super.onStop();
    }
}
