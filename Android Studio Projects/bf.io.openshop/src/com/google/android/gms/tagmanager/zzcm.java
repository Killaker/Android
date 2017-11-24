package com.google.android.gms.tagmanager;

import android.content.*;
import com.google.android.gms.internal.*;
import java.util.concurrent.*;

class zzcm implements zze
{
    private boolean mClosed;
    private final Context mContext;
    private final String zzbhM;
    private String zzbij;
    private zzbf<zzaf.zzj> zzbkg;
    private zzs zzbkh;
    private final ScheduledExecutorService zzbkj;
    private final zza zzbkk;
    private ScheduledFuture<?> zzbkl;
    
    public zzcm(final Context context, final String s, final zzs zzs) {
        this(context, s, zzs, null, null);
    }
    
    zzcm(final Context mContext, final String zzbhM, final zzs zzbkh, zzb zzb, final zza zzbkk) {
        this.zzbkh = zzbkh;
        this.mContext = mContext;
        this.zzbhM = zzbhM;
        if (zzb == null) {
            zzb = (zzb)new zzb() {
                @Override
                public ScheduledExecutorService zzHb() {
                    return Executors.newSingleThreadScheduledExecutor();
                }
            };
        }
        this.zzbkj = zzb.zzHb();
        if (zzbkk == null) {
            this.zzbkk = (zza)new zza() {
                @Override
                public zzcl zza(final zzs zzs) {
                    return new zzcl(zzcm.this.mContext, zzcm.this.zzbhM, zzs);
                }
            };
            return;
        }
        this.zzbkk = zzbkk;
    }
    
    private void zzHa() {
        synchronized (this) {
            if (this.mClosed) {
                throw new IllegalStateException("called method after closed");
            }
        }
    }
    // monitorexit(this)
    
    private zzcl zzgm(final String s) {
        final zzcl zza = this.zzbkk.zza(this.zzbkh);
        zza.zza(this.zzbkg);
        zza.zzfW(this.zzbij);
        zza.zzgl(s);
        return zza;
    }
    
    @Override
    public void release() {
        synchronized (this) {
            this.zzHa();
            if (this.zzbkl != null) {
                this.zzbkl.cancel(false);
            }
            this.zzbkj.shutdown();
            this.mClosed = true;
        }
    }
    
    @Override
    public void zza(final zzbf<zzaf.zzj> zzbkg) {
        synchronized (this) {
            this.zzHa();
            this.zzbkg = zzbkg;
        }
    }
    
    @Override
    public void zzf(final long n, final String s) {
        synchronized (this) {
            zzbg.v("loadAfterDelay: containerId=" + this.zzbhM + " delay=" + n);
            this.zzHa();
            if (this.zzbkg == null) {
                throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
            }
        }
        if (this.zzbkl != null) {
            this.zzbkl.cancel(false);
        }
        this.zzbkl = this.zzbkj.schedule(this.zzgm(s), n, TimeUnit.MILLISECONDS);
    }
    // monitorexit(this)
    
    @Override
    public void zzfW(final String zzbij) {
        synchronized (this) {
            this.zzHa();
            this.zzbij = zzbij;
        }
    }
    
    interface zza
    {
        zzcl zza(final zzs p0);
    }
    
    interface zzb
    {
        ScheduledExecutorService zzHb();
    }
}
