package com.google.android.gms.auth;

import android.accounts.*;
import com.google.android.gms.internal.*;
import android.content.*;
import java.io.*;
import android.os.*;

static final class zzd$1 implements zza<TokenData> {
    final /* synthetic */ Account zzVg;
    final /* synthetic */ String zzVh;
    final /* synthetic */ Bundle zzVi;
    
    public TokenData zzam(final IBinder binder) throws RemoteException, IOException, GoogleAuthException {
        final Bundle bundle = (Bundle)zzd.zzn(zzas.zza.zza(binder).zza(this.zzVg, this.zzVh, this.zzVi));
        final TokenData zzc = TokenData.zzc(bundle, "tokenDetails");
        if (zzc != null) {
            return zzc;
        }
        final String string = bundle.getString("Error");
        final Intent intent = (Intent)bundle.getParcelable("userRecoveryIntent");
        final com.google.android.gms.auth.firstparty.shared.zzd zzbY = com.google.android.gms.auth.firstparty.shared.zzd.zzbY(string);
        if (com.google.android.gms.auth.firstparty.shared.zzd.zza(zzbY)) {
            throw new UserRecoverableAuthException(string, intent);
        }
        if (com.google.android.gms.auth.firstparty.shared.zzd.zzc(zzbY)) {
            throw new IOException(string);
        }
        throw new GoogleAuthException(string);
    }
}