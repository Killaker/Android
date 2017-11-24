package com.google.android.gms.common.images;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import android.net.*;

public class zzb implements Parcelable$Creator<WebImage>
{
    static void zza(final WebImage webImage, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, webImage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable)webImage.getUrl(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 3, webImage.getWidth());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 4, webImage.getHeight());
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public WebImage zzal(final Parcel parcel) {
        int n = 0;
        final int zzau = zza.zzau(parcel);
        Uri uri = null;
        int n2 = 0;
        int n3 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            int zzg = 0;
            int n4 = 0;
            Uri uri2 = null;
            int n5 = 0;
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    zzg = n;
                    n4 = n2;
                    uri2 = uri;
                    n5 = n3;
                    break;
                }
                case 1: {
                    final int zzg2 = zza.zzg(parcel, zzat);
                    final int n6 = n;
                    n4 = n2;
                    uri2 = uri;
                    n5 = zzg2;
                    zzg = n6;
                    break;
                }
                case 2: {
                    final Uri uri3 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    n5 = n3;
                    final int n7 = n2;
                    uri2 = uri3;
                    zzg = n;
                    n4 = n7;
                    break;
                }
                case 3: {
                    final int zzg3 = zza.zzg(parcel, zzat);
                    uri2 = uri;
                    n5 = n3;
                    final int n8 = n;
                    n4 = zzg3;
                    zzg = n8;
                    break;
                }
                case 4: {
                    zzg = zza.zzg(parcel, zzat);
                    n4 = n2;
                    uri2 = uri;
                    n5 = n3;
                    break;
                }
            }
            n3 = n5;
            uri = uri2;
            n2 = n4;
            n = zzg;
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new WebImage(n3, uri, n2, n);
    }
    
    public WebImage[] zzbN(final int n) {
        return new WebImage[n];
    }
}
