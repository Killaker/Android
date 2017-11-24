package com.google.android.gms.maps.internal;

import android.os.*;

public interface zzr extends IInterface
{
    boolean onMyLocationButtonClick() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzr
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
        }
        
        public static zzr zzcN(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzr) {
                return (zzr)queryLocalInterface;
            }
            return new zzr.zza.zza(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
                    final boolean onMyLocationButtonClick = this.onMyLocationButtonClick();
                    parcel2.writeNoException();
                    int n3;
                    if (onMyLocationButtonClick) {
                        n3 = 1;
                    }
                    else {
                        n3 = 0;
                    }
                    parcel2.writeInt(n3);
                    return true;
                }
            }
        }
        
        private static class zza implements zzr
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public boolean onMyLocationButtonClick() throws RemoteException {
                boolean b = true;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
                    this.zzoz.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        b = false;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
