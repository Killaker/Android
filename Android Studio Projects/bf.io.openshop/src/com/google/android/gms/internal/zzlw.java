package com.google.android.gms.internal;

import android.content.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.clearcut.*;
import android.os.*;

public class zzlw extends zzj<zzly>
{
    public zzlw(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzf zzf, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 40, zzf, connectionCallbacks, onConnectionFailedListener);
    }
    
    public void zza(final zzlx zzlx, final LogEventParcelable logEventParcelable) throws RemoteException {
        this.zzqJ().zza(zzlx, logEventParcelable);
    }
    
    protected zzly zzaK(final IBinder binder) {
        return zzly.zza.zzaM(binder);
    }
    
    @Override
    protected String zzgu() {
        return "com.google.android.gms.clearcut.service.START";
    }
    
    @Override
    protected String zzgv() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }
}
