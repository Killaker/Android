package com.google.android.gms.auth.firstparty.shared;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzb implements Parcelable$Creator<FACLData>
{
    static void zza(final FACLData faclData, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, faclData.version);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable)faclData.zzYs, n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, faclData.zzYt, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, faclData.zzYu);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, faclData.zzYv, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public FACLData zzX(final Parcel parcel) {
        boolean zzc = false;
        String zzp = null;
        final int zzau = zza.zzau(parcel);
        String zzp2 = null;
        FACLConfig faclConfig = null;
        int zzg = 0;
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
                    faclConfig = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<FACLConfig>)FACLConfig.CREATOR);
                    continue;
                }
                case 3: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new FACLData(zzg, faclConfig, zzp2, zzc, zzp);
    }
    
    public FACLData[] zzaU(final int n) {
        return new FACLData[n];
    }
}
