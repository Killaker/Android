package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;
import android.content.*;

public class zza implements Parcelable$Creator<AuthAccountResult>
{
    static void zza(final AuthAccountResult authAccountResult, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, authAccountResult.mVersionCode);
        zzb.zzc(parcel, 2, authAccountResult.zzFK());
        zzb.zza(parcel, 3, (Parcelable)authAccountResult.zzFL(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public AuthAccountResult zzgR(final Parcel parcel) {
        int zzg = 0;
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        Intent intent = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 3: {
                    intent = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new AuthAccountResult(zzg2, zzg, intent);
    }
    
    public AuthAccountResult[] zzjY(final int n) {
        return new AuthAccountResult[n];
    }
}
