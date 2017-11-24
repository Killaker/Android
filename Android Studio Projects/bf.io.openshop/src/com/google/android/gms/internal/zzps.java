package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import java.util.*;
import android.text.*;

public final class zzps extends zze<zzps>
{
    public int zzDC;
    public int zzDD;
    public int zzaUL;
    public int zzaUM;
    public int zzaUN;
    private String zzaaL;
    
    public String getLanguage() {
        return this.zzaaL;
    }
    
    public void setLanguage(final String zzaaL) {
        this.zzaaL = zzaaL;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("language", this.zzaaL);
        hashMap.put("screenColors", (String)this.zzaUL);
        hashMap.put("screenWidth", (String)this.zzDC);
        hashMap.put("screenHeight", (String)this.zzDD);
        hashMap.put("viewportWidth", (String)this.zzaUM);
        hashMap.put("viewportHeight", (String)this.zzaUN);
        return zze.zzF(hashMap);
    }
    
    public int zzAQ() {
        return this.zzaUL;
    }
    
    public int zzAR() {
        return this.zzDC;
    }
    
    public int zzAS() {
        return this.zzDD;
    }
    
    public int zzAT() {
        return this.zzaUM;
    }
    
    public int zzAU() {
        return this.zzaUN;
    }
    
    @Override
    public void zza(final zzps zzps) {
        if (this.zzaUL != 0) {
            zzps.zziA(this.zzaUL);
        }
        if (this.zzDC != 0) {
            zzps.zziB(this.zzDC);
        }
        if (this.zzDD != 0) {
            zzps.zziC(this.zzDD);
        }
        if (this.zzaUM != 0) {
            zzps.zziD(this.zzaUM);
        }
        if (this.zzaUN != 0) {
            zzps.zziE(this.zzaUN);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaaL)) {
            zzps.setLanguage(this.zzaaL);
        }
    }
    
    public void zziA(final int zzaUL) {
        this.zzaUL = zzaUL;
    }
    
    public void zziB(final int zzDC) {
        this.zzDC = zzDC;
    }
    
    public void zziC(final int zzDD) {
        this.zzDD = zzDD;
    }
    
    public void zziD(final int zzaUM) {
        this.zzaUM = zzaUM;
    }
    
    public void zziE(final int zzaUN) {
        this.zzaUN = zzaUN;
    }
}
