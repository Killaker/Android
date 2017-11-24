package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class zzae implements Parcelable$Creator<ValidateAccountRequest>
{
    static void zza(final ValidateAccountRequest validateAccountRequest, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, validateAccountRequest.mVersionCode);
        zzb.zzc(parcel, 2, validateAccountRequest.zzre());
        zzb.zza(parcel, 3, validateAccountRequest.zzakA, false);
        zzb.zza(parcel, 4, validateAccountRequest.zzrd(), n, false);
        zzb.zza(parcel, 5, validateAccountRequest.zzrf(), false);
        zzb.zza(parcel, 6, validateAccountRequest.getCallingPackage(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public ValidateAccountRequest zzas(final Parcel parcel) {
        int zzg = 0;
        String zzp = null;
        final int zzau = zza.zzau(parcel);
        Bundle zzr = null;
        Scope[] array = null;
        IBinder zzq = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzq = zza.zzq(parcel, zzat);
                    continue;
                }
                case 4: {
                    array = zza.zzb(parcel, zzat, Scope.CREATOR);
                    continue;
                }
                case 5: {
                    zzr = zza.zzr(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new ValidateAccountRequest(zzg2, zzg, zzq, array, zzr, zzp);
    }
    
    public ValidateAccountRequest[] zzbZ(final int n) {
        return new ValidateAccountRequest[n];
    }
}
