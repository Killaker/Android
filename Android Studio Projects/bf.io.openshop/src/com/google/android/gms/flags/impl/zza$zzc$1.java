package com.google.android.gms.flags.impl;

import java.util.concurrent.*;
import android.content.*;

static final class zza$zzc$1 implements Callable<Long> {
    final /* synthetic */ SharedPreferences zzaBT;
    final /* synthetic */ String zzaBU;
    final /* synthetic */ Long zzaBX;
    
    public Long zzvv() {
        return this.zzaBT.getLong(this.zzaBU, (long)this.zzaBX);
    }
}