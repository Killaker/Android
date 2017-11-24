package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzd implements Parcelable$Creator<LatLngBounds>
{
    static void zza(final LatLngBounds latLngBounds, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, latLngBounds.getVersionCode());
        zzb.zza(parcel, 2, (Parcelable)latLngBounds.southwest, n, false);
        zzb.zza(parcel, 3, (Parcelable)latLngBounds.northeast, n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public LatLngBounds zzfy(final Parcel parcel) {
        LatLng latLng = null;
        final int zzau = zza.zzau(parcel);
        int n = 0;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            LatLng latLng3 = null;
            LatLng latLng4 = null;
            int n2 = 0;
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    latLng3 = latLng;
                    latLng4 = latLng2;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzat);
                    final LatLng latLng5 = latLng;
                    latLng4 = latLng2;
                    n2 = zzg;
                    latLng3 = latLng5;
                    break;
                }
                case 2: {
                    final LatLng latLng6 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    n2 = n;
                    latLng3 = latLng;
                    latLng4 = latLng6;
                    break;
                }
                case 3: {
                    latLng3 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    latLng4 = latLng2;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            latLng2 = latLng4;
            latLng = latLng3;
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new LatLngBounds(n, latLng2, latLng);
    }
    
    public LatLngBounds[] zzim(final int n) {
        return new LatLngBounds[n];
    }
}
