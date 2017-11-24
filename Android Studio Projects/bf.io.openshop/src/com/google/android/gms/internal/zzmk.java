package com.google.android.gms.internal;

import android.os.*;

public interface zzmk extends IInterface
{
    void zzcb(final int p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzmk
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.common.internal.service.ICommonCallbacks");
        }
        
        public static zzmk zzaX(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzmk) {
                return (zzmk)queryLocalInterface;
            }
            return new zzmk.zza.zza(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.common.internal.service.ICommonCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.service.ICommonCallbacks");
                    this.zzcb(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class zza implements zzmk
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void zzcb(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.service.ICommonCallbacks");
                    obtain.writeInt(n);
                    this.zzoz.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
