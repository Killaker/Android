package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;

class zzs
{
    final boolean zzaWY;
    final int zzaWZ;
    long zzaXa;
    float zzaXb;
    long zzaXc;
    float zzaXd;
    long zzaXe;
    float zzaXf;
    final boolean zzaXg;
    
    public zzs(final zzpz.zzd zzd) {
        boolean zzaWY = true;
        zzx.zzz(zzd);
        final boolean zzaXg = zzd.zzaZF != null && zzd.zzaZF != 0 && ((zzd.zzaZF == 4) ? (zzd.zzaZI != null && zzd.zzaZJ != null) : (zzd.zzaZH != null)) && zzaWY;
        if (zzaXg) {
            this.zzaWZ = zzd.zzaZF;
            if (zzd.zzaZG == null || !zzd.zzaZG) {
                zzaWY = false;
            }
            this.zzaWY = zzaWY;
            if (zzd.zzaZF == 4) {
                if (this.zzaWY) {
                    this.zzaXd = Float.parseFloat(zzd.zzaZI);
                    this.zzaXf = Float.parseFloat(zzd.zzaZJ);
                }
                else {
                    this.zzaXc = Long.parseLong(zzd.zzaZI);
                    this.zzaXe = Long.parseLong(zzd.zzaZJ);
                }
            }
            else if (this.zzaWY) {
                this.zzaXb = Float.parseFloat(zzd.zzaZH);
            }
            else {
                this.zzaXa = Long.parseLong(zzd.zzaZH);
            }
        }
        else {
            this.zzaWZ = 0;
            this.zzaWY = false;
        }
        this.zzaXg = zzaXg;
    }
    
    public Boolean zzac(final long n) {
        boolean b = true;
        if (!this.zzaXg) {
            return null;
        }
        if (this.zzaWY) {
            return null;
        }
        switch (this.zzaWZ) {
            default: {
                return null;
            }
            case 1: {
                if (n >= this.zzaXa) {
                    b = false;
                }
                return b;
            }
            case 2: {
                if (n <= this.zzaXa) {
                    b = false;
                }
                return b;
            }
            case 3: {
                if (n != this.zzaXa) {
                    b = false;
                }
                return b;
            }
            case 4: {
                if (n < this.zzaXc || n > this.zzaXe) {
                    b = false;
                }
                return b;
            }
        }
    }
    
    public Boolean zzi(final float n) {
        boolean b = true;
        if (!this.zzaXg) {
            return null;
        }
        if (!this.zzaWY) {
            return null;
        }
        switch (this.zzaWZ) {
            default: {
                return null;
            }
            case 1: {
                if (n >= this.zzaXb) {
                    b = false;
                }
                return b;
            }
            case 2: {
                if (n <= this.zzaXb) {
                    b = false;
                }
                return b;
            }
            case 3: {
                if (n != this.zzaXb) {
                    final float n2 = fcmpg(Math.abs(n - this.zzaXb), 2.0f * Math.max(Math.ulp(n), Math.ulp(this.zzaXb)));
                    final boolean b2 = false;
                    if (n2 >= 0) {
                        return b2;
                    }
                }
                return b;
            }
            case 4: {
                if (n < this.zzaXd || n > this.zzaXf) {
                    b = false;
                }
                return b;
            }
        }
    }
}
