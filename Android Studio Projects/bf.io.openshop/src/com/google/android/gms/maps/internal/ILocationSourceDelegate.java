package com.google.android.gms.maps.internal;

import android.os.*;

public interface ILocationSourceDelegate extends IInterface
{
    void activate(final zzk p0) throws RemoteException;
    
    void deactivate() throws RemoteException;
    
    public abstract static class zza extends Binder implements ILocationSourceDelegate
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.ILocationSourceDelegate");
        }
        
        public static ILocationSourceDelegate zzcx(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.ILocationSourceDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof ILocationSourceDelegate) {
                return (ILocationSourceDelegate)queryLocalInterface;
            }
            return new ILocationSourceDelegate.zza.zza(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.ILocationSourceDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ILocationSourceDelegate");
                    this.activate(zzk.zza.zzcG(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ILocationSourceDelegate");
                    this.deactivate();
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class zza implements ILocationSourceDelegate
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            @Override
            public void activate(final zzk zzk) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ILocationSourceDelegate");
                    IBinder binder;
                    if (zzk != null) {
                        binder = zzk.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzoz.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void deactivate() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ILocationSourceDelegate");
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
