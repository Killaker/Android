package bf.io.openshop.ux.dialogs;

import bf.io.openshop.interfaces.*;
import android.support.design.widget.*;
import android.view.inputmethod.*;
import com.facebook.login.*;
import android.support.v4.app.*;
import java.util.*;
import timber.log.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import bf.io.openshop.*;
import com.android.volley.*;
import org.json.*;
import bf.io.openshop.ux.*;
import bf.io.openshop.listeners.*;
import android.accounts.*;
import android.widget.*;
import bf.io.openshop.api.*;
import android.os.*;
import bf.io.openshop.utils.*;
import android.content.*;
import android.app.*;
import android.view.*;
import com.facebook.*;

public class LoginDialogFragment extends DialogFragment implements FacebookCallback<LoginResult>
{
    private FormState actualFormState;
    private CallbackManager callbackManager;
    private LinearLayout loginBaseForm;
    private LoginDialogInterface loginDialogInterface;
    private TextInputLayout loginEmailEmailWrapper;
    private TextInputLayout loginEmailForgottenEmailWrapper;
    private LinearLayout loginEmailForgottenForm;
    private LinearLayout loginEmailForm;
    private TextInputLayout loginEmailPasswordWrapper;
    private TextInputLayout loginRegistrationEmailWrapper;
    private LinearLayout loginRegistrationForm;
    private RadioButton loginRegistrationGenderWoman;
    private TextInputLayout loginRegistrationPasswordWrapper;
    private ProgressDialog progressDialog;
    
    public LoginDialogFragment() {
        this.actualFormState = FormState.BASE;
    }
    
    private void handleNonFatalError(final String s, final boolean b) {
        if (b) {
            logoutUser();
        }
        if (this.getActivity() != null) {
            MsgUtils.showToast(this.getActivity(), 1, s, MsgUtils.ToastLength.LONG);
        }
    }
    
    private void hideSoftKeyboard() {
        if (this.getActivity() != null && this.getView() != null) {
            ((InputMethodManager)this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.getView().getWindowToken(), 0);
        }
    }
    
    private void invokeFacebookLogin() {
        LoginManager.getInstance().registerCallback(this.callbackManager, this);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }
    
    private void invokeLoginWithEmail() {
        this.hideSoftKeyboard();
        if (this.isRequiredFields(this.loginEmailEmailWrapper, this.loginEmailPasswordWrapper)) {
            this.logInWithEmail(this.loginEmailEmailWrapper.getEditText(), this.loginEmailPasswordWrapper.getEditText());
        }
    }
    
    private void invokeRegisterNewUser() {
        this.hideSoftKeyboard();
        if (this.isRequiredFields(this.loginRegistrationEmailWrapper, this.loginRegistrationPasswordWrapper)) {
            this.registerNewUser(this.loginRegistrationEmailWrapper.getEditText(), this.loginRegistrationPasswordWrapper.getEditText());
        }
    }
    
    private void invokeResetPassword() {
        final EditText editText = this.loginEmailForgottenEmailWrapper.getEditText();
        if (editText == null || editText.getText().toString().equalsIgnoreCase("")) {
            this.loginEmailForgottenEmailWrapper.setErrorEnabled(true);
            this.loginEmailForgottenEmailWrapper.setError(this.getString(2131230880));
            return;
        }
        this.loginEmailForgottenEmailWrapper.setErrorEnabled(false);
        this.resetPassword(editText);
    }
    
    private boolean isRequiredFields(final TextInputLayout textInputLayout, final TextInputLayout textInputLayout2) {
        boolean b = true;
        if (textInputLayout == null || textInputLayout2 == null) {
            Timber.e(new RuntimeException(), "Called isRequiredFields with null parameters.", new Object[0]);
            MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.LONG);
            b = false;
        }
        else {
            final EditText editText = textInputLayout.getEditText();
            final EditText editText2 = textInputLayout2.getEditText();
            if (editText == null || editText2 == null) {
                Timber.e(new RuntimeException(), "Called isRequiredFields with null editTexts in wrappers.", new Object[0]);
                MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.LONG);
                return false;
            }
            boolean b2 = false;
            boolean b3 = false;
            if (editText.getText().toString().equalsIgnoreCase("")) {
                textInputLayout.setErrorEnabled(b);
                textInputLayout.setError(this.getString(2131230880));
            }
            else {
                textInputLayout.setErrorEnabled(false);
                b2 = true;
            }
            if (editText2.getText().toString().equalsIgnoreCase("")) {
                textInputLayout2.setErrorEnabled(b);
                textInputLayout2.setError(this.getString(2131230880));
            }
            else {
                textInputLayout2.setErrorEnabled(false);
                b3 = true;
            }
            if (!b2 || !b3) {
                Timber.e("Some fields are required.", new Object[0]);
                return false;
            }
        }
        return b;
    }
    
    private void logInWithEmail(final EditText editText, final EditText editText2) {
        SettingsMy.setUserEmailHint(editText.getText().toString());
        final String format = String.format(EndPoints.USER_LOGIN_EMAIL, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
        this.progressDialog.show();
        try {
            final JSONObject userAuthentication = JsonUtils.createUserAuthentication(editText.getText().toString().trim(), editText2.getText().toString().trim());
            editText2.setText((CharSequence)"");
            final GsonRequest gsonRequest = new GsonRequest<Object>(1, format, userAuthentication.toString(), User.class, new Response.Listener<User>() {
                public void onResponse(@NonNull final User user) {
                    Timber.d("response:" + user.toString(), new Object[0]);
                    LoginDialogFragment.this.handleUserLogin(user);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (LoginDialogFragment.this.progressDialog != null) {
                        LoginDialogFragment.this.progressDialog.cancel();
                    }
                    MsgUtils.logAndShowErrorMessage(LoginDialogFragment.this.getActivity(), volleyError);
                }
            });
            gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            gsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "login_dialog_requests");
        }
        catch (JSONException ex) {
            Timber.e((Throwable)ex, "Parse logInWithEmail exception", new Object[0]);
            MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
        }
    }
    
    public static void logoutUser() {
        final LoginManager instance = LoginManager.getInstance();
        if (instance != null) {
            instance.logOut();
        }
        SettingsMy.setActiveUser(null);
        MainActivity.updateCartCountNotification();
        MainActivity.invalidateDrawerMenuHeader();
    }
    
    public static LoginDialogFragment newInstance(final LoginDialogInterface loginDialogInterface) {
        final LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
        loginDialogFragment.loginDialogInterface = loginDialogInterface;
        return loginDialogFragment;
    }
    
    private void prepareActionButtons(final View view) {
        ((TextView)view.findViewById(2131624112)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                LoginDialogFragment.this.dismiss();
            }
        });
        ((Button)view.findViewById(2131624110)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                LoginDialogFragment.this.invokeFacebookLogin();
            }
        });
        ((Button)view.findViewById(2131624130)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                LoginDialogFragment.this.invokeLoginWithEmail();
            }
        });
        ((Button)view.findViewById(2131624123)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                LoginDialogFragment.this.invokeRegisterNewUser();
            }
        });
        ((Button)view.findViewById(2131624135)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                LoginDialogFragment.this.invokeResetPassword();
            }
        });
    }
    
    private void prepareInputBoxes(final View view) {
        this.loginRegistrationEmailWrapper = (TextInputLayout)view.findViewById(2131624116);
        this.loginRegistrationPasswordWrapper = (TextInputLayout)view.findViewById(2131624119);
        this.loginRegistrationGenderWoman = (RadioButton)view.findViewById(2131624122);
        final EditText editText = this.loginRegistrationPasswordWrapper.getEditText();
        if (editText != null) {
            editText.setOnTouchListener((View$OnTouchListener)new OnTouchPasswordListener(editText));
        }
        this.loginEmailEmailWrapper = (TextInputLayout)view.findViewById(2131624127);
        final EditText editText2 = this.loginEmailEmailWrapper.getEditText();
        if (editText2 != null) {
            editText2.setText((CharSequence)SettingsMy.getUserEmailHint());
        }
        this.loginEmailPasswordWrapper = (TextInputLayout)view.findViewById(2131624128);
        final EditText editText3 = this.loginEmailPasswordWrapper.getEditText();
        if (editText3 != null) {
            editText3.setOnTouchListener((View$OnTouchListener)new OnTouchPasswordListener(editText3));
            editText3.setOnEditorActionListener((TextView$OnEditorActionListener)new TextView$OnEditorActionListener() {
                public boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
                    if (n == 4 || n == 124) {
                        LoginDialogFragment.this.invokeLoginWithEmail();
                        return true;
                    }
                    return false;
                }
            });
        }
        this.loginEmailForgottenEmailWrapper = (TextInputLayout)view.findViewById(2131624134);
        final EditText editText4 = this.loginEmailForgottenEmailWrapper.getEditText();
        if (editText4 != null) {
            editText4.setText((CharSequence)SettingsMy.getUserEmailHint());
        }
        final Account[] accountsByType = AccountManager.get((Context)this.getActivity()).getAccountsByType("com.google");
        final String[] array = new String[accountsByType.length];
        for (int i = 0; i < accountsByType.length; ++i) {
            array[i] = accountsByType[i].name;
            Timber.e("Sets autocompleteEmails: " + accountsByType[i].name, new Object[0]);
        }
        ((AutoCompleteTextView)view.findViewById(2131624117)).setAdapter((ListAdapter)new ArrayAdapter((Context)this.getActivity(), 17367050, (Object[])array));
    }
    
    private void prepareLoginFormNavigation(final View view) {
        ((Button)view.findViewById(2131624109)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                LoginDialogFragment.this.setVisibilityOfEmailForm(true);
            }
        });
        ((ImageButton)view.findViewById(2131624126)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                new Handler().postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        LoginDialogFragment.this.setVisibilityOfEmailForm(false);
                    }
                }, 200L);
            }
        });
        ((TextView)view.findViewById(2131624111)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                LoginDialogFragment.this.setVisibilityOfRegistrationForm(true);
            }
        });
        ((ImageButton)view.findViewById(2131624115)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                new Handler().postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        LoginDialogFragment.this.setVisibilityOfRegistrationForm(false);
                    }
                }, 200L);
            }
        });
        ((TextView)view.findViewById(2131624129)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                LoginDialogFragment.this.setVisibilityOfEmailForgottenForm(true);
            }
        });
        ((ImageButton)view.findViewById(2131624133)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                new Handler().postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        LoginDialogFragment.this.setVisibilityOfEmailForgottenForm(false);
                    }
                }, 200L);
            }
        });
    }
    
    private void registerNewUser(final EditText editText, final EditText editText2) {
        SettingsMy.setUserEmailHint(editText.getText().toString());
        final String format = String.format(EndPoints.USER_REGISTER, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
        this.progressDialog.show();
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", (Object)editText.getText().toString().trim());
            jsonObject.put("password", (Object)editText2.getText().toString().trim());
            String s;
            if (this.loginRegistrationGenderWoman.isSelected()) {
                s = "female";
            }
            else {
                s = "male";
            }
            jsonObject.put("gender", (Object)s);
            final GsonRequest gsonRequest = new GsonRequest<Object>(1, format, jsonObject.toString(), (Class<Object>)User.class, (Response.Listener<Object>)new Response.Listener<User>() {
                public void onResponse(@NonNull final User user) {
                    Timber.d("response:" + user.toString(), new Object[0]);
                    LoginDialogFragment.this.handleUserLogin(user);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (LoginDialogFragment.this.progressDialog != null) {
                        LoginDialogFragment.this.progressDialog.cancel();
                    }
                    MsgUtils.logAndShowErrorMessage(LoginDialogFragment.this.getActivity(), volleyError);
                }
            });
            gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            gsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "login_dialog_requests");
        }
        catch (JSONException ex) {
            Timber.e((Throwable)ex, "Parse new user registration exception", new Object[0]);
            MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
        }
    }
    
    private void resetPassword(final EditText editText) {
        final String format = String.format(EndPoints.USER_RESET_PASSWORD, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
        this.progressDialog.show();
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", (Object)editText.getText().toString().trim());
            final JsonRequest jsonRequest = new JsonRequest(1, format, jsonObject, new Response.Listener<JSONObject>() {
                public void onResponse(final JSONObject jsonObject) {
                    Timber.d("Reset password on url success. Response:" + jsonObject.toString(), new Object[0]);
                    LoginDialogFragment.this.progressDialog.cancel();
                    MsgUtils.showToast(LoginDialogFragment.this.getActivity(), 1, LoginDialogFragment.this.getString(2131230800), MsgUtils.ToastLength.LONG);
                    LoginDialogFragment.this.setVisibilityOfEmailForgottenForm(false);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (LoginDialogFragment.this.progressDialog != null) {
                        LoginDialogFragment.this.progressDialog.cancel();
                    }
                    MsgUtils.logAndShowErrorMessage(LoginDialogFragment.this.getActivity(), volleyError);
                }
            });
            jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            jsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, "login_dialog_requests");
        }
        catch (JSONException ex) {
            Timber.e((Throwable)ex, "Parse resetPassword exception", new Object[0]);
            MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
        }
    }
    
    private void setVisibilityOfEmailForgottenForm(final boolean b) {
        if (b) {
            this.actualFormState = FormState.FORGOTTEN_PASSWORD;
            this.loginEmailForm.setVisibility(4);
            this.loginEmailForgottenForm.setVisibility(0);
        }
        else {
            this.actualFormState = FormState.EMAIL;
            this.loginEmailForm.setVisibility(0);
            this.loginEmailForgottenForm.setVisibility(4);
        }
        this.hideSoftKeyboard();
    }
    
    private void setVisibilityOfEmailForm(final boolean b) {
        if (b) {
            this.actualFormState = FormState.EMAIL;
            this.loginBaseForm.setVisibility(4);
            this.loginEmailForm.setVisibility(0);
            return;
        }
        this.actualFormState = FormState.BASE;
        this.loginBaseForm.setVisibility(0);
        this.loginEmailForm.setVisibility(4);
        this.hideSoftKeyboard();
    }
    
    private void setVisibilityOfRegistrationForm(final boolean b) {
        if (b) {
            this.actualFormState = FormState.REGISTRATION;
            this.loginBaseForm.setVisibility(4);
            this.loginRegistrationForm.setVisibility(0);
            return;
        }
        this.actualFormState = FormState.BASE;
        this.loginBaseForm.setVisibility(0);
        this.loginRegistrationForm.setVisibility(4);
        this.hideSoftKeyboard();
    }
    
    public void handleUserLogin(final User activeUser) {
        if (this.progressDialog != null) {
            this.progressDialog.cancel();
        }
        SettingsMy.setActiveUser(activeUser);
        SettingsMy.setTokenSentToServer(false);
        if (this.getActivity() instanceof MainActivity) {
            ((MainActivity)this.getActivity()).registerGcmOnServer();
        }
        MainActivity.invalidateDrawerMenuHeader();
        if (this.loginDialogInterface != null) {
            this.loginDialogInterface.successfulLoginOrRegistration(activeUser);
        }
        else {
            Timber.e("Interface is null", new Object[0]);
            MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
        }
        this.dismiss();
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (this.callbackManager != null) {
            this.callbackManager.onActivityResult(n, n2, intent);
            return;
        }
        Timber.d("OnActivityResult, null callbackManager object.", new Object[0]);
    }
    
    @Override
    public void onCancel() {
        Timber.d("Fb login canceled", new Object[0]);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setStyle(1, 2131427707);
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " - onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968622, viewGroup, false);
        this.callbackManager = CallbackManager.Factory.create();
        this.loginBaseForm = (LinearLayout)inflate.findViewById(2131624107);
        this.loginRegistrationForm = (LinearLayout)inflate.findViewById(2131624113);
        this.loginEmailForm = (LinearLayout)inflate.findViewById(2131624124);
        this.loginEmailForgottenForm = (LinearLayout)inflate.findViewById(2131624131);
        this.prepareLoginFormNavigation(inflate);
        this.prepareInputBoxes(inflate);
        this.prepareActionButtons(inflate);
        return inflate;
    }
    
    @Override
    public void onDetach() {
        this.loginDialogInterface = null;
        super.onDetach();
    }
    
    @Override
    public void onError(final FacebookException ex) {
        Timber.e(ex, "Fb login error", new Object[0]);
        this.handleNonFatalError(this.getString(2131230824), false);
    }
    
    @Override
    public void onStart() {
        super.onStart();
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            final Window window = dialog.getWindow();
            window.setLayout(-1, -1);
            window.setWindowAnimations(2131427706);
            dialog.setOnKeyListener((DialogInterface$OnKeyListener)new DialogInterface$OnKeyListener() {
                public boolean onKey(final DialogInterface dialogInterface, final int n, final KeyEvent keyEvent) {
                    boolean b = true;
                    if (n == 4 && keyEvent.getRepeatCount() == 0) {
                        switch (LoginDialogFragment.this.actualFormState) {
                            default: {
                                b = false;
                                break;
                            }
                            case REGISTRATION: {
                                if (keyEvent.getAction() == (b ? 1 : 0)) {
                                    LoginDialogFragment.this.setVisibilityOfRegistrationForm(false);
                                    return b;
                                }
                                break;
                            }
                            case FORGOTTEN_PASSWORD: {
                                if (keyEvent.getAction() == (b ? 1 : 0)) {
                                    LoginDialogFragment.this.setVisibilityOfEmailForgottenForm(false);
                                    return b;
                                }
                                break;
                            }
                            case EMAIL: {
                                if (keyEvent.getAction() == (b ? 1 : 0)) {
                                    LoginDialogFragment.this.setVisibilityOfEmailForm(false);
                                    return b;
                                }
                                break;
                            }
                        }
                        return b;
                    }
                    return false;
                }
            });
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        MyApplication.getInstance().getRequestQueue().cancelAll("login_dialog_requests");
    }
    
    @Override
    public void onSuccess(final LoginResult loginResult) {
        Timber.d("FB login success", new Object[0]);
        if (loginResult == null) {
            Timber.e("Fb login succeed with null loginResult.", new Object[0]);
            this.handleNonFatalError(this.getString(2131230824), true);
            return;
        }
        Timber.d("Result:" + loginResult.toString(), new Object[0]);
        final GraphRequest meRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), (GraphRequest.GraphJSONObjectCallback)new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(final JSONObject jsonObject, final GraphResponse graphResponse) {
                if (graphResponse != null && graphResponse.getError() == null) {
                    LoginDialogFragment.this.verifyUserOnApi(jsonObject, loginResult.getAccessToken());
                    return;
                }
                Timber.e("Error on receiving user profile information.", new Object[0]);
                if (graphResponse != null && graphResponse.getError() != null) {
                    Timber.e(new RuntimeException(), "Error:" + graphResponse.getError().toString(), new Object[0]);
                }
                LoginDialogFragment.this.handleNonFatalError(LoginDialogFragment.this.getString(2131230877), true);
            }
        });
        final Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender");
        meRequest.setParameters(parameters);
        meRequest.executeAsync();
    }
    
    public void verifyUserOnApi(final JSONObject jsonObject, final AccessToken accessToken) {
        final String format = String.format(EndPoints.USER_LOGIN_FACEBOOK, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
        final JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject2.put("fb_id", (Object)jsonObject.getString("id"));
            jsonObject2.put("fb_access_token", (Object)accessToken.getToken());
            this.progressDialog.show();
            final GsonRequest gsonRequest = new GsonRequest<Object>(1, format, jsonObject2.toString(), User.class, new Response.Listener<User>() {
                public void onResponse(@NonNull final User user) {
                    Timber.d("response:" + user.toString(), new Object[0]);
                    LoginDialogFragment.this.handleUserLogin(user);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    if (LoginDialogFragment.this.progressDialog != null) {
                        LoginDialogFragment.this.progressDialog.cancel();
                    }
                    MsgUtils.logAndShowErrorMessage(LoginDialogFragment.this.getActivity(), volleyError);
                    LoginDialogFragment.logoutUser();
                }
            }, this.getFragmentManager(), null);
            gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            gsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "login_dialog_requests");
        }
        catch (JSONException ex) {
            Timber.e((Throwable)ex, "Exception while parsing fb user.", new Object[0]);
            MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.LONG);
        }
    }
    
    private enum FormState
    {
        BASE, 
        EMAIL, 
        FORGOTTEN_PASSWORD, 
        REGISTRATION;
    }
}
