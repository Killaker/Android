package com.google.android.gms.iid;

import android.os.*;

private final class zza extends zzb.zza
{
    Handler handler;
    
    zza(final Handler handler) {
        this.handler = handler;
    }
    
    public void send(final Message message) throws RemoteException {
        message.arg2 = Binder.getCallingUid();
        this.handler.dispatchMessage(message);
    }
}
