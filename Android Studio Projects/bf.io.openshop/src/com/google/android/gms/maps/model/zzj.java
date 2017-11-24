package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzj implements Parcelable$Creator<StreetViewPanoramaCamera>
{
    static void zza(final StreetViewPanoramaCamera streetViewPanoramaCamera, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, streetViewPanoramaCamera.getVersionCode());
        zzb.zza(parcel, 2, streetViewPanoramaCamera.zoom);
        zzb.zza(parcel, 3, streetViewPanoramaCamera.tilt);
        zzb.zza(parcel, 4, streetViewPanoramaCamera.bearing);
        zzb.zzI(parcel, zzav);
    }
    
    public StreetViewPanoramaCamera zzfE(final Parcel parcel) {
        float zzl = 0.0f;
        final int zzau = zza.zzau(parcel);
        float zzl2 = 0.0f;
        int zzg = 0;
        float zzl3 = 0.0f;
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
                    zzl2 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzl3 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzl = zza.zzl(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new StreetViewPanoramaCamera(zzg, zzl2, zzl3, zzl);
    }
    
    public StreetViewPanoramaCamera[] zzis(final int n) {
        return new StreetViewPanoramaCamera[n];
    }
}
