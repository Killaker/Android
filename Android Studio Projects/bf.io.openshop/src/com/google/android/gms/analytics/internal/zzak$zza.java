package com.google.android.gms.analytics.internal;

private static class zza extends zzc implements zzq.zza<zzal>
{
    private final zzal zzTn;
    
    public zza(final zzf zzf) {
        super(zzf);
        this.zzTn = new zzal();
    }
    
    @Override
    public void zzc(final String s, final int zzTp) {
        if ("ga_sessionTimeout".equals(s)) {
            this.zzTn.zzTp = zzTp;
            return;
        }
        this.zzd("int configuration name not recognized", s);
    }
    
    @Override
    public void zzf(final String s, final boolean b) {
        int zzTs = 1;
        if ("ga_autoActivityTracking".equals(s)) {
            final zzal zzTn = this.zzTn;
            if (!b) {
                zzTs = 0;
            }
            zzTn.zzTq = zzTs;
            return;
        }
        if ("ga_anonymizeIp".equals(s)) {
            final zzal zzTn2 = this.zzTn;
            if (!b) {
                zzTs = 0;
            }
            zzTn2.zzTr = zzTs;
            return;
        }
        if ("ga_reportUncaughtExceptions".equals(s)) {
            final zzal zzTn3 = this.zzTn;
            if (!b) {
                zzTs = 0;
            }
            zzTn3.zzTs = zzTs;
            return;
        }
        this.zzd("bool configuration name not recognized", s);
    }
    
    @Override
    public void zzj(final String s, final String s2) {
        this.zzTn.zzTt.put(s, s2);
    }
    
    @Override
    public void zzk(final String s, final String zzOV) {
        if ("ga_trackingId".equals(s)) {
            this.zzTn.zzOV = zzOV;
            return;
        }
        if ("ga_sampleFrequency".equals(s)) {
            try {
                this.zzTn.zzTo = Double.parseDouble(zzOV);
                return;
            }
            catch (NumberFormatException ex) {
                this.zzc("Error parsing ga_sampleFrequency value", zzOV, ex);
                return;
            }
        }
        this.zzd("string configuration name not recognized", s);
    }
    
    public zzal zzlS() {
        return this.zzTn;
    }
}
