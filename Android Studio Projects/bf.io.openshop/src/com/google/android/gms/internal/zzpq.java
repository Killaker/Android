package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import java.util.*;
import android.text.*;

public final class zzpq extends zze<zzpq>
{
    private String zzSE;
    private String zzSF;
    private String zzaUE;
    private String zzaUa;
    
    public void setAppId(final String zzaUa) {
        this.zzaUa = zzaUa;
    }
    
    public void setAppInstallerId(final String zzaUE) {
        this.zzaUE = zzaUE;
    }
    
    public void setAppName(final String zzSE) {
        this.zzSE = zzSE;
    }
    
    public void setAppVersion(final String zzSF) {
        this.zzSF = zzSF;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("appName", this.zzSE);
        hashMap.put("appVersion", this.zzSF);
        hashMap.put("appId", this.zzaUa);
        hashMap.put("appInstallerId", this.zzaUE);
        return zze.zzF(hashMap);
    }
    
    public String zzAJ() {
        return this.zzaUE;
    }
    
    @Override
    public void zza(final zzpq zzpq) {
        if (!TextUtils.isEmpty((CharSequence)this.zzSE)) {
            zzpq.setAppName(this.zzSE);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzSF)) {
            zzpq.setAppVersion(this.zzSF);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUa)) {
            zzpq.setAppId(this.zzaUa);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUE)) {
            zzpq.setAppInstallerId(this.zzaUE);
        }
    }
    
    public String zzlg() {
        return this.zzSE;
    }
    
    public String zzli() {
        return this.zzSF;
    }
    
    public String zzwK() {
        return this.zzaUa;
    }
}
