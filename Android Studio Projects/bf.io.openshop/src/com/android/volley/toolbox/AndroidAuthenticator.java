package com.android.volley.toolbox;

import android.os.*;
import android.content.*;
import com.android.volley.*;
import android.accounts.*;

public class AndroidAuthenticator implements Authenticator
{
    private final Account mAccount;
    private final AccountManager mAccountManager;
    private final String mAuthTokenType;
    private final boolean mNotifyAuthFailure;
    
    AndroidAuthenticator(final AccountManager mAccountManager, final Account mAccount, final String mAuthTokenType, final boolean mNotifyAuthFailure) {
        this.mAccountManager = mAccountManager;
        this.mAccount = mAccount;
        this.mAuthTokenType = mAuthTokenType;
        this.mNotifyAuthFailure = mNotifyAuthFailure;
    }
    
    public AndroidAuthenticator(final Context context, final Account account, final String s) {
        this(context, account, s, false);
    }
    
    public AndroidAuthenticator(final Context context, final Account account, final String s, final boolean b) {
        this(AccountManager.get(context), account, s, b);
    }
    
    public Account getAccount() {
        return this.mAccount;
    }
    
    @Override
    public String getAuthToken() throws AuthFailureError {
        final AccountManagerFuture authToken = this.mAccountManager.getAuthToken(this.mAccount, this.mAuthTokenType, this.mNotifyAuthFailure, (AccountManagerCallback)null, (Handler)null);
        String string = null;
        Label_0110: {
            Bundle bundle;
            try {
                bundle = (Bundle)authToken.getResult();
                final boolean done = authToken.isDone();
                string = null;
                if (!done) {
                    break Label_0110;
                }
                final boolean cancelled = authToken.isCancelled();
                string = null;
                if (cancelled) {
                    break Label_0110;
                }
                if (bundle.containsKey("intent")) {
                    throw new AuthFailureError((Intent)bundle.getParcelable("intent"));
                }
            }
            catch (Exception ex) {
                throw new AuthFailureError("Error while retrieving auth token", ex);
            }
            string = bundle.getString("authtoken");
        }
        if (string == null) {
            throw new AuthFailureError("Got null auth token for type: " + this.mAuthTokenType);
        }
        return string;
    }
    
    public String getAuthTokenType() {
        return this.mAuthTokenType;
    }
    
    @Override
    public void invalidateAuthToken(final String s) {
        this.mAccountManager.invalidateAuthToken(this.mAccount.type, s);
    }
}
