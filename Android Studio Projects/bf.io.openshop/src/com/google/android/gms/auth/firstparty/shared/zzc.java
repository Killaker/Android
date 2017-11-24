package com.google.android.gms.auth.firstparty.shared;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;

public class zzc implements Parcelable$Creator<ScopeDetail>
{
    static void zza(final ScopeDetail scopeDetail, final Parcel parcel, final int n) {
        final int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, scopeDetail.version);
        zzb.zza(parcel, 2, scopeDetail.description, false);
        zzb.zza(parcel, 3, scopeDetail.zzYw, false);
        zzb.zza(parcel, 4, scopeDetail.zzYx, false);
        zzb.zza(parcel, 5, scopeDetail.zzYy, false);
        zzb.zza(parcel, 6, scopeDetail.zzYz, false);
        zzb.zzb(parcel, 7, scopeDetail.zzYA, false);
        zzb.zza(parcel, 8, (Parcelable)scopeDetail.zzYB, n, false);
        zzb.zzI(parcel, zzav);
    }
    
    public ScopeDetail zzY(final Parcel parcel) {
        FACLData faclData = null;
        final int zzau = zza.zzau(parcel);
        int zzg = 0;
        ArrayList<String> zzD = new ArrayList<String>();
        String zzp = null;
        String zzp2 = null;
        String zzp3 = null;
        String zzp4 = null;
        String zzp5 = null;
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
                    zzp5 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 3: {
                    zzp4 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 4: {
                    zzp3 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 5: {
                    zzp2 = zza.zzp(parcel, zzat);
                    continue;
                }
                case 6: {
                    zzp = zza.zzp(parcel, zzat);
                    continue;
                }
                case 7: {
                    zzD = zza.zzD(parcel, zzat);
                    continue;
                }
                case 8: {
                    faclData = zza.zza(parcel, zzat, (android.os.Parcelable$Creator<FACLData>)FACLData.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzau) {
            throw new zza.zza("Overread allowed size end=" + zzau, parcel);
        }
        return new ScopeDetail(zzg, zzp5, zzp4, zzp3, zzp2, zzp, zzD, faclData);
    }
    
    public ScopeDetail[] zzaV(final int n) {
        return new ScopeDetail[n];
    }
}
