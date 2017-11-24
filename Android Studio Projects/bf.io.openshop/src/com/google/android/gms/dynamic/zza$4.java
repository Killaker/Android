package com.google.android.gms.dynamic;

import android.os.*;
import android.widget.*;
import android.view.*;

class zza$4 implements zza {
    final /* synthetic */ Bundle zzavD;
    final /* synthetic */ FrameLayout zzavE;
    final /* synthetic */ LayoutInflater zzavF;
    final /* synthetic */ ViewGroup zzavG;
    
    @Override
    public int getState() {
        return 2;
    }
    
    @Override
    public void zzb(final LifecycleDelegate lifecycleDelegate) {
        this.zzavE.removeAllViews();
        this.zzavE.addView(zza.zzb(zza.this).onCreateView(this.zzavF, this.zzavG, this.zzavD));
    }
}