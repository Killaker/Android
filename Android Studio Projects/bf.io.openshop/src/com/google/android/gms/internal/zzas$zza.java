package com.google.android.gms.internal;

import android.accounts.*;
import com.google.android.gms.auth.*;
import android.os.*;

public abstract static class zza extends Binder implements zzas
{
    public static zzas zza(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzas) {
            return (zzas)queryLocalInterface;
        }
        return new zzas.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.auth.IAuthManagerService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                final String string = parcel.readString();
                final String string2 = parcel.readString();
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                final Bundle zza = this.zza(string, string2, bundle);
                parcel2.writeNoException();
                if (zza != null) {
                    parcel2.writeInt(1);
                    zza.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                final String string3 = parcel.readString();
                Bundle bundle2;
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle2 = null;
                }
                final Bundle zza2 = this.zza(string3, bundle2);
                parcel2.writeNoException();
                if (zza2 != null) {
                    parcel2.writeInt(1);
                    zza2.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                AccountChangeEventsRequest accountChangeEventsRequest;
                if (parcel.readInt() != 0) {
                    accountChangeEventsRequest = (AccountChangeEventsRequest)AccountChangeEventsRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    accountChangeEventsRequest = null;
                }
                final AccountChangeEventsResponse zza3 = this.zza(accountChangeEventsRequest);
                parcel2.writeNoException();
                if (zza3 != null) {
                    parcel2.writeInt(1);
                    zza3.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                Account account;
                if (parcel.readInt() != 0) {
                    account = (Account)Account.CREATOR.createFromParcel(parcel);
                }
                else {
                    account = null;
                }
                final String string4 = parcel.readString();
                Bundle bundle3;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle3 = null;
                }
                final Bundle zza4 = this.zza(account, string4, bundle3);
                parcel2.writeNoException();
                if (zza4 != null) {
                    parcel2.writeInt(1);
                    zza4.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                Bundle bundle4;
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle4 = null;
                }
                final Bundle zza5 = this.zza(bundle4);
                parcel2.writeNoException();
                if (zza5 != null) {
                    parcel2.writeInt(1);
                    zza5.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                Account account2;
                if (parcel.readInt() != 0) {
                    account2 = (Account)Account.CREATOR.createFromParcel(parcel);
                }
                else {
                    account2 = null;
                }
                final Bundle zza6 = this.zza(account2);
                parcel2.writeNoException();
                if (zza6 != null) {
                    parcel2.writeInt(1);
                    zza6.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }
    
    private static class zza implements zzas
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public Bundle zza(final Account account) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzoz.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                return null;
            }
        }
        
        @Override
        public Bundle zza(final Account account, final String s, final Bundle bundle) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                        if (account != null) {
                            obtain.writeInt(1);
                            account.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        obtain.writeString(s);
                        if (bundle != null) {
                            obtain.writeInt(1);
                            bundle.writeToParcel(obtain, 0);
                            this.zzoz.transact(5, obtain, obtain2, 0);
                            obtain2.readException();
                            if (obtain2.readInt() != 0) {
                                return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                            }
                            return null;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    obtain.writeInt(0);
                    continue;
                }
                return null;
            }
        }
        
        @Override
        public Bundle zza(final Bundle bundle) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzoz.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                return null;
            }
        }
        
        @Override
        public Bundle zza(final String s, final Bundle bundle) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzoz.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                return null;
            }
        }
        
        @Override
        public Bundle zza(final String s, final String s2, final Bundle bundle) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzoz.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                return null;
            }
        }
        
        @Override
        public AccountChangeEventsResponse zza(final AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                    if (accountChangeEventsRequest != null) {
                        obtain.writeInt(1);
                        accountChangeEventsRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzoz.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        return (AccountChangeEventsResponse)AccountChangeEventsResponse.CREATOR.createFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                return null;
            }
        }
    }
}
