package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class zzc implements Parcelable$Creator<GroundOverlayOptions>
{
    static void zza(final GroundOverlayOptions groundOverlayOptions, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, groundOverlayOptions.getVersionCode());
        zzb.zza(parcel, 2, groundOverlayOptions.zzAj(), false);
        zzb.zza(parcel, 3, (Parcelable)groundOverlayOptions.getLocation(), n, false);
        zzb.zza(parcel, 4, groundOverlayOptions.getWidth());
        zzb.zza(parcel, 5, groundOverlayOptions.getHeight());
        zzb.zza(parcel, 6, (Parcelable)groundOverlayOptions.getBounds(), n, false);
        zzb.zza(parcel, 7, groundOverlayOptions.getBearing());
        zzb.zza(parcel, 8, groundOverlayOptions.getZIndex());
        zzb.zza(parcel, 9, groundOverlayOptions.isVisible());
        zzb.zza(parcel, 10, groundOverlayOptions.getTransparency());
        zzb.zza(parcel, 11, groundOverlayOptions.getAnchorU());
        zzb.zza(parcel, 12, groundOverlayOptions.getAnchorV());
        zzb.zza(parcel, 13, groundOverlayOptions.isClickable());
        zzb.zzI(parcel, zzav);
    }
    
    public GroundOverlayOptions zzfx(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        IBinder zzq = null;
        LatLng latLng = null;
        float zzl = 0.0f;
        float zzl2 = 0.0f;
        LatLngBounds latLngBounds = null;
        float zzl3 = 0.0f;
        float zzl4 = 0.0f;
        boolean zzc = false;
        float zzl5 = 0.0f;
        float zzl6 = 0.0f;
        float zzl7 = 0.0f;
        boolean zzc2 = false;
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
                    zzq = zza.zzq(parcel, zzat);
                    continue;
                }
                case 3: {
                    latLng = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 4: {
                    zzl = zza.zzl(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzl2 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 6: {
                    latLngBounds = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<LatLngBounds>)LatLngBounds.CREATOR);
                    continue;
                }
                case 7: {
                    zzl3 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzl4 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 9: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
                case 10: {
                    zzl5 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 11: {
                    zzl6 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 12: {
                    zzl7 = zza.zzl(parcel, zzat);
                    continue;
                }
                case 13: {
                    zzc2 = zza.zzc(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new GroundOverlayOptions(zzg, zzq, latLng, zzl, zzl2, latLngBounds, zzl3, zzl4, zzc, zzl5, zzl6, zzl7, zzc2);
    }
    
    public GroundOverlayOptions[] zzil(final int n) {
        return new GroundOverlayOptions[n];
    }
}
