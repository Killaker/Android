package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.*;

public class zzk extends zzd
{
    private final zzpq zzQX;
    
    zzk(final zzf zzf) {
        super(zzf);
        this.zzQX = new zzpq();
    }
    
    public void zziE() {
        final zzan zziI = this.zziI();
        final String zzlg = zziI.zzlg();
        if (zzlg != null) {
            this.zzQX.setAppName(zzlg);
        }
        final String zzli = zziI.zzli();
        if (zzli != null) {
            this.zzQX.setAppVersion(zzli);
        }
    }
    
    @Override
    protected void zziJ() {
        this.zzjo().zzAH().zza(this.zzQX);
        this.zziE();
    }
    
    public zzpq zzjS() {
        this.zzjv();
        return this.zzQX;
    }
}
