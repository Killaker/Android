package com.google.android.gms.playlog.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zze implements Parcelable$Creator<PlayLoggerContext>
{
    static void zza(final PlayLoggerContext playLoggerContext, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, playLoggerContext.versionCode);
        zzb.zza(parcel, 2, playLoggerContext.packageName, false);
        zzb.zzc(parcel, 3, playLoggerContext.zzbdL);
        zzb.zzc(parcel, 4, playLoggerContext.zzbdM);
        zzb.zza(parcel, 5, playLoggerContext.zzbdN, false);
        zzb.zza(parcel, 6, playLoggerContext.zzbdO, false);
        zzb.zza(parcel, 7, playLoggerContext.zzbdP);
        zzb.zza(parcel, 8, playLoggerContext.zzbdQ, false);
        zzb.zza(parcel, 9, playLoggerContext.zzbdR);
        zzb.zzc(parcel, 10, playLoggerContext.zzbdS);
        zzb.zzI(parcel, zzav);
    }
    
    public PlayLoggerContext zzgz(final Parcel parcel) {
        String zzp = null;
        int zzg = 0;
        final int zzau = zza.zzau(parcel);
        boolean zzc = true;
        boolean zzc2 = false;
        String zzp2 = null;
        String zzp3 = null;
        int zzg2 = 0;
        int zzg3 = 0;
        String zzp4 = null;
        int zzg4 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg4 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzp4 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzg3 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzp3 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 9: {
                    zzc2 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 10: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new PlayLoggerContext(zzg4, zzp4, zzg3, zzg2, zzp3, zzp2, zzc, zzp, zzc2, zzg);
    }
    
    public PlayLoggerContext[] zzjF(final int n) {
        return new PlayLoggerContext[n];
    }
}
