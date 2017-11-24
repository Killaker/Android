package com.google.android.gms.common.internal;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import android.accounts.*;
import com.google.android.gms.auth.api.signin.*;

public class zzy implements Parcelable$Creator<ResolveAccountRequest>
{
    static void zza(final ResolveAccountRequest resolveAccountRequest, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, resolveAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)resolveAccountRequest.getAccount(), n, false);
        zzb.zzc(parcel, 3, resolveAccountRequest.getSessionId());
        zzb.zza(parcel, 4, (Parcelable)resolveAccountRequest.zzqW(), n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public ResolveAccountRequest zzap(final Parcel parcel) {
        GoogleSignInAccount googleSignInAccount = null;
        int n = 0;
        final int zzau = zza.zzau(parcel);
        Account account = null;
        int n2 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            GoogleSignInAccount googleSignInAccount2 = null;
            int n3 = 0;
            Account account2 = null;
            int n4 = 0;
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    googleSignInAccount2 = googleSignInAccount;
                    n3 = n;
                    account2 = account;
                    n4 = n2;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzat);
                    final GoogleSignInAccount googleSignInAccount3 = googleSignInAccount;
                    n3 = n;
                    account2 = account;
                    n4 = zzg;
                    googleSignInAccount2 = googleSignInAccount3;
                    break;
                }
                case 2: {
                    final Account account3 = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    n4 = n2;
                    final int n5 = n;
                    account2 = account3;
                    googleSignInAccount2 = googleSignInAccount;
                    n3 = n5;
                    break;
                }
                case 3: {
                    final int zzg2 = zza.zzg(parcel, zzat);
                    account2 = account;
                    n4 = n2;
                    final GoogleSignInAccount googleSignInAccount4 = googleSignInAccount;
                    n3 = zzg2;
                    googleSignInAccount2 = googleSignInAccount4;
                    break;
                }
                case 4: {
                    googleSignInAccount2 = zza.zza(parcel, zzat, GoogleSignInAccount.CREATOR);
                    n3 = n;
                    account2 = account;
                    n4 = n2;
                    break;
                }
            }
            n2 = n4;
            account = account2;
            n = n3;
            googleSignInAccount = googleSignInAccount2;
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new ResolveAccountRequest(n2, account, n, googleSignInAccount);
    }
    
    public ResolveAccountRequest[] zzbW(final int n) {
        return new ResolveAccountRequest[n];
    }
}
