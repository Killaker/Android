package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzl implements Parcelable$Creator<StreetViewPanoramaLocation>
{
    static void zza(final StreetViewPanoramaLocation streetViewPanoramaLocation, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, streetViewPanoramaLocation.getVersionCode());
        zzb.zza(parcel, 2, streetViewPanoramaLocation.links, n, false);
        zzb.zza(parcel, 3, (Parcelable)streetViewPanoramaLocation.position, n, false);
        zzb.zza(parcel, 4, streetViewPanoramaLocation.panoId, false);
        zzb.zzI(parcel, zzav);
    }
    
    public StreetViewPanoramaLocation zzfG(final Parcel parcel) {
        String s = null;
        final int zzau = zza.zzau(parcel);
        int n = 0;
        LatLng latLng = null;
        StreetViewPanoramaLink[] array = null;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            String zzp = null;
            LatLng latLng2 = null;
            StreetViewPanoramaLink[] array2 = null;
            int n2 = 0;
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    zzp = s;
                    latLng2 = latLng;
                    array2 = array;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzat);
                    final String s2 = s;
                    latLng2 = latLng;
                    array2 = array;
                    n2 = zzg;
                    zzp = s2;
                    break;
                }
                case 2: {
                    final StreetViewPanoramaLink[] array3 = zza.zzb(parcel, zzat, (android.os.Parcelable$Creator<StreetViewPanoramaLink>)StreetViewPanoramaLink.CREATOR);
                    n2 = n;
                    final LatLng latLng3 = latLng;
                    array2 = array3;
                    zzp = s;
                    latLng2 = latLng3;
                    break;
                }
                case 3: {
                    final LatLng latLng4 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    array2 = array;
                    n2 = n;
                    final String s3 = s;
                    latLng2 = latLng4;
                    zzp = s3;
                    break;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzat);
                    latLng2 = latLng;
                    array2 = array;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            array = array2;
            latLng = latLng2;
            s = zzp;
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new StreetViewPanoramaLocation(n, array, latLng, s);
    }
    
    public StreetViewPanoramaLocation[] zziu(final int n) {
        return new StreetViewPanoramaLocation[n];
    }
}
