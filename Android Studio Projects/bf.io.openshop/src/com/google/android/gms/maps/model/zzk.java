package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzk implements Parcelable$Creator<StreetViewPanoramaLink>
{
    static void zza(final StreetViewPanoramaLink streetViewPanoramaLink, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, streetViewPanoramaLink.getVersionCode());
        zzb.zza(parcel, 2, streetViewPanoramaLink.panoId, false);
        zzb.zza(parcel, 3, streetViewPanoramaLink.bearing);
        zzb.zzI(parcel, zzav);
    }
    
    public StreetViewPanoramaLink zzfF(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        String zzp = null;
        float zzl = 0.0f;
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
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzl = zza.zzl(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new StreetViewPanoramaLink(zzg, zzp, zzl);
    }
    
    public StreetViewPanoramaLink[] zzit(final int n) {
        return new StreetViewPanoramaLink[n];
    }
}
