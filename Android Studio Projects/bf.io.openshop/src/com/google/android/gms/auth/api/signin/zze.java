package com.google.android.gms.auth.api.signin;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import android.net.*;

public class zze implements Parcelable$Creator<SignInAccount>
{
    static void zza(final SignInAccount signInAccount, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, signInAccount.versionCode);
        zzb.zza(parcel, 2, signInAccount.zzmT(), false);
        zzb.zza(parcel, 3, signInAccount.getIdToken(), false);
        zzb.zza(parcel, 4, signInAccount.getEmail(), false);
        zzb.zza(parcel, 5, signInAccount.getDisplayName(), false);
        zzb.zza(parcel, 6, (Parcelable)signInAccount.getPhotoUrl(), n, false);
        zzb.zza(parcel, 7, (Parcelable)signInAccount.zzmV(), n, false);
        zzb.zza(parcel, 8, signInAccount.getUserId(), false);
        zzb.zza(parcel, 9, signInAccount.zzmW(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public SignInAccount zzT(final Parcel parcel) {
        String zzp = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        String zzp2 = "";
        GoogleSignInAccount googleSignInAccount = null;
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
                    googleSignInAccount = zza.zza(parcel, zzat, GoogleSignInAccount.CREATOR);
                    continue;
                }
                case 8: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 9: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new SignInAccount(zzg, zzp6, zzp5, zzp4, zzp3, uri, googleSignInAccount, zzp2, zzp);
    }
    
    public SignInAccount[] zzaO(final int n) {
        return new SignInAccount[n];
    }
}
