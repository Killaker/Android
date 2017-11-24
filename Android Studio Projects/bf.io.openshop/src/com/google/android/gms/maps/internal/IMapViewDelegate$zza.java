package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.*;
import android.os.*;

public abstract static class zza extends Binder implements IMapViewDelegate
{
    public static IMapViewDelegate zzcz(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof IMapViewDelegate) {
            return (IMapViewDelegate)queryLocalInterface;
        }
        return new IMapViewDelegate.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IMapViewDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                final IGoogleMapDelegate map = this.getMap();
                parcel2.writeNoException();
                IBinder binder = null;
                if (map != null) {
                    binder = map.asBinder();
                }
                parcel2.writeStrongBinder(binder);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                final int int1 = parcel.readInt();
                Bundle bundle = null;
                if (int1 != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.onCreate(bundle);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.onResume();
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.onPause();
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.onDestroy();
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.onLowMemory();
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                final int int2 = parcel.readInt();
                Bundle bundle2 = null;
                if (int2 != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.onSaveInstanceState(bundle2);
                parcel2.writeNoException();
                if (bundle2 != null) {
                    parcel2.writeInt(1);
                    bundle2.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                final zzd view = this.getView();
                parcel2.writeNoException();
                IBinder binder2 = null;
                if (view != null) {
                    binder2 = view.asBinder();
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.getMapAsync(zzo.zza.zzcK(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                final int int3 = parcel.readInt();
                Bundle bundle3 = null;
                if (int3 != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.onEnterAmbient(bundle3);
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.onExitAmbient();
                parcel2.writeNoException();
                return true;
            }
        }
    }
    
    private static class zza implements IMapViewDelegate
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public IGoogleMapDelegate getMap() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.zzoz.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return IGoogleMapDelegate.zza.zzcv(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void getMapAsync(final zzo zzo) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                IBinder binder;
                if (zzo != null) {
                    binder = zzo.asBinder();
                }
                else {
                    binder = null;
                }
                obtain.writeStrongBinder(binder);
                this.zzoz.transact(9, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd getView() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.zzoz.transact(8, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void onCreate(final Bundle bundle) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(2, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void onDestroy() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.zzoz.transact(5, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void onEnterAmbient(final Bundle bundle) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(10, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void onExitAmbient() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.zzoz.transact(11, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void onLowMemory() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.zzoz.transact(6, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void onPause() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.zzoz.transact(4, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void onResume() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.zzoz.transact(3, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(7, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    bundle.readFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
