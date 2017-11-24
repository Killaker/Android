package com.google.android.gms.analytics.internal;

import android.text.*;
import android.content.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import android.util.*;

public class zzai extends zzd
{
    private SharedPreferences zzTh;
    private long zzTi;
    private long zzTj;
    private final zza zzTk;
    
    protected zzai(final zzf zzf) {
        super(zzf);
        this.zzTj = -1L;
        this.zzTk = new zza("monitoring", this.zzjn().zzkX());
    }
    
    public void zzbp(final String s) {
        this.zzjk();
        this.zzjv();
        final SharedPreferences$Editor edit = this.zzTh.edit();
        if (TextUtils.isEmpty((CharSequence)s)) {
            edit.remove("installation_campaign");
        }
        else {
            edit.putString("installation_campaign", s);
        }
        if (!edit.commit()) {
            this.zzbg("Failed to commit campaign data");
        }
    }
    
    @Override
    protected void zziJ() {
        this.zzTh = this.getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }
    
    public long zzlF() {
        this.zzjk();
        this.zzjv();
        if (this.zzTi == 0L) {
            final long long1 = this.zzTh.getLong("first_run", 0L);
            if (long1 != 0L) {
                this.zzTi = long1;
            }
            else {
                final long currentTimeMillis = this.zzjl().currentTimeMillis();
                final SharedPreferences$Editor edit = this.zzTh.edit();
                edit.putLong("first_run", currentTimeMillis);
                if (!edit.commit()) {
                    this.zzbg("Failed to commit first run time");
                }
                this.zzTi = currentTimeMillis;
            }
        }
        return this.zzTi;
    }
    
    public zzaj zzlG() {
        return new zzaj(this.zzjl(), this.zzlF());
    }
    
    public long zzlH() {
        this.zzjk();
        this.zzjv();
        if (this.zzTj == -1L) {
            this.zzTj = this.zzTh.getLong("last_dispatch", 0L);
        }
        return this.zzTj;
    }
    
    public void zzlI() {
        this.zzjk();
        this.zzjv();
        final long currentTimeMillis = this.zzjl().currentTimeMillis();
        final SharedPreferences$Editor edit = this.zzTh.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.zzTj = currentTimeMillis;
    }
    
    public String zzlJ() {
        this.zzjk();
        this.zzjv();
        final String string = this.zzTh.getString("installation_campaign", (String)null);
        if (TextUtils.isEmpty((CharSequence)string)) {
            return null;
        }
        return string;
    }
    
    public zza zzlK() {
        return this.zzTk;
    }
    
    public final class zza
    {
        private final String mName;
        private final long zzTl;
        
        private zza(final String mName, final long zzTl) {
            zzx.zzcM(mName);
            zzx.zzac(zzTl > 0L);
            this.mName = mName;
            this.zzTl = zzTl;
        }
        
        private void zzlL() {
            final long currentTimeMillis = zzai.this.zzjl().currentTimeMillis();
            final SharedPreferences$Editor edit = zzai.this.zzTh.edit();
            edit.remove(this.zzlQ());
            edit.remove(this.zzlR());
            edit.putLong(this.zzlP(), currentTimeMillis);
            edit.commit();
        }
        
        private long zzlM() {
            final long zzlO = this.zzlO();
            if (zzlO == 0L) {
                return 0L;
            }
            return Math.abs(zzlO - zzai.this.zzjl().currentTimeMillis());
        }
        
        private long zzlO() {
            return zzai.this.zzTh.getLong(this.zzlP(), 0L);
        }
        
        private String zzlP() {
            return this.mName + ":start";
        }
        
        private String zzlQ() {
            return this.mName + ":count";
        }
        
        public void zzbq(String s) {
            if (this.zzlO() == 0L) {
                this.zzlL();
            }
            if (s == null) {
                s = "";
            }
            while (true) {
                while (true) {
                    synchronized (this) {
                        final long long1 = zzai.this.zzTh.getLong(this.zzlQ(), 0L);
                        if (long1 <= 0L) {
                            final SharedPreferences$Editor edit = zzai.this.zzTh.edit();
                            edit.putString(this.zzlR(), s);
                            edit.putLong(this.zzlQ(), 1L);
                            edit.apply();
                            return;
                        }
                        if ((Long.MAX_VALUE & UUID.randomUUID().getLeastSignificantBits()) < Long.MAX_VALUE / (long1 + 1L)) {
                            final int n = 1;
                            final SharedPreferences$Editor edit2 = zzai.this.zzTh.edit();
                            if (n != 0) {
                                edit2.putString(this.zzlR(), s);
                            }
                            edit2.putLong(this.zzlQ(), long1 + 1L);
                            edit2.apply();
                            return;
                        }
                    }
                    final int n = 0;
                    continue;
                }
            }
        }
        
        public Pair<String, Long> zzlN() {
            final long zzlM = this.zzlM();
            if (zzlM >= this.zzTl) {
                if (zzlM > 2L * this.zzTl) {
                    this.zzlL();
                    return null;
                }
                final String string = zzai.this.zzTh.getString(this.zzlR(), (String)null);
                final long long1 = zzai.this.zzTh.getLong(this.zzlQ(), 0L);
                this.zzlL();
                if (string != null && long1 > 0L) {
                    return (Pair<String, Long>)new Pair((Object)string, (Object)long1);
                }
            }
            return null;
        }
        
        protected String zzlR() {
            return this.mName + ":value";
        }
    }
}
