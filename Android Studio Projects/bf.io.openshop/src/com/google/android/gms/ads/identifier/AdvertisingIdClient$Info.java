package com.google.android.gms.ads.identifier;

public static final class Info
{
    private final String zzpc;
    private final boolean zzpd;
    
    public Info(final String zzpc, final boolean zzpd) {
        this.zzpc = zzpc;
        this.zzpd = zzpd;
    }
    
    public String getId() {
        return this.zzpc;
    }
    
    public boolean isLimitAdTrackingEnabled() {
        return this.zzpd;
    }
    
    @Override
    public String toString() {
        return "{" + this.zzpc + "}" + this.zzpd;
    }
}
