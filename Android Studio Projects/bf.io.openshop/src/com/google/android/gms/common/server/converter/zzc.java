package com.google.android.gms.common.server.converter;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzc implements Parcelable$Creator<StringToIntConverter.Entry>
{
    static void zza(final StringToIntConverter.Entry entry, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, entry.versionCode);
        zzb.zza(parcel, 2, entry.zzamJ, false);
        zzb.zzc(parcel, 3, entry.zzamK);
        zzb.zzI(parcel, zzav);
    }
    
    public StringToIntConverter.Entry zzaz(final Parcel parcel) {
        int zzg = 0;
        final int zzau = zza.zzau(parcel);
        String zzp = null;
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
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new StringToIntConverter.Entry(zzg2, zzp, zzg);
    }
    
    public StringToIntConverter.Entry[] zzcf(final int n) {
        return new StringToIntConverter.Entry[n];
    }
}
