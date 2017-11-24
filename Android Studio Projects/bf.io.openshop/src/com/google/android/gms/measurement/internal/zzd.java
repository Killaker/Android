package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.*;
import android.content.*;
import android.net.*;
import android.text.*;
import android.os.*;
import com.google.android.gms.internal.*;
import android.content.pm.*;

public class zzd extends zzy
{
    static final String zzaVA;
    private Boolean zzRy;
    
    static {
        zzaVA = String.valueOf(zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    }
    
    zzd(final zzw zzw) {
        super(zzw);
    }
    
    public int zzBA() {
        return 25;
    }
    
    int zzBB() {
        return 32;
    }
    
    public int zzBC() {
        return 24;
    }
    
    int zzBD() {
        return 36;
    }
    
    int zzBE() {
        return 256;
    }
    
    public int zzBF() {
        return 36;
    }
    
    public int zzBG() {
        return 2048;
    }
    
    int zzBH() {
        return 500;
    }
    
    public long zzBI() {
        return zzl.zzaWk.get();
    }
    
    public long zzBJ() {
        return zzl.zzaWl.get();
    }
    
    public long zzBK() {
        return zzl.zzaWm.get();
    }
    
    int zzBL() {
        return 25;
    }
    
    int zzBM() {
        return 50;
    }
    
    long zzBN() {
        return 3600000L;
    }
    
    long zzBO() {
        return 60000L;
    }
    
    long zzBP() {
        return 61000L;
    }
    
    public long zzBQ() {
        return zzl.zzaWw.get();
    }
    
    public long zzBR() {
        return zzl.zzaWs.get();
    }
    
    public long zzBS() {
        return 1000L;
    }
    
    public int zzBT() {
        return Math.max(0, zzl.zzaWi.get());
    }
    
    public int zzBU() {
        return Math.max(1, zzl.zzaWj.get());
    }
    
    public String zzBV() {
        return zzl.zzaWo.get();
    }
    
    public long zzBW() {
        return zzl.zzaWd.get();
    }
    
    public long zzBX() {
        return Math.max(0L, zzl.zzaWp.get());
    }
    
    public long zzBY() {
        return Math.max(0L, zzl.zzaWr.get());
    }
    
    public long zzBZ() {
        return zzl.zzaWq.get();
    }
    
    public long zzBp() {
        return zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000;
    }
    
    String zzBz() {
        return zzl.zzaWa.get();
    }
    
    public long zzCa() {
        return Math.max(0L, zzl.zzaWt.get());
    }
    
    public long zzCb() {
        return Math.max(0L, zzl.zzaWu.get());
    }
    
    public int zzCc() {
        return Math.min(20, Math.max(0, zzl.zzaWv.get()));
    }
    
    public String zzH(final String s, final String s2) {
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme((String)zzl.zzaWe.get()).authority((String)zzl.zzaWf.get()).path("config/app/" + s).appendQueryParameter("app_instance_id", s2).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", String.valueOf(this.zzBp()));
        return uri$Builder.build().toString();
    }
    
    public long zza(final String s, final zzl.zza<Long> zza) {
        if (s == null) {
            return zza.get();
        }
        final String zzO = this.zzCl().zzO(s, zza.getKey());
        if (TextUtils.isEmpty((CharSequence)zzO)) {
            return zza.get();
        }
        try {
            return zza.get((long)Long.valueOf(zzO));
        }
        catch (NumberFormatException ex) {
            return zza.get();
        }
    }
    
    public int zzb(final String s, final zzl.zza<Integer> zza) {
        if (s == null) {
            return zza.get();
        }
        final String zzO = this.zzCl().zzO(s, zza.getKey());
        if (TextUtils.isEmpty((CharSequence)zzO)) {
            return zza.get();
        }
        try {
            return zza.get((int)Integer.valueOf(zzO));
        }
        catch (NumberFormatException ex) {
            return zza.get();
        }
    }
    
    long zzeS(final String s) {
        return this.zza(s, zzl.zzaWb);
    }
    
    int zzeT(final String s) {
        return this.zzb(s, zzl.zzaWx);
    }
    
    public int zzeU(final String s) {
        return this.zzb(s, zzl.zzaWg);
    }
    
    public int zzeV(final String s) {
        return Math.max(0, this.zzb(s, zzl.zzaWh));
    }
    
    public int zzeW(final String s) {
        return Math.max(0, Math.min(1000000, this.zzb(s, zzl.zzaWn)));
    }
    
    long zzkM() {
        return zzl.zzaWy.get();
    }
    
    public String zzkR() {
        return "google_app_measurement.db";
    }
    
    public String zzkS() {
        return "google_app_measurement2.db";
    }
    
    public long zzkX() {
        return Math.max(0L, zzl.zzaWc.get());
    }
    
    public boolean zzkr() {
        return com.google.android.gms.common.internal.zzd.zzakE;
    }
    
    public boolean zzks() {
        Label_0100: {
            if (this.zzRy != null) {
                break Label_0100;
            }
            synchronized (this) {
                if (this.zzRy == null) {
                    final ApplicationInfo applicationInfo = this.getContext().getApplicationInfo();
                    final String zzi = zznf.zzi(this.getContext(), Process.myPid());
                    if (applicationInfo != null) {
                        final String processName = applicationInfo.processName;
                        this.zzRy = (processName != null && processName.equals(zzi));
                    }
                    if (this.zzRy == null) {
                        this.zzRy = Boolean.TRUE;
                        this.zzAo().zzCE().zzfg("My process not in the list of running processes");
                    }
                }
                // monitorexit(this)
                return this.zzRy;
            }
        }
    }
}
