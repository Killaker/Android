package com.google.android.gms.gcm;

import android.app.*;
import java.util.*;
import android.content.*;
import android.util.*;
import android.os.*;

public abstract class GcmTaskService extends Service
{
    public static final String SERVICE_ACTION_EXECUTE_TASK = "com.google.android.gms.gcm.ACTION_TASK_READY";
    public static final String SERVICE_ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
    public static final String SERVICE_PERMISSION = "com.google.android.gms.permission.BIND_NETWORK_TASK_SERVICE";
    private final Set<String> zzaLI;
    private int zzaLJ;
    
    public GcmTaskService() {
        this.zzaLI = new HashSet<String>();
    }
    
    private void zzdY(final String s) {
        synchronized (this.zzaLI) {
            this.zzaLI.remove(s);
            if (this.zzaLI.size() == 0) {
                this.stopSelf(this.zzaLJ);
            }
        }
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onInitializeTasks() {
    }
    
    public abstract int onRunTask(final TaskParams p0);
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        intent.setExtrasClassLoader(PendingCallback.class.getClassLoader());
        Label_0171: {
            if (!"com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction())) {
                break Label_0171;
            }
            final String stringExtra = intent.getStringExtra("tag");
            final Parcelable parcelableExtra = intent.getParcelableExtra("callback");
            final Bundle bundle = (Bundle)intent.getParcelableExtra("extras");
            if (parcelableExtra != null && parcelableExtra instanceof PendingCallback) {
                synchronized (this.zzaLI) {
                    this.zzaLI.add(stringExtra);
                    this.stopSelf(this.zzaLJ);
                    this.zzaLJ = n2;
                    // monitorexit(this.zzaLI)
                    new zza(stringExtra, ((PendingCallback)parcelableExtra).getIBinder(), bundle).start();
                    return 2;
                }
                break Label_0171;
            }
            Log.e("GcmTaskService", this.getPackageName() + " " + stringExtra + ": Could not process request, invalid callback.");
            return 2;
        }
        if (!"com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(intent.getAction())) {
            return 2;
        }
        this.onInitializeTasks();
        synchronized (this.zzaLI) {
            this.zzaLJ = n2;
            if (this.zzaLI.size() == 0) {
                this.stopSelf(this.zzaLJ);
            }
            return 2;
        }
    }
    
    private class zza extends Thread
    {
        private final Bundle mExtras;
        private final String mTag;
        private final zzc zzaLK;
        
        zza(final String mTag, final IBinder binder, final Bundle mExtras) {
            this.mTag = mTag;
            this.zzaLK = zzc.zza.zzbZ(binder);
            this.mExtras = mExtras;
        }
        
        @Override
        public void run() {
            Process.setThreadPriority(10);
            final int onRunTask = GcmTaskService.this.onRunTask(new TaskParams(this.mTag, this.mExtras));
            try {
                this.zzaLK.zzhe(onRunTask);
            }
            catch (RemoteException ex) {
                Log.e("GcmTaskService", "Error reporting result of operation to scheduler for " + this.mTag);
            }
            finally {
                GcmTaskService.this.zzdY(this.mTag);
            }
        }
    }
}
