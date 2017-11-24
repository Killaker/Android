package com.google.android.gms.analytics.internal;

import java.util.*;
import android.app.*;

public class zzal implements zzp
{
    public String zzOV;
    public double zzTo;
    public int zzTp;
    public int zzTq;
    public int zzTr;
    public int zzTs;
    public Map<String, String> zzTt;
    
    public zzal() {
        this.zzTo = -1.0;
        this.zzTp = -1;
        this.zzTq = -1;
        this.zzTr = -1;
        this.zzTs = -1;
        this.zzTt = new HashMap<String, String>();
    }
    
    public int getSessionTimeout() {
        return this.zzTp;
    }
    
    public String getTrackingId() {
        return this.zzOV;
    }
    
    public String zzbr(final String s) {
        final String s2 = this.zzTt.get(s);
        if (s2 != null) {
            return s2;
        }
        return s;
    }
    
    public boolean zzlT() {
        return this.zzOV != null;
    }
    
    public boolean zzlU() {
        return this.zzTo >= 0.0;
    }
    
    public double zzlV() {
        return this.zzTo;
    }
    
    public boolean zzlW() {
        return this.zzTp >= 0;
    }
    
    public boolean zzlX() {
        return this.zzTq != -1;
    }
    
    public boolean zzlY() {
        return this.zzTq == 1;
    }
    
    public boolean zzlZ() {
        return this.zzTr != -1;
    }
    
    public boolean zzma() {
        return this.zzTr == 1;
    }
    
    public boolean zzmb() {
        return this.zzTs == 1;
    }
    
    public String zzo(final Activity activity) {
        return this.zzbr(activity.getClass().getCanonicalName());
    }
}
