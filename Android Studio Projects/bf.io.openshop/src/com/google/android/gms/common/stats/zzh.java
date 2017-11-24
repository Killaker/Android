package com.google.android.gms.common.stats;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;

public class zzh implements Parcelable$Creator<WakeLockEvent>
{
    static void zza(final WakeLockEvent wakeLockEvent, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, wakeLockEvent.mVersionCode);
        zzb.zza(parcel, 2, wakeLockEvent.getTimeMillis());
        zzb.zza(parcel, 4, wakeLockEvent.zzrR(), false);
        zzb.zzc(parcel, 5, wakeLockEvent.zzrT());
        zzb.zzb(parcel, 6, wakeLockEvent.zzrU(), false);
        zzb.zza(parcel, 8, wakeLockEvent.zzrN());
        zzb.zza(parcel, 10, wakeLockEvent.zzrS(), false);
        zzb.zzc(parcel, 11, wakeLockEvent.getEventType());
        zzb.zza(parcel, 12, wakeLockEvent.zzrK(), false);
        zzb.zza(parcel, 13, wakeLockEvent.zzrW(), false);
        zzb.zzc(parcel, 14, wakeLockEvent.zzrV());
        zzb.zza(parcel, 15, wakeLockEvent.zzrX());
        zzb.zza(parcel, 16, wakeLockEvent.zzrY());
        zzb.zzI(parcel, zzav);
    }
    
    public WakeLockEvent zzaG(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        long zzi = 0L;
        int zzg2 = 0;
        String zzp = null;
        int zzg3 = 0;
        List<String> zzD = null;
        String zzp2 = null;
        long zzi2 = 0L;
        int zzg4 = 0;
        String zzp3 = null;
        String zzp4 = null;
        float zzl = 0.0f;
        long zzi3 = 0L;
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
                    zzi = zza.zzi(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzg3 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzD = zza.zzD(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzi2 = zza.zzi(parcel, zzat);
                    continue;
                }
                case 10: {
                    zzp3 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 11: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 12: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 13: {
                    zzp4 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 14: {
                    zzg4 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 15: {
                    zzl = zza.zzl(parcel, zzat);
                    continue;
                }
                case 16: {
                    zzi3 = zza.zzi(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new WakeLockEvent(zzg, zzi, zzg2, zzp, zzg3, zzD, zzp2, zzi2, zzg4, zzp3, zzp4, zzl, zzi3);
    }
    
    public WakeLockEvent[] zzcm(final int n) {
        return new WakeLockEvent[n];
    }
}
