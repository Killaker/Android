package com.google.android.gms.common.server.response;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zze implements Parcelable$Creator<SafeParcelResponse>
{
    static void zza(final SafeParcelResponse safeParcelResponse, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, safeParcelResponse.getVersionCode());
        zzb.zza(parcel, 2, safeParcelResponse.zzrD(), false);
        zzb.zza(parcel, 3, (Parcelable)safeParcelResponse.zzrE(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public SafeParcelResponse zzaE(final Parcel parcel) {
        FieldMappingDictionary fieldMappingDictionary = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        Parcel zzE = null;
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
                    zzE = zza.zzE(parcel, zzat);
                    continue;
                }
                case 3: {
                    fieldMappingDictionary = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<FieldMappingDictionary>)FieldMappingDictionary.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new SafeParcelResponse(zzg, zzE, fieldMappingDictionary);
    }
    
    public SafeParcelResponse[] zzck(final int n) {
        return new SafeParcelResponse[n];
    }
}
