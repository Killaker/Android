package com.google.android.gms.common;

import android.content.res.*;
import com.google.android.gms.internal.*;
import android.content.*;
import android.annotation.*;
import com.google.android.gms.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.os.*;
import android.util.*;

public final class GooglePlayServicesUtil extends zze
{
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    
    @Deprecated
    public static Dialog getErrorDialog(final int n, final Activity activity, final int n2) {
        return getErrorDialog(n, activity, n2, null);
    }
    
    @Deprecated
    public static Dialog getErrorDialog(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return zza(n, activity, null, n2, dialogInterface$OnCancelListener);
    }
    
    @Deprecated
    public static PendingIntent getErrorPendingIntent(final int n, final Context context, final int n2) {
        return zze.getErrorPendingIntent(n, context, n2);
    }
    
    @Deprecated
    public static String getErrorString(final int n) {
        return zze.getErrorString(n);
    }
    
    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(final Context context) {
        return zze.getOpenSourceSoftwareLicenseInfo(context);
    }
    
    public static Context getRemoteContext(final Context context) {
        return zze.getRemoteContext(context);
    }
    
    public static Resources getRemoteResource(final Context context) {
        return zze.getRemoteResource(context);
    }
    
    @Deprecated
    public static int isGooglePlayServicesAvailable(final Context context) {
        return zze.isGooglePlayServicesAvailable(context);
    }
    
    @Deprecated
    public static boolean isUserRecoverableError(final int n) {
        return zze.isUserRecoverableError(n);
    }
    
    @Deprecated
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2) {
        return showErrorDialogFragment(n, activity, n2, null);
    }
    
    @Deprecated
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return showErrorDialogFragment(n, activity, null, n2, dialogInterface$OnCancelListener);
    }
    
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final Fragment fragment, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        final Dialog zza = zza(n, activity, fragment, n2, dialogInterface$OnCancelListener);
        if (zza == null) {
            return false;
        }
        zza(activity, dialogInterface$OnCancelListener, "GooglePlayServicesErrorDialog", zza);
        return true;
    }
    
    @Deprecated
    public static void showErrorNotification(int n, final Context context) {
        if (zzmu.zzaw(context) && n == 2) {
            n = 42;
        }
        if (zzd(context, n) || zze(context, n)) {
            zzam(context);
            return;
        }
        zza(n, context);
    }
    
    @TargetApi(14)
    private static Dialog zza(int n, final Activity activity, final Fragment fragment, final int n2, final DialogInterface$OnCancelListener onCancelListener) {
        if (n == 0) {
            return null;
        }
        if (zzmu.zzaw((Context)activity) && n == 2) {
            n = 42;
        }
        if (zzd((Context)activity, n)) {
            n = 18;
        }
        final boolean zzsg = zzne.zzsg();
        AlertDialog$Builder alertDialog$Builder = null;
        if (zzsg) {
            final TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, typedValue, true);
            final boolean equals = "Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId));
            alertDialog$Builder = null;
            if (equals) {
                alertDialog$Builder = new AlertDialog$Builder((Context)activity, 5);
            }
        }
        if (alertDialog$Builder == null) {
            alertDialog$Builder = new AlertDialog$Builder((Context)activity);
        }
        alertDialog$Builder.setMessage((CharSequence)zzg.zzc((Context)activity, n, zze.zzao((Context)activity)));
        if (onCancelListener != null) {
            alertDialog$Builder.setOnCancelListener(onCancelListener);
        }
        final Intent zza = GoogleApiAvailability.getInstance().zza((Context)activity, n, "d");
        zzh zzh;
        if (fragment == null) {
            zzh = new zzh(activity, zza, n2);
        }
        else {
            zzh = new zzh(fragment, zza, n2);
        }
        final String zzh2 = zzg.zzh((Context)activity, n);
        if (zzh2 != null) {
            alertDialog$Builder.setPositiveButton((CharSequence)zzh2, (DialogInterface$OnClickListener)zzh);
        }
        final String zzg = com.google.android.gms.common.internal.zzg.zzg((Context)activity, n);
        if (zzg != null) {
            alertDialog$Builder.setTitle((CharSequence)zzg);
        }
        return (Dialog)alertDialog$Builder.create();
    }
    
    @TargetApi(21)
    private static void zza(final int n, final Context context) {
        zza(n, context, null);
    }
    
    @TargetApi(21)
    private static void zza(final int n, final Context context, final String s) {
        final Resources resources = context.getResources();
        final String zzao = zze.zzao(context);
        String s2 = zzg.zzg(context, n);
        if (s2 == null) {
            s2 = resources.getString(R.string.common_google_play_services_notification_ticker);
        }
        final String zzc = zzg.zzc(context, n, zzao);
        final PendingIntent zza = GoogleApiAvailability.getInstance().zza(context, n, 0, "n");
        Notification notification;
        if (zzmu.zzaw(context)) {
            zzx.zzab(zzne.zzsh());
            notification = new Notification$Builder(context).setSmallIcon(R.drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle((Notification$Style)new Notification$BigTextStyle().bigText((CharSequence)(s2 + " " + zzc))).addAction(R.drawable.common_full_open_on_phone, (CharSequence)resources.getString(R.string.common_open_on_phone), zza).build();
        }
        else {
            final String string = resources.getString(R.string.common_google_play_services_notification_ticker);
            if (zzne.zzsd()) {
                final Notification$Builder setAutoCancel = new Notification$Builder(context).setSmallIcon(17301642).setContentTitle((CharSequence)s2).setContentText((CharSequence)zzc).setContentIntent(zza).setTicker((CharSequence)string).setAutoCancel(true);
                if (zzne.zzsl()) {
                    setAutoCancel.setLocalOnly(true);
                }
                Notification notification2;
                if (zzne.zzsh()) {
                    setAutoCancel.setStyle((Notification$Style)new Notification$BigTextStyle().bigText((CharSequence)zzc));
                    notification2 = setAutoCancel.build();
                }
                else {
                    notification2 = setAutoCancel.getNotification();
                }
                if (Build$VERSION.SDK_INT == 19) {
                    notification2.extras.putBoolean("android.support.localOnly", true);
                }
                notification = notification2;
            }
            else {
                notification = new NotificationCompat.Builder(context).setSmallIcon(17301642).setTicker(string).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(zza).setContentTitle(s2).setContentText(zzc).build();
            }
        }
        int n2;
        if (zze.zzbw(n)) {
            GooglePlayServicesUtil.zzafQ.set(false);
            n2 = 10436;
        }
        else {
            n2 = 39789;
        }
        final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
        if (s != null) {
            notificationManager.notify(s, n2, notification);
            return;
        }
        notificationManager.notify(n2, notification);
    }
    
    @TargetApi(11)
    public static void zza(final Activity activity, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener, final String s, @NonNull final Dialog dialog) {
        while (true) {
            try {
                final int n = (activity instanceof FragmentActivity) ? 1 : 0;
                if (n != 0) {
                    SupportErrorDialogFragment.newInstance(dialog, dialogInterface$OnCancelListener).show(((FragmentActivity)activity).getSupportFragmentManager(), s);
                    return;
                }
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                final int n = 0;
                continue;
            }
            break;
        }
        if (zzne.zzsd()) {
            ErrorDialogFragment.newInstance(dialog, dialogInterface$OnCancelListener).show(activity.getFragmentManager(), s);
            return;
        }
        throw new RuntimeException("This Activity does not support Fragments.");
    }
    
    private static void zzam(final Context context) {
        final zza zza = new zza(context);
        zza.sendMessageDelayed(zza.obtainMessage(1), 120000L);
    }
    
    @Deprecated
    public static Intent zzbv(final int n) {
        return zze.zzbv(n);
    }
    
    @Deprecated
    public static boolean zzd(final Context context, final int n) {
        return zze.zzd(context, n);
    }
    
    @Deprecated
    public static boolean zze(final Context context, final int n) {
        return zze.zze(context, n);
    }
    
    private static class zza extends Handler
    {
        private final Context zzsa;
        
        zza(final Context context) {
            Looper looper;
            if (Looper.myLooper() == null) {
                looper = Looper.getMainLooper();
            }
            else {
                looper = Looper.myLooper();
            }
            super(looper);
            this.zzsa = context.getApplicationContext();
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.w("GooglePlayServicesUtil", "Don't know how to handle this message: " + message.what);
                    break;
                }
                case 1: {
                    final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzsa);
                    if (GooglePlayServicesUtil.isUserRecoverableError(googlePlayServicesAvailable)) {
                        zza(googlePlayServicesAvailable, this.zzsa);
                        return;
                    }
                    break;
                }
            }
        }
    }
}
