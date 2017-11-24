package com.google.android.gms.flags.impl;

import java.util.concurrent.*;
import android.content.*;

static final class zza$zzd$1 implements Callable<String> {
    final /* synthetic */ SharedPreferences zzaBT;
    final /* synthetic */ String zzaBU;
    final /* synthetic */ String zzaBY;
    
    public String zzkp() {
        return this.zzaBT.getString(this.zzaBU, this.zzaBY);
    }
}