package com.google.android.gms.common.server.response;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzb implements Parcelable$Creator<FieldMappingDictionary.FieldMapPair>
{
    static void zza(final FieldMappingDictionary.FieldMapPair fieldMapPair, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, fieldMapPair.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, fieldMapPair.key, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable)fieldMapPair.zzamZ, n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public FieldMappingDictionary.FieldMapPair zzaB(final Parcel parcel) {
        SafeParcelable safeParcelable = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        String zzp = null;
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
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    safeParcelable = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<FastJsonResponse.Field<?, ?>>)FastJsonResponse.Field.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new FieldMappingDictionary.FieldMapPair(zzg, zzp, (FastJsonResponse.Field<?, ?>)safeParcelable);
    }
    
    public FieldMappingDictionary.FieldMapPair[] zzch(final int n) {
        return new FieldMappingDictionary.FieldMapPair[n];
    }
}
