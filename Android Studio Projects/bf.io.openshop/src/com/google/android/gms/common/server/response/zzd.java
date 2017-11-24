package com.google.android.gms.common.server.response;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;

public class zzd implements Parcelable$Creator<FieldMappingDictionary.Entry>
{
    static void zza(final FieldMappingDictionary.Entry entry, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, entry.versionCode);
        zzb.zza(parcel, 2, entry.className, false);
        zzb.zzc(parcel, 3, entry.zzamY, false);
        zzb.zzI(parcel, zzav);
    }
    
    public FieldMappingDictionary.Entry zzaD(final Parcel parcel) {
        ArrayList<FieldMappingDictionary.FieldMapPair> zzc = null;
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
                    zzc = zza.zzc(parcel, zzat, (android.os.Parcelable$Creator<FieldMappingDictionary.FieldMapPair>)FieldMappingDictionary.FieldMapPair.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new FieldMappingDictionary.Entry(zzg, zzp, zzc);
    }
    
    public FieldMappingDictionary.Entry[] zzcj(final int n) {
        return new FieldMappingDictionary.Entry[n];
    }
}
