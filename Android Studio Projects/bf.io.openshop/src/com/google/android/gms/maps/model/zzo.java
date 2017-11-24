package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class zzo implements Parcelable$Creator<TileOverlayOptions>
{
    static void zza(final TileOverlayOptions tileOverlayOptions, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, tileOverlayOptions.getVersionCode());
        zzb.zza(parcel, 2, tileOverlayOptions.zzAm(), false);
        zzb.zza(parcel, 3, tileOverlayOptions.isVisible());
        zzb.zza(parcel, 4, tileOverlayOptions.getZIndex());
        zzb.zza(parcel, 5, tileOverlayOptions.getFadeIn());
        zzb.zzI(parcel, zzav);
    }
    
    public TileOverlayOptions zzfJ(final Parcel parcel) {
        boolean zzc = false;
        final int zzau = zza.zzau(parcel);
        IBinder zzq = null;
        float zzl = 0.0f;
        boolean zzc2 = true;
        int zzg = 0;
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
                    zzq = zza.zzq(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzl = zza.zzl(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzc2 = zza.zzc(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new TileOverlayOptions(zzg, zzq, zzc, zzl, zzc2);
    }
    
    public TileOverlayOptions[] zzix(final int n) {
        return new TileOverlayOptions[n];
    }
}
