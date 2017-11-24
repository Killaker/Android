package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.model.internal.*;
import com.google.android.gms.maps.*;
import android.os.*;

public interface zzc extends IInterface
{
    void init(final zzd p0) throws RemoteException;
    
    ICameraUpdateFactoryDelegate zzAe() throws RemoteException;
    
    com.google.android.gms.maps.model.internal.zza zzAf() throws RemoteException;
    
    IMapViewDelegate zza(final zzd p0, final GoogleMapOptions p1) throws RemoteException;
    
    IStreetViewPanoramaViewDelegate zza(final zzd p0, final StreetViewPanoramaOptions p1) throws RemoteException;
    
    void zzd(final zzd p0, final int p1) throws RemoteException;
    
    IMapFragmentDelegate zzs(final zzd p0) throws RemoteException;
    
    IStreetViewPanoramaFragmentDelegate zzt(final zzd p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzc
    {
        public static zzc zzcu(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzc) {
                return (zzc)queryLocalInterface;
            }
            return new zzc.zza.zza(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.internal.ICreator");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    this.init(zzd.zza.zzbs(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final IMapFragmentDelegate zzs = this.zzs(zzd.zza.zzbs(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder = null;
                    if (zzs != null) {
                        binder = zzs.asBinder();
                    }
                    parcel2.writeStrongBinder(binder);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final zzd zzbs = zzd.zza.zzbs(parcel.readStrongBinder());
                    GoogleMapOptions zzft;
                    if (parcel.readInt() != 0) {
                        zzft = GoogleMapOptions.CREATOR.zzft(parcel);
                    }
                    else {
                        zzft = null;
                    }
                    final IMapViewDelegate zza = this.zza(zzbs, zzft);
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (zza != null) {
                        binder2 = zza.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final ICameraUpdateFactoryDelegate zzAe = this.zzAe();
                    parcel2.writeNoException();
                    IBinder binder3 = null;
                    if (zzAe != null) {
                        binder3 = zzAe.asBinder();
                    }
                    parcel2.writeStrongBinder(binder3);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final zza zzAf = this.zzAf();
                    parcel2.writeNoException();
                    IBinder binder4 = null;
                    if (zzAf != null) {
                        binder4 = zzAf.asBinder();
                    }
                    parcel2.writeStrongBinder(binder4);
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    this.zzd(zzd.zza.zzbs(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final zzd zzbs2 = zzd.zza.zzbs(parcel.readStrongBinder());
                    StreetViewPanoramaOptions zzfu;
                    if (parcel.readInt() != 0) {
                        zzfu = StreetViewPanoramaOptions.CREATOR.zzfu(parcel);
                    }
                    else {
                        zzfu = null;
                    }
                    final IStreetViewPanoramaViewDelegate zza2 = this.zza(zzbs2, zzfu);
                    parcel2.writeNoException();
                    IBinder binder5 = null;
                    if (zza2 != null) {
                        binder5 = zza2.asBinder();
                    }
                    parcel2.writeStrongBinder(binder5);
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final IStreetViewPanoramaFragmentDelegate zzt = this.zzt(zzd.zza.zzbs(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder6 = null;
                    if (zzt != null) {
                        binder6 = zzt.asBinder();
                    }
                    parcel2.writeStrongBinder(binder6);
                    return true;
                }
            }
        }
        
        private static class zza implements zzc
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void init(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
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
            
            @Override
            public ICameraUpdateFactoryDelegate zzAe() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    this.zzoz.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return ICameraUpdateFactoryDelegate.zza.zzcs(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public com.google.android.gms.maps.model.internal.zza zzAf() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    this.zzoz.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.maps.model.internal.zza.zza.zzdd(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IMapViewDelegate zza(final zzd zzd, final GoogleMapOptions googleMapOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (googleMapOptions != null) {
                        obtain.writeInt(1);
                        googleMapOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzoz.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return IMapViewDelegate.zza.zzcz(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IStreetViewPanoramaViewDelegate zza(final zzd zzd, final StreetViewPanoramaOptions streetViewPanoramaOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (streetViewPanoramaOptions != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzoz.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return IStreetViewPanoramaViewDelegate.zza.zzdb(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzd(final zzd zzd, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    this.zzoz.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IMapFragmentDelegate zzs(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzoz.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return IMapFragmentDelegate.zza.zzcy(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IStreetViewPanoramaFragmentDelegate zzt(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzoz.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return IStreetViewPanoramaFragmentDelegate.zza.zzda(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
