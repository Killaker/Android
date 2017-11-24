package com.google.android.gms.internal;

import com.google.android.gms.clearcut.*;
import android.os.*;

private static class zza implements zzly
{
    private IBinder zzoz;
    
    zza(final IBinder zzoz) {
        this.zzoz = zzoz;
    }
    
    public IBinder asBinder() {
        return this.zzoz;
    }
    
    @Override
    public void zza(final zzlx zzlx, final LogEventParcelable logEventParcelable) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
            IBinder binder = null;
            if (zzlx != null) {
                binder = zzlx.asBinder();
            }
            obtain.writeStrongBinder(binder);
            if (logEventParcelable != null) {
                obtain.writeInt(1);
                logEventParcelable.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zzoz.transact(1, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
}
