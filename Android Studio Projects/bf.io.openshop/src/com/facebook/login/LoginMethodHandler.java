package com.facebook.login;

import com.facebook.internal.*;
import android.os.*;
import com.facebook.*;
import android.util.*;
import org.json.*;
import java.io.*;
import java.util.*;
import com.facebook.appevents.*;
import android.content.*;

abstract class LoginMethodHandler implements Parcelable
{
    protected LoginClient loginClient;
    Map<String, String> methodLoggingExtras;
    
    LoginMethodHandler(final Parcel parcel) {
        this.methodLoggingExtras = Utility.readStringMapFromParcel(parcel);
    }
    
    LoginMethodHandler(final LoginClient loginClient) {
        this.loginClient = loginClient;
    }
    
    static AccessToken createAccessTokenFromNativeLogin(final Bundle bundle, final AccessTokenSource accessTokenSource, final String s) {
        final Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH", new Date(0L));
        final ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
        final String string = bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN");
        if (Utility.isNullOrEmpty(string)) {
            return null;
        }
        return new AccessToken(string, s, bundle.getString("com.facebook.platform.extra.USER_ID"), stringArrayList, null, accessTokenSource, bundleLongAsDate, new Date());
    }
    
    public static AccessToken createAccessTokenFromWebBundle(Collection<String> list, final Bundle bundle, final AccessTokenSource accessTokenSource, final String s) throws FacebookException {
        final Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, "expires_in", new Date());
        final String string = bundle.getString("access_token");
        final String string2 = bundle.getString("granted_scopes");
        if (!Utility.isNullOrEmpty(string2)) {
            list = new ArrayList<String>(Arrays.asList(string2.split(",")));
        }
        final String string3 = bundle.getString("denied_scopes");
        final boolean nullOrEmpty = Utility.isNullOrEmpty(string3);
        ArrayList<String> list2 = null;
        if (!nullOrEmpty) {
            list2 = new ArrayList<String>(Arrays.asList(string3.split(",")));
        }
        if (Utility.isNullOrEmpty(string)) {
            return null;
        }
        return new AccessToken(string, s, getUserIDFromSignedRequest(bundle.getString("signed_request")), (Collection<String>)list, list2, accessTokenSource, bundleLongAsDate, new Date());
    }
    
    private static String getUserIDFromSignedRequest(final String s) throws FacebookException {
        if (s == null || s.isEmpty()) {
            throw new FacebookException("Authorization response does not contain the signed_request");
        }
        try {
            final String[] split = s.split("\\.");
            if (split.length == 2) {
                return new JSONObject(new String(Base64.decode(split[1], 0), "UTF-8")).getString("user_id");
            }
            goto Label_0068;
        }
        catch (JSONException ex) {}
        catch (UnsupportedEncodingException ex2) {
            goto Label_0068;
        }
    }
    
    protected void addLoggingExtra(final String s, final Object o) {
        if (this.methodLoggingExtras == null) {
            this.methodLoggingExtras = new HashMap<String, String>();
        }
        final Map<String, String> methodLoggingExtras = this.methodLoggingExtras;
        String string;
        if (o == null) {
            string = null;
        }
        else {
            string = o.toString();
        }
        methodLoggingExtras.put(s, string);
    }
    
    void cancel() {
    }
    
    abstract String getNameForLogging();
    
    protected void logWebLoginCompleted(final String s) {
        final String applicationId = this.loginClient.getPendingRequest().getApplicationId();
        final AppEventsLogger logger = AppEventsLogger.newLogger((Context)this.loginClient.getActivity(), applicationId);
        final Bundle bundle = new Bundle();
        bundle.putString("fb_web_login_e2e", s);
        bundle.putLong("fb_web_login_switchback_time", System.currentTimeMillis());
        bundle.putString("app_id", applicationId);
        logger.logSdkEvent("fb_dialogs_web_login_dialog_complete", null, bundle);
    }
    
    boolean needsInternetPermission() {
        return false;
    }
    
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        return false;
    }
    
    void setLoginClient(final LoginClient loginClient) {
        if (this.loginClient != null) {
            throw new FacebookException("Can't set LoginClient if it is already set.");
        }
        this.loginClient = loginClient;
    }
    
    abstract boolean tryAuthorize(final LoginClient.Request p0);
    
    public void writeToParcel(final Parcel parcel, final int n) {
        Utility.writeStringMapToParcel(parcel, this.methodLoggingExtras);
    }
}
