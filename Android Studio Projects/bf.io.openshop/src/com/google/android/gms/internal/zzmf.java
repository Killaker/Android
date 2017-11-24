package com.google.android.gms.internal;

import android.content.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.api.*;

public final class zzmf
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.zzc<zzmj> zzUI;
    private static final Api.zza<zzmj, Api.ApiOptions.NoOptions> zzUJ;
    public static final zzmg zzamA;
    
    static {
        zzUI = new Api.zzc();
        zzUJ = new Api.zza<zzmj, Api.ApiOptions.NoOptions>() {
            public zzmj zzf(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzf zzf, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new zzmj(context, looper, zzf, connectionCallbacks, onConnectionFailedListener);
            }
        };
        API = new Api<Api.ApiOptions.NoOptions>("Common.API", (Api.zza<C, Api.ApiOptions.NoOptions>)zzmf.zzUJ, (Api.zzc<C>)zzmf.zzUI);
        zzamA = new zzmh();
    }
}
