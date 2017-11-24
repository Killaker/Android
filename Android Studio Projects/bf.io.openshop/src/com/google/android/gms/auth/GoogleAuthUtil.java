package com.google.android.gms.auth;

import java.io.*;
import java.util.*;
import android.accounts.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import android.content.*;
import android.support.annotation.*;
import com.google.android.gms.common.*;

public final class GoogleAuthUtil extends zzd
{
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_ANDROID_PACKAGE_NAME;
    public static final String KEY_CALLER_UID;
    public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
    @Deprecated
    public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    
    static {
        KEY_CALLER_UID = zzd.KEY_CALLER_UID;
        KEY_ANDROID_PACKAGE_NAME = zzd.KEY_ANDROID_PACKAGE_NAME;
    }
    
    public static void clearToken(final Context context, final String s) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
        zzd.clearToken(context, s);
    }
    
    public static List<AccountChangeEvent> getAccountChangeEvents(final Context context, final int n, final String s) throws GoogleAuthException, IOException {
        return zzd.getAccountChangeEvents(context, n, s);
    }
    
    public static String getAccountId(final Context context, final String s) throws GoogleAuthException, IOException {
        return zzd.getAccountId(context, s);
    }
    
    public static String getToken(final Context context, final Account account, final String s) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzd.getToken(context, account, s);
    }
    
    public static String getToken(final Context context, final Account account, final String s, final Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzd.getToken(context, account, s, bundle);
    }
    
    @Deprecated
    public static String getToken(final Context context, final String s, final String s2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzd.getToken(context, s, s2);
    }
    
    @Deprecated
    public static String getToken(final Context context, final String s, final String s2, final Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzd.getToken(context, s, s2, bundle);
    }
    
    public static String getTokenWithNotification(final Context context, final Account account, final String s, final Bundle bundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return zza(context, account, s, bundle).getToken();
    }
    
    public static String getTokenWithNotification(final Context context, final Account account, final String s, Bundle bundle, final Intent intent) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        zzd.zzi(intent);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putParcelable("callback_intent", (Parcelable)intent);
        bundle.putBoolean("handle_notification", true);
        return zzb(context, account, s, bundle).getToken();
    }
    
    public static String getTokenWithNotification(final Context context, final Account account, final String s, Bundle bundle, final String s2, Bundle bundle2) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        zzx.zzh(s2, "Authority cannot be empty or null.");
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        ContentResolver.validateSyncExtrasBundle(bundle2);
        bundle.putString("authority", s2);
        bundle.putBundle("sync_extras", bundle2);
        bundle.putBoolean("handle_notification", true);
        return zzb(context, account, s, bundle).getToken();
    }
    
    @Deprecated
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(s, "com.google"), s2, bundle);
    }
    
    @Deprecated
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle, final Intent intent) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(s, "com.google"), s2, bundle, intent);
    }
    
    @Deprecated
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle, final String s3, final Bundle bundle2) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(s, "com.google"), s2, bundle, s3, bundle2);
    }
    
    @Deprecated
    @RequiresPermission("android.permission.MANAGE_ACCOUNTS")
    public static void invalidateToken(final Context context, final String s) {
        zzd.invalidateToken(context, s);
    }
    
    public static TokenData zza(final Context context, final Account account, final String s, Bundle bundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean("handle_notification", true);
        return zzb(context, account, s, bundle);
    }
    
    private static TokenData zzb(final Context context, final Account account, final String s, Bundle bundle) throws IOException, GoogleAuthException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            final TokenData zzc = zzd.zzc(context, account, s, bundle);
            zze.zzal(context);
            return zzc;
        }
        catch (GooglePlayServicesAvailabilityException ex) {
            GooglePlayServicesUtil.showErrorNotification(ex.getConnectionStatusCode(), context);
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
        catch (UserRecoverableAuthException ex2) {
            zze.zzal(context);
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
    }
}
