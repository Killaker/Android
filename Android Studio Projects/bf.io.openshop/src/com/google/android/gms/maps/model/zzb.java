package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzb implements Parcelable$Creator<CircleOptions>
{
    static void zza(final CircleOptions circleOptions, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, circleOptions.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable)circleOptions.getCenter(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, circleOptions.getRadius());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, circleOptions.getStrokeWidth());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 5, circleOptions.getStrokeColor());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 6, circleOptions.getFillColor());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, circleOptions.getZIndex());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, circleOptions.isVisible());
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public CircleOptions zzfw(final Parcel parcel) {
        float zzl = 0.0f;
        boolean zzc = false;
        final int zzau = zza.zzau(parcel);
        LatLng latLng = null;
        double zzn = 0.0;
        int zzg = 0;
        int zzg2 = 0;
        float zzl2 = 0.0f;
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
                    latLng = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    zzn = zza.zzn(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzl2 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzl = zza.zzl(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new CircleOptions(zzg3, latLng, zzn, zzl2, zzg2, zzg, zzl, zzc);
    }
    
    public CircleOptions[] zzik(final int n) {
        return new CircleOptions[n];
    }
}
