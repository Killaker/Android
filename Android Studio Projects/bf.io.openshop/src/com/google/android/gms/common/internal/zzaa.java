package com.google.android.gms.common.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;

public class zzaa implements Parcelable$Creator<SignInButtonConfig>
{
    static void zza(final SignInButtonConfig signInButtonConfig, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, signInButtonConfig.mVersionCode);
        zzb.zzc(parcel, 2, signInButtonConfig.zzrb());
        zzb.zzc(parcel, 3, signInButtonConfig.zzrc());
        zzb.zza(parcel, 4, signInButtonConfig.zzrd(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public SignInButtonConfig zzar(final Parcel parcel) {
        int zzg = 0;
        final int zzau = zza.zzau(parcel);
        Scope[] array = null;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
                case 4: {
                    array = zza.zzb(parcel, zzat, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new SignInButtonConfig(zzg3, zzg2, zzg, array);
    }
    
    public SignInButtonConfig[] zzbY(final int n) {
        return new SignInButtonConfig[n];
    }
}
