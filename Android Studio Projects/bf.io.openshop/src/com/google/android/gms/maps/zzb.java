package com.google.android.gms.maps;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.maps.model.*;

public class zzb implements Parcelable$Creator<StreetViewPanoramaOptions>
{
    static void zza(final StreetViewPanoramaOptions streetViewPanoramaOptions, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, streetViewPanoramaOptions.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable)streetViewPanoramaOptions.getStreetViewPanoramaCamera(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, streetViewPanoramaOptions.getPanoramaId(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, (Parcelable)streetViewPanoramaOptions.getPosition(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, streetViewPanoramaOptions.getRadius(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, streetViewPanoramaOptions.zzAa());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, streetViewPanoramaOptions.zzzP());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, streetViewPanoramaOptions.zzAb());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 9, streetViewPanoramaOptions.zzAc());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 10, streetViewPanoramaOptions.zzzL());
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public StreetViewPanoramaOptions zzfu(final Parcel parcel) {
        Integer zzh = null;
        byte zze = 0;
        final int zzau = zza.zzau(parcel);
        byte zze2 = 0;
        byte zze3 = 0;
        byte zze4 = 0;
        byte zze5 = 0;
        LatLng latLng = null;
        String zzp = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
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
                    streetViewPanoramaCamera = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<StreetViewPanoramaCamera>)StreetViewPanoramaCamera.CREATOR);
                    continue;
                }
                case 3: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    latLng = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 5: {
                    zzh = zza.zzh(parcel, zzat);
                    continue;
                }
                case 6: {
                    zze5 = zza.zze(parcel, zzat);
                    continue;
                }
                case 7: {
                    zze4 = zza.zze(parcel, zzat);
                    continue;
                }
                case 8: {
                    zze3 = zza.zze(parcel, zzat);
                    continue;
                }
                case 9: {
                    zze2 = zza.zze(parcel, zzat);
                    continue;
                }
                case 10: {
                    zze = zza.zze(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new StreetViewPanoramaOptions(zzg, streetViewPanoramaCamera, zzp, latLng, zzh, zze5, zze4, zze3, zze2, zze);
    }
    
    public StreetViewPanoramaOptions[] zzii(final int n) {
        return new StreetViewPanoramaOptions[n];
    }
}
