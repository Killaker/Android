package com.google.android.gms.measurement.internal;

import android.util.*;
import android.support.annotation.*;
import android.content.*;
import com.google.android.gms.measurement.*;
import java.util.*;
import java.math.*;
import android.text.*;
import com.google.android.gms.ads.identifier.*;
import java.security.*;
import com.google.android.gms.common.internal.*;

class zzt extends zzz
{
    static final Pair<String, Long> zzaXh;
    private SharedPreferences zzTh;
    public final zzc zzaXi;
    public final zzb zzaXj;
    public final zzb zzaXk;
    public final zzb zzaXl;
    public final zzb zzaXm;
    public final zzb zzaXn;
    private String zzaXo;
    private boolean zzaXp;
    private long zzaXq;
    private final SecureRandom zzaXr;
    public final zzb zzaXs;
    public final zzb zzaXt;
    public final zza zzaXu;
    public final zzb zzaXv;
    public final zzb zzaXw;
    public boolean zzaXx;
    
    static {
        zzaXh = new Pair((Object)"", (Object)0L);
    }
    
    zzt(final zzw zzw) {
        super(zzw);
        this.zzaXi = new zzc("health_monitor", this.zzCp().zzkX());
        this.zzaXj = new zzb("last_upload", 0L);
        this.zzaXk = new zzb("last_upload_attempt", 0L);
        this.zzaXl = new zzb("backoff", 0L);
        this.zzaXm = new zzb("last_delete_stale", 0L);
        this.zzaXs = new zzb("time_before_start", 10000L);
        this.zzaXt = new zzb("session_timeout", 1800000L);
        this.zzaXu = new zza("start_new_session", true);
        this.zzaXv = new zzb("last_pause_time", 0L);
        this.zzaXw = new zzb("time_active", 0L);
        this.zzaXr = new SecureRandom();
        this.zzaXn = new zzb("midnight_offset", 0L);
    }
    
    @WorkerThread
    private SharedPreferences zzCO() {
        this.zzjk();
        this.zzjv();
        return this.zzTh;
    }
    
    @WorkerThread
    void setMeasurementEnabled(final boolean b) {
        this.zzjk();
        this.zzAo().zzCK().zzj("Setting measurementEnabled", b);
        final SharedPreferences$Editor edit = this.zzCO().edit();
        edit.putBoolean("measurement_enabled", b);
        edit.apply();
    }
    
    @WorkerThread
    boolean zzAr() {
        this.zzjk();
        return this.zzCO().getBoolean("measurement_enabled", !com.google.android.gms.measurement.zza.zzAs());
    }
    
    String zzCM() {
        final byte[] array = new byte[16];
        this.zzaXr.nextBytes(array);
        return String.format(Locale.US, "%032x", new BigInteger(1, array));
    }
    
    @WorkerThread
    long zzCN() {
        this.zzjv();
        this.zzjk();
        long value = this.zzaXn.get();
        if (value == 0L) {
            value = 1 + this.zzaXr.nextInt(86400000);
            this.zzaXn.set(value);
        }
        return value;
    }
    
    @WorkerThread
    Boolean zzCP() {
        this.zzjk();
        if (!this.zzCO().contains("use_service")) {
            return null;
        }
        return this.zzCO().getBoolean("use_service", false);
    }
    
    @WorkerThread
    protected String zzCQ() {
        this.zzjk();
        final String string = this.zzCO().getString("previous_os_version", (String)null);
        final String zzCy = this.zzCh().zzCy();
        if (!TextUtils.isEmpty((CharSequence)zzCy) && !zzCy.equals(string)) {
            final SharedPreferences$Editor edit = this.zzCO().edit();
            edit.putString("previous_os_version", zzCy);
            edit.apply();
        }
        return string;
    }
    
    @WorkerThread
    void zzar(final boolean b) {
        this.zzjk();
        this.zzAo().zzCK().zzj("Setting useService", b);
        final SharedPreferences$Editor edit = this.zzCO().edit();
        edit.putBoolean("use_service", b);
        edit.apply();
    }
    
    @WorkerThread
    Pair<String, Boolean> zzfh(final String s) {
        this.zzjk();
        final long elapsedRealtime = this.zzjl().elapsedRealtime();
        if (this.zzaXo != null && elapsedRealtime < this.zzaXq) {
            return (Pair<String, Boolean>)new Pair((Object)this.zzaXo, (Object)this.zzaXp);
        }
        this.zzaXq = elapsedRealtime + this.zzCp().zzeS(s);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        while (true) {
            try {
                final AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.getContext());
                this.zzaXo = advertisingIdInfo.getId();
                this.zzaXp = advertisingIdInfo.isLimitAdTrackingEnabled();
                AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
                return (Pair<String, Boolean>)new Pair((Object)this.zzaXo, (Object)this.zzaXp);
            }
            catch (Throwable t) {
                this.zzAo().zzCJ().zzj("Unable to get advertising id", t);
                this.zzaXo = "";
                continue;
            }
            break;
        }
    }
    
    String zzfi(final String s) {
        final String s2 = (String)this.zzfh(s).first;
        final MessageDigest zzbv = zzaj.zzbv("MD5");
        if (zzbv == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, zzbv.digest(s2.getBytes())));
    }
    
    @Override
    protected void zziJ() {
        this.zzTh = this.getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        if (!(this.zzaXx = this.zzTh.getBoolean("has_been_opened", false))) {
            final SharedPreferences$Editor edit = this.zzTh.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
    }
    
    public final class zza
    {
        private final boolean zzaXy;
        private boolean zzaXz;
        private boolean zzagf;
        private final String zzvs;
        
        public zza(final String zzvs, final boolean zzaXy) {
            zzx.zzcM(zzvs);
            this.zzvs = zzvs;
            this.zzaXy = zzaXy;
        }
        
        @WorkerThread
        private void zzCR() {
            if (this.zzaXz) {
                return;
            }
            this.zzaXz = true;
            this.zzagf = zzt.this.zzTh.getBoolean(this.zzvs, this.zzaXy);
        }
        
        @WorkerThread
        public boolean get() {
            this.zzCR();
            return this.zzagf;
        }
        
        @WorkerThread
        public void set(final boolean zzagf) {
            final SharedPreferences$Editor edit = zzt.this.zzTh.edit();
            edit.putBoolean(this.zzvs, zzagf);
            edit.apply();
            this.zzagf = zzagf;
        }
    }
    
    public final class zzb
    {
        private long zzaDV;
        private final long zzaXB;
        private boolean zzaXz;
        private final String zzvs;
        
        public zzb(final String zzvs, final long zzaXB) {
            zzx.zzcM(zzvs);
            this.zzvs = zzvs;
            this.zzaXB = zzaXB;
        }
        
        @WorkerThread
        private void zzCR() {
            if (this.zzaXz) {
                return;
            }
            this.zzaXz = true;
            this.zzaDV = zzt.this.zzTh.getLong(this.zzvs, this.zzaXB);
        }
        
        @WorkerThread
        public long get() {
            this.zzCR();
            return this.zzaDV;
        }
        
        @WorkerThread
        public void set(final long zzaDV) {
            final SharedPreferences$Editor edit = zzt.this.zzTh.edit();
            edit.putLong(this.zzvs, zzaDV);
            edit.apply();
            this.zzaDV = zzaDV;
        }
    }
    
    public final class zzc
    {
        private final long zzTl;
        final String zzaXC;
        private final String zzaXD;
        private final String zzaXE;
        
        private zzc(final String s, final long zzTl) {
            zzx.zzcM(s);
            zzx.zzac(zzTl > 0L);
            this.zzaXC = s + ":start";
            this.zzaXD = s + ":count";
            this.zzaXE = s + ":value";
            this.zzTl = zzTl;
        }
        
        @WorkerThread
        private void zzlL() {
            zzt.this.zzjk();
            final long currentTimeMillis = zzt.this.zzjl().currentTimeMillis();
            final SharedPreferences$Editor edit = zzt.this.zzTh.edit();
            edit.remove(this.zzaXD);
            edit.remove(this.zzaXE);
            edit.putLong(this.zzaXC, currentTimeMillis);
            edit.apply();
        }
        
        @WorkerThread
        private long zzlM() {
            zzt.this.zzjk();
            final long zzlO = this.zzlO();
            if (zzlO == 0L) {
                this.zzlL();
                return 0L;
            }
            return Math.abs(zzlO - zzt.this.zzjl().currentTimeMillis());
        }
        
        @WorkerThread
        private long zzlO() {
            return zzt.this.zzCO().getLong(this.zzaXC, 0L);
        }
        
        @WorkerThread
        public void zzbq(final String s) {
            this.zzf(s, 1L);
        }
        
        @WorkerThread
        public void zzf(String s, final long n) {
            zzt.this.zzjk();
            if (this.zzlO() == 0L) {
                this.zzlL();
            }
            if (s == null) {
                s = "";
            }
            final long long1 = zzt.this.zzTh.getLong(this.zzaXD, 0L);
            if (long1 <= 0L) {
                final SharedPreferences$Editor edit = zzt.this.zzTh.edit();
                edit.putString(this.zzaXE, s);
                edit.putLong(this.zzaXD, n);
                edit.apply();
                return;
            }
            int n2;
            if ((Long.MAX_VALUE & zzt.this.zzaXr.nextLong()) < n * (Long.MAX_VALUE / (long1 + n))) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            final SharedPreferences$Editor edit2 = zzt.this.zzTh.edit();
            if (n2 != 0) {
                edit2.putString(this.zzaXE, s);
            }
            edit2.putLong(this.zzaXD, long1 + n);
            edit2.apply();
        }
        
        @WorkerThread
        public Pair<String, Long> zzlN() {
            zzt.this.zzjk();
            final long zzlM = this.zzlM();
            if (zzlM < this.zzTl) {
                return null;
            }
            if (zzlM > 2L * this.zzTl) {
                this.zzlL();
                return null;
            }
            final String string = zzt.this.zzCO().getString(this.zzaXE, (String)null);
            final long long1 = zzt.this.zzCO().getLong(this.zzaXD, 0L);
            this.zzlL();
            if (string == null || long1 <= 0L) {
                return zzt.zzaXh;
            }
            return (Pair<String, Long>)new Pair((Object)string, (Object)long1);
        }
    }
}
