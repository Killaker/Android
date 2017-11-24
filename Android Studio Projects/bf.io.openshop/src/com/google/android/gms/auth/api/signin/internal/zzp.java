package com.google.android.gms.auth.api.signin.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.auth.api.signin.*;

public class zzp implements Parcelable$Creator<SignInConfiguration>
{
    static void zza(final SignInConfiguration signInConfiguration, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, signInConfiguration.versionCode);
        zzb.zza(parcel, 2, signInConfiguration.zznk(), false);
        zzb.zza(parcel, 3, signInConfiguration.zzmR(), false);
        zzb.zza(parcel, 4, (Parcelable)signInConfiguration.zznl(), n, false);
        zzb.zza(parcel, 5, (Parcelable)signInConfiguration.zznm(), n, false);
        zzb.zza(parcel, 7, signInConfiguration.zznn(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public SignInConfiguration zzV(final Parcel parcel) {
        String zzp = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        GoogleSignInOptions googleSignInOptions = null;
        EmailSignInOptions emailSignInOptions = null;
        String zzp2 = null;
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
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    emailSignInOptions = zza.zza(parcel, zzat, EmailSignInOptions.CREATOR);
                    continue;
                }
                case 5: {
                    googleSignInOptions = zza.zza(parcel, zzat, GoogleSignInOptions.CREATOR);
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
        return new SignInConfiguration(zzg, zzp3, zzp2, emailSignInOptions, googleSignInOptions, zzp);
    }
    
    public SignInConfiguration[] zzaQ(final int n) {
        return new SignInConfiguration[n];
    }
}
