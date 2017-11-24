package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zze implements Parcelable$Creator<LatLng>
{
    static void zza(final LatLng latLng, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, latLng.getVersionCode());
        zzb.zza(parcel, 2, latLng.latitude);
        zzb.zza(parcel, 3, latLng.longitude);
        zzb.zzI(parcel, zzav);
    }
    
    public LatLng zzfz(final Parcel parcel) {
        double zzn = 0.0;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        double zzn2 = zzn;
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
                    zzn2 = zza.zzn(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzn = zza.zzn(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new LatLng(zzg, zzn2, zzn);
    }
    
    public LatLng[] zzin(final int n) {
        return new LatLng[n];
    }
}
