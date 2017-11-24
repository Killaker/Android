package com.facebook.login.widget;

import android.view.*;
import com.facebook.*;
import android.app.*;
import com.facebook.appevents.*;
import android.os.*;
import android.content.*;
import com.facebook.login.*;
import java.util.*;
import com.facebook.internal.*;

private class LoginClickListener implements View$OnClickListener
{
    public void onClick(final View view) {
        LoginButton.access$400(LoginButton.this, view);
        final Context context = LoginButton.this.getContext();
        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (currentAccessToken != null) {
            if (LoginButton.access$500(LoginButton.this)) {
                final String string = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_log_out_action);
                final String string2 = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_cancel_action);
                final Profile currentProfile = Profile.getCurrentProfile();
                String message;
                if (currentProfile != null && currentProfile.getName() != null) {
                    message = String.format(LoginButton.this.getResources().getString(R.string.com_facebook_loginview_logged_in_as), currentProfile.getName());
                }
                else {
                    message = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_logged_in_using_facebook);
                }
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
                alertDialog$Builder.setMessage((CharSequence)message).setCancelable(true).setPositiveButton((CharSequence)string, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        LoginButton.this.getLoginManager().logOut();
                    }
                }).setNegativeButton((CharSequence)string2, (DialogInterface$OnClickListener)null);
                alertDialog$Builder.create().show();
            }
            else {
                LoginButton.this.getLoginManager().logOut();
            }
        }
        else {
            final LoginManager loginManager = LoginButton.this.getLoginManager();
            loginManager.setDefaultAudience(LoginButton.this.getDefaultAudience());
            loginManager.setLoginBehavior(LoginButton.this.getLoginBehavior());
            if (LoginAuthorizationType.PUBLISH.equals(LoginButton.access$600(LoginButton.this).authorizationType)) {
                if (LoginButton.this.getFragment() != null) {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getFragment(), LoginButton.access$600(LoginButton.this).permissions);
                }
                else if (LoginButton.this.getNativeFragment() != null) {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getNativeFragment(), LoginButton.access$600(LoginButton.this).permissions);
                }
                else {
                    loginManager.logInWithPublishPermissions(LoginButton.access$900(LoginButton.this), LoginButton.access$600(LoginButton.this).permissions);
                }
            }
            else if (LoginButton.this.getFragment() != null) {
                loginManager.logInWithReadPermissions(LoginButton.this.getFragment(), LoginButton.access$600(LoginButton.this).permissions);
            }
            else if (LoginButton.this.getNativeFragment() != null) {
                loginManager.logInWithReadPermissions(LoginButton.this.getNativeFragment(), LoginButton.access$600(LoginButton.this).permissions);
            }
            else {
                loginManager.logInWithReadPermissions(LoginButton.access$1000(LoginButton.this), LoginButton.access$600(LoginButton.this).permissions);
            }
        }
        final AppEventsLogger logger = AppEventsLogger.newLogger(LoginButton.this.getContext());
        final Bundle bundle = new Bundle();
        int n;
        if (currentAccessToken != null) {
            n = 0;
        }
        else {
            n = 1;
        }
        bundle.putInt("logging_in", n);
        logger.logSdkEvent(LoginButton.access$1100(LoginButton.this), null, bundle);
    }
}
