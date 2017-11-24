package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class zza implements Parcelable$Creator<CameraPosition>
{
    static void zza(final CameraPosition cameraPosition, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, cameraPosition.getVersionCode());
        zzb.zza(parcel, 2, (Parcelable)cameraPosition.target, n, false);
        zzb.zza(parcel, 3, cameraPosition.zoom);
        zzb.zza(parcel, 4, cameraPosition.tilt);
        zzb.zza(parcel, 5, cameraPosition.bearing);
        zzb.zzI(parcel, zzav);
    }
    
    public CameraPosition zzfv(final Parcel parcel) {
        float zzl = 0.0f;
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        int zzg = 0;
        LatLng latLng = null;
        float zzl2 = 0.0f;
        float zzl3 = 0.0f;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    latLng = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    zzl3 = com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzl2 = com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzl = com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new CameraPosition(zzg, latLng, zzl3, zzl2, zzl);
    }
    
    public CameraPosition[] zzij(final int n) {
        return new CameraPosition[n];
    }
}
