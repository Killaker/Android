package com.google.android.gms.flags.impl;

import java.util.concurrent.*;
import android.content.*;

static final class zza$zzb$1 implements Callable<Integer> {
    final /* synthetic */ SharedPreferences zzaBT;
    final /* synthetic */ String zzaBU;
    final /* synthetic */ Integer zzaBW;
    
    public Integer zzvu() {
        return this.zzaBT.getInt(this.zzaBU, (int)this.zzaBW);
    }
}