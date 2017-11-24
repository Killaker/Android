package com.google.android.gms.gcm;

import android.text.*;
import com.google.android.gms.common.internal.*;
import android.content.pm.*;
import java.util.*;
import android.util.*;
import android.app.*;
import android.os.*;
import android.content.*;

public class GcmNetworkManager
{
    public static final int RESULT_FAILURE = 2;
    public static final int RESULT_RESCHEDULE = 1;
    public static final int RESULT_SUCCESS;
    private static GcmNetworkManager zzaLB;
    private Context mContext;
    private final PendingIntent mPendingIntent;
    
    private GcmNetworkManager(final Context mContext) {
        this.mContext = mContext;
        this.mPendingIntent = PendingIntent.getBroadcast(this.mContext, 0, new Intent(), 0);
    }
    
    public static GcmNetworkManager getInstance(final Context context) {
        synchronized (GcmNetworkManager.class) {
            if (GcmNetworkManager.zzaLB == null) {
                GcmNetworkManager.zzaLB = new GcmNetworkManager(context.getApplicationContext());
            }
            return GcmNetworkManager.zzaLB;
        }
    }
    
    static void zzdT(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Must provide a valid tag.");
        }
        if (100 < s.length()) {
            throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
        }
    }
    
    private void zzdU(final String s) {
        boolean b = true;
        zzx.zzb(s, "GcmTaskService must not be null.");
        final Intent intent = new Intent("com.google.android.gms.gcm.ACTION_TASK_READY");
        intent.setPackage(this.mContext.getPackageName());
        final List queryIntentServices = this.mContext.getPackageManager().queryIntentServices(intent, 0);
        zzx.zzb(queryIntentServices != null && queryIntentServices.size() != 0 && b, (Object)"There is no GcmTaskService component registered within this package. Have you extended GcmTaskService correctly?");
        final Iterator<ResolveInfo> iterator = queryIntentServices.iterator();
        while (true) {
            while (iterator.hasNext()) {
                if (iterator.next().serviceInfo.name.equals(s)) {
                    zzx.zzb(b, (Object)("The GcmTaskService class you provided " + s + " does not seem to support receiving" + " com.google.android.gms.gcm.ACTION_TASK_READY."));
                    return;
                }
            }
            b = false;
            continue;
        }
    }
    
    private Intent zzyi() {
        final int zzaK = GoogleCloudMessaging.zzaK(this.mContext);
        if (zzaK < GoogleCloudMessaging.zzaLM) {
            Log.e("GcmNetworkManager", "Google Play Services is not available, dropping GcmNetworkManager request. code=" + zzaK);
            return null;
        }
        final Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage(GoogleCloudMessaging.zzaJ(this.mContext));
        intent.putExtra("app", (Parcelable)this.mPendingIntent);
        return intent;
    }
    
    public void cancelAllTasks(final Class<? extends GcmTaskService> clazz) {
        this.zzc(clazz);
    }
    
    public void cancelTask(final String s, final Class<? extends GcmTaskService> clazz) {
        this.zza(s, clazz);
    }
    
    public void schedule(final Task task) {
        this.zzdU(task.getServiceName());
        final Intent zzyi = this.zzyi();
        if (zzyi == null) {
            return;
        }
        final Bundle extras = zzyi.getExtras();
        extras.putString("scheduler_action", "SCHEDULE_TASK");
        task.toBundle(extras);
        zzyi.putExtras(extras);
        this.mContext.sendBroadcast(zzyi);
    }
    
    public void zza(final String s, final Class<? extends Service> clazz) {
        zzdT(s);
        this.zzdU(clazz.getName());
        final Intent zzyi = this.zzyi();
        if (zzyi == null) {
            return;
        }
        zzyi.putExtra("scheduler_action", "CANCEL_TASK");
        zzyi.putExtra("tag", s);
        zzyi.putExtra("component", (Parcelable)new ComponentName(this.mContext, (Class)clazz));
        this.mContext.sendBroadcast(zzyi);
    }
    
    public void zzc(final Class<? extends Service> clazz) {
        this.zzdU(clazz.getName());
        final Intent zzyi = this.zzyi();
        if (zzyi == null) {
            return;
        }
        zzyi.putExtra("scheduler_action", "CANCEL_ALL");
        zzyi.putExtra("component", (Parcelable)new ComponentName(this.mContext, (Class)clazz));
        this.mContext.sendBroadcast(zzyi);
    }
}
