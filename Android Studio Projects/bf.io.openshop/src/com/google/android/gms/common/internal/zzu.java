package com.google.android.gms.common.internal;

import com.google.android.gms.dynamic.*;
import android.os.*;

public interface zzu extends IInterface
{
    zzd zza(final zzd p0, final int p1, final int p2) throws RemoteException;
    
    zzd zza(final zzd p0, final SignInButtonConfig p1) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzu
    {
        public static zzu zzaU(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzu) {
                return (zzu)queryLocalInterface;
            }
            return new zzu.zza.zza(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.common.internal.ISignInButtonCreator");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
                    final zzd zza = this.zza(zzd.zza.zzbs(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    IBinder binder;
                    if (zza != null) {
                        binder = zza.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    parcel2.writeStrongBinder(binder);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
                    final zzd zzbs = zzd.zza.zzbs(parcel.readStrongBinder());
                    SignInButtonConfig signInButtonConfig;
                    if (parcel.readInt() != 0) {
                        signInButtonConfig = (SignInButtonConfig)SignInButtonConfig.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        signInButtonConfig = null;
                    }
                    final zzd zza2 = this.zza(zzbs, signInButtonConfig);
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (zza2 != null) {
                        binder2 = zza2.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    return true;
                }
            }
        }
        
        private static class zza implements zzu
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public zzd zza(final zzd zzd, final int n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.ISignInButtonCreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
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
            public zzd zza(final zzd zzd, final SignInButtonConfig signInButtonConfig) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.ISignInButtonCreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (signInButtonConfig != null) {
                        obtain.writeInt(1);
                        signInButtonConfig.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
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
