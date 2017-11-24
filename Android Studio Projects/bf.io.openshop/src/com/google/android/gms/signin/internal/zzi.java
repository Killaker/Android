package com.google.android.gms.signin.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;

public class zzi implements Parcelable$Creator<SignInRequest>
{
    static void zza(final SignInRequest signInRequest, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, signInRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)signInRequest.zzFO(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public SignInRequest zzgU(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        ResolveAccountRequest resolveAccountRequest = null;
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
                    resolveAccountRequest = zza.zza(parcel, zzat, ResolveAccountRequest.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new SignInRequest(zzg, resolveAccountRequest);
    }
    
    public SignInRequest[] zzkc(final int n) {
        return new SignInRequest[n];
    }
}
