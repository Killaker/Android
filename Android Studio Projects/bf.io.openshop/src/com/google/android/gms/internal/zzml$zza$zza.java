package com.google.android.gms.internal;

import android.os.*;

private static class zza implements zzml
{
    private IBinder zzoz;
    
    zza(final IBinder zzoz) {
        this.zzoz = zzoz;
    }
    
    public IBinder asBinder() {
        return this.zzoz;
    }
    
    @Override
    public void zza(final zzmk zzmk) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.service.ICommonService");
            IBinder binder = null;
            if (zzmk != null) {
                binder = zzmk.asBinder();
            }
            obtain.writeStrongBinder(binder);
            this.zzoz.transact(1, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
}
