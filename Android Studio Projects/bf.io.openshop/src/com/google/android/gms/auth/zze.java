package com.google.android.gms.auth;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;

public class zze implements Parcelable$Creator<TokenData>
{
    static void zza(final TokenData tokenData, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, tokenData.mVersionCode);
        zzb.zza(parcel, 2, tokenData.getToken(), false);
        zzb.zza(parcel, 3, tokenData.zzmn(), false);
        zzb.zza(parcel, 4, tokenData.zzmo());
        zzb.zza(parcel, 5, tokenData.zzmp());
        zzb.zzb(parcel, 6, tokenData.zzmq(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public TokenData zzC(final Parcel parcel) {
        List<String> zzD = null;
        boolean zzc = false;
        final int zzau = zza.zzau(parcel);
        boolean zzc2 = false;
        Long zzj = null;
        String zzp = null;
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
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzj = zza.zzj(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzc2 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzD = zza.zzD(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new TokenData(zzg, zzp, zzj, zzc2, zzc, zzD);
    }
    
    public TokenData[] zzax(final int n) {
        return new TokenData[n];
    }
}
