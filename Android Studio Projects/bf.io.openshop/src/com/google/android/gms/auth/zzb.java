package com.google.android.gms.auth;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import android.accounts.*;

public class zzb implements Parcelable$Creator<AccountChangeEventsRequest>
{
    static void zza(final AccountChangeEventsRequest accountChangeEventsRequest, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, accountChangeEventsRequest.mVersion);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, accountChangeEventsRequest.zzVc);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, accountChangeEventsRequest.zzVa, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, (Parcelable)accountChangeEventsRequest.zzTI, n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public AccountChangeEventsRequest zzA(final Parcel parcel) {
        Account account = null;
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
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    account = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new AccountChangeEventsRequest(zzg2, zzg, zzp, account);
    }
    
    public AccountChangeEventsRequest[] zzav(final int n) {
        return new AccountChangeEventsRequest[n];
    }
}
