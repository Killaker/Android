package com.google.android.gms.signin.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import android.accounts.*;
import com.google.android.gms.common.api.*;

public class zzf implements Parcelable$Creator<RecordConsentRequest>
{
    static void zza(final RecordConsentRequest recordConsentRequest, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, recordConsentRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)recordConsentRequest.getAccount(), n, false);
        zzb.zza(parcel, 3, recordConsentRequest.zzFM(), n, false);
        zzb.zza(parcel, 4, recordConsentRequest.zzmR(), false);
        zzb.zzI(parcel, zzav);
    }
    
    public RecordConsentRequest zzgT(final Parcel parcel) {
        String s = null;
        final int zzau = zza.zzau(parcel);
        int n = 0;
        Scope[] array = null;
        Account account = null;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            String zzp = null;
            Scope[] array2 = null;
            Account account2 = null;
            int n2 = 0;
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    zzp = s;
                    array2 = array;
                    account2 = account;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzat);
                    final String s2 = s;
                    array2 = array;
                    account2 = account;
                    n2 = zzg;
                    zzp = s2;
                    break;
                }
                case 2: {
                    final Account account3 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    n2 = n;
                    final Scope[] array3 = array;
                    account2 = account3;
                    zzp = s;
                    array2 = array3;
                    break;
                }
                case 3: {
                    final Scope[] array4 = zza.zzb(parcel, zzat, Scope.CREATOR);
                    account2 = account;
                    n2 = n;
                    final String s3 = s;
                    array2 = array4;
                    zzp = s3;
                    break;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzat);
                    array2 = array;
                    account2 = account;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            account = account2;
            array = array2;
            s = zzp;
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new RecordConsentRequest(n, account, array, s);
    }
    
    public RecordConsentRequest[] zzkb(final int n) {
        return new RecordConsentRequest[n];
    }
}
