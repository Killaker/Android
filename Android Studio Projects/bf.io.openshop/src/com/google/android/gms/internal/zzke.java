package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import android.text.*;

public final class zzke extends zze<zzke>
{
    private String zzPN;
    private String zzPO;
    private String zzPP;
    private boolean zzPQ;
    private String zzPR;
    private boolean zzPS;
    private double zzPT;
    private String zzrG;
    
    public String getClientId() {
        return this.zzPO;
    }
    
    public String getUserId() {
        return this.zzrG;
    }
    
    public void setClientId(final String zzPO) {
        this.zzPO = zzPO;
    }
    
    public void setSampleRate(final double zzPT) {
        zzx.zzb(zzPT >= 0.0 && zzPT <= 100.0, (Object)"Sample rate must be between 0% and 100%");
        this.zzPT = zzPT;
    }
    
    public void setUserId(final String zzrG) {
        this.zzrG = zzrG;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("hitType", this.zzPN);
        hashMap.put("clientId", this.zzPO);
        hashMap.put("userId", this.zzrG);
        hashMap.put("androidAdId", this.zzPP);
        hashMap.put("AdTargetingEnabled", (String)this.zzPQ);
        hashMap.put("sessionControl", this.zzPR);
        hashMap.put("nonInteraction", (String)this.zzPS);
        hashMap.put("sampleRate", (String)this.zzPT);
        return zze.zzF(hashMap);
    }
    
    public void zzH(final boolean zzPQ) {
        this.zzPQ = zzPQ;
    }
    
    public void zzI(final boolean zzPS) {
        this.zzPS = zzPS;
    }
    
    @Override
    public void zza(final zzke zzke) {
        if (!TextUtils.isEmpty((CharSequence)this.zzPN)) {
            zzke.zzaX(this.zzPN);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzPO)) {
            zzke.setClientId(this.zzPO);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzrG)) {
            zzke.setUserId(this.zzrG);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzPP)) {
            zzke.zzaY(this.zzPP);
        }
        if (this.zzPQ) {
            zzke.zzH(true);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzPR)) {
            zzke.zzaZ(this.zzPR);
        }
        if (this.zzPS) {
            zzke.zzI(this.zzPS);
        }
        if (this.zzPT != 0.0) {
            zzke.setSampleRate(this.zzPT);
        }
    }
    
    public void zzaX(final String zzPN) {
        this.zzPN = zzPN;
    }
    
    public void zzaY(final String zzPP) {
        this.zzPP = zzPP;
    }
    
    public void zzaZ(final String zzPR) {
        this.zzPR = zzPR;
    }
    
    public String zziS() {
        return this.zzPN;
    }
    
    public String zziT() {
        return this.zzPP;
    }
    
    public boolean zziU() {
        return this.zzPQ;
    }
    
    public String zziV() {
        return this.zzPR;
    }
    
    public boolean zziW() {
        return this.zzPS;
    }
    
    public double zziX() {
        return this.zzPT;
    }
}
