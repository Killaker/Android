package com.google.android.gms.measurement.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzk implements Parcelable$Creator<EventParcel>
{
    static void zza(final EventParcel eventParcel, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, eventParcel.versionCode);
        zzb.zza(parcel, 2, eventParcel.name, false);
        zzb.zza(parcel, 3, (Parcelable)eventParcel.zzaVV, n, false);
        zzb.zza(parcel, 4, eventParcel.zzaVW, false);
        zzb.zza(parcel, 5, eventParcel.zzaVX);
        zzb.zzI(parcel, zzav);
    }
    
    public EventParcel zzfN(final Parcel parcel) {
        String zzp = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        long zzi = 0L;
        EventParams eventParams = null;
        String zzp2 = null;
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
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    eventParams = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<EventParams>)EventParams.CREATOR);
                    continue;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzi = zza.zzi(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new EventParcel(zzg, zzp2, eventParams, zzp, zzi);
    }
    
    public EventParcel[] zziJ(final int n) {
        return new EventParcel[n];
    }
}
