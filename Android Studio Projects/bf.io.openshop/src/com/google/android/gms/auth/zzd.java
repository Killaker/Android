package com.google.android.gms.auth;

import java.io.*;
import com.google.android.gms.internal.*;
import java.util.*;
import android.accounts.*;
import android.support.annotation.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.*;
import android.text.*;
import android.os.*;
import android.content.*;
import java.net.*;
import android.util.*;

public class zzd
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
    private static final ComponentName zzVe;
    private static final ComponentName zzVf;
    
    static {
        String key_CALLER_UID;
        if (Build$VERSION.SDK_INT >= 11) {
            key_CALLER_UID = "callerUid";
        }
        else {
            key_CALLER_UID = "callerUid";
        }
        KEY_CALLER_UID = key_CALLER_UID;
        String key_ANDROID_PACKAGE_NAME;
        if (Build$VERSION.SDK_INT >= 14) {
            key_ANDROID_PACKAGE_NAME = "androidPackageName";
        }
        else {
            key_ANDROID_PACKAGE_NAME = "androidPackageName";
        }
        KEY_ANDROID_PACKAGE_NAME = key_ANDROID_PACKAGE_NAME;
        zzVe = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
        zzVf = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
    }
    
    public static void clearToken(final Context context, final String s) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
        zzx.zzcE("Calling this from your main thread can lead to deadlock");
        zzad(context);
        final Bundle bundle = new Bundle();
        final String packageName = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", packageName);
        if (!bundle.containsKey(zzd.KEY_ANDROID_PACKAGE_NAME)) {
            bundle.putString(zzd.KEY_ANDROID_PACKAGE_NAME, packageName);
        }
        zza(context, zzd.zzVe, (zza<Object>)new zza<Void>() {
            public Void zzao(final IBinder binder) throws RemoteException, IOException, GoogleAuthException {
                final Bundle bundle = (Bundle)zzm(zzas.zza.zza(binder).zza(s, bundle));
                final String string = bundle.getString("Error");
                if (!bundle.getBoolean("booleanResult")) {
                    throw new GoogleAuthException(string);
                }
                return null;
            }
        });
    }
    
    public static List<AccountChangeEvent> getAccountChangeEvents(final Context context, final int n, final String s) throws GoogleAuthException, IOException {
        zzx.zzh(s, "accountName must be provided");
        zzx.zzcE("Calling this from your main thread can lead to deadlock");
        zzad(context);
        return zza(context, zzd.zzVe, (zza<List<AccountChangeEvent>>)new zza<List<AccountChangeEvent>>() {
            public List<AccountChangeEvent> zzap(final IBinder binder) throws RemoteException, IOException, GoogleAuthException {
                return ((AccountChangeEventsResponse)zzm(zzas.zza.zza(binder).zza(new AccountChangeEventsRequest().setAccountName(s).setEventIndex(n)))).getEvents();
            }
        });
    }
    
    public static String getAccountId(final Context context, final String s) throws GoogleAuthException, IOException {
        zzx.zzh(s, "accountName must be provided");
        zzx.zzcE("Calling this from your main thread can lead to deadlock");
        zzad(context);
        return getToken(context, s, "^^_account_id_^^", new Bundle());
    }
    
    public static String getToken(final Context context, final Account account, final String s) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, account, s, new Bundle());
    }
    
    public static String getToken(final Context context, final Account account, final String s, final Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzc(context, account, s, bundle).getToken();
    }
    
    @Deprecated
    public static String getToken(final Context context, final String s, final String s2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(s, "com.google"), s2);
    }
    
    @Deprecated
    public static String getToken(final Context context, final String s, final String s2, final Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(s, "com.google"), s2, bundle);
    }
    
    @Deprecated
    @RequiresPermission("android.permission.MANAGE_ACCOUNTS")
    public static void invalidateToken(final Context context, final String s) {
        AccountManager.get(context).invalidateAuthToken("com.google", s);
    }
    
    private static <T> T zza(final Context context, final ComponentName componentName, final zza<T> zza) throws IOException, GoogleAuthException {
        final com.google.android.gms.common.zza zza2 = new com.google.android.gms.common.zza();
        if (!zzl.zzau(context).zza(componentName, (ServiceConnection)zza2, "GoogleAuthUtil")) {
            goto Label_0088;
        }
        try {
            return zza.zzan(zza2.zzoJ());
        }
        catch (InterruptedException ex) {}
        catch (RemoteException ex2) {}
    }
    
    private static void zzad(final Context context) throws GoogleAuthException {
        try {
            zze.zzad(context.getApplicationContext());
        }
        catch (GooglePlayServicesRepairableException ex) {
            throw new GooglePlayServicesAvailabilityException(ex.getConnectionStatusCode(), ex.getMessage(), ex.getIntent());
        }
        catch (GooglePlayServicesNotAvailableException ex2) {
            throw new GoogleAuthException(ex2.getMessage());
        }
    }
    
    public static TokenData zzc(final Context context, final Account account, final String s, final Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        zzx.zzcE("Calling this from your main thread can lead to deadlock");
        zzad(context);
        Bundle bundle2;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        else {
            bundle2 = new Bundle(bundle);
        }
        final String packageName = context.getApplicationInfo().packageName;
        bundle2.putString("clientPackageName", packageName);
        if (TextUtils.isEmpty((CharSequence)bundle2.getString(zzd.KEY_ANDROID_PACKAGE_NAME))) {
            bundle2.putString(zzd.KEY_ANDROID_PACKAGE_NAME, packageName);
        }
        bundle2.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
        return zza(context, zzd.zzVe, (zza<TokenData>)new zza<TokenData>() {
            public TokenData zzam(final IBinder binder) throws RemoteException, IOException, GoogleAuthException {
                final Bundle bundle = (Bundle)zzm(zzas.zza.zza(binder).zza(account, s, bundle2));
                final TokenData zzc = TokenData.zzc(bundle, "tokenDetails");
                if (zzc != null) {
                    return zzc;
                }
                final String string = bundle.getString("Error");
                final Intent intent = (Intent)bundle.getParcelable("userRecoveryIntent");
                final com.google.android.gms.auth.firstparty.shared.zzd zzbY = com.google.android.gms.auth.firstparty.shared.zzd.zzbY(string);
                if (com.google.android.gms.auth.firstparty.shared.zzd.zza(zzbY)) {
                    throw new UserRecoverableAuthException(string, intent);
                }
                if (com.google.android.gms.auth.firstparty.shared.zzd.zzc(zzbY)) {
                    throw new IOException(string);
                }
                throw new GoogleAuthException(string);
            }
        });
    }
    
    static void zzi(final Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("Callback cannot be null.");
        }
        final String uri = intent.toUri(1);
        try {
            Intent.parseUri(uri, 1);
        }
        catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }
    
    private static <T> T zzm(final T t) throws IOException {
        if (t == null) {
            Log.w("GoogleAuthUtil", "Binder call returned null.");
            throw new IOException("Service unavailable.");
        }
        return t;
    }
    
    private interface zza<T>
    {
        T zzan(final IBinder p0) throws RemoteException, IOException, GoogleAuthException;
    }
}
