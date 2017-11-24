package com.google.android.gms.auth.firstparty.shared;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zza implements Parcelable$Creator<FACLConfig>
{
    static void zza(final FACLConfig faclConfig, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, faclConfig.version);
        zzb.zza(parcel, 2, faclConfig.zzYm);
        zzb.zza(parcel, 3, faclConfig.zzYn, false);
        zzb.zza(parcel, 4, faclConfig.zzYo);
        zzb.zza(parcel, 5, faclConfig.zzYp);
        zzb.zza(parcel, 6, faclConfig.zzYq);
        zzb.zza(parcel, 7, faclConfig.zzYr);
        zzb.zzI(parcel, zzav);
    }
    
    public FACLConfig zzW(final Parcel parcel) {
        boolean zzc = false;
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        String zzp = null;
        boolean zzc2 = false;
        boolean zzc3 = false;
        boolean zzc4 = false;
        boolean zzc5 = false;
        int zzg = 0;
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
                    zzc5 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzc4 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzc3 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzc2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new FACLConfig(zzg, zzc5, zzp, zzc4, zzc3, zzc2, zzc);
    }
    
    public FACLConfig[] zzaT(final int n) {
        return new FACLConfig[n];
    }
}
