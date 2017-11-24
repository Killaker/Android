package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.*;
import android.os.*;

public class zzz implements Parcelable$Creator<ResolveAccountResponse>
{
    static void zza(final ResolveAccountResponse resolveAccountResponse, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, resolveAccountResponse.mVersionCode);
        zzb.zza(parcel, 2, resolveAccountResponse.zzakA, false);
        zzb.zza(parcel, 3, (Parcelable)resolveAccountResponse.zzqY(), n, false);
        zzb.zza(parcel, 4, resolveAccountResponse.zzqZ());
        zzb.zza(parcel, 5, resolveAccountResponse.zzra());
        zzb.zzI(parcel, zzav);
    }
    
    public ResolveAccountResponse zzaq(final Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean zzc = false;
        final int zzau = zza.zzau(parcel);
        boolean zzc2 = false;
        IBinder zzq = null;
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
                    zzq = zza.zzq(parcel, zzat);
                    continue;
                }
                case 3: {
                    connectionResult = zza.zza(parcel, zzat, ConnectionResult.CREATOR);
                    continue;
                }
                case 4: {
                    zzc2 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new ResolveAccountResponse(zzg, zzq, connectionResult, zzc2, zzc);
    }
    
    public ResolveAccountResponse[] zzbX(final int n) {
        return new ResolveAccountResponse[n];
    }
}
