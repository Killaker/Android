package com.google.android.gms.auth;

import android.os.*;
import java.io.*;
import com.google.android.gms.internal.*;

static final class zzd$2 implements zza<Void> {
    final /* synthetic */ String zzVj;
    final /* synthetic */ Bundle zzVk;
    
    public Void zzao(final IBinder binder) throws RemoteException, IOException, GoogleAuthException {
        final Bundle bundle = (Bundle)zzd.zzn(zzas.zza.zza(binder).zza(this.zzVj, this.zzVk));
        final String string = bundle.getString("Error");
        if (!bundle.getBoolean("booleanResult")) {
            throw new GoogleAuthException(string);
        }
        return null;
    }
}