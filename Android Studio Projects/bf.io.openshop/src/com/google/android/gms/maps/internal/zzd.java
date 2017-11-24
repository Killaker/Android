package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.*;
import android.os.*;

public interface zzd extends IInterface
{
    com.google.android.gms.dynamic.zzd zzb(final zzf p0) throws RemoteException;
    
    com.google.android.gms.dynamic.zzd zzc(final zzf p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzd
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
        }
        
        public static zzd zzcw(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzd) {
                return (zzd)queryLocalInterface;
            }
            return new zzd.zza.zza(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    final com.google.android.gms.dynamic.zzd zzb = this.zzb(zzf.zza.zzdi(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder = null;
                    if (zzb != null) {
                        binder = zzb.asBinder();
                    }
                    parcel2.writeStrongBinder(binder);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    final com.google.android.gms.dynamic.zzd zzc = this.zzc(zzf.zza.zzdi(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (zzc != null) {
                        binder2 = zzc.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    return true;
                }
            }
        }
        
        private static class zza implements zzd
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public com.google.android.gms.dynamic.zzd zzb(final zzf zzf) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    IBinder binder;
                    if (zzf != null) {
                        binder = zzf.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzoz.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.zzd.zza.zzbs(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public com.google.android.gms.dynamic.zzd zzc(final zzf zzf) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    IBinder binder;
                    if (zzf != null) {
                        binder = zzf.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzoz.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.zzd.zza.zzbs(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
