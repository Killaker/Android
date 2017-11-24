package com.google.android.gms.flags.impl;

import java.util.concurrent.*;
import android.content.*;

static final class zza$zza$1 implements Callable<Boolean> {
    final /* synthetic */ SharedPreferences zzaBT;
    final /* synthetic */ String zzaBU;
    final /* synthetic */ Boolean zzaBV;
    
    public Boolean zzvt() {
        return this.zzaBT.getBoolean(this.zzaBU, (boolean)this.zzaBV);
    }
}