package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import android.accounts.*;
import android.os.*;

public class zzi implements Parcelable$Creator<GetServiceRequest>
{
    static void zza(final GetServiceRequest getServiceRequest, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.zzall);
        zzb.zzc(parcel, 3, getServiceRequest.zzalm);
        zzb.zza(parcel, 4, getServiceRequest.zzaln, false);
        zzb.zza(parcel, 5, getServiceRequest.zzalo, false);
        zzb.zza(parcel, 6, getServiceRequest.zzalp, n, false);
        zzb.zza(parcel, 7, getServiceRequest.zzalq, false);
        zzb.zza(parcel, 8, (Parcelable)getServiceRequest.zzalr, n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public GetServiceRequest zzao(final Parcel parcel) {
        int zzg = 0;
        Account account = null;
        final int zzau = zza.zzau(parcel);
        Bundle zzr = null;
        Scope[] array = null;
        IBinder zzq = null;
        String zzp = null;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzau) {
            final int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                default: {
                    zza.zzb(parcel, zzat);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 2: {
                    zzg2 = zza.zzg(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzq = zza.zzq(parcel, zzat);
                    continue;
                }
                case 6: {
                    array = zza.zzb(parcel, zzat, Scope.CREATOR);
                    continue;
                }
                case 7: {
                    zzr = zza.zzr(parcel, zzat);
                    continue;
                }
                case 8: {
                    account = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new GetServiceRequest(zzg3, zzg2, zzg, zzp, zzq, array, zzr, account);
    }
    
    public GetServiceRequest[] zzbR(final int n) {
        return new GetServiceRequest[n];
    }
}
