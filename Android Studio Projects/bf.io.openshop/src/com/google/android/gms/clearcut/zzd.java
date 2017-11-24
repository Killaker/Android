package com.google.android.gms.clearcut;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.playlog.internal.*;

public class zzd implements Parcelable$Creator<LogEventParcelable>
{
    static void zza(final LogEventParcelable logEventParcelable, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, logEventParcelable.versionCode);
        zzb.zza(parcel, 2, (Parcelable)logEventParcelable.zzafh, n, false);
        zzb.zza(parcel, 3, logEventParcelable.zzafi, false);
        zzb.zza(parcel, 4, logEventParcelable.zzafj, false);
        zzb.zzI(parcel, zzav);
    }
    
    public LogEventParcelable zzaf(final Parcel parcel) {
        int[] array = null;
        final int zzau = zza.zzau(parcel);
        int n = 0;
        byte[] array2 = null;
        PlayLoggerContext playLoggerContext = null;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            int[] zzv = null;
            byte[] array3 = null;
            PlayLoggerContext playLoggerContext2 = null;
            int n2 = 0;
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    zzv = array;
                    array3 = array2;
                    playLoggerContext2 = playLoggerContext;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzat);
                    final int[] array4 = array;
                    array3 = array2;
                    playLoggerContext2 = playLoggerContext;
                    n2 = zzg;
                    zzv = array4;
                    break;
                }
                case 2: {
                    final PlayLoggerContext playLoggerContext3 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<PlayLoggerContext>)PlayLoggerContext.CREATOR);
                    n2 = n;
                    final byte[] array5 = array2;
                    playLoggerContext2 = playLoggerContext3;
                    zzv = array;
                    array3 = array5;
                    break;
                }
                case 3: {
                    final byte[] zzs = zza.zzs(parcel, zzat);
                    playLoggerContext2 = playLoggerContext;
                    n2 = n;
                    final int[] array6 = array;
                    array3 = zzs;
                    zzv = array6;
                    break;
                }
                case 4: {
                    zzv = zza.zzv(parcel, zzat);
                    array3 = array2;
                    playLoggerContext2 = playLoggerContext;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            playLoggerContext = playLoggerContext2;
            array2 = array3;
            array = zzv;
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new LogEventParcelable(n, playLoggerContext, array2, array);
    }
    
    public LogEventParcelable[] zzbs(final int n) {
        return new LogEventParcelable[n];
    }
}
