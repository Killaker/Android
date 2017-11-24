package com.google.android.gms.common.internal;

import android.os.*;

public abstract static class zza extends Binder implements zzq
{
    public static zzq zzaQ(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzq) {
            return (zzq)queryLocalInterface;
        }
        return new zzq.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.common.internal.ICancelToken");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.common.internal.ICancelToken");
                this.cancel();
                return true;
            }
        }
    }
    
    private static class zza implements zzq
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public void cancel() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.common.internal.ICancelToken");
                this.zzoz.transact(2, obtain, (Parcel)null, 1);
            }
            finally {
                obtain.recycle();
            }
        }
    }
}
