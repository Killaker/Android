package com.google.android.gms.auth;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zza implements Parcelable$Creator<AccountChangeEvent>
{
    static void zza(final AccountChangeEvent accountChangeEvent, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, accountChangeEvent.mVersion);
        zzb.zza(parcel, 2, accountChangeEvent.zzUZ);
        zzb.zza(parcel, 3, accountChangeEvent.zzVa, false);
        zzb.zzc(parcel, 4, accountChangeEvent.zzVb);
        zzb.zzc(parcel, 5, accountChangeEvent.zzVc);
        zzb.zza(parcel, 6, accountChangeEvent.zzVd, false);
        zzb.zzI(parcel, zzav);
    }
    
    public AccountChangeEvent[] zzau(final int n) {
        return new AccountChangeEvent[n];
    }
    
    public AccountChangeEvent zzz(final Parcel parcel) {
        String zzp = null;
        int zzg = 0;
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        long zzi = 0L;
        int zzg2 = 0;
        String zzp2 = null;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg3 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzi = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzp2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new AccountChangeEvent(zzg3, zzi, zzp2, zzg2, zzg, zzp);
    }
}
