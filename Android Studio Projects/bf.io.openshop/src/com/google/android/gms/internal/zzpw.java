package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import com.google.android.gms.common.internal.*;
import android.util.*;
import java.util.*;
import android.text.*;

public final class zzpw extends zze<zzpw>
{
    private String zzaUQ;
    private int zzaUR;
    private int zzaUS;
    private String zzaUT;
    private String zzaUU;
    private boolean zzaUV;
    private boolean zzaUW;
    private boolean zzaUX;
    
    public zzpw() {
        this(false);
    }
    
    public zzpw(final boolean b) {
        this(b, zzBb());
    }
    
    public zzpw(final boolean zzaUW, final int zzaUR) {
        zzx.zzbV(zzaUR);
        this.zzaUR = zzaUR;
        this.zzaUW = zzaUW;
    }
    
    static int zzBb() {
        final UUID randomUUID = UUID.randomUUID();
        int n = (int)(0x7FFFFFFFL & randomUUID.getLeastSignificantBits());
        if (n == 0) {
            n = (int)(0x7FFFFFFFL & randomUUID.getMostSignificantBits());
            if (n == 0) {
                Log.e("GAv4", "UUID.randomUUID() returned 0.");
                return Integer.MAX_VALUE;
            }
        }
        return n;
    }
    
    private void zzBf() {
        if (this.zzaUX) {
            throw new IllegalStateException("ScreenViewInfo is immutable");
        }
    }
    
    public void setScreenName(final String zzaUQ) {
        this.zzBf();
        this.zzaUQ = zzaUQ;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("screenName", this.zzaUQ);
        hashMap.put("interstitial", (String)this.zzaUV);
        hashMap.put("automatic", (String)this.zzaUW);
        hashMap.put("screenId", (String)this.zzaUR);
        hashMap.put("referrerScreenId", (String)this.zzaUS);
        hashMap.put("referrerScreenName", this.zzaUT);
        hashMap.put("referrerUri", this.zzaUU);
        return zze.zzF(hashMap);
    }
    
    public String zzBc() {
        return this.zzaUQ;
    }
    
    public int zzBd() {
        return this.zzaUR;
    }
    
    public String zzBe() {
        return this.zzaUU;
    }
    
    @Override
    public void zza(final zzpw zzpw) {
        if (!TextUtils.isEmpty((CharSequence)this.zzaUQ)) {
            zzpw.setScreenName(this.zzaUQ);
        }
        if (this.zzaUR != 0) {
            zzpw.zziF(this.zzaUR);
        }
        if (this.zzaUS != 0) {
            zzpw.zziG(this.zzaUS);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUT)) {
            zzpw.zzeH(this.zzaUT);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUU)) {
            zzpw.zzeI(this.zzaUU);
        }
        if (this.zzaUV) {
            zzpw.zzaq(this.zzaUV);
        }
        if (this.zzaUW) {
            zzpw.zzap(this.zzaUW);
        }
    }
    
    public void zzap(final boolean zzaUW) {
        this.zzBf();
        this.zzaUW = zzaUW;
    }
    
    public void zzaq(final boolean zzaUV) {
        this.zzBf();
        this.zzaUV = zzaUV;
    }
    
    public void zzeH(final String zzaUT) {
        this.zzBf();
        this.zzaUT = zzaUT;
    }
    
    public void zzeI(final String zzaUU) {
        this.zzBf();
        if (TextUtils.isEmpty((CharSequence)zzaUU)) {
            this.zzaUU = null;
            return;
        }
        this.zzaUU = zzaUU;
    }
    
    public void zziF(final int zzaUR) {
        this.zzBf();
        this.zzaUR = zzaUR;
    }
    
    public void zziG(final int zzaUS) {
        this.zzBf();
        this.zzaUS = zzaUS;
    }
}
