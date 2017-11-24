package com.google.android.gms.auth;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;

public class zzc implements Parcelable$Creator<AccountChangeEventsResponse>
{
    static void zza(final AccountChangeEventsResponse accountChangeEventsResponse, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, accountChangeEventsResponse.mVersion);
        zzb.zzc(parcel, 2, accountChangeEventsResponse.zzpH, false);
        zzb.zzI(parcel, zzav);
    }
    
    public AccountChangeEventsResponse zzB(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        List<AccountChangeEvent> zzc = null;
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
                    zzc = zza.zzc(parcel, zzat, AccountChangeEvent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new AccountChangeEventsResponse(zzg, zzc);
    }
    
    public AccountChangeEventsResponse[] zzaw(final int n) {
        return new AccountChangeEventsResponse[n];
    }
}
