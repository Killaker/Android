package com.google.android.gms.analytics.internal;

import android.text.*;
import java.util.*;
import com.google.android.gms.common.internal.*;
import android.os.*;
import com.google.android.gms.internal.*;
import android.content.pm.*;

public class zzr
{
    private final zzf zzOK;
    private Set<Integer> zzRA;
    private volatile Boolean zzRy;
    private String zzRz;
    
    protected zzr(final zzf zzOK) {
        zzx.zzz(zzOK);
        this.zzOK = zzOK;
    }
    
    public long zzkA() {
        return zzy.zzRV.get();
    }
    
    public long zzkB() {
        return zzy.zzRW.get();
    }
    
    public int zzkC() {
        return zzy.zzRX.get();
    }
    
    public int zzkD() {
        return zzy.zzRY.get();
    }
    
    public long zzkE() {
        return zzy.zzSl.get();
    }
    
    public String zzkF() {
        return zzy.zzSa.get();
    }
    
    public String zzkG() {
        return zzy.zzRZ.get();
    }
    
    public String zzkH() {
        return zzy.zzSb.get();
    }
    
    public String zzkI() {
        return zzy.zzSc.get();
    }
    
    public zzm zzkJ() {
        return zzm.zzbm(zzy.zzSe.get());
    }
    
    public zzo zzkK() {
        return zzo.zzbn(zzy.zzSf.get());
    }
    
    public Set<Integer> zzkL() {
        final String zzRz = zzy.zzSk.get();
        Label_0101: {
            if (this.zzRA != null && this.zzRz != null && this.zzRz.equals(zzRz)) {
                break Label_0101;
            }
            final String[] split = TextUtils.split(zzRz, ",");
            final HashSet<Integer> zzRA = new HashSet<Integer>();
            final int length = split.length;
            int n = 0;
        Label_0085_Outer:
            while (true) {
                Label_0091: {
                    if (n >= length) {
                        break Label_0091;
                    }
                    final String s = split[n];
                    while (true) {
                        try {
                            zzRA.add(Integer.parseInt(s));
                            ++n;
                            continue Label_0085_Outer;
                            return this.zzRA;
                            this.zzRz = zzRz;
                            this.zzRA = zzRA;
                            return this.zzRA;
                        }
                        catch (NumberFormatException ex) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    public long zzkM() {
        return zzy.zzSt.get();
    }
    
    public long zzkN() {
        return zzy.zzSu.get();
    }
    
    public long zzkO() {
        return zzy.zzSx.get();
    }
    
    public int zzkP() {
        return zzy.zzRO.get();
    }
    
    public int zzkQ() {
        return zzy.zzRQ.get();
    }
    
    public String zzkR() {
        return "google_analytics_v4.db";
    }
    
    public String zzkS() {
        return "google_analytics2_v4.db";
    }
    
    public long zzkT() {
        return 86400000L;
    }
    
    public int zzkU() {
        return zzy.zzSn.get();
    }
    
    public int zzkV() {
        return zzy.zzSo.get();
    }
    
    public long zzkW() {
        return zzy.zzSp.get();
    }
    
    public long zzkX() {
        return zzy.zzSy.get();
    }
    
    public boolean zzkr() {
        return zzd.zzakE;
    }
    
    public boolean zzks() {
        Label_0138: {
            if (this.zzRy != null) {
                break Label_0138;
            }
            synchronized (this) {
                if (this.zzRy == null) {
                    final ApplicationInfo applicationInfo = this.zzOK.getContext().getApplicationInfo();
                    final String zzi = zznf.zzi(this.zzOK.getContext(), Process.myPid());
                    if (applicationInfo != null) {
                        final String processName = applicationInfo.processName;
                        this.zzRy = (processName != null && processName.equals(zzi));
                    }
                    if ((this.zzRy == null || !this.zzRy) && "com.google.android.gms.analytics".equals(zzi)) {
                        this.zzRy = Boolean.TRUE;
                    }
                    if (this.zzRy == null) {
                        this.zzRy = Boolean.TRUE;
                        this.zzOK.zzjm().zzbh("My process not in the list of running processes");
                    }
                }
                // monitorexit(this)
                return this.zzRy;
            }
        }
    }
    
    public boolean zzkt() {
        return zzy.zzRK.get();
    }
    
    public int zzku() {
        return zzy.zzSd.get();
    }
    
    public int zzkv() {
        return zzy.zzSh.get();
    }
    
    public int zzkw() {
        return zzy.zzSi.get();
    }
    
    public int zzkx() {
        return zzy.zzSj.get();
    }
    
    public long zzky() {
        return zzy.zzRS.get();
    }
    
    public long zzkz() {
        return zzy.zzRR.get();
    }
}
