package com.google.android.gms.maps.internal;

import android.os.*;

public interface zzm extends IInterface
{
    void onMapLoaded() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzm
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnMapLoadedCallback");
        }
        
        public static zzm zzcI(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzm) {
                return (zzm)queryLocalInterface;
            }
            return new zzm.zza.zza(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
                    this.onMapLoaded();
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class zza implements zzm
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void onMapLoaded() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
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
