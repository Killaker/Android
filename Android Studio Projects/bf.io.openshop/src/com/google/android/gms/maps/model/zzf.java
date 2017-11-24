package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class zzf implements Parcelable$Creator<MarkerOptions>
{
    static void zza(final MarkerOptions markerOptions, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, markerOptions.getVersionCode());
        zzb.zza(parcel, 2, (Parcelable)markerOptions.getPosition(), n, false);
        zzb.zza(parcel, 3, markerOptions.getTitle(), false);
        zzb.zza(parcel, 4, markerOptions.getSnippet(), false);
        zzb.zza(parcel, 5, markerOptions.zzAk(), false);
        zzb.zza(parcel, 6, markerOptions.getAnchorU());
        zzb.zza(parcel, 7, markerOptions.getAnchorV());
        zzb.zza(parcel, 8, markerOptions.isDraggable());
        zzb.zza(parcel, 9, markerOptions.isVisible());
        zzb.zza(parcel, 10, markerOptions.isFlat());
        zzb.zza(parcel, 11, markerOptions.getRotation());
        zzb.zza(parcel, 12, markerOptions.getInfoWindowAnchorU());
        zzb.zza(parcel, 13, markerOptions.getInfoWindowAnchorV());
        zzb.zza(parcel, 14, markerOptions.getAlpha());
        zzb.zzI(parcel, zzav);
    }
    
    public MarkerOptions zzfA(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        LatLng latLng = null;
        String zzp = null;
        String zzp2 = null;
        IBinder zzq = null;
        float zzl = 0.0f;
        float zzl2 = 0.0f;
        boolean zzc = false;
        boolean zzc2 = false;
        boolean zzc3 = false;
        float zzl3 = 0.0f;
        float zzl4 = 0.5f;
        float zzl5 = 0.0f;
        float zzl6 = 1.0f;
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
                    latLng = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzq = zza.zzq(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzl = zza.zzl(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzl2 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
                case 9: {
                    zzc2 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 10: {
                    zzc3 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 11: {
                    zzl3 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 12: {
                    zzl4 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 13: {
                    zzl5 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 14: {
                    zzl6 = zza.zzl(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new MarkerOptions(zzg, latLng, zzp, zzp2, zzq, zzl, zzl2, zzc, zzc2, zzc3, zzl3, zzl4, zzl5, zzl6);
    }
    
    public MarkerOptions[] zzio(final int n) {
        return new MarkerOptions[n];
    }
}
