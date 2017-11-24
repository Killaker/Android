package com.google.android.gms.flags.impl;

import java.util.concurrent.*;
import android.content.*;

static final class zzb$1 implements Callable<SharedPreferences> {
    final /* synthetic */ Context zzxh;
    
    public SharedPreferences zzvw() {
        return this.zzxh.getSharedPreferences("google_sdk_flags", 1);
    }
}