package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.*;
import android.os.*;

public abstract static class zza extends Binder implements zzf
{
    public zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
    }
    
    public static zzf zzcB(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzf) {
            return (zzf)queryLocalInterface;
        }
        return new zzf.zza.zza(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
                this.zza(zzc.zza.zzdf(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
    
    private static class zza implements zzf
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public void zza(final zzc zzc) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
                IBinder binder;
                if (zzc != null) {
                    binder = zzc.asBinder();
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
    }
}
