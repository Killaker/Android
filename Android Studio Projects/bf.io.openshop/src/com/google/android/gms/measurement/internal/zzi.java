package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.*;

class zzi
{
    final String mName;
    final String zzaUa;
    final long zzaVP;
    final long zzaVQ;
    final long zzaVR;
    
    zzi(final String zzaUa, final String mName, final long zzaVP, final long zzaVQ, final long zzaVR) {
        boolean b = true;
        zzx.zzcM(zzaUa);
        zzx.zzcM(mName);
        zzx.zzac(zzaVP >= 0L && b);
        if (zzaVQ < 0L) {
            b = false;
        }
        zzx.zzac(b);
        this.zzaUa = zzaUa;
        this.mName = mName;
        this.zzaVP = zzaVP;
        this.zzaVQ = zzaVQ;
        this.zzaVR = zzaVR;
    }
    
    zzi zzCB() {
        return new zzi(this.zzaUa, this.mName, 1L + this.zzaVP, 1L + this.zzaVQ, this.zzaVR);
    }
    
    zzi zzab(final long n) {
        return new zzi(this.zzaUa, this.mName, this.zzaVP, this.zzaVQ, n);
    }
}
