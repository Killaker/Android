package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import java.util.*;
import android.text.*;

public final class zzpr extends zze<zzpr>
{
    private String mName;
    private String zzaPI;
    private String zzaUF;
    private String zzaUG;
    private String zzaUH;
    private String zzaUI;
    private String zzaUJ;
    private String zzaUK;
    private String zzxG;
    private String zzyv;
    
    public String getContent() {
        return this.zzxG;
    }
    
    public String getId() {
        return this.zzyv;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getSource() {
        return this.zzaPI;
    }
    
    public void setName(final String mName) {
        this.mName = mName;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", this.mName);
        hashMap.put("source", this.zzaPI);
        hashMap.put("medium", this.zzaUF);
        hashMap.put("keyword", this.zzaUG);
        hashMap.put("content", this.zzxG);
        hashMap.put("id", this.zzyv);
        hashMap.put("adNetworkId", this.zzaUH);
        hashMap.put("gclid", this.zzaUI);
        hashMap.put("dclid", this.zzaUJ);
        hashMap.put("aclid", this.zzaUK);
        return zze.zzF(hashMap);
    }
    
    public String zzAK() {
        return this.zzaUF;
    }
    
    public String zzAL() {
        return this.zzaUG;
    }
    
    public String zzAM() {
        return this.zzaUH;
    }
    
    public String zzAN() {
        return this.zzaUI;
    }
    
    public String zzAO() {
        return this.zzaUJ;
    }
    
    public String zzAP() {
        return this.zzaUK;
    }
    
    @Override
    public void zza(final zzpr zzpr) {
        if (!TextUtils.isEmpty((CharSequence)this.mName)) {
            zzpr.setName(this.mName);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaPI)) {
            zzpr.zzev(this.zzaPI);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUF)) {
            zzpr.zzew(this.zzaUF);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUG)) {
            zzpr.zzex(this.zzaUG);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzxG)) {
            zzpr.zzey(this.zzxG);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzyv)) {
            zzpr.zzez(this.zzyv);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUH)) {
            zzpr.zzeA(this.zzaUH);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUI)) {
            zzpr.zzeB(this.zzaUI);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUJ)) {
            zzpr.zzeC(this.zzaUJ);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUK)) {
            zzpr.zzeD(this.zzaUK);
        }
    }
    
    public void zzeA(final String zzaUH) {
        this.zzaUH = zzaUH;
    }
    
    public void zzeB(final String zzaUI) {
        this.zzaUI = zzaUI;
    }
    
    public void zzeC(final String zzaUJ) {
        this.zzaUJ = zzaUJ;
    }
    
    public void zzeD(final String zzaUK) {
        this.zzaUK = zzaUK;
    }
    
    public void zzev(final String zzaPI) {
        this.zzaPI = zzaPI;
    }
    
    public void zzew(final String zzaUF) {
        this.zzaUF = zzaUF;
    }
    
    public void zzex(final String zzaUG) {
        this.zzaUG = zzaUG;
    }
    
    public void zzey(final String zzxG) {
        this.zzxG = zzxG;
    }
    
    public void zzez(final String zzyv) {
        this.zzyv = zzyv;
    }
}
