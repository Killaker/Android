package com.google.android.gms.analytics.internal;

import android.content.*;
import android.content.pm.*;
import android.os.*;

public class zzan extends zzd
{
    protected boolean zzPi;
    protected int zzRB;
    protected String zzSE;
    protected String zzSF;
    protected int zzSH;
    protected boolean zzTv;
    protected boolean zzTw;
    protected boolean zzTx;
    
    public zzan(final zzf zzf) {
        super(zzf);
    }
    
    private static int zzby(final String s) {
        final String lowerCase = s.toLowerCase();
        if ("verbose".equals(lowerCase)) {
            return 0;
        }
        if ("info".equals(lowerCase)) {
            return 1;
        }
        if ("warning".equals(lowerCase)) {
            return 2;
        }
        if ("error".equals(lowerCase)) {
            return 3;
        }
        return -1;
    }
    
    public int getLogLevel() {
        this.zzjv();
        return this.zzRB;
    }
    
    void zza(final zzaa zzaa) {
        this.zzbd("Loading global XML config values");
        if (zzaa.zzlf()) {
            this.zzb("XML config - app name", this.zzSE = zzaa.zzlg());
        }
        if (zzaa.zzlh()) {
            this.zzb("XML config - app version", this.zzSF = zzaa.zzli());
        }
        if (zzaa.zzlj()) {
            final int zzby = zzby(zzaa.zzlk());
            if (zzby >= 0) {
                this.zzRB = zzby;
                this.zza("XML config - log level", zzby);
            }
        }
        if (zzaa.zzll()) {
            final int zzlm = zzaa.zzlm();
            this.zzSH = zzlm;
            this.zzTw = true;
            this.zzb("XML config - dispatch period (sec)", zzlm);
        }
        if (zzaa.zzln()) {
            final boolean zzlo = zzaa.zzlo();
            this.zzPi = zzlo;
            this.zzTx = true;
            this.zzb("XML config - dry run", zzlo);
        }
    }
    
    @Override
    protected void zziJ() {
        this.zzmd();
    }
    
    public String zzlg() {
        this.zzjv();
        return this.zzSE;
    }
    
    public String zzli() {
        this.zzjv();
        return this.zzSF;
    }
    
    public boolean zzlj() {
        this.zzjv();
        return this.zzTv;
    }
    
    public boolean zzll() {
        this.zzjv();
        return this.zzTw;
    }
    
    public boolean zzln() {
        this.zzjv();
        return this.zzTx;
    }
    
    public boolean zzlo() {
        this.zzjv();
        return this.zzPi;
    }
    
    public int zzmc() {
        this.zzjv();
        return this.zzSH;
    }
    
    protected void zzmd() {
        zzaa zzaa;
        while (true) {
            final Context context = this.getContext();
            ApplicationInfo applicationInfo;
            while (true) {
                try {
                    applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 129);
                    if (applicationInfo == null) {
                        this.zzbg("Couldn't get ApplicationInfo to load global config");
                        return;
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    this.zzd("PackageManager doesn't know about the app package", ex);
                    applicationInfo = null;
                    continue;
                }
                break;
            }
            final Bundle metaData = applicationInfo.metaData;
            if (metaData == null) {
                return;
            }
            final int int1 = metaData.getInt("com.google.android.gms.analytics.globalConfigResource");
            if (int1 <= 0) {
                return;
            }
            zzaa = new zzz(this.zzji()).zzah(int1);
            if (zzaa != null) {
                break;
            }
            return;
        }
        this.zza(zzaa);
    }
}
