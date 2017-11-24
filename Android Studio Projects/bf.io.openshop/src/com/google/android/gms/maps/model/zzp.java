package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzp implements Parcelable$Creator<VisibleRegion>
{
    static void zza(final VisibleRegion visibleRegion, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, visibleRegion.getVersionCode());
        zzb.zza(parcel, 2, (Parcelable)visibleRegion.nearLeft, n, false);
        zzb.zza(parcel, 3, (Parcelable)visibleRegion.nearRight, n, false);
        zzb.zza(parcel, 4, (Parcelable)visibleRegion.farLeft, n, false);
        zzb.zza(parcel, 5, (Parcelable)visibleRegion.farRight, n, false);
        zzb.zza(parcel, 6, (Parcelable)visibleRegion.latLngBounds, n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public VisibleRegion zzfK(final Parcel parcel) {
        LatLngBounds latLngBounds = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
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
                    latLng4 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    latLng3 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 4: {
                    latLng2 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 5: {
                    latLng = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 6: {
                    latLngBounds = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLngBounds>)LatLngBounds.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new VisibleRegion(zzg, latLng4, latLng3, latLng2, latLng, latLngBounds);
    }
    
    public VisibleRegion[] zziy(final int n) {
        return new VisibleRegion[n];
    }
}
