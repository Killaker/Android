package com.google.android.gms.internal;

import android.content.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class zzmj extends zzj<zzml>
{
    public zzmj(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzf zzf, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 39, zzf, connectionCallbacks, onConnectionFailedListener);
    }
    
    protected zzml zzaW(final IBinder binder) {
        return zzml.zza.zzaY(binder);
    }
    
    public String zzgu() {
        return "com.google.android.gms.common.service.START";
    }
    
    @Override
    protected String zzgv() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }
}
