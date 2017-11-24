package com.google.android.gms.internal;

import android.content.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.signin.internal.*;
import android.os.*;

public final class zzrl
{
    public static final Api<zzro> API;
    public static final Api.zzc<zzh> zzUI;
    public static final Api.zza<zzh, zzro> zzUJ;
    public static final Scope zzWW;
    public static final Scope zzWX;
    public static final Api<zza> zzaoG;
    public static final Api.zzc<zzh> zzavN;
    static final Api.zza<zzh, zza> zzbgS;
    public static final zzrm zzbgT;
    
    static {
        zzUI = new Api.zzc();
        zzavN = new Api.zzc();
        zzUJ = new Api.zza<zzh, zzro>() {
            public zzh zza(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzf zzf, final zzro zzro, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                zzro zzbgV;
                if (zzro == null) {
                    zzbgV = zzro.zzbgV;
                }
                else {
                    zzbgV = zzro;
                }
                return new zzh(context, looper, true, zzf, zzbgV, connectionCallbacks, onConnectionFailedListener);
            }
        };
        zzbgS = new Api.zza<zzh, zza>() {
            public zzh zza(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzf zzf, final zzrl.zza zza, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new zzh(context, looper, false, zzf, zza.zzFF(), connectionCallbacks, onConnectionFailedListener);
            }
        };
        zzWW = new Scope("profile");
        zzWX = new Scope("email");
        API = new Api<zzro>("SignIn.API", (Api.zza<C, zzro>)zzrl.zzUJ, (Api.zzc<C>)zzrl.zzUI);
        zzaoG = new Api<zza>("SignIn.INTERNAL_API", (Api.zza<C, zza>)zzrl.zzbgS, (Api.zzc<C>)zzrl.zzavN);
        zzbgT = new zzg();
    }
    
    public static class zza implements HasOptions
    {
        private final Bundle zzbgU;
        
        public Bundle zzFF() {
            return this.zzbgU;
        }
    }
}
