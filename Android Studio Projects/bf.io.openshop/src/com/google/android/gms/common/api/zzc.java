package com.google.android.gms.common.api;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import android.app.*;

public class zzc implements Parcelable$Creator<Status>
{
    static void zza(final Status status, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, status.getStatusCode());
        zzb.zzc(parcel, 1000, status.getVersionCode());
        zzb.zza(parcel, 2, status.getStatusMessage(), false);
        zzb.zza(parcel, 3, (Parcelable)status.zzpc(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public Status zzai(final Parcel parcel) {
        PendingIntent pendingIntent = null;
        int zzg = 0;
        final int zzau = zza.zzau(parcel);
        String zzp = null;
        int zzg2 = 0;
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
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    pendingIntent = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new Status(zzg2, zzg, zzp, pendingIntent);
    }
    
    public Status[] zzby(final int n) {
        return new Status[n];
    }
}
