package com.facebook.login;

import com.facebook.internal.*;
import android.content.*;
import android.os.*;

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
