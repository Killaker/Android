package com.google.android.gms.signin.internal;

import android.accounts.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public abstract static class zza extends Binder implements zze
{
    public static zze zzeb(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zze) {
            return (zze)queryLocalInterface;
        }
        return new zze.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.signin.internal.ISignInService");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                final int int1 = parcel.readInt();
                AuthAccountRequest authAccountRequest = null;
                if (int1 != 0) {
                    authAccountRequest = (AuthAccountRequest)AuthAccountRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(authAccountRequest, zzd.zza.zzea(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                final int int2 = parcel.readInt();
                CheckServerAuthResult checkServerAuthResult = null;
                if (int2 != 0) {
                    checkServerAuthResult = (CheckServerAuthResult)CheckServerAuthResult.CREATOR.createFromParcel(parcel);
                }
                this.zza(checkServerAuthResult);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                this.zzav(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                final int int3 = parcel.readInt();
                ResolveAccountRequest resolveAccountRequest = null;
                if (int3 != 0) {
                    resolveAccountRequest = (ResolveAccountRequest)ResolveAccountRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(resolveAccountRequest, zzt.zza.zzaT(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                this.zzka(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                final int int4 = parcel.readInt();
                final int int5 = parcel.readInt();
                Account account = null;
                if (int5 != 0) {
                    account = (Account)Account.CREATOR.createFromParcel(parcel);
                }
                this.zza(int4, account, zzd.zza.zzea(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                final zzp zzaP = zzp.zza.zzaP(parcel.readStrongBinder());
                final int int6 = parcel.readInt();
                final int int7 = parcel.readInt();
                boolean b = false;
                if (int7 != 0) {
                    b = true;
                }
                this.zza(zzaP, int6, b);
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                final int int8 = parcel.readInt();
                RecordConsentRequest recordConsentRequest = null;
                if (int8 != 0) {
                    recordConsentRequest = (RecordConsentRequest)RecordConsentRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(recordConsentRequest, zzd.zza.zzea(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                this.zzb(zzd.zza.zzea(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                final int int9 = parcel.readInt();
                SignInRequest signInRequest = null;
                if (int9 != 0) {
                    signInRequest = (SignInRequest)SignInRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(signInRequest, zzd.zza.zzea(parcel.readStrongBinder()));
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
        public void zza(final int n, final Account account, final zzd zzd) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                        obtain.writeInt(n);
                        if (account != null) {
                            obtain.writeInt(1);
                            account.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (zzd != null) {
                            final IBinder binder = zzd.asBinder();
                            obtain.writeStrongBinder(binder);
                            this.zzoz.transact(8, obtain, obtain2, 0);
                            obtain2.readException();
                            return;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    final IBinder binder = null;
                    continue;
                }
            }
        }
        
        @Override
        public void zza(final AuthAccountRequest authAccountRequest, final zzd zzd) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                        if (authAccountRequest != null) {
                            obtain.writeInt(1);
                            authAccountRequest.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (zzd != null) {
                            final IBinder binder = zzd.asBinder();
                            obtain.writeStrongBinder(binder);
                            this.zzoz.transact(2, obtain, obtain2, 0);
                            obtain2.readException();
                            return;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    final IBinder binder = null;
                    continue;
                }
            }
        }
        
        @Override
        public void zza(final ResolveAccountRequest resolveAccountRequest, final zzt zzt) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                        if (resolveAccountRequest != null) {
                            obtain.writeInt(1);
                            resolveAccountRequest.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (zzt != null) {
                            final IBinder binder = zzt.asBinder();
                            obtain.writeStrongBinder(binder);
                            this.zzoz.transact(5, obtain, obtain2, 0);
                            obtain2.readException();
                            return;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    final IBinder binder = null;
                    continue;
                }
            }
        }
        
        @Override
        public void zza(final zzp zzp, final int n, final boolean b) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                IBinder binder;
                if (zzp != null) {
                    binder = zzp.asBinder();
                }
                else {
                    binder = null;
                }
                obtain.writeStrongBinder(binder);
                obtain.writeInt(n);
                int n2 = 0;
                if (b) {
                    n2 = 1;
                }
                obtain.writeInt(n2);
                this.zzoz.transact(9, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void zza(final CheckServerAuthResult checkServerAuthResult) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                if (checkServerAuthResult != null) {
                    obtain.writeInt(1);
                    checkServerAuthResult.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(3, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void zza(final RecordConsentRequest recordConsentRequest, final zzd zzd) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                        if (recordConsentRequest != null) {
                            obtain.writeInt(1);
                            recordConsentRequest.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (zzd != null) {
                            final IBinder binder = zzd.asBinder();
                            obtain.writeStrongBinder(binder);
                            this.zzoz.transact(10, obtain, obtain2, 0);
                            obtain2.readException();
                            return;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    final IBinder binder = null;
                    continue;
                }
            }
        }
        
        @Override
        public void zza(final SignInRequest signInRequest, final zzd zzd) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                        if (signInRequest != null) {
                            obtain.writeInt(1);
                            signInRequest.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (zzd != null) {
                            final IBinder binder = zzd.asBinder();
                            obtain.writeStrongBinder(binder);
                            this.zzoz.transact(12, obtain, obtain2, 0);
                            obtain2.readException();
                            return;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    final IBinder binder = null;
                    continue;
                }
            }
        }
        
        @Override
        public void zzav(final boolean b) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                int n = 0;
                if (b) {
                    n = 1;
                }
                obtain.writeInt(n);
                this.zzoz.transact(4, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void zzb(final zzd zzd) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                IBinder binder;
                if (zzd != null) {
                    binder = zzd.asBinder();
                }
                else {
                    binder = null;
                }
                obtain.writeStrongBinder(binder);
                this.zzoz.transact(11, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void zzka(final int n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                obtain.writeInt(n);
                this.zzoz.transact(7, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
