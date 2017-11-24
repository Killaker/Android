package com.facebook.login;

import android.os.*;
import java.util.*;
import com.facebook.*;
import com.facebook.internal.*;
import android.content.*;

class KatanaProxyLoginMethodHandler extends LoginMethodHandler
{
    public static final Parcelable$Creator<KatanaProxyLoginMethodHandler> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public KatanaProxyLoginMethodHandler createFromParcel(final Parcel parcel) {
                return new KatanaProxyLoginMethodHandler(parcel);
            }
            
            public KatanaProxyLoginMethodHandler[] newArray(final int n) {
                return new KatanaProxyLoginMethodHandler[n];
            }
        };
    }
    
    KatanaProxyLoginMethodHandler(final Parcel parcel) {
        super(parcel);
    }
    
    KatanaProxyLoginMethodHandler(final LoginClient loginClient) {
        super(loginClient);
    }
    
    private String getError(final Bundle bundle) {
        String s = bundle.getString("error");
        if (s == null) {
            s = bundle.getString("error_type");
        }
        return s;
    }
    
    private String getErrorMessage(final Bundle bundle) {
        String s = bundle.getString("error_message");
        if (s == null) {
            s = bundle.getString("error_description");
        }
        return s;
    }
    
    private LoginClient.Result handleResultCancel(final LoginClient.Request request, final Intent intent) {
        final Bundle extras = intent.getExtras();
        final String error = this.getError(extras);
        final String string = extras.getString("error_code");
        if ("CONNECTION_FAILURE".equals(string)) {
            return LoginClient.Result.createErrorResult(request, error, this.getErrorMessage(extras), string);
        }
        return LoginClient.Result.createCancelResult(request, error);
    }
    
    private LoginClient.Result handleResultOk(final LoginClient.Request request, final Intent intent) {
        final Bundle extras = intent.getExtras();
        final String error = this.getError(extras);
        final String string = extras.getString("error_code");
        final String errorMessage = this.getErrorMessage(extras);
        final String string2 = extras.getString("e2e");
        if (!Utility.isNullOrEmpty(string2)) {
            this.logWebLoginCompleted(string2);
        }
        Label_0105: {
            if (error != null || string != null || errorMessage != null) {
                break Label_0105;
            }
            try {
                final Object tokenResult = LoginClient.Result.createTokenResult(request, LoginMethodHandler.createAccessTokenFromWebBundle(request.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, request.getApplicationId()));
                return (LoginClient.Result)tokenResult;
            }
            catch (FacebookException ex) {
                return LoginClient.Result.createErrorResult(request, null, ex.getMessage());
            }
        }
        final boolean contains = ServerProtocol.errorsProxyAuthDisabled.contains(error);
        final Object tokenResult = null;
        if (contains) {
            return (LoginClient.Result)tokenResult;
        }
        if (ServerProtocol.errorsUserCanceled.contains(error)) {
            return LoginClient.Result.createCancelResult(request, null);
        }
        return LoginClient.Result.createErrorResult(request, error, errorMessage, string);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    String getNameForLogging() {
        return "katana_proxy_auth";
    }
    
    @Override
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        final LoginClient.Request pendingRequest = this.loginClient.getPendingRequest();
        Object o;
        if (intent == null) {
            o = LoginClient.Result.createCancelResult(pendingRequest, "Operation canceled");
        }
        else if (n2 == 0) {
            o = this.handleResultCancel(pendingRequest, intent);
        }
        else if (n2 != -1) {
            o = LoginClient.Result.createErrorResult(pendingRequest, "Unexpected resultCode from authorization.", null);
        }
        else {
            o = this.handleResultOk(pendingRequest, intent);
        }
        if (o != null) {
            this.loginClient.completeAndValidate((LoginClient.Result)o);
        }
        else {
            this.loginClient.tryNextHandler();
        }
        return true;
    }
    
    @Override
    boolean tryAuthorize(final LoginClient.Request request) {
        final String e2E = LoginClient.getE2E();
        final Intent proxyAuthIntent = NativeProtocol.createProxyAuthIntent((Context)this.loginClient.getActivity(), request.getApplicationId(), request.getPermissions(), e2E, request.isRerequest(), request.hasPublishPermission(), request.getDefaultAudience());
        this.addLoggingExtra("e2e", e2E);
        return this.tryIntent(proxyAuthIntent, LoginClient.getLoginRequestCode());
    }
    
    protected boolean tryIntent(final Intent intent, final int n) {
        if (intent == null) {
            return false;
        }
        try {
            this.loginClient.getFragment().startActivityForResult(intent, n);
            return true;
        }
        catch (ActivityNotFoundException ex) {
            return false;
        }
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
    }
}
