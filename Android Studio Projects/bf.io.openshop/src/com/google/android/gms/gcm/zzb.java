package com.google.android.gms.gcm;

import android.support.v4.app.*;
import android.text.*;
import android.graphics.*;
import android.net.*;
import android.content.*;
import android.util.*;
import android.os.*;
import android.app.*;
import android.content.res.*;
import android.media.*;
import org.json.*;
import java.util.*;

class zzb
{
    static zzb zzaLC;
    private final Context mContext;
    private final Class<? extends GcmListenerService> zzaLD;
    
    private zzb(final Context context, final Class<? extends GcmListenerService> zzaLD) {
        this.mContext = context.getApplicationContext();
        this.zzaLD = zzaLD;
    }
    
    private Notification zzB(final Bundle bundle) {
        final String zzf = this.zzf(bundle, "gcm.n.title");
        final String zzf2 = this.zzf(bundle, "gcm.n.body");
        final int zzdW = this.zzdW(zze(bundle, "gcm.n.icon"));
        final String zze = zze(bundle, "gcm.n.color");
        final Uri zzdX = this.zzdX(zze(bundle, "gcm.n.sound2"));
        PendingIntent contentIntent = this.zzC(bundle);
        final boolean zzx = GcmListenerService.zzx(bundle);
        PendingIntent zzD = null;
        if (zzx) {
            contentIntent = this.zza(bundle, contentIntent);
            zzD = this.zzD(bundle);
        }
        final NotificationCompat.Builder setSmallIcon = new NotificationCompat.Builder(this.mContext).setAutoCancel(true).setSmallIcon(zzdW);
        if (!TextUtils.isEmpty((CharSequence)zzf)) {
            setSmallIcon.setContentTitle(zzf);
        }
        else {
            setSmallIcon.setContentTitle(this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager()));
        }
        if (!TextUtils.isEmpty((CharSequence)zzf2)) {
            setSmallIcon.setContentText(zzf2);
        }
        if (!TextUtils.isEmpty((CharSequence)zze)) {
            setSmallIcon.setColor(Color.parseColor(zze));
        }
        if (zzdX != null) {
            setSmallIcon.setSound(zzdX);
        }
        if (contentIntent != null) {
            setSmallIcon.setContentIntent(contentIntent);
        }
        if (zzD != null) {
            setSmallIcon.setDeleteIntent(zzD);
        }
        return setSmallIcon.build();
    }
    
    private PendingIntent zzC(final Bundle bundle) {
        final String zze = zze(bundle, "gcm.n.click_action");
        Intent intent2;
        if (!TextUtils.isEmpty((CharSequence)zze)) {
            final Intent intent = new Intent(zze);
            intent.setPackage(this.mContext.getPackageName());
            intent.setFlags(268435456);
            intent2 = intent;
        }
        else {
            final Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
            if (launchIntentForPackage == null) {
                Log.w("GcmNotification", "No activity found to launch app");
                return null;
            }
            intent2 = launchIntentForPackage;
        }
        final Bundle bundle2 = new Bundle(bundle);
        GcmListenerService.zzw(bundle2);
        intent2.putExtras(bundle2);
        for (final String s : bundle2.keySet()) {
            if (s.startsWith("gcm.n.") || s.startsWith("gcm.notification.")) {
                intent2.removeExtra(s);
            }
        }
        return PendingIntent.getActivity(this.mContext, this.zzyj(), intent2, 1073741824);
    }
    
    private PendingIntent zzD(final Bundle bundle) {
        final Intent intent = new Intent("com.google.android.gms.gcm.NOTIFICATION_DISMISS");
        this.zza(intent, bundle);
        return PendingIntent.getService(this.mContext, this.zzyj(), intent, 1073741824);
    }
    
    private PendingIntent zza(final Bundle bundle, final PendingIntent pendingIntent) {
        final Intent intent = new Intent("com.google.android.gms.gcm.NOTIFICATION_OPEN");
        this.zza(intent, bundle);
        intent.putExtra("com.google.android.gms.gcm.PENDING_INTENT", (Parcelable)pendingIntent);
        return PendingIntent.getService(this.mContext, this.zzyj(), intent, 1073741824);
    }
    
    private void zza(final Intent intent, final Bundle bundle) {
        intent.setClass(this.mContext, (Class)this.zzaLD);
        for (final String s : bundle.keySet()) {
            if (s.startsWith("google.c.a.") || s.equals("from")) {
                intent.putExtra(s, bundle.getString(s));
            }
        }
    }
    
    private void zza(String string, final Notification notification) {
        if (Log.isLoggable("GcmNotification", 3)) {
            Log.d("GcmNotification", "Showing notification");
        }
        final NotificationManager notificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (TextUtils.isEmpty((CharSequence)string)) {
            string = "GCM-Notification:" + SystemClock.uptimeMillis();
        }
        notificationManager.notify(string, 0, notification);
    }
    
    static boolean zzaI(final Context context) {
        if (!((KeyguardManager)context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            final int myPid = Process.myPid();
            final List runningAppProcesses = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                    if (activityManager$RunningAppProcessInfo.pid == myPid) {
                        return activityManager$RunningAppProcessInfo.importance == 100;
                    }
                }
            }
        }
        return false;
    }
    
    static zzb zzc(final Context context, final Class<? extends GcmListenerService> clazz) {
        synchronized (zzb.class) {
            if (zzb.zzaLC == null) {
                zzb.zzaLC = new zzb(context, clazz);
            }
            return zzb.zzaLC;
        }
    }
    
    private String zzdV(final String s) {
        return s.substring("gcm.n.".length());
    }
    
    private int zzdW(final String s) {
        Label_0089: {
            if (TextUtils.isEmpty((CharSequence)s)) {
                break Label_0089;
            }
            final Resources resources = this.mContext.getResources();
            int n = resources.getIdentifier(s, "drawable", this.mContext.getPackageName());
            if (n == 0) {
                n = resources.getIdentifier(s, "mipmap", this.mContext.getPackageName());
                if (n == 0) {
                    Log.w("GcmNotification", "Icon resource " + s + " not found. Notification will use app icon.");
                    break Label_0089;
                }
            }
            return n;
        }
        int n = this.mContext.getApplicationInfo().icon;
        if (n == 0) {
            return 17301651;
        }
        return n;
    }
    
    private Uri zzdX(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        if (!"default".equals(s) && this.mContext.getResources().getIdentifier(s, "raw", this.mContext.getPackageName()) != 0) {
            return Uri.parse("android.resource://" + this.mContext.getPackageName() + "/raw/" + s);
        }
        return RingtoneManager.getDefaultUri(2);
    }
    
    static String zze(final Bundle bundle, final String s) {
        String s2 = bundle.getString(s);
        if (s2 == null) {
            s2 = bundle.getString(s.replace("gcm.n.", "gcm.notification."));
        }
        return s2;
    }
    
    private String zzf(final Bundle bundle, final String s) {
        final String zze = zze(bundle, s);
        if (!TextUtils.isEmpty((CharSequence)zze)) {
            return zze;
        }
        final String zze2 = zze(bundle, s + "_loc_key");
        if (TextUtils.isEmpty((CharSequence)zze2)) {
            return null;
        }
        final Resources resources = this.mContext.getResources();
        final int identifier = resources.getIdentifier(zze2, "string", this.mContext.getPackageName());
        if (identifier == 0) {
            Log.w("GcmNotification", this.zzdV(s + "_loc_key") + " resource not found: " + zze2 + " Default value will be used.");
            return null;
        }
        final String zze3 = zze(bundle, s + "_loc_args");
        if (TextUtils.isEmpty((CharSequence)zze3)) {
            return resources.getString(identifier);
        }
        try {
            final JSONArray jsonArray = new JSONArray(zze3);
            final String[] array = new String[jsonArray.length()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = (String)jsonArray.opt(i);
            }
            return resources.getString(identifier, (Object[])array);
        }
        catch (JSONException ex2) {
            Log.w("GcmNotification", "Malformed " + this.zzdV(s + "_loc_args") + ": " + zze3 + "  Default value will be used.");
        }
        catch (MissingFormatArgumentException ex) {
            Log.w("GcmNotification", "Missing format argument for " + zze2 + ": " + zze3 + " Default value will be used.", (Throwable)ex);
            goto Label_0320;
        }
    }
    
    static boolean zzy(final Bundle bundle) {
        return "1".equals(zze(bundle, "gcm.n.e")) || zze(bundle, "gcm.n.icon") != null;
    }
    
    private int zzyj() {
        return (int)SystemClock.uptimeMillis();
    }
    
    static void zzz(final Bundle bundle) {
        final Bundle bundle2 = new Bundle();
        final Iterator<String> iterator = (Iterator<String>)bundle.keySet().iterator();
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (s.startsWith("gcm.n.")) {
                bundle2.putString(s.substring("gcm.n.".length()), bundle.getString(s));
                iterator.remove();
            }
            else {
                if (!s.startsWith("gcm.notification.")) {
                    continue;
                }
                bundle2.putString(s.substring("gcm.notification.".length()), bundle.getString(s));
                iterator.remove();
            }
        }
        if (!bundle2.isEmpty()) {
            bundle.putBundle("notification", bundle2);
        }
    }
    
    boolean zzA(final Bundle bundle) {
        try {
            this.zza(zze(bundle, "gcm.n.tag"), this.zzB(bundle));
            return true;
        }
        catch (zza zza) {
            Log.e("GcmNotification", "Failed to show notification: " + zza.getMessage());
            return false;
        }
    }
    
    private class zza extends IllegalArgumentException
    {
    }
}
