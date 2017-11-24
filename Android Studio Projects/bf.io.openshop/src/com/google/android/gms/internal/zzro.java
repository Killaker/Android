package com.google.android.gms.internal;

import com.google.android.gms.common.api.*;
import android.support.annotation.*;

public final class zzro implements Optional
{
    public static final zzro zzbgV;
    private final boolean zzXa;
    private final boolean zzXc;
    private final String zzXd;
    private final String zzXe;
    private final boolean zzbgW;
    private final boolean zzbgX;
    
    static {
        zzbgV = new zza().zzFJ();
    }
    
    private zzro(final boolean zzbgW, final boolean zzXa, final String zzXd, final boolean zzXc, final String zzXe, final boolean zzbgX) {
        this.zzbgW = zzbgW;
        this.zzXa = zzXa;
        this.zzXd = zzXd;
        this.zzXc = zzXc;
        this.zzbgX = zzbgX;
        this.zzXe = zzXe;
    }
    
    public boolean zzFH() {
        return this.zzbgW;
    }
    
    public boolean zzFI() {
        return this.zzbgX;
    }
    
    public boolean zzmO() {
        return this.zzXa;
    }
    
    public boolean zzmQ() {
        return this.zzXc;
    }
    
    public String zzmR() {
        return this.zzXd;
    }
    
    @Nullable
    public String zzmS() {
        return this.zzXe;
    }
    
    public static final class zza
    {
        private String zzbdY;
        private boolean zzbgY;
        private boolean zzbgZ;
        private boolean zzbha;
        private String zzbhb;
        private boolean zzbhc;
        
        public zzro zzFJ() {
            return new zzro(this.zzbgY, this.zzbgZ, this.zzbdY, this.zzbha, this.zzbhb, this.zzbhc, null);
        }
    }
}
