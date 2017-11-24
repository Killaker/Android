package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;
import com.google.android.gms.common.server.converter.*;

public class zza implements Parcelable$Creator<FastJsonResponse.Field>
{
    static void zza(final FastJsonResponse.Field field, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, field.getVersionCode());
        zzb.zzc(parcel, 2, field.zzrj());
        zzb.zza(parcel, 3, field.zzrp());
        zzb.zzc(parcel, 4, field.zzrk());
        zzb.zza(parcel, 5, field.zzrq());
        zzb.zza(parcel, 6, field.zzrr(), false);
        zzb.zzc(parcel, 7, field.zzrs());
        zzb.zza(parcel, 8, field.zzru(), false);
        zzb.zza(parcel, 9, (Parcelable)field.zzrw(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public FastJsonResponse.Field zzaA(final Parcel parcel) {
        ConverterWrapper converterWrapper = null;
        int zzg = 0;
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        String zzp = null;
        String zzp2 = null;
        boolean zzc = false;
        int zzg2 = 0;
        boolean zzc2 = false;
        int zzg3 = 0;
        int zzg4 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg4 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzg3 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzc2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzp2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 9: {
                    converterWrapper = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, (android.os.Parcelable$Creator<ConverterWrapper>)ConverterWrapper.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new FastJsonResponse.Field(zzg4, zzg3, zzc2, zzg2, zzc, zzp2, zzg, zzp, converterWrapper);
    }
    
    public FastJsonResponse.Field[] zzcg(final int n) {
        return new FastJsonResponse.Field[n];
    }
}
