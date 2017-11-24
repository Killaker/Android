package com.google.android.gms.common;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import android.app.*;

public class zzb implements Parcelable$Creator<ConnectionResult>
{
    static void zza(final ConnectionResult connectionResult, final Parcel parcel, final int n) {
        final int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, connectionResult.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, connectionResult.getErrorCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable)connectionResult.getResolution(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, connectionResult.getErrorMessage(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }
    
    public ConnectionResult zzag(final Parcel parcel) {
        String s = null;
        int n = 0;
        final int zzau = zza.zzau(parcel);
        PendingIntent pendingIntent = null;
        int n2 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            String zzp = null;
            PendingIntent pendingIntent2 = null;
            int n3 = 0;
            int n4 = 0;
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    zzp = s;
                    pendingIntent2 = pendingIntent;
                    n3 = n;
                    n4 = n2;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzat);
                    final String s2 = s;
                    pendingIntent2 = pendingIntent;
                    n3 = n;
                    n4 = zzg;
                    zzp = s2;
                    break;
                }
                case 2: {
                    final int zzg2 = zza.zzg(parcel, zzat);
                    n4 = n2;
                    final PendingIntent pendingIntent3 = pendingIntent;
                    n3 = zzg2;
                    zzp = s;
                    pendingIntent2 = pendingIntent3;
                    break;
                }
                case 3: {
                    final PendingIntent pendingIntent4 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    n3 = n;
                    n4 = n2;
                    final String s3 = s;
                    pendingIntent2 = pendingIntent4;
                    zzp = s3;
                    break;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzat);
                    pendingIntent2 = pendingIntent;
                    n3 = n;
                    n4 = n2;
                    break;
                }
            }
            n2 = n4;
            n = n3;
            pendingIntent = pendingIntent2;
            s = zzp;
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new ConnectionResult(n2, n, pendingIntent, s);
    }
    
    public ConnectionResult[] zzbt(final int n) {
        return new ConnectionResult[n];
    }
}
