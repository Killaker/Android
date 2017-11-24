package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.*;
import java.util.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.stats.*;
import android.content.*;
import android.text.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.*;
import android.support.annotation.*;
import android.os.*;

public class zzac extends zzz
{
    private final zza zzaYN;
    private zzm zzaYO;
    private Boolean zzaYP;
    private final zzf zzaYQ;
    private final zzaf zzaYR;
    private final List<Runnable> zzaYS;
    private final zzf zzaYT;
    
    protected zzac(final zzw zzw) {
        super(zzw);
        this.zzaYS = new ArrayList<Runnable>();
        this.zzaYR = new zzaf(zzw.zzjl());
        this.zzaYN = new zza();
        this.zzaYQ = new zzf(zzw) {
            @Override
            public void run() {
                zzac.this.zzjJ();
            }
        };
        this.zzaYT = new zzf(zzw) {
            @Override
            public void run() {
                zzac.this.zzAo().zzCF().zzfg("Tasks have been queued for a long time");
            }
        };
    }
    
    @WorkerThread
    private void onServiceDisconnected(final ComponentName componentName) {
        this.zzjk();
        if (this.zzaYO != null) {
            this.zzaYO = null;
            this.zzAo().zzCK().zzj("Disconnected from device MeasurementService", componentName);
            this.zzDr();
        }
    }
    
    private boolean zzDp() {
        final List queryIntentServices = this.getContext().getPackageManager().queryIntentServices(new Intent(this.getContext(), (Class)AppMeasurementService.class), 65536);
        return queryIntentServices != null && queryIntentServices.size() > 0;
    }
    
    @WorkerThread
    private boolean zzDq() {
        this.zzjk();
        this.zzjv();
        if (this.zzCp().zzkr()) {
            return true;
        }
        this.zzAo().zzCK().zzfg("Checking service availability");
        switch (zzc.zzoK().isGooglePlayServicesAvailable(this.getContext())) {
            default: {
                return false;
            }
            case 0: {
                this.zzAo().zzCK().zzfg("Service available");
                return true;
            }
            case 1: {
                this.zzAo().zzCK().zzfg("Service missing");
                return false;
            }
            case 18: {
                this.zzAo().zzCK().zzfg("Service updating");
                return true;
            }
            case 2: {
                this.zzAo().zzCK().zzfg("Service version update required");
                return false;
            }
            case 3: {
                this.zzAo().zzCK().zzfg("Service disabled");
                return false;
            }
            case 9: {
                this.zzAo().zzCK().zzfg("Service invalid");
                return false;
            }
        }
    }
    
    @WorkerThread
    private void zzDr() {
        this.zzjk();
        this.zzjX();
    }
    
    @WorkerThread
    private void zzDs() {
        this.zzjk();
        this.zzAo().zzCK().zzj("Processing queued up service tasks", this.zzaYS.size());
        final Iterator<Runnable> iterator = this.zzaYS.iterator();
        while (iterator.hasNext()) {
            this.zzCn().zzg(iterator.next());
        }
        this.zzaYS.clear();
        this.zzaYT.cancel();
    }
    
    @WorkerThread
    private void zza(final zzm zzaYO) {
        this.zzjk();
        zzx.zzz(zzaYO);
        this.zzaYO = zzaYO;
        this.zzjI();
        this.zzDs();
    }
    
    @WorkerThread
    private void zzi(final Runnable runnable) throws IllegalStateException {
        this.zzjk();
        if (this.isConnected()) {
            runnable.run();
            return;
        }
        if (this.zzaYS.size() >= this.zzCp().zzBS()) {
            this.zzAo().zzCE().zzfg("Discarding data. Max runnable queue size reached");
            return;
        }
        this.zzaYS.add(runnable);
        if (!this.zzaTV.zzCZ()) {
            this.zzaYT.zzt(60000L);
        }
        this.zzjX();
    }
    
    @WorkerThread
    private void zzjI() {
        this.zzjk();
        this.zzaYR.start();
        if (!this.zzaTV.zzCZ()) {
            this.zzaYQ.zzt(this.zzCp().zzkM());
        }
    }
    
    @WorkerThread
    private void zzjJ() {
        this.zzjk();
        if (!this.isConnected()) {
            return;
        }
        this.zzAo().zzCK().zzfg("Inactivity, disconnecting from AppMeasurementService");
        this.disconnect();
    }
    
    @WorkerThread
    private void zzjX() {
        this.zzjk();
        this.zzjv();
        if (this.isConnected()) {
            return;
        }
        if (this.zzaYP == null) {
            this.zzaYP = this.zzCo().zzCP();
            if (this.zzaYP == null) {
                this.zzAo().zzCK().zzfg("State of service unknown");
                this.zzaYP = this.zzDq();
                this.zzCo().zzar(this.zzaYP);
            }
        }
        if (this.zzaYP) {
            this.zzAo().zzCK().zzfg("Using measurement service");
            this.zzaYN.zzDt();
            return;
        }
        if (this.zzDp() && !this.zzaTV.zzCZ()) {
            this.zzAo().zzCK().zzfg("Using local app measurement service");
            final Intent intent = new Intent("com.google.android.gms.measurement.START");
            intent.setComponent(new ComponentName(this.getContext(), (Class)AppMeasurementService.class));
            this.zzaYN.zzz(intent);
            return;
        }
        if (this.zzCp().zzks()) {
            this.zzAo().zzCK().zzfg("Using direct local measurement implementation");
            this.zza(new com.google.android.gms.measurement.internal.zzx(this.zzaTV, true));
            return;
        }
        this.zzAo().zzCE().zzfg("Not in main process. Unable to use local measurement implementation. Please register the AppMeasurementService service in the app manifest");
    }
    
    @WorkerThread
    public void disconnect() {
        this.zzjk();
        this.zzjv();
        while (true) {
            try {
                zzb.zzrP().zza(this.getContext(), (ServiceConnection)this.zzaYN);
                this.zzaYO = null;
            }
            catch (IllegalStateException ex) {
                continue;
            }
            catch (IllegalArgumentException ex2) {
                continue;
            }
            break;
        }
    }
    
    @WorkerThread
    public boolean isConnected() {
        this.zzjk();
        this.zzjv();
        return this.zzaYO != null;
    }
    
    @WorkerThread
    protected void zzDl() {
        this.zzjk();
        this.zzjv();
        this.zzi(new Runnable() {
            @Override
            public void run() {
                final zzm zzc = zzac.this.zzaYO;
                if (zzc == null) {
                    zzac.this.zzAo().zzCE().zzfg("Discarding data. Failed to send app launch");
                    return;
                }
                try {
                    zzc.zza(zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
                    zzac.this.zzjI();
                }
                catch (RemoteException ex) {
                    zzac.this.zzAo().zzCE().zzj("Failed to send app launch to AppMeasurementService", ex);
                }
            }
        });
    }
    
    @WorkerThread
    protected void zzDo() {
        this.zzjk();
        this.zzjv();
        this.zzi(new Runnable() {
            @Override
            public void run() {
                final zzm zzc = zzac.this.zzaYO;
                if (zzc == null) {
                    zzac.this.zzAo().zzCE().zzfg("Failed to send measurementEnabled to service");
                    return;
                }
                try {
                    zzc.zzb(zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
                    zzac.this.zzjI();
                }
                catch (RemoteException ex) {
                    zzac.this.zzAo().zzCE().zzj("Failed to send measurementEnabled to AppMeasurementService", ex);
                }
            }
        });
    }
    
    @WorkerThread
    protected void zza(final UserAttributeParcel userAttributeParcel) {
        this.zzjk();
        this.zzjv();
        this.zzi(new Runnable() {
            @Override
            public void run() {
                final zzm zzc = zzac.this.zzaYO;
                if (zzc == null) {
                    zzac.this.zzAo().zzCE().zzfg("Discarding data. Failed to set user attribute");
                    return;
                }
                try {
                    zzc.zza(userAttributeParcel, zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
                    zzac.this.zzjI();
                }
                catch (RemoteException ex) {
                    zzac.this.zzAo().zzCE().zzj("Failed to send attribute to AppMeasurementService", ex);
                }
            }
        });
    }
    
    @WorkerThread
    protected void zzb(final EventParcel eventParcel, final String s) {
        zzx.zzz(eventParcel);
        this.zzjk();
        this.zzjv();
        this.zzi(new Runnable() {
            @Override
            public void run() {
                final zzm zzc = zzac.this.zzaYO;
                if (zzc == null) {
                    zzac.this.zzAo().zzCE().zzfg("Discarding data. Failed to send event to service");
                    return;
                }
                while (true) {
                    while (true) {
                        try {
                            if (TextUtils.isEmpty((CharSequence)s)) {
                                zzc.zza(eventParcel, zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
                                zzac.this.zzjI();
                                return;
                            }
                        }
                        catch (RemoteException ex) {
                            zzac.this.zzAo().zzCE().zzj("Failed to send event to AppMeasurementService", ex);
                            return;
                        }
                        zzc.zza(eventParcel, s, zzac.this.zzAo().zzCL());
                        continue;
                    }
                }
            }
        });
    }
    
    @Override
    protected void zziJ() {
    }
    
    protected class zza implements ServiceConnection, ConnectionCallbacks, OnConnectionFailedListener
    {
        private volatile boolean zzaYV;
        private volatile zzo zzaYW;
        
        @MainThread
        public void onConnected(@Nullable final Bundle bundle) {
            zzx.zzcD("MeasurementServiceConnection.onConnected");
            // monitorenter(this)
            while (true) {
                try {
                    try {
                        final zzm zzm = this.zzaYW.zzqJ();
                        this.zzaYW = null;
                        zzac.this.zzCn().zzg(new Runnable() {
                            @Override
                            public void run() {
                                synchronized (zza.this) {
                                    zza.this.zzaYV = false;
                                    if (!zzac.this.isConnected()) {
                                        zzac.this.zzAo().zzCJ().zzfg("Connected to remote service");
                                        zzac.this.zza(zzm);
                                    }
                                }
                            }
                        });
                        return;
                        this.zzaYW = null;
                        this.zzaYV = false;
                    }
                    finally {
                    }
                    // monitorexit(this)
                }
                catch (DeadObjectException ex) {
                    continue;
                }
                catch (IllegalStateException ex2) {
                    continue;
                }
                break;
            }
        }
        
        @MainThread
        public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
            zzx.zzcD("MeasurementServiceConnection.onConnectionFailed");
            final zzp zzCT = zzac.this.zzaTV.zzCT();
            if (zzCT != null) {
                zzCT.zzCF().zzj("Service connection failed", connectionResult);
            }
            synchronized (this) {
                this.zzaYV = false;
                this.zzaYW = null;
            }
        }
        
        @MainThread
        public void onConnectionSuspended(final int n) {
            zzx.zzcD("MeasurementServiceConnection.onConnectionSuspended");
            zzac.this.zzAo().zzCJ().zzfg("Service connection suspended");
            zzac.this.zzCn().zzg(new Runnable() {
                @Override
                public void run() {
                    zzac.this.onServiceDisconnected(new ComponentName(zzac.this.getContext(), (Class)AppMeasurementService.class));
                }
            });
        }
        
        @MainThread
        public void onServiceConnected(final ComponentName p0, final IBinder p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: ldc             "MeasurementServiceConnection.onServiceConnected"
            //     2: invokestatic    com/google/android/gms/common/internal/zzx.zzcD:(Ljava/lang/String;)V
            //     5: aload_0        
            //     6: monitorenter   
            //     7: aload_2        
            //     8: ifnonnull       34
            //    11: aload_0        
            //    12: iconst_0       
            //    13: putfield        com/google/android/gms/measurement/internal/zzac$zza.zzaYV:Z
            //    16: aload_0        
            //    17: getfield        com/google/android/gms/measurement/internal/zzac$zza.zzaYU:Lcom/google/android/gms/measurement/internal/zzac;
            //    20: invokevirtual   com/google/android/gms/measurement/internal/zzac.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
            //    23: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
            //    26: ldc             "Service connected with null binder"
            //    28: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
            //    31: aload_0        
            //    32: monitorexit    
            //    33: return         
            //    34: aconst_null    
            //    35: astore_3       
            //    36: aload_2        
            //    37: invokeinterface android/os/IBinder.getInterfaceDescriptor:()Ljava/lang/String;
            //    42: astore          7
            //    44: ldc             "com.google.android.gms.measurement.internal.IMeasurementService"
            //    46: aload           7
            //    48: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //    51: istore          8
            //    53: aconst_null    
            //    54: astore_3       
            //    55: iload           8
            //    57: ifeq            119
            //    60: aload_2        
            //    61: invokestatic    com/google/android/gms/measurement/internal/zzm$zza.zzdn:(Landroid/os/IBinder;)Lcom/google/android/gms/measurement/internal/zzm;
            //    64: astore_3       
            //    65: aload_0        
            //    66: getfield        com/google/android/gms/measurement/internal/zzac$zza.zzaYU:Lcom/google/android/gms/measurement/internal/zzac;
            //    69: invokevirtual   com/google/android/gms/measurement/internal/zzac.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
            //    72: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
            //    75: ldc             "Bound to IMeasurementService interface"
            //    77: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
            //    80: aload_3        
            //    81: ifnonnull       161
            //    84: aload_0        
            //    85: iconst_0       
            //    86: putfield        com/google/android/gms/measurement/internal/zzac$zza.zzaYV:Z
            //    89: invokestatic    com/google/android/gms/common/stats/zzb.zzrP:()Lcom/google/android/gms/common/stats/zzb;
            //    92: aload_0        
            //    93: getfield        com/google/android/gms/measurement/internal/zzac$zza.zzaYU:Lcom/google/android/gms/measurement/internal/zzac;
            //    96: invokevirtual   com/google/android/gms/measurement/internal/zzac.getContext:()Landroid/content/Context;
            //    99: aload_0        
            //   100: getfield        com/google/android/gms/measurement/internal/zzac$zza.zzaYU:Lcom/google/android/gms/measurement/internal/zzac;
            //   103: invokestatic    com/google/android/gms/measurement/internal/zzac.zza:(Lcom/google/android/gms/measurement/internal/zzac;)Lcom/google/android/gms/measurement/internal/zzac$zza;
            //   106: invokevirtual   com/google/android/gms/common/stats/zzb.zza:(Landroid/content/Context;Landroid/content/ServiceConnection;)V
            //   109: aload_0        
            //   110: monitorexit    
            //   111: return         
            //   112: astore          5
            //   114: aload_0        
            //   115: monitorexit    
            //   116: aload           5
            //   118: athrow         
            //   119: aload_0        
            //   120: getfield        com/google/android/gms/measurement/internal/zzac$zza.zzaYU:Lcom/google/android/gms/measurement/internal/zzac;
            //   123: invokevirtual   com/google/android/gms/measurement/internal/zzac.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
            //   126: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
            //   129: ldc             "Got binder with a wrong descriptor"
            //   131: aload           7
            //   133: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
            //   136: aconst_null    
            //   137: astore_3       
            //   138: goto            80
            //   141: astore          4
            //   143: aload_0        
            //   144: getfield        com/google/android/gms/measurement/internal/zzac$zza.zzaYU:Lcom/google/android/gms/measurement/internal/zzac;
            //   147: invokevirtual   com/google/android/gms/measurement/internal/zzac.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
            //   150: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
            //   153: ldc             "Service connect failed to get IMeasurementService"
            //   155: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
            //   158: goto            80
            //   161: aload_0        
            //   162: getfield        com/google/android/gms/measurement/internal/zzac$zza.zzaYU:Lcom/google/android/gms/measurement/internal/zzac;
            //   165: invokevirtual   com/google/android/gms/measurement/internal/zzac.zzCn:()Lcom/google/android/gms/measurement/internal/zzv;
            //   168: new             Lcom/google/android/gms/measurement/internal/zzac$zza$1;
            //   171: dup            
            //   172: aload_0        
            //   173: aload_3        
            //   174: invokespecial   com/google/android/gms/measurement/internal/zzac$zza$1.<init>:(Lcom/google/android/gms/measurement/internal/zzac$zza;Lcom/google/android/gms/measurement/internal/zzm;)V
            //   177: invokevirtual   com/google/android/gms/measurement/internal/zzv.zzg:(Ljava/lang/Runnable;)V
            //   180: goto            109
            //   183: astore          6
            //   185: goto            109
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  11     33     112    119    Any
            //  36     53     141    161    Landroid/os/RemoteException;
            //  36     53     112    119    Any
            //  60     80     141    161    Landroid/os/RemoteException;
            //  60     80     112    119    Any
            //  84     89     112    119    Any
            //  89     109    183    188    Ljava/lang/IllegalArgumentException;
            //  89     109    112    119    Any
            //  109    111    112    119    Any
            //  114    116    112    119    Any
            //  119    136    141    161    Landroid/os/RemoteException;
            //  119    136    112    119    Any
            //  143    158    112    119    Any
            //  161    180    112    119    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        @MainThread
        public void onServiceDisconnected(final ComponentName componentName) {
            zzx.zzcD("MeasurementServiceConnection.onServiceDisconnected");
            zzac.this.zzAo().zzCJ().zzfg("Service disconnected");
            zzac.this.zzCn().zzg(new Runnable() {
                @Override
                public void run() {
                    zzac.this.onServiceDisconnected(componentName);
                }
            });
        }
        
        @WorkerThread
        public void zzDt() {
            zzac.this.zzjk();
            final Context context = zzac.this.getContext();
            synchronized (this) {
                if (this.zzaYV) {
                    zzac.this.zzAo().zzCK().zzfg("Connection attempt already in progress");
                    return;
                }
                if (this.zzaYW != null) {
                    zzac.this.zzAo().zzCK().zzfg("Already awaiting connection attempt");
                    return;
                }
            }
            this.zzaYW = new zzo(context, Looper.getMainLooper(), com.google.android.gms.common.internal.zzf.zzat(context), this, this);
            zzac.this.zzAo().zzCK().zzfg("Connecting to remote service");
            this.zzaYV = true;
            this.zzaYW.zzqG();
        }
        // monitorexit(this)
        
        @WorkerThread
        public void zzz(final Intent intent) {
            zzac.this.zzjk();
            final Context context = zzac.this.getContext();
            final zzb zzrP = zzb.zzrP();
            synchronized (this) {
                if (this.zzaYV) {
                    zzac.this.zzAo().zzCK().zzfg("Connection attempt already in progress");
                    return;
                }
                this.zzaYV = true;
                zzrP.zza(context, intent, (ServiceConnection)zzac.this.zzaYN, 129);
            }
        }
    }
}
