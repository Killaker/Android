package com.google.android.gms.signin.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import java.util.*;

public class zzc implements Parcelable$Creator<CheckServerAuthResult>
{
    static void zza(final CheckServerAuthResult checkServerAuthResult, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, checkServerAuthResult.mVersionCode);
        zzb.zza(parcel, 2, checkServerAuthResult.zzbhf);
        zzb.zzc(parcel, 3, checkServerAuthResult.zzbhg, false);
        zzb.zzI(parcel, zzav);
    }
    
    public CheckServerAuthResult zzgS(final Parcel parcel) {
        boolean zzc = false;
        final int zzau = zza.zzau(parcel);
        List<Scope> zzc2 = null;
        int zzg = 0;
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
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzc2 = zza.zzc(parcel, zzat, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new CheckServerAuthResult(zzg, zzc, zzc2);
    }
    
    public CheckServerAuthResult[] zzjZ(final int n) {
        return new CheckServerAuthResult[n];
    }
}
