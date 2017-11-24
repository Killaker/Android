package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.*;
import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import android.accounts.*;
import java.util.*;

public class zzc implements Parcelable$Creator<GoogleSignInOptions>
{
    static void zza(final GoogleSignInOptions googleSignInOptions, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, googleSignInOptions.versionCode);
        zzb.zzc(parcel, 2, googleSignInOptions.zzmN(), false);
        zzb.zza(parcel, 3, (Parcelable)googleSignInOptions.getAccount(), n, false);
        zzb.zza(parcel, 4, googleSignInOptions.zzmO());
        zzb.zza(parcel, 5, googleSignInOptions.zzmP());
        zzb.zza(parcel, 6, googleSignInOptions.zzmQ());
        zzb.zza(parcel, 7, googleSignInOptions.zzmR(), false);
        zzb.zza(parcel, 8, googleSignInOptions.zzmS(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public GoogleSignInOptions zzS(final Parcel parcel) {
        String zzp = null;
        boolean zzc = false;
        final int zzau = zza.zzau(parcel);
        String zzp2 = null;
        boolean zzc2 = false;
        boolean zzc3 = false;
        Account account = null;
        ArrayList<Scope> zzc4 = null;
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
                    zzc4 = zza.zzc(parcel, zzat, Scope.CREATOR);
                    continue;
                }
                case 3: {
                    account = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
                case 4: {
                    zzc3 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzc2 = zza.zzc(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzc = zza.zzc(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new GoogleSignInOptions(zzg, zzc4, account, zzc3, zzc2, zzc, zzp2, zzp);
    }
    
    public GoogleSignInOptions[] zzaN(final int n) {
        return new GoogleSignInOptions[n];
    }
}
