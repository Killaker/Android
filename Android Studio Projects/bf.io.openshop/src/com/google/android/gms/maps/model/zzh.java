package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;

public class zzh implements Parcelable$Creator<PolygonOptions>
{
    static void zza(final PolygonOptions polygonOptions, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, polygonOptions.getVersionCode());
        zzb.zzc(parcel, 2, polygonOptions.getPoints(), false);
        zzb.zzd(parcel, 3, polygonOptions.zzAl(), false);
        zzb.zza(parcel, 4, polygonOptions.getStrokeWidth());
        zzb.zzc(parcel, 5, polygonOptions.getStrokeColor());
        zzb.zzc(parcel, 6, polygonOptions.getFillColor());
        zzb.zza(parcel, 7, polygonOptions.getZIndex());
        zzb.zza(parcel, 8, polygonOptions.isVisible());
        zzb.zza(parcel, 9, polygonOptions.isGeodesic());
        zzb.zza(parcel, 10, polygonOptions.isClickable());
        zzb.zzI(parcel, zzav);
    }
    
    public PolygonOptions zzfC(final Parcel parcel) {
        float zzl = 0.0f;
        boolean zzc = false;
        final int zzau = zza.zzau(parcel);
        List<LatLng> zzc2 = null;
        final ArrayList list = new ArrayList();
        boolean zzc3 = false;
        boolean zzc4 = false;
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
                    zzc2 = zza.zzc(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    zza.zza(parcel, zzat, list, this.getClass().getClassLoader());
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
                    zzc4 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 9: {
                    zzc3 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 10: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new PolygonOptions(zzg3, zzc2, list, zzl2, zzg2, zzg, zzl, zzc4, zzc3, zzc);
    }
    
    public PolygonOptions[] zziq(final int n) {
        return new PolygonOptions[n];
    }
}
