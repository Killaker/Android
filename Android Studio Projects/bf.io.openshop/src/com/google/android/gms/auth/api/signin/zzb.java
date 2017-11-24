package com.google.android.gms.auth.api.signin;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import android.net.*;
import com.google.android.gms.common.api.*;
import java.util.*;

public class zzb implements Parcelable$Creator<GoogleSignInAccount>
{
    static void zza(final GoogleSignInAccount googleSignInAccount, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, googleSignInAccount.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, googleSignInAccount.getId(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, googleSignInAccount.getIdToken(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, googleSignInAccount.getEmail(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, googleSignInAccount.getDisplayName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, (Parcelable)googleSignInAccount.getPhotoUrl(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, googleSignInAccount.getServerAuthCode(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, googleSignInAccount.zzmK());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 9, googleSignInAccount.zzmL(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 10, googleSignInAccount.zzVs, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public GoogleSignInAccount zzR(final Parcel parcel) {
        List<Scope> zzc = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        long zzi = 0L;
        String zzp = null;
        String zzp2 = null;
        Uri uri = null;
        String zzp3 = null;
        String zzp4 = null;
        String zzp5 = null;
        String zzp6 = null;
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
                    zzp6 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzp5 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzp4 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzp3 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 6: {
                    uri = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 7: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 8: {
                    zzi = zza.zzi(parcel, zzat);
                    continue;
                }
                case 9: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 10: {
                    zzc = zza.zzc(parcel, zzat, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new GoogleSignInAccount(zzg, zzp6, zzp5, zzp4, zzp3, uri, zzp2, zzi, zzp, zzc);
    }
    
    public GoogleSignInAccount[] zzaM(final int n) {
        return new GoogleSignInAccount[n];
    }
}
