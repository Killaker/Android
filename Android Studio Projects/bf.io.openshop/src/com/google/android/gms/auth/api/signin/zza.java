package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;
import android.net.*;

public class zza implements Parcelable$Creator<EmailSignInOptions>
{
    static void zza(final EmailSignInOptions emailSignInOptions, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, emailSignInOptions.versionCode);
        zzb.zza(parcel, 2, (Parcelable)emailSignInOptions.zzmF(), n, false);
        zzb.zza(parcel, 3, emailSignInOptions.zzmH(), false);
        zzb.zza(parcel, 4, (Parcelable)emailSignInOptions.zzmG(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public EmailSignInOptions zzQ(final Parcel parcel) {
        Uri uri = null;
        final int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        int n = 0;
        String s = null;
        Uri uri2 = null;
        while (parcel.dataPosition() < zzau) {
            final int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            Uri uri3 = null;
            String s2 = null;
            Uri uri4 = null;
            int n2 = 0;
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    uri3 = uri;
                    s2 = s;
                    uri4 = uri2;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    final Uri uri5 = uri;
                    s2 = s;
                    uri4 = uri2;
                    n2 = zzg;
                    uri3 = uri5;
                    break;
                }
                case 2: {
                    final Uri uri6 = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    n2 = n;
                    final String s3 = s;
                    uri4 = uri6;
                    uri3 = uri;
                    s2 = s3;
                    break;
                }
                case 3: {
                    final String zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    uri4 = uri2;
                    n2 = n;
                    final Uri uri7 = uri;
                    s2 = zzp;
                    uri3 = uri7;
                    break;
                }
                case 4: {
                    uri3 = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    s2 = s;
                    uri4 = uri2;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            uri2 = uri4;
            s = s2;
            uri = uri3;
        }
        if (parcel.dataPosition() != zzau) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new EmailSignInOptions(n, uri2, s, uri);
    }
    
    public EmailSignInOptions[] zzaL(final int n) {
        return new EmailSignInOptions[n];
    }
}
