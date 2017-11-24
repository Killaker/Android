package com.google.android.gms.common.server.response;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;

public class zzc implements Parcelable$Creator<FieldMappingDictionary>
{
    static void zza(final FieldMappingDictionary fieldMappingDictionary, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, fieldMappingDictionary.getVersionCode());
        zzb.zzc(parcel, 2, fieldMappingDictionary.zzrA(), false);
        zzb.zza(parcel, 3, fieldMappingDictionary.zzrB(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public FieldMappingDictionary zzaC(final Parcel parcel) {
        String zzp = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        ArrayList<FieldMappingDictionary.Entry> zzc = null;
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
                    zzc = zza.zzc(parcel, zzat, (android.os.Parcelable$Creator<FieldMappingDictionary.Entry>)FieldMappingDictionary.Entry.CREATOR);
                    continue;
                }
                case 3: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new FieldMappingDictionary(zzg, zzc, zzp);
    }
    
    public FieldMappingDictionary[] zzci(final int n) {
        return new FieldMappingDictionary[n];
    }
}
