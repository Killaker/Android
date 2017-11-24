package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;

public class zzi implements Parcelable$Creator<PolylineOptions>
{
    static void zza(final PolylineOptions polylineOptions, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, polylineOptions.getVersionCode());
        zzb.zzc(parcel, 2, polylineOptions.getPoints(), false);
        zzb.zza(parcel, 3, polylineOptions.getWidth());
        zzb.zzc(parcel, 4, polylineOptions.getColor());
        zzb.zza(parcel, 5, polylineOptions.getZIndex());
        zzb.zza(parcel, 6, polylineOptions.isVisible());
        zzb.zza(parcel, 7, polylineOptions.isGeodesic());
        zzb.zza(parcel, 8, polylineOptions.isClickable());
        zzb.zzI(parcel, zzav);
    }
    
    public PolylineOptions zzfD(final Parcel parcel) {
        float zzl = 0.0f;
        boolean zzc = false;
        final int zzau = zza.zzau(parcel);
        List<Object> zzc2 = null;
        boolean zzc3 = false;
        boolean zzc4 = false;
        int zzg = 0;
        float zzl2 = 0.0f;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzc2 = zza.zzc(parcel, zzat, (android.os.Parcelable$Creator<Object>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    zzl2 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzl = zza.zzl(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzc4 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzc3 = zza.zzc(parcel, zzat);
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
        return new PolylineOptions(zzg2, zzc2, zzl2, zzg, zzl, zzc4, zzc3, zzc);
    }
    
    public PolylineOptions[] zzir(final int n) {
        return new PolylineOptions[n];
    }
}
