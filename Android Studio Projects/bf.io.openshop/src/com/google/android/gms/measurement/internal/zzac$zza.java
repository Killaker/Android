package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.api.*;
import com.google.android.gms.common.*;
import com.google.android.gms.measurement.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import android.support.annotation.*;
import android.content.*;
import com.google.android.gms.common.stats.*;

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
                                    zzac.zza(zzac.this, zzm);
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
                zzac.zza(zzac.this, new ComponentName(zzac.this.getContext(), (Class)AppMeasurementService.class));
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
                zzac.zza(zzac.this, componentName);
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
        this.zzaYW = new zzo(context, Looper.getMainLooper(), zzf.zzat(context), this, this);
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
            zzrP.zza(context, intent, (ServiceConnection)zzac.zza(zzac.this), 129);
        }
    }
}
