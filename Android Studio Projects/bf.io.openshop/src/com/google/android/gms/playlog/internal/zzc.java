package com.google.android.gms.playlog.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class zzc implements Parcelable$Creator<LogEvent>
{
    static void zza(final LogEvent logEvent, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, logEvent.versionCode);
        zzb.zza(parcel, 2, logEvent.zzbdA);
        zzb.zza(parcel, 3, logEvent.tag, false);
        zzb.zza(parcel, 4, logEvent.zzbdC, false);
        zzb.zza(parcel, 5, logEvent.zzbdD, false);
        zzb.zza(parcel, 6, logEvent.zzbdB);
        zzb.zzI(parcel, zzav);
    }
    
    public LogEvent zzgy(final Parcel parcel) {
        long zzi = 0L;
        Bundle zzr = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        byte[] zzs = null;
        String zzp = null;
        long zzi2 = zzi;
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
                    zzi2 = zza.zzi(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzs = zza.zzs(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzr = zza.zzr(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzi = zza.zzi(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new LogEvent(zzg, zzi2, zzi, zzp, zzs, zzr);
    }
    
    public LogEvent[] zzjE(final int n) {
        return new LogEvent[n];
    }
}
