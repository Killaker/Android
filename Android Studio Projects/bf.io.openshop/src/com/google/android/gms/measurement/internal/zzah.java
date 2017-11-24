package com.google.android.gms.measurement.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzah implements Parcelable$Creator<UserAttributeParcel>
{
    static void zza(final UserAttributeParcel userAttributeParcel, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, userAttributeParcel.versionCode);
        zzb.zza(parcel, 2, userAttributeParcel.name, false);
        zzb.zza(parcel, 3, userAttributeParcel.zzaZm);
        zzb.zza(parcel, 4, userAttributeParcel.zzaZn, false);
        zzb.zza(parcel, 5, userAttributeParcel.zzaZo, false);
        zzb.zza(parcel, 6, userAttributeParcel.zzamJ, false);
        zzb.zza(parcel, 7, userAttributeParcel.zzaVW, false);
        zzb.zzI(parcel, zzav);
    }
    
    public UserAttributeParcel zzfO(final Parcel parcel) {
        String zzp = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        long zzi = 0L;
        String zzp2 = null;
        Float zzm = null;
        Long zzj = null;
        String zzp3 = null;
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
                    zzp3 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzi = zza.zzi(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzj = zza.zzj(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzm = zza.zzm(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new UserAttributeParcel(zzg, zzp3, zzi, zzj, zzm, zzp2, zzp);
    }
    
    public UserAttributeParcel[] zziK(final int n) {
        return new UserAttributeParcel[n];
    }
}
