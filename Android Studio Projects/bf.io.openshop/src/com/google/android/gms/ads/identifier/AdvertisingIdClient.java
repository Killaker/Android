package com.google.android.gms.ads.identifier;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import java.io.*;
import com.google.android.gms.common.*;
import android.content.pm.*;
import com.google.android.gms.common.stats.*;
import android.content.*;
import android.util.*;
import android.os.*;
import java.lang.ref.*;
import java.util.concurrent.*;

public class AdvertisingIdClient
{
    private final Context mContext;
    com.google.android.gms.common.zza zzoS;
    zzat zzoT;
    boolean zzoU;
    Object zzoV;
    zza zzoW;
    final long zzoX;
    
    public AdvertisingIdClient(final Context context) {
        this(context, 30000L);
    }
    
    public AdvertisingIdClient(final Context mContext, final long zzoX) {
        this.zzoV = new Object();
        zzx.zzz(mContext);
        this.mContext = mContext;
        this.zzoU = false;
        this.zzoX = zzoX;
    }
    
    public static Info getAdvertisingIdInfo(final Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        final AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1L);
        try {
            advertisingIdClient.zzb(false);
            return advertisingIdClient.getInfo();
        }
        finally {
            advertisingIdClient.finish();
        }
    }
    
    public static void setShouldSkipGmsCoreVersionCheck(final boolean b) {
    }
    
    static zzat zza(final Context context, final com.google.android.gms.common.zza zza) throws IOException {
        try {
            return zzat.zza.zzb(zza.zzoJ());
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted exception");
        }
        catch (Throwable t) {
            throw new IOException(t);
        }
    }
    
    private void zzaJ() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoV:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoW:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    11: ifnull          28
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoW:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    18: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.cancel:()V
        //    21: aload_0        
        //    22: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoW:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    25: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.join:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoX:J
        //    32: lconst_0       
        //    33: lcmp           
        //    34: ifle            53
        //    37: aload_0        
        //    38: new             Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    41: dup            
        //    42: aload_0        
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoX:J
        //    47: invokespecial   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.<init>:(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;J)V
        //    50: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoW:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    53: aload_1        
        //    54: monitorexit    
        //    55: return         
        //    56: astore_2       
        //    57: aload_1        
        //    58: monitorexit    
        //    59: aload_2        
        //    60: athrow         
        //    61: astore_3       
        //    62: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  7      21     56     61     Any
        //  21     28     61     65     Ljava/lang/InterruptedException;
        //  21     28     56     61     Any
        //  28     53     56     61     Any
        //  53     55     56     61     Any
        //  57     59     56     61     Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static com.google.android.gms.common.zza zzp(final Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            switch (zzc.zzoK().isGooglePlayServicesAvailable(context)) {
                default: {
                    throw new IOException("Google Play services not available");
                }
                case 0:
                case 2: {
                    break;
                }
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
        final com.google.android.gms.common.zza zza = new com.google.android.gms.common.zza();
        final Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        try {
            if (zzb.zzrP().zza(context, intent, (ServiceConnection)zza, 1)) {
                return zza;
            }
        }
        catch (Throwable t) {
            throw new IOException(t);
        }
        throw new IOException("Connection failure");
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.finish();
        super.finalize();
    }
    
    public void finish() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "Calling this from your main thread can lead to deadlock"
        //     2: invokestatic    com/google/android/gms/common/internal/zzx.zzcE:(Ljava/lang/String;)V
        //     5: aload_0        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    11: ifnull          21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoS:Lcom/google/android/gms/common/zza;
        //    18: ifnonnull       24
        //    21: aload_0        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoU:Z
        //    28: ifeq            45
        //    31: invokestatic    com/google/android/gms/common/stats/zzb.zzrP:()Lcom/google/android/gms/common/stats/zzb;
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoS:Lcom/google/android/gms/common/zza;
        //    42: invokevirtual   com/google/android/gms/common/stats/zzb.zza:(Landroid/content/Context;Landroid/content/ServiceConnection;)V
        //    45: aload_0        
        //    46: iconst_0       
        //    47: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoU:Z
        //    50: aload_0        
        //    51: aconst_null    
        //    52: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoT:Lcom/google/android/gms/internal/zzat;
        //    55: aload_0        
        //    56: aconst_null    
        //    57: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzoS:Lcom/google/android/gms/common/zza;
        //    60: aload_0        
        //    61: monitorexit    
        //    62: return         
        //    63: astore_1       
        //    64: aload_0        
        //    65: monitorexit    
        //    66: aload_1        
        //    67: athrow         
        //    68: astore_2       
        //    69: ldc             "AdvertisingIdClient"
        //    71: ldc             "AdvertisingIdClient unbindService failed."
        //    73: aload_2        
        //    74: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    77: pop            
        //    78: goto            45
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      21     63     68     Any
        //  21     23     63     68     Any
        //  24     45     68     81     Ljava/lang/IllegalArgumentException;
        //  24     45     63     68     Any
        //  45     62     63     68     Any
        //  64     66     63     68     Any
        //  69     78     63     68     Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Info getInfo() throws IOException {
        zzx.zzcE("Calling this from your main thread can lead to deadlock");
        // monitorexit(o)
        Label_0102: {
            synchronized (this) {
                if (this.zzoU) {
                    break Label_0102;
                }
                synchronized (this.zzoV) {
                    if (this.zzoW == null || !this.zzoW.zzaK()) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
            }
            try {
                this.zzb(false);
                if (!this.zzoU) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            catch (Exception ex) {
                throw new IOException("AdvertisingIdClient cannot reconnect.", ex);
            }
        }
        zzx.zzz(this.zzoS);
        zzx.zzz(this.zzoT);
        try {
            final Info info = new Info(this.zzoT.getId(), this.zzoT.zzc(true));
            // monitorexit(this)
            this.zzaJ();
            return info;
        }
        catch (RemoteException ex2) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", (Throwable)ex2);
            throw new IOException("Remote exception");
        }
    }
    
    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        this.zzb(true);
    }
    
    protected void zzb(final boolean b) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzx.zzcE("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzoU) {
                this.finish();
            }
            this.zzoS = zzp(this.mContext);
            this.zzoT = zza(this.mContext, this.zzoS);
            this.zzoU = true;
            if (b) {
                this.zzaJ();
            }
        }
    }
    
    public static final class Info
    {
        private final String zzpc;
        private final boolean zzpd;
        
        public Info(final String zzpc, final boolean zzpd) {
            this.zzpc = zzpc;
            this.zzpd = zzpd;
        }
        
        public String getId() {
            return this.zzpc;
        }
        
        public boolean isLimitAdTrackingEnabled() {
            return this.zzpd;
        }
        
        @Override
        public String toString() {
            return "{" + this.zzpc + "}" + this.zzpd;
        }
    }
    
    static class zza extends Thread
    {
        private WeakReference<AdvertisingIdClient> zzoY;
        private long zzoZ;
        CountDownLatch zzpa;
        boolean zzpb;
        
        public zza(final AdvertisingIdClient advertisingIdClient, final long zzoZ) {
            this.zzoY = new WeakReference<AdvertisingIdClient>(advertisingIdClient);
            this.zzoZ = zzoZ;
            this.zzpa = new CountDownLatch(1);
            this.zzpb = false;
            this.start();
        }
        
        private void disconnect() {
            final AdvertisingIdClient advertisingIdClient = this.zzoY.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.zzpb = true;
            }
        }
        
        public void cancel() {
            this.zzpa.countDown();
        }
        
        @Override
        public void run() {
            try {
                if (!this.zzpa.await(this.zzoZ, TimeUnit.MILLISECONDS)) {
                    this.disconnect();
                }
            }
            catch (InterruptedException ex) {
                this.disconnect();
            }
        }
        
        public boolean zzaK() {
            return this.zzpb;
        }
    }
}
