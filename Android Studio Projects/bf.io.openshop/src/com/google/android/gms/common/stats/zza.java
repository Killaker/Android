package com.google.android.gms.common.stats;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zza implements Parcelable$Creator<ConnectionEvent>
{
    static void zza(final ConnectionEvent connectionEvent, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, connectionEvent.mVersionCode);
        zzb.zza(parcel, 2, connectionEvent.getTimeMillis());
        zzb.zza(parcel, 4, connectionEvent.zzrF(), false);
        zzb.zza(parcel, 5, connectionEvent.zzrG(), false);
        zzb.zza(parcel, 6, connectionEvent.zzrH(), false);
        zzb.zza(parcel, 7, connectionEvent.zzrI(), false);
        zzb.zza(parcel, 8, connectionEvent.zzrJ(), false);
        zzb.zza(parcel, 10, connectionEvent.zzrN());
        zzb.zza(parcel, 11, connectionEvent.zzrM());
        zzb.zzc(parcel, 12, connectionEvent.getEventType());
        zzb.zza(parcel, 13, connectionEvent.zzrK(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public ConnectionEvent zzaF(final Parcel parcel) {
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        int zzg = 0;
        long zzi = 0L;
        int zzg2 = 0;
        String zzp = null;
        String zzp2 = null;
        String zzp3 = null;
        String zzp4 = null;
        String zzp5 = null;
        String zzp6 = null;
        long zzi2 = 0L;
        long zzi3 = 0L;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzi = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzp2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzp3 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzp4 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzp5 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 10: {
                    zzi2 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzat);
                    continue;
                }
                case 11: {
                    zzi3 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzat);
                    continue;
                }
                case 12: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 13: {
                    zzp6 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new ConnectionEvent(zzg, zzi, zzg2, zzp, zzp2, zzp3, zzp4, zzp5, zzp6, zzi2, zzi3);
    }
    
    public ConnectionEvent[] zzcl(final int n) {
        return new ConnectionEvent[n];
    }
}
