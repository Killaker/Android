package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzn implements Parcelable$Creator<Tile>
{
    static void zza(final Tile tile, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, tile.getVersionCode());
        zzb.zzc(parcel, 2, tile.width);
        zzb.zzc(parcel, 3, tile.height);
        zzb.zza(parcel, 4, tile.data, false);
        zzb.zzI(parcel, zzav);
    }
    
    public Tile zzfI(final Parcel parcel) {
        int zzg = 0;
        final int zzau = zza.zzau(parcel);
        byte[] zzs = null;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzs = zza.zzs(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new Tile(zzg3, zzg2, zzg, zzs);
    }
    
    public Tile[] zziw(final int n) {
        return new Tile[n];
    }
}
