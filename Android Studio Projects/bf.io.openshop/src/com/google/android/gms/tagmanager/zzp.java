package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.internal.*;
import android.content.*;
import android.os.*;
import com.google.android.gms.internal.*;
import android.app.*;
import com.google.android.gms.common.api.*;

public class zzp extends com.google.android.gms.common.api.internal.zzb<ContainerHolder>
{
    private final Context mContext;
    private final Looper zzagr;
    private final String zzbhM;
    private long zzbhR;
    private final TagManager zzbhY;
    private final zzd zzbib;
    private final zzcd zzbic;
    private final int zzbid;
    private zzf zzbie;
    private zzrr zzbif;
    private volatile zzo zzbig;
    private volatile boolean zzbih;
    private zzaf.zzj zzbii;
    private String zzbij;
    private zze zzbik;
    private zza zzbil;
    private final zzmq zzqW;
    
    zzp(final Context mContext, final TagManager zzbhY, Looper mainLooper, final String zzbhM, final int zzbid, final zzf zzbie, final zze zzbik, final zzrr zzbif, final zzmq zzqW, final zzcd zzbic) {
        Looper mainLooper2;
        if (mainLooper == null) {
            mainLooper2 = Looper.getMainLooper();
        }
        else {
            mainLooper2 = mainLooper;
        }
        super(mainLooper2);
        this.mContext = mContext;
        this.zzbhY = zzbhY;
        if (mainLooper == null) {
            mainLooper = Looper.getMainLooper();
        }
        this.zzagr = mainLooper;
        this.zzbhM = zzbhM;
        this.zzbid = zzbid;
        this.zzbie = zzbie;
        this.zzbik = zzbik;
        this.zzbif = zzbif;
        this.zzbib = new zzd();
        this.zzbii = new zzaf.zzj();
        this.zzqW = zzqW;
        this.zzbic = zzbic;
        if (this.zzGj()) {
            this.zzfT(zzcb.zzGU().zzGW());
        }
    }
    
    public zzp(final Context context, final TagManager tagManager, final Looper looper, final String s, final int n, final zzs zzs) {
        this(context, tagManager, looper, s, n, (zzf)new zzcn(context, s), (zze)new zzcm(context, s, zzs), new zzrr(context), zzmt.zzsc(), new zzbe(30, 900000L, 5000L, "refreshing", zzmt.zzsc()));
        this.zzbif.zzgB(zzs.zzGm());
    }
    
    private boolean zzGj() {
        final zzcb zzGU = zzcb.zzGU();
        return (zzGU.zzGV() == zzcb.zza.zzbjV || zzGU.zzGV() == zzcb.zza.zzbjW) && this.zzbhM.equals(zzGU.getContainerId());
    }
    
    private void zza(final zzaf.zzj zzbme) {
        synchronized (this) {
            if (this.zzbie != null) {
                final zzrq.zza zza = new zzrq.zza();
                zza.zzbmd = this.zzbhR;
                zza.zzju = new zzaf.zzf();
                zza.zzbme = zzbme;
                this.zzbie.zzb(zza);
            }
        }
    }
    
    private void zza(final zzaf.zzj zzbii, final long zzbhR, final boolean b) {
        // monitorenter(this)
        Label_0021: {
            if (!b) {
                break Label_0021;
            }
            while (true) {
                while (true) {
                    Container container = null;
                    Label_0174: {
                        try {
                            if (!this.zzbih) {
                                if (!this.isReady() || this.zzbig == null) {}
                                this.zzbii = zzbii;
                                this.zzbhR = zzbhR;
                                this.zzak(Math.max(0L, Math.min(43200000L, 43200000L + this.zzbhR - this.zzqW.currentTimeMillis())));
                                container = new Container(this.mContext, this.zzbhY.getDataLayer(), this.zzbhM, zzbhR, zzbii);
                                if (this.zzbig != null) {
                                    break Label_0174;
                                }
                                this.zzbig = new zzo(this.zzbhY, this.zzagr, container, (zzo.zza)this.zzbib);
                                if (!this.isReady() && this.zzbil.zzb(container)) {
                                    ((com.google.android.gms.common.api.internal.zzb<zzo>)this).zza(this.zzbig);
                                }
                            }
                            return;
                        }
                        finally {
                        }
                        // monitorexit(this)
                    }
                    this.zzbig.zza(container);
                    continue;
                }
            }
        }
    }
    
    private void zzak(final long n) {
        synchronized (this) {
            if (this.zzbik == null) {
                zzbg.zzaK("Refresh requested, but no network load scheduler.");
            }
            else {
                this.zzbik.zzf(n, this.zzbii.zzjv);
            }
        }
    }
    
    private void zzaw(final boolean b) {
        this.zzbie.zza(new zzb());
        this.zzbik.zza(new zzc());
        final zzrs.zzc zzke = this.zzbie.zzke(this.zzbid);
        if (zzke != null) {
            this.zzbig = new zzo(this.zzbhY, this.zzagr, new Container(this.mContext, this.zzbhY.getDataLayer(), this.zzbhM, 0L, zzke), (zzo.zza)this.zzbib);
        }
        this.zzbil = (zza)new zza() {
            @Override
            public boolean zzb(final Container container) {
                if (b) {
                    if (43200000L + container.getLastRefreshTime() < zzp.this.zzqW.currentTimeMillis()) {
                        return false;
                    }
                }
                else if (container.isDefault()) {
                    return false;
                }
                return true;
            }
        };
        if (this.zzGj()) {
            this.zzbik.zzf(0L, "");
            return;
        }
        this.zzbie.zzGl();
    }
    
    String zzGd() {
        synchronized (this) {
            return this.zzbij;
        }
    }
    
    public void zzGg() {
        final zzrs.zzc zzke = this.zzbie.zzke(this.zzbid);
        if (zzke != null) {
            ((com.google.android.gms.common.api.internal.zzb<zzo>)this).zza(new zzo(this.zzbhY, this.zzagr, new Container(this.mContext, this.zzbhY.getDataLayer(), this.zzbhM, 0L, zzke), (zzo.zza)new zzo.zza() {
                @Override
                public String zzGd() {
                    return zzp.this.zzGd();
                }
                
                @Override
                public void zzGf() {
                    zzbg.zzaK("Refresh ignored: container loaded as default only.");
                }
                
                @Override
                public void zzfT(final String s) {
                    zzp.this.zzfT(s);
                }
            }));
        }
        else {
            zzbg.e("Default was requested, but no default container was found");
            this.zza(this.zzbn(new Status(10, "Default was requested, but no default container was found", null)));
        }
        this.zzbik = null;
        this.zzbie = null;
    }
    
    public void zzGh() {
        this.zzaw(false);
    }
    
    public void zzGi() {
        this.zzaw(true);
    }
    
    protected ContainerHolder zzbn(final Status status) {
        if (this.zzbig != null) {
            return this.zzbig;
        }
        if (status == Status.zzagF) {
            zzbg.e("timer expired: setting result to failure");
        }
        return new zzo(status);
    }
    
    void zzfT(final String zzbij) {
        synchronized (this) {
            this.zzbij = zzbij;
            if (this.zzbik != null) {
                this.zzbik.zzfW(zzbij);
            }
        }
    }
    
    interface zza
    {
        boolean zzb(final Container p0);
    }
    
    private class zzb implements zzbf<zzrq.zza>
    {
        @Override
        public void zzGk() {
        }
        
        public void zza(final zzrq.zza zza) {
            zzaf.zzj zzbme;
            if (zza.zzbme != null) {
                zzbme = zza.zzbme;
            }
            else {
                final zzaf.zzf zzju = zza.zzju;
                zzbme = new zzaf.zzj();
                zzbme.zzju = zzju;
                zzbme.zzjt = null;
                zzbme.zzjv = zzju.version;
            }
            zzp.this.zza(zzbme, zza.zzbmd, true);
        }
        
        @Override
        public void zza(final zzbf.zza zza) {
            if (!zzp.this.zzbih) {
                zzp.this.zzak(0L);
            }
        }
    }
    
    private class zzc implements zzbf<zzaf.zzj>
    {
        @Override
        public void zzGk() {
        }
        
        @Override
        public void zza(final zzbf.zza zza) {
            synchronized (zzp.this) {
                if (!zzp.this.isReady()) {
                    if (zzp.this.zzbig != null) {
                        ((com.google.android.gms.common.api.internal.zzb<zzo>)zzp.this).zza(zzp.this.zzbig);
                    }
                    else {
                        zzp.this.zza(zzp.this.zzbn(Status.zzagF));
                    }
                }
                // monitorexit(this.zzbim)
                zzp.this.zzak(3600000L);
            }
        }
        
        public void zzb(final zzaf.zzj zzj) {
            synchronized (zzp.this) {
                if (zzj.zzju == null) {
                    if (zzp.this.zzbii.zzju == null) {
                        zzbg.e("Current resource is null; network resource is also null");
                        zzp.this.zzak(3600000L);
                        return;
                    }
                    zzj.zzju = zzp.this.zzbii.zzju;
                }
                zzp.this.zza(zzj, zzp.this.zzqW.currentTimeMillis(), false);
                zzbg.v("setting refresh time to current time: " + zzp.this.zzbhR);
                if (!zzp.this.zzGj()) {
                    zzp.this.zza(zzj);
                }
            }
        }
    }
    
    private class zzd implements zzo.zza
    {
        @Override
        public String zzGd() {
            return zzp.this.zzGd();
        }
        
        @Override
        public void zzGf() {
            if (zzp.this.zzbic.zzlw()) {
                zzp.this.zzak(0L);
            }
        }
        
        @Override
        public void zzfT(final String s) {
            zzp.this.zzfT(s);
        }
    }
    
    interface zze extends Releasable
    {
        void zza(final zzbf<zzaf.zzj> p0);
        
        void zzf(final long p0, final String p1);
        
        void zzfW(final String p0);
    }
    
    interface zzf extends Releasable
    {
        void zzGl();
        
        void zza(final zzbf<zzrq.zza> p0);
        
        void zzb(final zzrq.zza p0);
        
        zzrs.zzc zzke(final int p0);
    }
}
