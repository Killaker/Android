package com.facebook.login;

import com.facebook.appevents.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import org.json.*;
import java.util.*;
import android.text.*;

class LoginLogger
{
    static final String EVENT_EXTRAS_DEFAULT_AUDIENCE = "default_audience";
    static final String EVENT_EXTRAS_FACEBOOK_VERSION = "facebookVersion";
    static final String EVENT_EXTRAS_IS_REAUTHORIZE = "isReauthorize";
    static final String EVENT_EXTRAS_LOGIN_BEHAVIOR = "login_behavior";
    static final String EVENT_EXTRAS_MISSING_INTERNET_PERMISSION = "no_internet_permission";
    static final String EVENT_EXTRAS_NEW_PERMISSIONS = "new_permissions";
    static final String EVENT_EXTRAS_NOT_TRIED = "not_tried";
    static final String EVENT_EXTRAS_PERMISSIONS = "permissions";
    static final String EVENT_EXTRAS_REQUEST_CODE = "request_code";
    static final String EVENT_EXTRAS_TRY_LOGIN_ACTIVITY = "try_login_activity";
    static final String EVENT_NAME_LOGIN_COMPLETE = "fb_mobile_login_complete";
    static final String EVENT_NAME_LOGIN_METHOD_COMPLETE = "fb_mobile_login_method_complete";
    static final String EVENT_NAME_LOGIN_METHOD_START = "fb_mobile_login_method_start";
    static final String EVENT_NAME_LOGIN_START = "fb_mobile_login_start";
    static final String EVENT_PARAM_AUTH_LOGGER_ID = "0_auth_logger_id";
    static final String EVENT_PARAM_ERROR_CODE = "4_error_code";
    static final String EVENT_PARAM_ERROR_MESSAGE = "5_error_message";
    static final String EVENT_PARAM_EXTRAS = "6_extras";
    static final String EVENT_PARAM_LOGIN_RESULT = "2_result";
    static final String EVENT_PARAM_METHOD = "3_method";
    static final String EVENT_PARAM_METHOD_RESULT_SKIPPED = "skipped";
    static final String EVENT_PARAM_TIMESTAMP = "1_timestamp_ms";
    static final String FACEBOOK_PACKAGE_NAME = "com.facebook.katana";
    private final AppEventsLogger appEventsLogger;
    private String applicationId;
    private String facebookVersion;
    
    LoginLogger(final Context context, final String applicationId) {
        this.applicationId = applicationId;
        this.appEventsLogger = AppEventsLogger.newLogger(context, applicationId);
        try {
            final PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                final PackageInfo packageInfo = packageManager.getPackageInfo("com.facebook.katana", 0);
                if (packageInfo != null) {
                    this.facebookVersion = packageInfo.versionName;
                }
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
    }
    
    static Bundle newAuthorizationLoggingBundle(final String s) {
        final Bundle bundle = new Bundle();
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        bundle.putString("0_auth_logger_id", s);
        bundle.putString("3_method", "");
        bundle.putString("2_result", "");
        bundle.putString("5_error_message", "");
        bundle.putString("4_error_code", "");
        bundle.putString("6_extras", "");
        return bundle;
    }
    
    public String getApplicationId() {
        return this.applicationId;
    }
    
    public void logAuthorizationMethodComplete(final String s, final String s2, final String s3, final String s4, final String s5, final Map<String, String> map) {
        final Bundle authorizationLoggingBundle = newAuthorizationLoggingBundle(s);
        if (s3 != null) {
            authorizationLoggingBundle.putString("2_result", s3);
        }
        if (s4 != null) {
            authorizationLoggingBundle.putString("5_error_message", s4);
        }
        if (s5 != null) {
            authorizationLoggingBundle.putString("4_error_code", s5);
        }
        if (map != null && !map.isEmpty()) {
            authorizationLoggingBundle.putString("6_extras", new JSONObject((Map)map).toString());
        }
        authorizationLoggingBundle.putString("3_method", s2);
        this.appEventsLogger.logSdkEvent("fb_mobile_login_method_complete", null, authorizationLoggingBundle);
    }
    
    public void logAuthorizationMethodStart(final String s, final String s2) {
        final Bundle authorizationLoggingBundle = newAuthorizationLoggingBundle(s);
        authorizationLoggingBundle.putString("3_method", s2);
        this.appEventsLogger.logSdkEvent("fb_mobile_login_method_start", null, authorizationLoggingBundle);
    }
    
    public void logCompleteLogin(final String s, final Map<String, String> map, final LoginClient.Result.Code code, final Map<String, String> map2, final Exception ex) {
        final Bundle authorizationLoggingBundle = newAuthorizationLoggingBundle(s);
        if (code != null) {
            authorizationLoggingBundle.putString("2_result", code.getLoggingValue());
        }
        if (ex != null && ex.getMessage() != null) {
            authorizationLoggingBundle.putString("5_error_message", ex.getMessage());
        }
        final boolean empty = map.isEmpty();
        JSONObject jsonObject = null;
        if (!empty) {
            jsonObject = new JSONObject((Map)map);
        }
        if (map2 != null) {
            if (jsonObject == null) {
                jsonObject = new JSONObject();
            }
            try {
                for (final Map.Entry<String, String> entry : map2.entrySet()) {
                    jsonObject.put((String)entry.getKey(), (Object)entry.getValue());
                }
            }
            catch (JSONException ex2) {}
        }
        if (jsonObject != null) {
            authorizationLoggingBundle.putString("6_extras", jsonObject.toString());
        }
        this.appEventsLogger.logSdkEvent("fb_mobile_login_complete", null, authorizationLoggingBundle);
    }
    
    public void logStartLogin(final LoginClient.Request request) {
        final Bundle authorizationLoggingBundle = newAuthorizationLoggingBundle(request.getAuthId());
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("login_behavior", (Object)request.getLoginBehavior().toString());
                jsonObject.put("request_code", LoginClient.getLoginRequestCode());
                jsonObject.put("permissions", (Object)TextUtils.join((CharSequence)",", (Iterable)request.getPermissions()));
                jsonObject.put("default_audience", (Object)request.getDefaultAudience().toString());
                jsonObject.put("isReauthorize", request.isRerequest());
                if (this.facebookVersion != null) {
                    jsonObject.put("facebookVersion", (Object)this.facebookVersion);
                }
                authorizationLoggingBundle.putString("6_extras", jsonObject.toString());
                this.appEventsLogger.logSdkEvent("fb_mobile_login_start", null, authorizationLoggingBundle);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public void logUnexpectedError(final String s, final String s2) {
        this.logUnexpectedError(s, s2, "");
    }
    
    public void logUnexpectedError(final String s, final String s2, final String s3) {
        final Bundle authorizationLoggingBundle = newAuthorizationLoggingBundle("");
        authorizationLoggingBundle.putString("2_result", LoginClient.Result.Code.ERROR.getLoggingValue());
        authorizationLoggingBundle.putString("5_error_message", s2);
        authorizationLoggingBundle.putString("3_method", s3);
        this.appEventsLogger.logSdkEvent(s, null, authorizationLoggingBundle);
    }
}
