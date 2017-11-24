package com.google.android.gms.measurement.internal;

import android.support.annotation.*;
import com.google.android.gms.common.internal.*;

@WorkerThread
private static class zzb implements Runnable
{
    private final int zzBc;
    private final String zzTJ;
    private final zza zzaWP;
    private final Throwable zzaWQ;
    private final byte[] zzaWR;
    
    private zzb(final String zzTJ, final zza zzaWP, final int zzBc, final Throwable zzaWQ, final byte[] zzaWR) {
        zzx.zzz(zzaWP);
        this.zzaWP = zzaWP;
        this.zzBc = zzBc;
        this.zzaWQ = zzaWQ;
        this.zzaWR = zzaWR;
        this.zzTJ = zzTJ;
    }
    
    @Override
    public void run() {
        this.zzaWP.zza(this.zzTJ, this.zzBc, this.zzaWQ, this.zzaWR);
    }
}
