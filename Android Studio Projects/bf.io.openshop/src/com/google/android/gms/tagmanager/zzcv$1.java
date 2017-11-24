package com.google.android.gms.tagmanager;

import android.content.*;

static final class zzcv$1 implements Runnable {
    final /* synthetic */ SharedPreferences$Editor zzblc;
    
    @Override
    public void run() {
        this.zzblc.commit();
    }
}