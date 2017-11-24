package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import android.content.*;
import java.util.*;
import android.util.*;

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
        final SharedPreferences$Editor edit = zzai.zza(zzai.this).edit();
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
        return zzai.zza(zzai.this).getLong(this.zzlP(), 0L);
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
                    final long long1 = zzai.zza(zzai.this).getLong(this.zzlQ(), 0L);
                    if (long1 <= 0L) {
                        final SharedPreferences$Editor edit = zzai.zza(zzai.this).edit();
                        edit.putString(this.zzlR(), s);
                        edit.putLong(this.zzlQ(), 1L);
                        edit.apply();
                        return;
                    }
                    if ((Long.MAX_VALUE & UUID.randomUUID().getLeastSignificantBits()) < Long.MAX_VALUE / (long1 + 1L)) {
                        final int n = 1;
                        final SharedPreferences$Editor edit2 = zzai.zza(zzai.this).edit();
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
            final String string = zzai.zza(zzai.this).getString(this.zzlR(), (String)null);
            final long long1 = zzai.zza(zzai.this).getLong(this.zzlQ(), 0L);
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
