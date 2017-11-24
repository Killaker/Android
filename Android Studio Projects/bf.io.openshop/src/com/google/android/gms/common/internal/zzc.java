package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class zzc implements Parcelable$Creator<AuthAccountRequest>
{
    static void zza(final AuthAccountRequest authAccountRequest, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, authAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, authAccountRequest.zzakA, false);
        zzb.zza(parcel, 3, authAccountRequest.zzafT, n, false);
        zzb.zza(parcel, 4, authAccountRequest.zzakB, false);
        zzb.zza(parcel, 5, authAccountRequest.zzakC, false);
        zzb.zzI(parcel, zzav);
    }
    
    public AuthAccountRequest zzam(final Parcel parcel) {
        Integer zzh = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        Integer zzh2 = null;
        Scope[] array = null;
        IBinder zzq = null;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzq = zza.zzq(parcel, zzat);
                    continue;
                }
                case 3: {
                    array = zza.zzb(parcel, zzat, Scope.CREATOR);
                    continue;
                }
                case 4: {
                    zzh2 = zza.zzh(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzh = zza.zzh(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new AuthAccountRequest(zzg, zzq, array, zzh2, zzh);
    }
    
    public AuthAccountRequest[] zzbP(final int n) {
        return new AuthAccountRequest[n];
    }
}
