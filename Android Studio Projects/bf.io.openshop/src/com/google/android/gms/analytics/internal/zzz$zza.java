package com.google.android.gms.analytics.internal;

private static class zza implements zzq.zza<zzaa>
{
    private final zzf zzQj;
    private final zzaa zzSD;
    
    public zza(final zzf zzQj) {
        this.zzQj = zzQj;
        this.zzSD = new zzaa();
    }
    
    @Override
    public void zzc(final String s, final int zzSH) {
        if ("ga_dispatchPeriod".equals(s)) {
            this.zzSD.zzSH = zzSH;
            return;
        }
        this.zzQj.zzjm().zzd("Int xml configuration name not recognized", s);
    }
    
    @Override
    public void zzf(final String s, final boolean b) {
        if ("ga_dryRun".equals(s)) {
            final zzaa zzSD = this.zzSD;
            int zzSI;
            if (b) {
                zzSI = 1;
            }
            else {
                zzSI = 0;
            }
            zzSD.zzSI = zzSI;
            return;
        }
        this.zzQj.zzjm().zzd("Bool xml configuration name not recognized", s);
    }
    
    @Override
    public void zzj(final String s, final String s2) {
    }
    
    @Override
    public void zzk(final String s, final String zzSG) {
        if ("ga_appName".equals(s)) {
            this.zzSD.zzSE = zzSG;
            return;
        }
        if ("ga_appVersion".equals(s)) {
            this.zzSD.zzSF = zzSG;
            return;
        }
        if ("ga_logLevel".equals(s)) {
            this.zzSD.zzSG = zzSG;
            return;
        }
        this.zzQj.zzjm().zzd("String xml configuration name not recognized", s);
    }
    
    public zzaa zzle() {
        return this.zzSD;
    }
}
