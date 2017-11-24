package com.google.android.gms.common.server.converter;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;

public class zzb implements Parcelable$Creator<StringToIntConverter>
{
    static void zza(final StringToIntConverter stringToIntConverter, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, stringToIntConverter.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, stringToIntConverter.zzri(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public StringToIntConverter zzay(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        ArrayList<StringToIntConverter.Entry> zzc = null;
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
                    zzc = zza.zzc(parcel, zzat, (android.os.Parcelable$Creator<StringToIntConverter.Entry>)StringToIntConverter.Entry.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new StringToIntConverter(zzg, zzc);
    }
    
    public StringToIntConverter[] zzce(final int n) {
        return new StringToIntConverter[n];
    }
}
