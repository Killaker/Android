package com.google.android.gms.common.server.converter;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class zza implements Parcelable$Creator<ConverterWrapper>
{
    static void zza(final ConverterWrapper converterWrapper, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, converterWrapper.getVersionCode());
        zzb.zza(parcel, 2, (Parcelable)converterWrapper.zzrg(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public ConverterWrapper zzax(final Parcel parcel) {
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        int zzg = 0;
        StringToIntConverter stringToIntConverter = null;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    stringToIntConverter = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, (android.os.Parcelable$Creator<StringToIntConverter>)StringToIntConverter.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new ConverterWrapper(zzg, stringToIntConverter);
    }
    
    public ConverterWrapper[] zzcd(final int n) {
        return new ConverterWrapper[n];
    }
}
