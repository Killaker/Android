package com.google.android.gms.common.api;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;

public class zzb implements Parcelable$Creator<Scope>
{
    static void zza(final Scope scope, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, scope.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, scope.zzpb(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public Scope zzah(final Parcel parcel) {
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        String zzp = null;
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
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new Scope(zzg, zzp);
    }
    
    public Scope[] zzbx(final int n) {
        return new Scope[n];
    }
}
