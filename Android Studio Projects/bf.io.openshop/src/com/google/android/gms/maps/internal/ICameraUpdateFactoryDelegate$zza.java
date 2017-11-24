package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.*;
import com.google.android.gms.dynamic.*;
import android.os.*;

public abstract static class zza extends Binder implements ICameraUpdateFactoryDelegate
{
    public static ICameraUpdateFactoryDelegate zzcs(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof ICameraUpdateFactoryDelegate) {
            return (ICameraUpdateFactoryDelegate)queryLocalInterface;
        }
        return new ICameraUpdateFactoryDelegate.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final zzd zoomIn = this.zoomIn();
                parcel2.writeNoException();
                IBinder binder = null;
                if (zoomIn != null) {
                    binder = zoomIn.asBinder();
                }
                parcel2.writeStrongBinder(binder);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final zzd zoomOut = this.zoomOut();
                parcel2.writeNoException();
                IBinder binder2 = null;
                if (zoomOut != null) {
                    binder2 = zoomOut.asBinder();
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final zzd scrollBy = this.scrollBy(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                IBinder binder3 = null;
                if (scrollBy != null) {
                    binder3 = scrollBy.asBinder();
                }
                parcel2.writeStrongBinder(binder3);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final zzd zoomTo = this.zoomTo(parcel.readFloat());
                parcel2.writeNoException();
                IBinder binder4 = null;
                if (zoomTo != null) {
                    binder4 = zoomTo.asBinder();
                }
                parcel2.writeStrongBinder(binder4);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final zzd zoomBy = this.zoomBy(parcel.readFloat());
                parcel2.writeNoException();
                IBinder binder5 = null;
                if (zoomBy != null) {
                    binder5 = zoomBy.asBinder();
                }
                parcel2.writeStrongBinder(binder5);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final zzd zoomByWithFocus = this.zoomByWithFocus(parcel.readFloat(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                IBinder binder6 = null;
                if (zoomByWithFocus != null) {
                    binder6 = zoomByWithFocus.asBinder();
                }
                parcel2.writeStrongBinder(binder6);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                CameraPosition zzfv;
                if (parcel.readInt() != 0) {
                    zzfv = CameraPosition.CREATOR.zzfv(parcel);
                }
                else {
                    zzfv = null;
                }
                final zzd cameraPosition = this.newCameraPosition(zzfv);
                parcel2.writeNoException();
                IBinder binder7 = null;
                if (cameraPosition != null) {
                    binder7 = cameraPosition.asBinder();
                }
                parcel2.writeStrongBinder(binder7);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                LatLng zzfz;
                if (parcel.readInt() != 0) {
                    zzfz = LatLng.CREATOR.zzfz(parcel);
                }
                else {
                    zzfz = null;
                }
                final zzd latLng = this.newLatLng(zzfz);
                parcel2.writeNoException();
                IBinder binder8 = null;
                if (latLng != null) {
                    binder8 = latLng.asBinder();
                }
                parcel2.writeStrongBinder(binder8);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                LatLng zzfz2;
                if (parcel.readInt() != 0) {
                    zzfz2 = LatLng.CREATOR.zzfz(parcel);
                }
                else {
                    zzfz2 = null;
                }
                final zzd latLngZoom = this.newLatLngZoom(zzfz2, parcel.readFloat());
                parcel2.writeNoException();
                IBinder binder9 = null;
                if (latLngZoom != null) {
                    binder9 = latLngZoom.asBinder();
                }
                parcel2.writeStrongBinder(binder9);
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                LatLngBounds zzfy;
                if (parcel.readInt() != 0) {
                    zzfy = LatLngBounds.CREATOR.zzfy(parcel);
                }
                else {
                    zzfy = null;
                }
                final zzd latLngBounds = this.newLatLngBounds(zzfy, parcel.readInt());
                parcel2.writeNoException();
                IBinder binder10 = null;
                if (latLngBounds != null) {
                    binder10 = latLngBounds.asBinder();
                }
                parcel2.writeStrongBinder(binder10);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                LatLngBounds zzfy2;
                if (parcel.readInt() != 0) {
                    zzfy2 = LatLngBounds.CREATOR.zzfy(parcel);
                }
                else {
                    zzfy2 = null;
                }
                final zzd latLngBoundsWithSize = this.newLatLngBoundsWithSize(zzfy2, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                IBinder binder11 = null;
                if (latLngBoundsWithSize != null) {
                    binder11 = latLngBoundsWithSize.asBinder();
                }
                parcel2.writeStrongBinder(binder11);
                return true;
            }
        }
    }
    
    private static class zza implements ICameraUpdateFactoryDelegate
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public zzd newCameraPosition(final CameraPosition cameraPosition) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                if (cameraPosition != null) {
                    obtain.writeInt(1);
                    cameraPosition.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(7, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd newLatLng(final LatLng latLng) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                if (latLng != null) {
                    obtain.writeInt(1);
                    latLng.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
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
        public zzd newLatLngBounds(final LatLngBounds latLngBounds, final int n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                if (latLngBounds != null) {
                    obtain.writeInt(1);
                    latLngBounds.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                obtain.writeInt(n);
                this.zzoz.transact(10, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd newLatLngBoundsWithSize(final LatLngBounds latLngBounds, final int n, final int n2, final int n3) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                if (latLngBounds != null) {
                    obtain.writeInt(1);
                    latLngBounds.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                obtain.writeInt(n);
                obtain.writeInt(n2);
                obtain.writeInt(n3);
                this.zzoz.transact(11, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd newLatLngZoom(final LatLng latLng, final float n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                if (latLng != null) {
                    obtain.writeInt(1);
                    latLng.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                obtain.writeFloat(n);
                this.zzoz.transact(9, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd scrollBy(final float n, final float n2) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                obtain.writeFloat(n);
                obtain.writeFloat(n2);
                this.zzoz.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd zoomBy(final float n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                obtain.writeFloat(n);
                this.zzoz.transact(5, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd zoomByWithFocus(final float n, final int n2, final int n3) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                obtain.writeFloat(n);
                obtain.writeInt(n2);
                obtain.writeInt(n3);
                this.zzoz.transact(6, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd zoomIn() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                this.zzoz.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd zoomOut() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                this.zzoz.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public zzd zoomTo(final float n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                obtain.writeFloat(n);
                this.zzoz.transact(4, obtain, obtain2, 0);
                obtain2.readException();
                return zzd.zza.zzbs(obtain2.readStrongBinder());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
