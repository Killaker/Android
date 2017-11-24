package com.google.android.gms.measurement.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzb implements Parcelable$Creator<AppMetadata>
{
    static void zza(final AppMetadata appMetadata, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, appMetadata.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, appMetadata.packageName, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, appMetadata.zzaVt, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, appMetadata.zzaMV, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, appMetadata.zzaVu, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, appMetadata.zzaVv);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, appMetadata.zzaVw);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, appMetadata.zzaVx, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 9, appMetadata.zzaVy);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 10, appMetadata.zzaVz);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public AppMetadata zzfL(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        String zzp = null;
        String zzp2 = null;
        String zzp3 = null;
        String zzp4 = null;
        long zzi = 0L;
        long zzi2 = 0L;
        String zzp5 = null;
        boolean zzc = false;
        boolean zzc2 = false;
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
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzp3 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzp4 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzi = zza.zzi(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzi2 = zza.zzi(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzp5 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 9: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
                case 10: {
                    zzc2 = zza.zzc(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new AppMetadata(zzg, zzp, zzp2, zzp3, zzp4, zzi, zzi2, zzp5, zzc, zzc2);
    }
    
    public AppMetadata[] zziH(final int n) {
        return new AppMetadata[n];
    }
}
