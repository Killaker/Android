package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.stats.*;
import android.content.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import android.os.*;

public class zzi extends zzd
{
    private final zza zzQH;
    private zzac zzQI;
    private final zzt zzQJ;
    private zzaj zzQK;
    
    protected zzi(final zzf zzf) {
        super(zzf);
        this.zzQK = new zzaj(zzf.zzjl());
        this.zzQH = new zza();
        this.zzQJ = new zzt(zzf) {
            @Override
            public void run() {
                zzi.this.zzjJ();
            }
        };
    }
    
    private void onDisconnect() {
        this.zziH().zzjf();
    }
    
    private void onServiceDisconnected(final ComponentName componentName) {
        this.zzjk();
        if (this.zzQI != null) {
            this.zzQI = null;
            this.zza("Disconnected from device AnalyticsService", componentName);
            this.onDisconnect();
        }
    }
    
    private void zza(final zzac zzQI) {
        this.zzjk();
        this.zzQI = zzQI;
        this.zzjI();
        this.zziH().onServiceConnected();
    }
    
    private void zzjI() {
        this.zzQK.start();
        this.zzQJ.zzt(this.zzjn().zzkM());
    }
    
    private void zzjJ() {
        this.zzjk();
        if (!this.isConnected()) {
            return;
        }
        this.zzbd("Inactivity, disconnecting from device AnalyticsService");
        this.disconnect();
    }
    
    public boolean connect() {
        this.zzjk();
        this.zzjv();
        if (this.zzQI != null) {
            return true;
        }
        final zzac zzjK = this.zzQH.zzjK();
        if (zzjK != null) {
            this.zzQI = zzjK;
            this.zzjI();
            return true;
        }
        return false;
    }
    
    public void disconnect() {
        this.zzjk();
        this.zzjv();
        while (true) {
            try {
                zzb.zzrP().zza(this.getContext(), (ServiceConnection)this.zzQH);
                if (this.zzQI != null) {
                    this.zzQI = null;
                    this.onDisconnect();
                }
            }
            catch (IllegalArgumentException ex) {
                continue;
            }
            catch (IllegalStateException ex2) {
                continue;
            }
            break;
        }
    }
    
    public boolean isConnected() {
        this.zzjk();
        this.zzjv();
        return this.zzQI != null;
    }
    
    public boolean zzb(final zzab zzab) {
        zzx.zzz(zzab);
        this.zzjk();
        this.zzjv();
        final zzac zzQI = this.zzQI;
        if (zzQI == null) {
            return false;
        }
        Label_0069: {
            if (!zzab.zzlt()) {
                break Label_0069;
            }
            String s = this.zzjn().zzkF();
            while (true) {
                final List<Command> emptyList = Collections.emptyList();
                try {
                    zzQI.zza(zzab.zzn(), zzab.zzlr(), s, emptyList);
                    this.zzjI();
                    return true;
                    s = this.zzjn().zzkG();
                }
                catch (RemoteException ex) {
                    this.zzbd("Failed to send hits to AnalyticsService");
                    return false;
                }
            }
        }
    }
    
    @Override
    protected void zziJ() {
    }
    
    public boolean zzjH() {
        this.zzjk();
        this.zzjv();
        final zzac zzQI = this.zzQI;
        if (zzQI == null) {
            return false;
        }
        try {
            zzQI.zzjc();
            this.zzjI();
            return true;
        }
        catch (RemoteException ex) {
            this.zzbd("Failed to clear hits from AnalyticsService");
            return false;
        }
    }
    
    protected class zza implements ServiceConnection
    {
        private volatile zzac zzQM;
        private volatile boolean zzQN;
        
        public void onServiceConnected(final ComponentName p0, final IBinder p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: ldc             "AnalyticsServiceConnection.onServiceConnected"
            //     2: invokestatic    com/google/android/gms/common/internal/zzx.zzcD:(Ljava/lang/String;)V
            //     5: aload_0        
            //     6: monitorenter   
            //     7: aload_2        
            //     8: ifnonnull       27
            //    11: aload_0        
            //    12: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //    15: ldc             "Service connected with null binder"
            //    17: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbh:(Ljava/lang/String;)V
            //    20: aload_0        
            //    21: invokevirtual   java/lang/Object.notifyAll:()V
            //    24: aload_0        
            //    25: monitorexit    
            //    26: return         
            //    27: aconst_null    
            //    28: astore_3       
            //    29: aload_2        
            //    30: invokeinterface android/os/IBinder.getInterfaceDescriptor:()Ljava/lang/String;
            //    35: astore          8
            //    37: ldc             "com.google.android.gms.analytics.internal.IAnalyticsService"
            //    39: aload           8
            //    41: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //    44: istore          9
            //    46: aconst_null    
            //    47: astore_3       
            //    48: iload           9
            //    50: ifeq            105
            //    53: aload_2        
            //    54: invokestatic    com/google/android/gms/analytics/internal/zzac$zza.zzaf:(Landroid/os/IBinder;)Lcom/google/android/gms/analytics/internal/zzac;
            //    57: astore_3       
            //    58: aload_0        
            //    59: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //    62: ldc             "Bound to IAnalyticsService interface"
            //    64: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbd:(Ljava/lang/String;)V
            //    67: aload_3        
            //    68: ifnonnull       144
            //    71: invokestatic    com/google/android/gms/common/stats/zzb.zzrP:()Lcom/google/android/gms/common/stats/zzb;
            //    74: aload_0        
            //    75: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //    78: invokevirtual   com/google/android/gms/analytics/internal/zzi.getContext:()Landroid/content/Context;
            //    81: aload_0        
            //    82: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //    85: invokestatic    com/google/android/gms/analytics/internal/zzi.zza:(Lcom/google/android/gms/analytics/internal/zzi;)Lcom/google/android/gms/analytics/internal/zzi$zza;
            //    88: invokevirtual   com/google/android/gms/common/stats/zzb.zza:(Landroid/content/Context;Landroid/content/ServiceConnection;)V
            //    91: aload_0        
            //    92: invokevirtual   java/lang/Object.notifyAll:()V
            //    95: aload_0        
            //    96: monitorexit    
            //    97: return         
            //    98: astore          5
            //   100: aload_0        
            //   101: monitorexit    
            //   102: aload           5
            //   104: athrow         
            //   105: aload_0        
            //   106: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //   109: ldc             "Got binder with a wrong descriptor"
            //   111: aload           8
            //   113: invokevirtual   com/google/android/gms/analytics/internal/zzi.zze:(Ljava/lang/String;Ljava/lang/Object;)V
            //   116: aconst_null    
            //   117: astore_3       
            //   118: goto            67
            //   121: astore          6
            //   123: aload_0        
            //   124: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //   127: ldc             "Service connect failed to get IAnalyticsService"
            //   129: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbh:(Ljava/lang/String;)V
            //   132: goto            67
            //   135: astore          4
            //   137: aload_0        
            //   138: invokevirtual   java/lang/Object.notifyAll:()V
            //   141: aload           4
            //   143: athrow         
            //   144: aload_0        
            //   145: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQN:Z
            //   148: ifne            182
            //   151: aload_0        
            //   152: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //   155: ldc             "onServiceConnected received after the timeout limit"
            //   157: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbg:(Ljava/lang/String;)V
            //   160: aload_0        
            //   161: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //   164: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzjo:()Lcom/google/android/gms/measurement/zzg;
            //   167: new             Lcom/google/android/gms/analytics/internal/zzi$zza$1;
            //   170: dup            
            //   171: aload_0        
            //   172: aload_3        
            //   173: invokespecial   com/google/android/gms/analytics/internal/zzi$zza$1.<init>:(Lcom/google/android/gms/analytics/internal/zzi$zza;Lcom/google/android/gms/analytics/internal/zzac;)V
            //   176: invokevirtual   com/google/android/gms/measurement/zzg.zzf:(Ljava/lang/Runnable;)V
            //   179: goto            91
            //   182: aload_0        
            //   183: aload_3        
            //   184: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzQM:Lcom/google/android/gms/analytics/internal/zzac;
            //   187: goto            91
            //   190: astore          7
            //   192: goto            91
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  11     20     135    144    Any
            //  20     26     98     105    Any
            //  29     46     121    135    Landroid/os/RemoteException;
            //  29     46     135    144    Any
            //  53     67     121    135    Landroid/os/RemoteException;
            //  53     67     135    144    Any
            //  71     91     190    195    Ljava/lang/IllegalArgumentException;
            //  71     91     135    144    Any
            //  91     97     98     105    Any
            //  100    102    98     105    Any
            //  105    116    121    135    Landroid/os/RemoteException;
            //  105    116    135    144    Any
            //  123    132    135    144    Any
            //  137    144    98     105    Any
            //  144    179    135    144    Any
            //  182    187    135    144    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            zzx.zzcD("AnalyticsServiceConnection.onServiceDisconnected");
            zzi.this.zzjo().zzf(new Runnable() {
                @Override
                public void run() {
                    zzi.this.onServiceDisconnected(componentName);
                }
            });
        }
        
        public zzac zzjK() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //     4: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzjk:()V
            //     7: new             Landroid/content/Intent;
            //    10: dup            
            //    11: ldc             "com.google.android.gms.analytics.service.START"
            //    13: invokespecial   android/content/Intent.<init>:(Ljava/lang/String;)V
            //    16: astore_1       
            //    17: aload_1        
            //    18: new             Landroid/content/ComponentName;
            //    21: dup            
            //    22: ldc             "com.google.android.gms"
            //    24: ldc             "com.google.android.gms.analytics.service.AnalyticsService"
            //    26: invokespecial   android/content/ComponentName.<init>:(Ljava/lang/String;Ljava/lang/String;)V
            //    29: invokevirtual   android/content/Intent.setComponent:(Landroid/content/ComponentName;)Landroid/content/Intent;
            //    32: pop            
            //    33: aload_0        
            //    34: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //    37: invokevirtual   com/google/android/gms/analytics/internal/zzi.getContext:()Landroid/content/Context;
            //    40: astore_3       
            //    41: aload_1        
            //    42: ldc             "app_package_name"
            //    44: aload_3        
            //    45: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
            //    48: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
            //    51: pop            
            //    52: invokestatic    com/google/android/gms/common/stats/zzb.zzrP:()Lcom/google/android/gms/common/stats/zzb;
            //    55: astore          5
            //    57: aload_0        
            //    58: monitorenter   
            //    59: aload_0        
            //    60: aconst_null    
            //    61: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzQM:Lcom/google/android/gms/analytics/internal/zzac;
            //    64: aload_0        
            //    65: iconst_1       
            //    66: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzQN:Z
            //    69: aload           5
            //    71: aload_3        
            //    72: aload_1        
            //    73: aload_0        
            //    74: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //    77: invokestatic    com/google/android/gms/analytics/internal/zzi.zza:(Lcom/google/android/gms/analytics/internal/zzi;)Lcom/google/android/gms/analytics/internal/zzi$zza;
            //    80: sipush          129
            //    83: invokevirtual   com/google/android/gms/common/stats/zzb.zza:(Landroid/content/Context;Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
            //    86: istore          7
            //    88: aload_0        
            //    89: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //    92: ldc             "Bind to service requested"
            //    94: iload           7
            //    96: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //    99: invokevirtual   com/google/android/gms/analytics/internal/zzi.zza:(Ljava/lang/String;Ljava/lang/Object;)V
            //   102: iload           7
            //   104: ifne            116
            //   107: aload_0        
            //   108: iconst_0       
            //   109: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzQN:Z
            //   112: aload_0        
            //   113: monitorexit    
            //   114: aconst_null    
            //   115: areturn        
            //   116: aload_0        
            //   117: aload_0        
            //   118: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //   121: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzjn:()Lcom/google/android/gms/analytics/internal/zzr;
            //   124: invokevirtual   com/google/android/gms/analytics/internal/zzr.zzkN:()J
            //   127: invokevirtual   java/lang/Object.wait:(J)V
            //   130: aload_0        
            //   131: iconst_0       
            //   132: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzQN:Z
            //   135: aload_0        
            //   136: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQM:Lcom/google/android/gms/analytics/internal/zzac;
            //   139: astore          9
            //   141: aload_0        
            //   142: aconst_null    
            //   143: putfield        com/google/android/gms/analytics/internal/zzi$zza.zzQM:Lcom/google/android/gms/analytics/internal/zzac;
            //   146: aload           9
            //   148: ifnonnull       160
            //   151: aload_0        
            //   152: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //   155: ldc             "Successfully bound to service but never got onServiceConnected callback"
            //   157: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbh:(Ljava/lang/String;)V
            //   160: aload_0        
            //   161: monitorexit    
            //   162: aload           9
            //   164: areturn        
            //   165: astore          6
            //   167: aload_0        
            //   168: monitorexit    
            //   169: aload           6
            //   171: athrow         
            //   172: astore          8
            //   174: aload_0        
            //   175: getfield        com/google/android/gms/analytics/internal/zzi$zza.zzQL:Lcom/google/android/gms/analytics/internal/zzi;
            //   178: ldc             "Wait for service connect was interrupted"
            //   180: invokevirtual   com/google/android/gms/analytics/internal/zzi.zzbg:(Ljava/lang/String;)V
            //   183: goto            130
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  59     102    165    172    Any
            //  107    114    165    172    Any
            //  116    130    172    186    Ljava/lang/InterruptedException;
            //  116    130    165    172    Any
            //  130    146    165    172    Any
            //  151    160    165    172    Any
            //  160    162    165    172    Any
            //  167    169    165    172    Any
            //  174    183    165    172    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
}
