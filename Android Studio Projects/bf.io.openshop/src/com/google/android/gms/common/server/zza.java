package com.google.android.gms.common.server;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zza implements Parcelable$Creator<FavaDiagnosticsEntity>
{
    static void zza(final FavaDiagnosticsEntity favaDiagnosticsEntity, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, favaDiagnosticsEntity.mVersionCode);
        zzb.zza(parcel, 2, favaDiagnosticsEntity.zzamD, false);
        zzb.zzc(parcel, 3, favaDiagnosticsEntity.zzamE);
        zzb.zzI(parcel, zzav);
    }
    
    public FavaDiagnosticsEntity zzaw(final Parcel parcel) {
        int zzg = 0;
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        String zzp = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new FavaDiagnosticsEntity(zzg2, zzp, zzg);
    }
    
    public FavaDiagnosticsEntity[] zzcc(final int n) {
        return new FavaDiagnosticsEntity[n];
    }
}
