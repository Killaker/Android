package bf.io.openshop.ux.fragments;

import android.support.v4.app.*;
import android.support.design.widget.*;
import bf.io.openshop.entities.*;
import android.app.*;
import timber.log.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.*;
import com.android.volley.*;
import org.json.*;
import bf.io.openshop.ux.dialogs.*;
import android.support.annotation.*;
import bf.io.openshop.api.*;
import android.os.*;
import bf.io.openshop.ux.*;
import android.content.*;
import android.widget.*;
import android.view.*;
import bf.io.openshop.listeners.*;
import android.view.inputmethod.*;

public class AccountEditFragment extends Fragment
{
    private LinearLayout accountForm;
    private TextInputLayout cityInputWrapper;
    private TextInputLayout currentPasswordWrapper;
    private TextInputLayout emailInputWrapper;
    private TextInputLayout houseNumberInputWrapper;
    private boolean isPasswordForm;
    private TextInputLayout nameInputWrapper;
    private TextInputLayout newPasswordAgainWrapper;
    private TextInputLayout newPasswordWrapper;
    private LinearLayout passwordForm;
    private TextInputLayout phoneInputWrapper;
    private ProgressDialog progressDialog;
    private TextInputLayout streetInputWrapper;
    private TextInputLayout zipInputWrapper;
    
    public AccountEditFragment() {
        this.isPasswordForm = false;
    }
    
    private void changePassword() {
        if (!this.isRequiredPasswordFields()) {
            return;
        }
        final User activeUser = SettingsMy.getActiveUser();
        Label_0227: {
            if (activeUser == null) {
                break Label_0227;
            }
            final String format = String.format(EndPoints.USER_CHANGE_PASSWORD, SettingsMy.getActualNonNullShop(this.getActivity()).getId(), activeUser.getId());
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("old_password", (Object)Utils.getTextFromInputLayout(this.currentPasswordWrapper).trim());
                jsonObject.put("new_password", (Object)Utils.getTextFromInputLayout(this.newPasswordWrapper).trim());
                Utils.setTextToInputLayout(this.currentPasswordWrapper, "");
                Utils.setTextToInputLayout(this.newPasswordWrapper, "");
                Utils.setTextToInputLayout(this.newPasswordAgainWrapper, "");
                this.progressDialog.show();
                final JsonRequest jsonRequest = new JsonRequest(2, format, jsonObject, new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject jsonObject) {
                        Timber.d("Change password successful: " + jsonObject.toString(), new Object[0]);
                        MsgUtils.showToast(AccountEditFragment.this.getActivity(), 1, AccountEditFragment.this.getString(2131230854), MsgUtils.ToastLength.SHORT);
                        if (AccountEditFragment.this.progressDialog != null) {
                            AccountEditFragment.this.progressDialog.cancel();
                        }
                        AccountEditFragment.this.getFragmentManager().popBackStackImmediate();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError volleyError) {
                        if (AccountEditFragment.this.progressDialog != null) {
                            AccountEditFragment.this.progressDialog.cancel();
                        }
                        MsgUtils.logAndShowErrorMessage(AccountEditFragment.this.getActivity(), volleyError);
                    }
                }, this.getFragmentManager(), activeUser.getAccessToken());
                jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                jsonRequest.setShouldCache(false);
                MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, "account_edit_requests");
                return;
            }
            catch (JSONException ex) {
                Timber.e((Throwable)ex, "Parsing change password exception.", new Object[0]);
                MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
                return;
            }
        }
        new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
    }
    
    private User getUserFromView() {
        final User user = new User();
        user.setName(Utils.getTextFromInputLayout(this.nameInputWrapper));
        user.setStreet(Utils.getTextFromInputLayout(this.streetInputWrapper));
        user.setHouseNumber(Utils.getTextFromInputLayout(this.houseNumberInputWrapper));
        user.setCity(Utils.getTextFromInputLayout(this.cityInputWrapper));
        user.setZip(Utils.getTextFromInputLayout(this.zipInputWrapper));
        user.setPhone(Utils.getTextFromInputLayout(this.phoneInputWrapper));
        return user;
    }
    
    private boolean isRequiredFields() {
        final String string = this.getString(2131230880);
        final boolean checkTextInputLayoutValueRequirement = Utils.checkTextInputLayoutValueRequirement(this.nameInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement2 = Utils.checkTextInputLayoutValueRequirement(this.streetInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement3 = Utils.checkTextInputLayoutValueRequirement(this.houseNumberInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement4 = Utils.checkTextInputLayoutValueRequirement(this.cityInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement5 = Utils.checkTextInputLayoutValueRequirement(this.zipInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement6 = Utils.checkTextInputLayoutValueRequirement(this.phoneInputWrapper, string);
        final boolean checkTextInputLayoutValueRequirement7 = Utils.checkTextInputLayoutValueRequirement(this.emailInputWrapper, string);
        return checkTextInputLayoutValueRequirement && checkTextInputLayoutValueRequirement2 && checkTextInputLayoutValueRequirement3 && checkTextInputLayoutValueRequirement4 && checkTextInputLayoutValueRequirement5 && checkTextInputLayoutValueRequirement6 && checkTextInputLayoutValueRequirement7;
    }
    
    private boolean isRequiredPasswordFields() {
        boolean b = true;
        final String string = this.getString(2131230880);
        final boolean checkTextInputLayoutValueRequirement = Utils.checkTextInputLayoutValueRequirement(this.currentPasswordWrapper, string);
        final boolean checkTextInputLayoutValueRequirement2 = Utils.checkTextInputLayoutValueRequirement(this.newPasswordWrapper, string);
        final boolean checkTextInputLayoutValueRequirement3 = Utils.checkTextInputLayoutValueRequirement(this.newPasswordAgainWrapper, string);
        if (checkTextInputLayoutValueRequirement2 && checkTextInputLayoutValueRequirement3) {
            if (!Utils.getTextFromInputLayout(this.newPasswordWrapper).equals(Utils.getTextFromInputLayout(this.newPasswordAgainWrapper))) {
                Timber.d("The entries for the new password must match", new Object[0]);
                this.newPasswordWrapper.setErrorEnabled(b);
                this.newPasswordAgainWrapper.setErrorEnabled(b);
                this.newPasswordWrapper.setError(this.getString(2131230907));
                this.newPasswordAgainWrapper.setError(this.getString(2131230907));
                return false;
            }
            this.newPasswordWrapper.setErrorEnabled(false);
            this.newPasswordAgainWrapper.setErrorEnabled(false);
        }
        if (!checkTextInputLayoutValueRequirement || !checkTextInputLayoutValueRequirement2 || !checkTextInputLayoutValueRequirement3) {
            b = false;
        }
        return b;
    }
    
    private void putUser(final User user) {
        if (this.isRequiredFields()) {
            final User activeUser = SettingsMy.getActiveUser();
            if (activeUser != null) {
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", (Object)user.getName());
                    jsonObject.put("street", (Object)user.getStreet());
                    jsonObject.put("house_number", (Object)user.getHouseNumber());
                    jsonObject.put("city", (Object)user.getCity());
                    jsonObject.put("zip", (Object)user.getZip());
                    jsonObject.put("email", (Object)user.getEmail());
                    jsonObject.put("phone", (Object)user.getPhone());
                    final String format = String.format(EndPoints.USER_SINGLE, SettingsMy.getActualNonNullShop(this.getActivity()).getId(), activeUser.getId());
                    this.progressDialog.show();
                    final GsonRequest gsonRequest = new GsonRequest<Object>(2, format, jsonObject.toString(), User.class, new Response.Listener<User>() {
                        public void onResponse(@NonNull final User activeUser) {
                            SettingsMy.setActiveUser(activeUser);
                            AccountEditFragment.this.refreshScreen(activeUser);
                            AccountEditFragment.this.progressDialog.cancel();
                            MsgUtils.showToast(AccountEditFragment.this.getActivity(), 1, AccountEditFragment.this.getString(2131230854), MsgUtils.ToastLength.SHORT);
                            AccountEditFragment.this.getFragmentManager().popBackStackImmediate();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError volleyError) {
                            if (AccountEditFragment.this.progressDialog != null) {
                                AccountEditFragment.this.progressDialog.cancel();
                            }
                            MsgUtils.logAndShowErrorMessage(AccountEditFragment.this.getActivity(), volleyError);
                        }
                    }, this.getFragmentManager(), activeUser.getAccessToken());
                    gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                    gsonRequest.setShouldCache(false);
                    MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "account_edit_requests");
                    return;
                }
                catch (JSONException ex) {
                    Timber.e((Throwable)ex, "Parse new user registration exception.", new Object[0]);
                    MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
                    return;
                }
            }
            new LoginExpiredDialogFragment().show(this.getFragmentManager(), "loginExpiredDialogFragment");
            return;
        }
        Timber.d("Missing required fields.", new Object[0]);
    }
    
    private void refreshScreen(final User user) {
        Utils.setTextToInputLayout(this.nameInputWrapper, user.getName());
        Utils.setTextToInputLayout(this.streetInputWrapper, user.getStreet());
        Utils.setTextToInputLayout(this.houseNumberInputWrapper, user.getHouseNumber());
        Utils.setTextToInputLayout(this.cityInputWrapper, user.getCity());
        Utils.setTextToInputLayout(this.zipInputWrapper, user.getZip());
        Utils.setTextToInputLayout(this.emailInputWrapper, user.getEmail());
        Utils.setTextToInputLayout(this.phoneInputWrapper, user.getPhone());
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " - OnCreateView", new Object[0]);
        MainActivity.setActionBarTitle(this.getString(2131230788));
        final View inflate = layoutInflater.inflate(2130968629, viewGroup, false);
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.accountForm = (LinearLayout)inflate.findViewById(2131624175);
        this.nameInputWrapper = (TextInputLayout)inflate.findViewById(2131624176);
        this.streetInputWrapper = (TextInputLayout)inflate.findViewById(2131624179);
        this.houseNumberInputWrapper = (TextInputLayout)inflate.findViewById(2131624181);
        this.cityInputWrapper = (TextInputLayout)inflate.findViewById(2131624183);
        this.zipInputWrapper = (TextInputLayout)inflate.findViewById(2131624185);
        this.phoneInputWrapper = (TextInputLayout)inflate.findViewById(2131624189);
        this.emailInputWrapper = (TextInputLayout)inflate.findViewById(2131624187);
        this.passwordForm = (LinearLayout)inflate.findViewById(2131624190);
        this.currentPasswordWrapper = (TextInputLayout)inflate.findViewById(2131624191);
        this.newPasswordWrapper = (TextInputLayout)inflate.findViewById(2131624193);
        this.newPasswordAgainWrapper = (TextInputLayout)inflate.findViewById(2131624195);
        final Button button = (Button)inflate.findViewById(2131624197);
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (AccountEditFragment.this.isPasswordForm) {
                    AccountEditFragment.this.isPasswordForm = false;
                    AccountEditFragment.this.passwordForm.setVisibility(8);
                    AccountEditFragment.this.accountForm.setVisibility(0);
                    button.setText((CharSequence)AccountEditFragment.this.getString(2131230798));
                    return;
                }
                AccountEditFragment.this.isPasswordForm = true;
                AccountEditFragment.this.passwordForm.setVisibility(0);
                AccountEditFragment.this.accountForm.setVisibility(8);
                button.setText(2131230797);
            }
        });
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            this.refreshScreen(activeUser);
            Timber.d("user: " + activeUser.toString(), new Object[0]);
        }
        else {
            Timber.e(new RuntimeException(), "No active user. Shouldn't happen.", new Object[0]);
        }
        ((Button)inflate.findViewById(2131624198)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                while (true) {
                    Label_0104: {
                        if (AccountEditFragment.this.isPasswordForm) {
                            break Label_0104;
                        }
                        try {
                            AccountEditFragment.this.putUser(AccountEditFragment.this.getUserFromView());
                            if (AccountEditFragment.this.getActivity().getCurrentFocus() != null) {
                                ((InputMethodManager)AccountEditFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(AccountEditFragment.this.getActivity().getCurrentFocus().getWindowToken(), 0);
                            }
                            return;
                        }
                        catch (Exception ex) {
                            Timber.e(ex, "Update user information exception.", new Object[0]);
                            MsgUtils.showToast(AccountEditFragment.this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
                            continue;
                        }
                    }
                    AccountEditFragment.this.changePassword();
                    continue;
                }
            }
        });
        return inflate;
    }
    
    @Override
    public void onPause() {
        if (this.getActivity().getCurrentFocus() != null) {
            ((InputMethodManager)this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(), 0);
        }
        super.onPause();
    }
    
    @Override
    public void onStop() {
        if (this.progressDialog != null) {
            this.progressDialog.cancel();
        }
        MyApplication.getInstance().cancelPendingRequests("account_edit_requests");
        super.onStop();
    }
}
