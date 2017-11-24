package com.google.android.gms.tagmanager;

import android.text.*;

class zzaq
{
    private final long zzSL;
    private final long zzbiX;
    private final long zzbiY;
    private String zzbiZ;
    
    zzaq(final long zzbiX, final long zzSL, final long zzbiY) {
        this.zzbiX = zzbiX;
        this.zzSL = zzSL;
        this.zzbiY = zzbiY;
    }
    
    long zzGD() {
        return this.zzbiX;
    }
    
    long zzGE() {
        return this.zzbiY;
    }
    
    String zzGF() {
        return this.zzbiZ;
    }
    
    void zzgf(final String zzbiZ) {
        if (zzbiZ == null || TextUtils.isEmpty((CharSequence)zzbiZ.trim())) {
            return;
        }
        this.zzbiZ = zzbiZ;
    }
}
