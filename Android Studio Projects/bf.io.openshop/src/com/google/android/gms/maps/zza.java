package com.google.android.gms.maps;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;
import com.google.android.gms.maps.model.*;

public class zza implements Parcelable$Creator<GoogleMapOptions>
{
    static void zza(final GoogleMapOptions googleMapOptions, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, googleMapOptions.getVersionCode());
        zzb.zza(parcel, 2, googleMapOptions.zzzK());
        zzb.zza(parcel, 3, googleMapOptions.zzzL());
        zzb.zzc(parcel, 4, googleMapOptions.getMapType());
        zzb.zza(parcel, 5, (Parcelable)googleMapOptions.getCamera(), n, false);
        zzb.zza(parcel, 6, googleMapOptions.zzzM());
        zzb.zza(parcel, 7, googleMapOptions.zzzN());
        zzb.zza(parcel, 8, googleMapOptions.zzzO());
        zzb.zza(parcel, 9, googleMapOptions.zzzP());
        zzb.zza(parcel, 10, googleMapOptions.zzzQ());
        zzb.zza(parcel, 11, googleMapOptions.zzzR());
        zzb.zza(parcel, 12, googleMapOptions.zzzS());
        zzb.zza(parcel, 14, googleMapOptions.zzzT());
        zzb.zza(parcel, 15, googleMapOptions.zzzU());
        zzb.zzI(parcel, zzav);
    }
    
    public GoogleMapOptions zzft(final Parcel parcel) {
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        int zzg = 0;
        byte zze = -1;
        byte zze2 = -1;
        int zzg2 = 0;
        CameraPosition cameraPosition = null;
        byte zze3 = -1;
        byte zze4 = -1;
        byte zze5 = -1;
        byte zze6 = -1;
        byte zze7 = -1;
        byte zze8 = -1;
        byte zze9 = -1;
        byte zze10 = -1;
        byte zze11 = -1;
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
                    zze = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 3: {
                    zze2 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    continue;
                }
                case 5: {
                    cameraPosition = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, (android.os.Parcelable$Creator<CameraPosition>)CameraPosition.CREATOR);
                    continue;
                }
                case 6: {
                    zze3 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 7: {
                    zze4 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 8: {
                    zze5 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 9: {
                    zze6 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 10: {
                    zze7 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 11: {
                    zze8 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 12: {
                    zze9 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 14: {
                    zze10 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
                case 15: {
                    zze11 = com.google.android.gms.common.internal.safeparcel.zza.zze(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new GoogleMapOptions(zzg, zze, zze2, zzg2, cameraPosition, zze3, zze4, zze5, zze6, zze7, zze8, zze9, zze10, zze11);
    }
    
    public GoogleMapOptions[] zzih(final int n) {
        return new GoogleMapOptions[n];
    }
}
