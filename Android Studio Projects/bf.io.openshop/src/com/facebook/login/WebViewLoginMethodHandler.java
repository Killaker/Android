package com.facebook.login;

import android.os.*;
import android.webkit.*;
import android.content.*;
import java.util.*;
import com.facebook.*;
import android.text.*;
import com.facebook.internal.*;
import android.app.*;
import android.support.v4.app.*;

class WebViewLoginMethodHandler extends LoginMethodHandler
{
    public static final Parcelable$Creator<WebViewLoginMethodHandler> CREATOR;
    private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
    private String e2e;
    private WebDialog loginDialog;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public WebViewLoginMethodHandler createFromParcel(final Parcel parcel) {
                return new WebViewLoginMethodHandler(parcel);
            }
            
            public WebViewLoginMethodHandler[] newArray(final int n) {
                return new WebViewLoginMethodHandler[n];
            }
        };
    }
    
    WebViewLoginMethodHandler(final Parcel parcel) {
        super(parcel);
        this.e2e = parcel.readString();
    }
    
    WebViewLoginMethodHandler(final LoginClient loginClient) {
        super(loginClient);
    }
    
    private String loadCookieToken() {
        return ((Context)this.loginClient.getActivity()).getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
    }
    
    private void saveCookieToken(final String s) {
        ((Context)this.loginClient.getActivity()).getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit().putString("TOKEN", s).apply();
    }
    
    @Override
    void cancel() {
        if (this.loginDialog != null) {
            this.loginDialog.cancel();
            this.loginDialog = null;
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    String getNameForLogging() {
        return "web_view";
    }
    
    @Override
    boolean needsInternetPermission() {
        return true;
    }
    
    void onWebDialogComplete(final LoginClient.Request request, final Bundle bundle, final FacebookException ex) {
        while (true) {
            Label_0127: {
                if (bundle == null) {
                    break Label_0127;
                }
                if (bundle.containsKey("e2e")) {
                    this.e2e = bundle.getString("e2e");
                }
                try {
                    final AccessToken accessTokenFromWebBundle = LoginMethodHandler.createAccessTokenFromWebBundle(request.getPermissions(), bundle, AccessTokenSource.WEB_VIEW, request.getApplicationId());
                    final LoginClient.Result result = LoginClient.Result.createTokenResult(this.loginClient.getPendingRequest(), accessTokenFromWebBundle);
                    CookieSyncManager.createInstance((Context)this.loginClient.getActivity()).sync();
                    this.saveCookieToken(accessTokenFromWebBundle.getToken());
                    if (!Utility.isNullOrEmpty(this.e2e)) {
                        this.logWebLoginCompleted(this.e2e);
                    }
                    this.loginClient.completeAndValidate(result);
                    return;
                }
                catch (FacebookException ex2) {
                    final LoginClient.Result result = LoginClient.Result.createErrorResult(this.loginClient.getPendingRequest(), null, ex2.getMessage());
                    continue;
                }
            }
            if (ex instanceof FacebookOperationCanceledException) {
                final LoginClient.Result result = LoginClient.Result.createCancelResult(this.loginClient.getPendingRequest(), "User canceled log in.");
                continue;
            }
            this.e2e = null;
            String s = ex.getMessage();
            final boolean b = ex instanceof FacebookServiceException;
            String format = null;
            if (b) {
                final FacebookRequestError requestError = ((FacebookServiceException)ex).getRequestError();
                format = String.format(Locale.ROOT, "%d", requestError.getErrorCode());
                s = requestError.toString();
            }
            final LoginClient.Result result = LoginClient.Result.createErrorResult(this.loginClient.getPendingRequest(), null, s, format);
            continue;
        }
    }
    
    @Override
    boolean tryAuthorize(final LoginClient.Request request) {
        final Bundle bundle = new Bundle();
        if (!Utility.isNullOrEmpty(request.getPermissions())) {
            final String join = TextUtils.join((CharSequence)",", (Iterable)request.getPermissions());
            bundle.putString("scope", join);
            this.addLoggingExtra("scope", join);
        }
        bundle.putString("default_audience", request.getDefaultAudience().getNativeProtocolAudience());
        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        String token;
        if (currentAccessToken != null) {
            token = currentAccessToken.getToken();
        }
        else {
            token = null;
        }
        if (token != null && token.equals(this.loadCookieToken())) {
            bundle.putString("access_token", token);
            this.addLoggingExtra("access_token", "1");
        }
        else {
            Utility.clearFacebookCookies((Context)this.loginClient.getActivity());
            this.addLoggingExtra("access_token", "0");
        }
        final WebDialog.OnCompleteListener onCompleteListener = new WebDialog.OnCompleteListener() {
            @Override
            public void onComplete(final Bundle bundle, final FacebookException ex) {
                WebViewLoginMethodHandler.this.onWebDialogComplete(request, bundle, ex);
            }
        };
        this.addLoggingExtra("e2e", this.e2e = LoginClient.getE2E());
        final FragmentActivity activity = this.loginClient.getActivity();
        this.loginDialog = ((WebDialog.Builder)new AuthDialogBuilder((Context)activity, request.getApplicationId(), bundle).setE2E(this.e2e).setIsRerequest(request.isRerequest())).setOnCompleteListener(onCompleteListener).build();
        final FacebookDialogFragment facebookDialogFragment = new FacebookDialogFragment();
        facebookDialogFragment.setRetainInstance(true);
        facebookDialogFragment.setDialog(this.loginDialog);
        facebookDialogFragment.show(activity.getSupportFragmentManager(), "FacebookDialogFragment");
        return true;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.e2e);
    }
    
    static class AuthDialogBuilder extends Builder
    {
        private static final String OAUTH_DIALOG = "oauth";
        static final String REDIRECT_URI = "fbconnect://success";
        private String e2e;
        private boolean isRerequest;
        
        public AuthDialogBuilder(final Context context, final String s, final Bundle bundle) {
            super(context, s, "oauth", bundle);
        }
        
        @Override
        public WebDialog build() {
            final Bundle parameters = ((WebDialog.Builder)this).getParameters();
            parameters.putString("redirect_uri", "fbconnect://success");
            parameters.putString("client_id", ((WebDialog.Builder)this).getApplicationId());
            parameters.putString("e2e", this.e2e);
            parameters.putString("response_type", "token,signed_request");
            parameters.putString("return_scopes", "true");
            if (this.isRerequest) {
                parameters.putString("auth_type", "rerequest");
            }
            return new WebDialog(((WebDialog.Builder)this).getContext(), "oauth", parameters, ((WebDialog.Builder)this).getTheme(), ((WebDialog.Builder)this).getListener());
        }
        
        public AuthDialogBuilder setE2E(final String e2e) {
            this.e2e = e2e;
            return this;
        }
        
        public AuthDialogBuilder setIsRerequest(final boolean isRerequest) {
            this.isRerequest = isRerequest;
            return this;
        }
    }
}
