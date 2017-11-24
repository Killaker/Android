package com.google.android.gms.dynamic;

import android.content.*;
import android.view.*;
import com.google.android.gms.common.*;

static final class zza$5 implements View$OnClickListener {
    final /* synthetic */ int zzAe;
    final /* synthetic */ Context zzxh;
    
    public void onClick(final View view) {
        this.zzxh.startActivity(GooglePlayServicesUtil.zzbv(this.zzAe));
    }
}