package com.google.android.gms.gcm;

import android.os.*;

public interface zzc extends IInterface
{
    void zzhe(final int p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzc
    {
        public static zzc zzbZ(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.gcm.INetworkTaskCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzc) {
                return (zzc)queryLocalInterface;
            }
            return new zzc.zza.zza(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.gcm.INetworkTaskCallback");
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.gcm.INetworkTaskCallback");
                    this.zzhe(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class zza implements zzc
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void zzhe(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.gcm.INetworkTaskCallback");
                    obtain.writeInt(n);
                    this.zzoz.transact(2, obtain, obtain2, 0);
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
