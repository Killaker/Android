package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.*;
import android.text.*;

class zza
{
    private String zzSF;
    private final zzw zzaTV;
    private final String zzaUa;
    private String zzaVc;
    private String zzaVd;
    private String zzaVe;
    private long zzaVf;
    private long zzaVg;
    private long zzaVh;
    private String zzaVi;
    private long zzaVj;
    private long zzaVk;
    private boolean zzaVl;
    private long zzaVm;
    private long zzaVn;
    private long zzaVo;
    private long zzaVp;
    private boolean zzaVq;
    private long zzaVr;
    private long zzaVs;
    
    zza(final zzw zzaTV, final String zzaUa) {
        zzx.zzz(zzaTV);
        zzx.zzcM(zzaUa);
        this.zzaTV = zzaTV;
        this.zzaUa = zzaUa;
        this.zzaTV.zzjk();
    }
    
    public void setAppVersion(final String zzSF) {
        this.zzaTV.zzjk();
        this.zzaVq |= zzaj.zzQ(this.zzSF, zzSF);
        this.zzSF = zzSF;
    }
    
    public void setMeasurementEnabled(final boolean zzaVl) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVl != zzaVl);
        this.zzaVl = zzaVl;
    }
    
    public boolean zzAr() {
        this.zzaTV.zzjk();
        return this.zzaVl;
    }
    
    public void zzBi() {
        this.zzaTV.zzjk();
        this.zzaVq = false;
    }
    
    public String zzBj() {
        this.zzaTV.zzjk();
        return this.zzaVc;
    }
    
    public String zzBk() {
        this.zzaTV.zzjk();
        return this.zzaVd;
    }
    
    public String zzBl() {
        this.zzaTV.zzjk();
        return this.zzaVe;
    }
    
    public long zzBm() {
        this.zzaTV.zzjk();
        return this.zzaVg;
    }
    
    public long zzBn() {
        this.zzaTV.zzjk();
        return this.zzaVh;
    }
    
    public String zzBo() {
        this.zzaTV.zzjk();
        return this.zzaVi;
    }
    
    public long zzBp() {
        this.zzaTV.zzjk();
        return this.zzaVj;
    }
    
    public long zzBq() {
        this.zzaTV.zzjk();
        return this.zzaVk;
    }
    
    public long zzBr() {
        this.zzaTV.zzjk();
        return this.zzaVf;
    }
    
    public long zzBs() {
        this.zzaTV.zzjk();
        return this.zzaVr;
    }
    
    public long zzBt() {
        this.zzaTV.zzjk();
        return this.zzaVs;
    }
    
    public void zzBu() {
        this.zzaTV.zzjk();
        long zzaVf = 1L + this.zzaVf;
        if (zzaVf > 2147483647L) {
            this.zzaTV.zzAo().zzCF().zzfg("Bundle index overflow");
            zzaVf = 0L;
        }
        this.zzaVq = true;
        this.zzaVf = zzaVf;
    }
    
    public long zzBv() {
        this.zzaTV.zzjk();
        return this.zzaVm;
    }
    
    public long zzBw() {
        this.zzaTV.zzjk();
        return this.zzaVn;
    }
    
    public long zzBx() {
        this.zzaTV.zzjk();
        return this.zzaVo;
    }
    
    public long zzBy() {
        this.zzaTV.zzjk();
        return this.zzaVp;
    }
    
    public void zzO(final long zzaVg) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVg != zzaVg);
        this.zzaVg = zzaVg;
    }
    
    public void zzP(final long zzaVh) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVh != zzaVh);
        this.zzaVh = zzaVh;
    }
    
    public void zzQ(final long zzaVj) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVj != zzaVj);
        this.zzaVj = zzaVj;
    }
    
    public void zzR(final long zzaVk) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVk != zzaVk);
        this.zzaVk = zzaVk;
    }
    
    public void zzS(final long zzaVf) {
        boolean b = true;
        zzx.zzac(zzaVf >= 0L && b);
        this.zzaTV.zzjk();
        final boolean zzaVq = this.zzaVq;
        if (this.zzaVf == zzaVf) {
            b = false;
        }
        this.zzaVq = (zzaVq | b);
        this.zzaVf = zzaVf;
    }
    
    public void zzT(final long zzaVr) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVr != zzaVr);
        this.zzaVr = zzaVr;
    }
    
    public void zzU(final long zzaVs) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVs != zzaVs);
        this.zzaVs = zzaVs;
    }
    
    public void zzV(final long zzaVm) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVm != zzaVm);
        this.zzaVm = zzaVm;
    }
    
    public void zzW(final long zzaVn) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVn != zzaVn);
        this.zzaVn = zzaVn;
    }
    
    public void zzX(final long zzaVo) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVo != zzaVo);
        this.zzaVo = zzaVo;
    }
    
    public void zzY(final long zzaVp) {
        this.zzaTV.zzjk();
        this.zzaVq |= (this.zzaVp != zzaVp);
        this.zzaVp = zzaVp;
    }
    
    public void zzeM(final String zzaVc) {
        this.zzaTV.zzjk();
        this.zzaVq |= zzaj.zzQ(this.zzaVc, zzaVc);
        this.zzaVc = zzaVc;
    }
    
    public void zzeN(String zzaVd) {
        this.zzaTV.zzjk();
        if (TextUtils.isEmpty((CharSequence)zzaVd)) {
            zzaVd = null;
        }
        this.zzaVq |= zzaj.zzQ(this.zzaVd, zzaVd);
        this.zzaVd = zzaVd;
    }
    
    public void zzeO(final String zzaVe) {
        this.zzaTV.zzjk();
        this.zzaVq |= zzaj.zzQ(this.zzaVe, zzaVe);
        this.zzaVe = zzaVe;
    }
    
    public void zzeP(final String zzaVi) {
        this.zzaTV.zzjk();
        this.zzaVq |= zzaj.zzQ(this.zzaVi, zzaVi);
        this.zzaVi = zzaVi;
    }
    
    public String zzli() {
        this.zzaTV.zzjk();
        return this.zzSF;
    }
    
    public String zzwK() {
        this.zzaTV.zzjk();
        return this.zzaUa;
    }
}
