package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class zza implements Parcelable$Creator<BitmapTeleporter>
{
    static void zza(final BitmapTeleporter bitmapTeleporter, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, bitmapTeleporter.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)bitmapTeleporter.zzIq, n, false);
        zzb.zzc(parcel, 3, bitmapTeleporter.zzabB);
        zzb.zzI(parcel, zzav);
    }
    
    public BitmapTeleporter zzaj(final Parcel parcel) {
        int n = 0;
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        int n2 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            int zzg = 0;
            ParcelFileDescriptor parcelFileDescriptor2 = null;
            int n3 = 0;
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    zzg = n;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    n3 = n2;
                    break;
                }
                case 1: {
                    final int zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    final int n4 = n;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    n3 = zzg2;
                    zzg = n4;
                    break;
                }
                case 2: {
                    final ParcelFileDescriptor parcelFileDescriptor3 = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    n3 = n2;
                    zzg = n;
                    parcelFileDescriptor2 = parcelFileDescriptor3;
                    break;
                }
                case 3: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    n3 = n2;
                    break;
                }
            }
            n2 = n3;
            parcelFileDescriptor = parcelFileDescriptor2;
            n = zzg;
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new BitmapTeleporter(n2, parcelFileDescriptor, n);
    }
    
    public BitmapTeleporter[] zzbE(final int n) {
        return new BitmapTeleporter[n];
    }
}
