package com.google.android.gms.signin.internal;

import android.content.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.auth.api.signin.internal.*;
import android.accounts.*;
import com.google.android.gms.auth.api.signin.*;
import android.util.*;
import android.os.*;
import com.google.android.gms.common.internal.*;

public class zzh extends zzj<zze> implements zzrn
{
    private final zzf zzahz;
    private Integer zzale;
    private final Bundle zzbgU;
    private final boolean zzbhi;
    
    public zzh(final Context context, final Looper looper, final boolean zzbhi, final zzf zzahz, final Bundle zzbgU, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, zzahz, connectionCallbacks, onConnectionFailedListener);
        this.zzbhi = zzbhi;
        this.zzahz = zzahz;
        this.zzbgU = zzbgU;
        this.zzale = zzahz.zzqz();
    }
    
    public zzh(final Context context, final Looper looper, final boolean b, final zzf zzf, final zzro zzro, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, b, zzf, zza(zzf), connectionCallbacks, onConnectionFailedListener);
    }
    
    private ResolveAccountRequest zzFN() {
        final Account zzqq = this.zzahz.zzqq();
        final boolean equals = "<<default account>>".equals(zzqq.name);
        GoogleSignInAccount zzno = null;
        if (equals) {
            zzno = zzq.zzaf(this.getContext()).zzno();
        }
        return new ResolveAccountRequest(zzqq, this.zzale, zzno);
    }
    
    public static Bundle zza(final zzf zzf) {
        final zzro zzqy = zzf.zzqy();
        final Integer zzqz = zzf.zzqz();
        final Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", (Parcelable)zzf.getAccount());
        if (zzqz != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", (int)zzqz);
        }
        if (zzqy != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzqy.zzFH());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzqy.zzmO());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzqy.zzmR());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzqy.zzmQ());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzqy.zzmS());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzqy.zzFI());
        }
        return bundle;
    }
    
    @Override
    public void connect() {
        this.zza(new zzj.zzf(this));
    }
    
    @Override
    public void zzFG() {
        try {
            this.zzqJ().zzka(this.zzale);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }
    
    @Override
    public void zza(final zzp zzp, final boolean b) {
        try {
            this.zzqJ().zza(zzp, this.zzale, b);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }
    
    @Override
    public void zza(final zzd zzd) {
        zzx.zzb(zzd, "Expecting a valid ISignInCallbacks");
        try {
            this.zzqJ().zza(new SignInRequest(this.zzFN()), zzd);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zzd.zzb(new SignInResponse(8));
            }
            catch (RemoteException ex2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", (Throwable)ex);
            }
        }
    }
    
    protected zze zzec(final IBinder binder) {
        return zze.zza.zzeb(binder);
    }
    
    @Override
    protected String zzgu() {
        return "com.google.android.gms.signin.service.START";
    }
    
    @Override
    protected String zzgv() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }
    
    @Override
    public boolean zzmE() {
        return this.zzbhi;
    }
    
    @Override
    protected Bundle zzml() {
        if (!this.getContext().getPackageName().equals(this.zzahz.zzqv())) {
            this.zzbgU.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzahz.zzqv());
        }
        return this.zzbgU;
    }
}
