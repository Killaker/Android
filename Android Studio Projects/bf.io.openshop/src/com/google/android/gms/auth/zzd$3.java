package com.google.android.gms.auth;

import java.util.*;
import android.os.*;
import java.io.*;
import com.google.android.gms.internal.*;

static final class zzd$3 implements zza<List<AccountChangeEvent>> {
    final /* synthetic */ String zzVl;
    final /* synthetic */ int zzVm;
    
    public List<AccountChangeEvent> zzap(final IBinder binder) throws RemoteException, IOException, GoogleAuthException {
        return ((AccountChangeEventsResponse)zzd.zzn(zzas.zza.zza(binder).zza(new AccountChangeEventsRequest().setAccountName(this.zzVl).setEventIndex(this.zzVm)))).getEvents();
    }
}