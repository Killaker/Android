package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.safeparcel.*;
import android.database.*;
import android.os.*;

public class zze implements Parcelable$Creator<DataHolder>
{
    static void zza(final DataHolder dataHolder, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, dataHolder.zzqe(), false);
        zzb.zzc(parcel, 1000, dataHolder.getVersionCode());
        zzb.zza(parcel, 2, dataHolder.zzqf(), n, false);
        zzb.zzc(parcel, 3, dataHolder.getStatusCode());
        zzb.zza(parcel, 4, dataHolder.zzpZ(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public DataHolder zzak(final Parcel parcel) {
        int zzg = 0;
        Bundle zzr = null;
        final int zzau = zza.zzau(parcel);
        CursorWindow[] array = null;
        String[] zzB = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzB = zza.zzB(parcel, zzat);
                    continue;
                }
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    array = zza.zzb(parcel, zzat, (android.os.Parcelable$Creator<CursorWindow>)CursorWindow.CREATOR);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzr = zza.zzr(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        final DataHolder dataHolder = new DataHolder(zzg2, zzB, array, zzg, zzr);
        dataHolder.zzqd();
        return dataHolder;
    }
    
    public DataHolder[] zzbJ(final int n) {
        return new DataHolder[n];
    }
}
