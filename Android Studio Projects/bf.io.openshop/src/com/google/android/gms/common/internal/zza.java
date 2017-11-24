package com.google.android.gms.common.internal;

import android.content.*;
import android.accounts.*;
import android.util.*;
import android.os.*;
import com.google.android.gms.common.*;

public class zza extends zzp.zza
{
    private Context mContext;
    private Account zzTI;
    int zzakz;
    
    public static Account zza(final zzp zzp) {
        Account account = null;
        if (zzp == null) {
            return account;
        }
        final long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            account = zzp.getAccount();
            return account;
        }
        catch (RemoteException ex) {
            Log.w("AccountAccessor", "Remote account accessor probably died");
            return null;
        }
        finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof zza && this.zzTI.equals((Object)((zza)o).zzTI));
    }
    
    public Account getAccount() {
        final int callingUid = Binder.getCallingUid();
        if (callingUid == this.zzakz) {
            return this.zzTI;
        }
        if (zze.zzf(this.mContext, callingUid)) {
            this.zzakz = callingUid;
            return this.zzTI;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }
}
