package com.google.android.gms.signin.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.*;
import com.google.android.gms.common.internal.*;

public class zzj implements Parcelable$Creator<SignInResponse>
{
    static void zza(final SignInResponse signInResponse, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, signInResponse.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)signInResponse.zzqY(), n, false);
        zzb.zza(parcel, 3, (Parcelable)signInResponse.zzFP(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public SignInResponse zzgV(final Parcel parcel) {
        ResolveAccountResponse resolveAccountResponse = null;
        final int zzau = zza.zzau(parcel);
        int n = 0;
        ConnectionResult connectionResult = null;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            ResolveAccountResponse resolveAccountResponse2 = null;
            ConnectionResult connectionResult2 = null;
            int n2 = 0;
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    resolveAccountResponse2 = resolveAccountResponse;
                    connectionResult2 = connectionResult;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzat);
                    final ResolveAccountResponse resolveAccountResponse3 = resolveAccountResponse;
                    connectionResult2 = connectionResult;
                    n2 = zzg;
                    resolveAccountResponse2 = resolveAccountResponse3;
                    break;
                }
                case 2: {
                    final ConnectionResult connectionResult3 = zza.zza(parcel, zzat, ConnectionResult.CREATOR);
                    n2 = n;
                    resolveAccountResponse2 = resolveAccountResponse;
                    connectionResult2 = connectionResult3;
                    break;
                }
                case 3: {
                    resolveAccountResponse2 = zza.zza(parcel, zzat, ResolveAccountResponse.CREATOR);
                    connectionResult2 = connectionResult;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            connectionResult = connectionResult2;
            resolveAccountResponse = resolveAccountResponse2;
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new SignInResponse(n, connectionResult, resolveAccountResponse);
    }
    
    public SignInResponse[] zzkd(final int n) {
        return new SignInResponse[n];
    }
}
