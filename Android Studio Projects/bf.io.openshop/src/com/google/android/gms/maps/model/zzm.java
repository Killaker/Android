package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzm implements Parcelable$Creator<StreetViewPanoramaOrientation>
{
    static void zza(final StreetViewPanoramaOrientation streetViewPanoramaOrientation, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, streetViewPanoramaOrientation.getVersionCode());
        zzb.zza(parcel, 2, streetViewPanoramaOrientation.tilt);
        zzb.zza(parcel, 3, streetViewPanoramaOrientation.bearing);
        zzb.zzI(parcel, zzav);
    }
    
    public StreetViewPanoramaOrientation zzfH(final Parcel parcel) {
        float zzl = 0.0f;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        float zzl2 = 0.0f;
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
                    zzl = zza.zzl(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new StreetViewPanoramaOrientation(zzg, zzl2, zzl);
    }
    
    public StreetViewPanoramaOrientation[] zziv(final int n) {
        return new StreetViewPanoramaOrientation[n];
    }
}
