package com.google.android.gms.internal;

import com.google.android.gms.clearcut.*;
import android.os.*;

public abstract static class zza extends Binder implements zzly
{
    public static zzly zzaM(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzly) {
            return (zzly)queryLocalInterface;
        }
        return new zzly.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                final zzlx zzaL = zzlx.zza.zzaL(parcel.readStrongBinder());
                LogEventParcelable zzaf;
                if (parcel.readInt() != 0) {
                    zzaf = LogEventParcelable.CREATOR.zzaf(parcel);
                }
                else {
                    zzaf = null;
                }
                this.zza(zzaL, zzaf);
                return true;
            }
        }
    }
    
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
}
