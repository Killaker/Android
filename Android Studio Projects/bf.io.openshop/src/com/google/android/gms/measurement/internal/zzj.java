package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class zzj implements Parcelable$Creator<EventParams>
{
    static void zza(final EventParams eventParams, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, eventParams.versionCode);
        zzb.zza(parcel, 2, eventParams.zzCC(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public EventParams zzfM(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        Bundle zzr = null;
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
                    zzr = zza.zzr(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new EventParams(zzg, zzr);
    }
    
    public EventParams[] zziI(final int n) {
        return new EventParams[n];
    }
}
