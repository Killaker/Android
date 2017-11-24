package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

public interface zze extends IInterface
{
    void onCameraChange(final CameraPosition p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zze
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnCameraChangeListener");
        }
        
        public static zze zzcA(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnCameraChangeListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof zze) {
                return (zze)queryLocalInterface;
            }
            return new zze.zza.zza(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnCameraChangeListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnCameraChangeListener");
                    CameraPosition zzfv;
                    if (parcel.readInt() != 0) {
                        zzfv = CameraPosition.CREATOR.zzfv(parcel);
                    }
                    else {
                        zzfv = null;
                    }
                    this.onCameraChange(zzfv);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class zza implements zze
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void onCameraChange(final CameraPosition cameraPosition) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnCameraChangeListener");
                    if (cameraPosition != null) {
                        obtain.writeInt(1);
                        cameraPosition.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
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
