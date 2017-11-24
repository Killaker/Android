package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import android.accounts.*;
import com.google.android.gms.common.*;
import android.os.*;
import java.util.*;

public class GetServiceRequest implements SafeParcelable
{
    public static final Parcelable$Creator<GetServiceRequest> CREATOR;
    final int version;
    final int zzall;
    int zzalm;
    String zzaln;
    IBinder zzalo;
    Scope[] zzalp;
    Bundle zzalq;
    Account zzalr;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    public GetServiceRequest(final int zzall) {
        this.version = 2;
        this.zzalm = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzall = zzall;
    }
    
    GetServiceRequest(final int version, final int zzall, final int zzalm, final String zzaln, final IBinder zzalo, final Scope[] zzalp, final Bundle zzalq, final Account zzalr) {
        this.version = version;
        this.zzall = zzall;
        this.zzalm = zzalm;
        this.zzaln = zzaln;
        if (version < 2) {
            this.zzalr = this.zzaO(zzalo);
        }
        else {
            this.zzalo = zzalo;
            this.zzalr = zzalr;
        }
        this.zzalp = zzalp;
        this.zzalq = zzalq;
    }
    
    private Account zzaO(final IBinder binder) {
        Account zza = null;
        if (binder != null) {
            zza = com.google.android.gms.common.internal.zza.zza(zzp.zza.zzaP(binder));
        }
        return zza;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
    
    public GetServiceRequest zzb(final zzp zzp) {
        if (zzp != null) {
            this.zzalo = zzp.asBinder();
        }
        return this;
    }
    
    public GetServiceRequest zzc(final Account zzalr) {
        this.zzalr = zzalr;
        return this;
    }
    
    public GetServiceRequest zzcG(final String zzaln) {
        this.zzaln = zzaln;
        return this;
    }
    
    public GetServiceRequest zzd(final Collection<Scope> collection) {
        this.zzalp = collection.toArray(new Scope[collection.size()]);
        return this;
    }
    
    public GetServiceRequest zzj(final Bundle zzalq) {
        this.zzalq = zzalq;
        return this;
    }
}
