package bf.io.openshop.ux.fragments;

import android.widget.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import android.app.*;
import timber.log.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import android.os.*;
import bf.io.openshop.ux.*;
import bf.io.openshop.utils.*;
import android.content.*;
import bf.io.openshop.listeners.*;
import android.view.*;
import android.support.v4.app.*;
import bf.io.openshop.entities.delivery.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.interfaces.*;

public class AccountFragment extends Fragment
{
    private Button loginLogoutBtn;
    private boolean mAlreadyLoaded;
    private Button myOrdersBtn;
    private ProgressDialog pDialog;
    private TextView tvAddress;
    private TextView tvEmail;
    private TextView tvPhone;
    private TextView tvUserName;
    private Button updateUserBtn;
    private LinearLayout userInfoLayout;
    
    public AccountFragment() {
        this.mAlreadyLoaded = false;
    }
    
    private String appendCommaText(String s, String s2, final boolean b) {
        if (s != null && !s.isEmpty()) {
            if (s2 != null && !s2.isEmpty()) {
                if (b) {
                    s += this.getString(2131230935, s2);
                }
                else {
                    s += this.getString(2131230941, s2);
                }
            }
            s2 = s;
        }
        return s2;
    }
    
    private void refreshScreen(final User user) {
        if (user == null) {
            this.loginLogoutBtn.setText((CharSequence)this.getString(2131230839));
            this.userInfoLayout.setVisibility(8);
            this.updateUserBtn.setVisibility(8);
            this.myOrdersBtn.setVisibility(8);
            return;
        }
        this.loginLogoutBtn.setText((CharSequence)this.getString(2131230843));
        this.userInfoLayout.setVisibility(0);
        this.updateUserBtn.setVisibility(0);
        this.myOrdersBtn.setVisibility(0);
        this.tvUserName.setText((CharSequence)user.getName());
        this.tvAddress.setText((CharSequence)this.appendCommaText(this.appendCommaText(this.appendCommaText(user.getStreet(), user.getHouseNumber(), false), user.getCity(), true), user.getZip(), true));
        this.tvEmail.setText((CharSequence)user.getEmail());
        this.tvPhone.setText((CharSequence)user.getPhone());
    }
    
    private void syncUserData(@NonNull final User user) {
        final String format = String.format(EndPoints.USER_SINGLE, SettingsMy.getActualNonNullShop(this.getActivity()).getId(), user.getId());
        this.pDialog.show();
        final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, User.class, new Response.Listener<User>() {
            public void onResponse(@NonNull final User activeUser) {
                Timber.d("response:" + activeUser.toString(), new Object[0]);
                SettingsMy.setActiveUser(activeUser);
                AccountFragment.this.refreshScreen(SettingsMy.getActiveUser());
                if (AccountFragment.this.pDialog != null) {
                    AccountFragment.this.pDialog.cancel();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                if (AccountFragment.this.pDialog != null) {
                    AccountFragment.this.pDialog.cancel();
                }
                MsgUtils.logAndShowErrorMessage(AccountFragment.this.getActivity(), volleyError);
            }
        }, this.getFragmentManager(), user.getAccessToken());
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "account_requests");
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " - OnCreateView", new Object[0]);
        MainActivity.setActionBarTitle(this.getString(2131230875));
        final View inflate = layoutInflater.inflate(2130968628, viewGroup, false);
        this.pDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.userInfoLayout = (LinearLayout)inflate.findViewById(2131624165);
        this.tvUserName = (TextView)inflate.findViewById(2131624166);
        this.tvAddress = (TextView)inflate.findViewById(2131624167);
        this.tvEmail = (TextView)inflate.findViewById(2131624168);
        this.tvPhone = (TextView)inflate.findViewById(2131624169);
        (this.updateUserBtn = (Button)inflate.findViewById(2131624170)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                if (AccountFragment.this.getActivity() instanceof MainActivity) {
                    ((MainActivity)AccountFragment.this.getActivity()).onAccountEditSelected();
                }
            }
        });
        (this.myOrdersBtn = (Button)inflate.findViewById(2131624171)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                if (AccountFragment.this.getActivity() instanceof MainActivity) {
                    ((MainActivity)AccountFragment.this.getActivity()).onOrdersHistory();
                }
            }
        });
        ((Button)inflate.findViewById(2131624173)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final FragmentActivity activity = AccountFragment.this.getActivity();
                if (activity != null && activity instanceof MainActivity) {
                    ((MainActivity)AccountFragment.this.getActivity()).startSettingsFragment();
                }
            }
        });
        ((Button)inflate.findViewById(2131624172)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ShippingDialogFragment.newInstance(new ShippingDialogInterface() {
                    @Override
                    public void onShippingSelected(final Shipping shipping) {
                    }
                }).show(AccountFragment.this.getFragmentManager(), "shippingDialogFragment");
            }
        });
        (this.loginLogoutBtn = (Button)inflate.findViewById(2131624174)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                if (SettingsMy.getActiveUser() != null) {
                    LoginDialogFragment.logoutUser();
                    AccountFragment.this.refreshScreen(null);
                    return;
                }
                LoginDialogFragment.newInstance(new LoginDialogInterface() {
                    @Override
                    public void successfulLoginOrRegistration(final User user) {
                        AccountFragment.this.refreshScreen(user);
                        MainActivity.updateCartCountNotification();
                    }
                }).show(AccountFragment.this.getFragmentManager(), LoginDialogFragment.class.getSimpleName());
            }
        });
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser == null) {
            this.refreshScreen(null);
            return inflate;
        }
        Timber.d("user: " + activeUser.toString(), new Object[0]);
        if (bundle == null && !this.mAlreadyLoaded) {
            this.mAlreadyLoaded = true;
            this.syncUserData(activeUser);
            return inflate;
        }
        this.refreshScreen(activeUser);
        return inflate;
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().getRequestQueue().cancelAll("account_requests");
        super.onStop();
    }
}
