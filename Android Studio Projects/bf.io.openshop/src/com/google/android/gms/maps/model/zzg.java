package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzg implements Parcelable$Creator<PointOfInterest>
{
    static void zza(final PointOfInterest pointOfInterest, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, pointOfInterest.getVersionCode());
        zzb.zza(parcel, 2, (Parcelable)pointOfInterest.zzaTG, n, false);
        zzb.zza(parcel, 3, pointOfInterest.zzaTH, false);
        zzb.zza(parcel, 4, pointOfInterest.name, false);
        zzb.zzI(parcel, zzav);
    }
    
    public PointOfInterest zzfB(final Parcel parcel) {
        String s = null;
        final int zzau = zza.zzau(parcel);
        int n = 0;
        String s2 = null;
        LatLng latLng = null;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            String zzp = null;
            String s3 = null;
            LatLng latLng2 = null;
            int n2 = 0;
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    zzp = s;
                    s3 = s2;
                    latLng2 = latLng;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzat);
                    final String s4 = s;
                    s3 = s2;
                    latLng2 = latLng;
                    n2 = zzg;
                    zzp = s4;
                    break;
                }
                case 2: {
                    final LatLng latLng3 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    n2 = n;
                    final String s5 = s2;
                    latLng2 = latLng3;
                    zzp = s;
                    s3 = s5;
                    break;
                }
                case 3: {
                    final String zzp2 = zza.zzp(parcel, zzat);
                    latLng2 = latLng;
                    n2 = n;
                    final String s6 = s;
                    s3 = zzp2;
                    zzp = s6;
                    break;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzat);
                    s3 = s2;
                    latLng2 = latLng;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            latLng = latLng2;
            s2 = s3;
            s = zzp;
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new PointOfInterest(n, latLng, s2, s);
    }
    
    public PointOfInterest[] zzip(final int n) {
        return new PointOfInterest[n];
    }
}
