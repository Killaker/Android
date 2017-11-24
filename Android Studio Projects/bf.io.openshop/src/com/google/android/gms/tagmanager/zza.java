package com.google.android.gms.tagmanager;

import android.content.*;
import com.google.android.gms.ads.identifier.*;
import com.google.android.gms.internal.*;
import java.io.*;
import com.google.android.gms.common.*;
import android.os.*;

public class zza
{
    private static zza zzbhA;
    private static Object zzbhz;
    private volatile boolean mClosed;
    private final Context mContext;
    private final Thread zzLM;
    private volatile AdvertisingIdClient.Info zzPW;
    private volatile long zzbht;
    private volatile long zzbhu;
    private volatile long zzbhv;
    private volatile long zzbhw;
    private final Object zzbhx;
    private zza zzbhy;
    private final zzmq zzqW;
    
    static {
        zza.zzbhz = new Object();
    }
    
    private zza(final Context context) {
        this(context, null, zzmt.zzsc());
    }
    
    public zza(final Context mContext, final zza zzbhy, final zzmq zzqW) {
        this.zzbht = 900000L;
        this.zzbhu = 30000L;
        this.mClosed = false;
        this.zzbhx = new Object();
        this.zzbhy = (zza)new zza() {
            @Override
            public AdvertisingIdClient.Info zzFV() {
                try {
                    return AdvertisingIdClient.getAdvertisingIdInfo(zza.this.mContext);
                }
                catch (IllegalStateException ex) {
                    zzbg.zzd("IllegalStateException getting Advertising Id Info", ex);
                    return null;
                }
                catch (GooglePlayServicesRepairableException ex2) {
                    zzbg.zzd("GooglePlayServicesRepairableException getting Advertising Id Info", ex2);
                    return null;
                }
                catch (IOException ex3) {
                    zzbg.zzd("IOException getting Ad Id Info", ex3);
                    return null;
                }
                catch (GooglePlayServicesNotAvailableException ex4) {
                    zzbg.zzd("GooglePlayServicesNotAvailableException getting Advertising Id Info", ex4);
                    return null;
                }
                catch (Exception ex5) {
                    zzbg.zzd("Unknown exception. Could not get the Advertising Id Info.", ex5);
                    return null;
                }
            }
        };
        this.zzqW = zzqW;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        if (zzbhy != null) {
            this.zzbhy = zzbhy;
        }
        this.zzbhv = this.zzqW.currentTimeMillis();
        this.zzLM = new Thread(new Runnable() {
            @Override
            public void run() {
                zza.this.zzFU();
            }
        });
    }
    
    private void zzFR() {
        // monitorenter(this)
        try {
            try {
                this.zzFS();
                this.wait(500L);
            }
            finally {
            }
            // monitorexit(this)
        }
        catch (InterruptedException ex) {}
    }
    
    private void zzFS() {
        if (this.zzqW.currentTimeMillis() - this.zzbhv <= this.zzbhu) {
            return;
        }
        synchronized (this.zzbhx) {
            this.zzbhx.notify();
            // monitorexit(this.zzbhx)
            this.zzbhv = this.zzqW.currentTimeMillis();
        }
    }
    
    private void zzFT() {
        if (this.zzqW.currentTimeMillis() - this.zzbhw > 3600000L) {
            this.zzPW = null;
        }
    }
    
    private void zzFU() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            final AdvertisingIdClient.Info zzFV = this.zzbhy.zzFV();
            if (zzFV != null) {
                this.zzPW = zzFV;
                this.zzbhw = this.zzqW.currentTimeMillis();
                zzbg.zzaJ("Obtained fresh AdvertisingId info from GmsCore.");
            }
            synchronized (this) {
                this.notifyAll();
                // monitorexit(this)
                try {
                    synchronized (this.zzbhx) {
                        this.zzbhx.wait(this.zzbht);
                    }
                }
                catch (InterruptedException ex) {
                    zzbg.zzaJ("sleep interrupted in AdvertiserDataPoller thread; continuing");
                }
            }
            break;
        }
    }
    
    public static zza zzaW(final Context context) {
        Label_0037: {
            if (zza.zzbhA != null) {
                break Label_0037;
            }
            synchronized (zza.zzbhz) {
                if (zza.zzbhA == null) {
                    (zza.zzbhA = new zza(context)).start();
                }
                return zza.zzbhA;
            }
        }
    }
    
    public boolean isLimitAdTrackingEnabled() {
        if (this.zzPW == null) {
            this.zzFR();
        }
        else {
            this.zzFS();
        }
        this.zzFT();
        return this.zzPW == null || this.zzPW.isLimitAdTrackingEnabled();
    }
    
    public void start() {
        this.zzLM.start();
    }
    
    public String zzFQ() {
        if (this.zzPW == null) {
            this.zzFR();
        }
        else {
            this.zzFS();
        }
        this.zzFT();
        if (this.zzPW == null) {
            return null;
        }
        return this.zzPW.getId();
    }
    
    public interface zza
    {
        AdvertisingIdClient.Info zzFV();
    }
}
