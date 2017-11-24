package com.google.android.gms.maps.internal;

import android.os.*;

public interface IUiSettingsDelegate extends IInterface
{
    boolean isCompassEnabled() throws RemoteException;
    
    boolean isIndoorLevelPickerEnabled() throws RemoteException;
    
    boolean isMapToolbarEnabled() throws RemoteException;
    
    boolean isMyLocationButtonEnabled() throws RemoteException;
    
    boolean isRotateGesturesEnabled() throws RemoteException;
    
    boolean isScrollGesturesEnabled() throws RemoteException;
    
    boolean isTiltGesturesEnabled() throws RemoteException;
    
    boolean isZoomControlsEnabled() throws RemoteException;
    
    boolean isZoomGesturesEnabled() throws RemoteException;
    
    void setAllGesturesEnabled(final boolean p0) throws RemoteException;
    
    void setCompassEnabled(final boolean p0) throws RemoteException;
    
    void setIndoorLevelPickerEnabled(final boolean p0) throws RemoteException;
    
    void setMapToolbarEnabled(final boolean p0) throws RemoteException;
    
    void setMyLocationButtonEnabled(final boolean p0) throws RemoteException;
    
    void setRotateGesturesEnabled(final boolean p0) throws RemoteException;
    
    void setScrollGesturesEnabled(final boolean p0) throws RemoteException;
    
    void setTiltGesturesEnabled(final boolean p0) throws RemoteException;
    
    void setZoomControlsEnabled(final boolean p0) throws RemoteException;
    
    void setZoomGesturesEnabled(final boolean p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements IUiSettingsDelegate
    {
        public static IUiSettingsDelegate zzdc(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof IUiSettingsDelegate) {
                return (IUiSettingsDelegate)queryLocalInterface;
            }
            return new IUiSettingsDelegate.zza.zza(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int1 = parcel.readInt();
                    boolean zoomControlsEnabled = false;
                    if (int1 != 0) {
                        zoomControlsEnabled = true;
                    }
                    this.setZoomControlsEnabled(zoomControlsEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int2 = parcel.readInt();
                    boolean compassEnabled = false;
                    if (int2 != 0) {
                        compassEnabled = true;
                    }
                    this.setCompassEnabled(compassEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int3 = parcel.readInt();
                    boolean myLocationButtonEnabled = false;
                    if (int3 != 0) {
                        myLocationButtonEnabled = true;
                    }
                    this.setMyLocationButtonEnabled(myLocationButtonEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int4 = parcel.readInt();
                    boolean scrollGesturesEnabled = false;
                    if (int4 != 0) {
                        scrollGesturesEnabled = true;
                    }
                    this.setScrollGesturesEnabled(scrollGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int5 = parcel.readInt();
                    boolean zoomGesturesEnabled = false;
                    if (int5 != 0) {
                        zoomGesturesEnabled = true;
                    }
                    this.setZoomGesturesEnabled(zoomGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int6 = parcel.readInt();
                    boolean tiltGesturesEnabled = false;
                    if (int6 != 0) {
                        tiltGesturesEnabled = true;
                    }
                    this.setTiltGesturesEnabled(tiltGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int7 = parcel.readInt();
                    boolean rotateGesturesEnabled = false;
                    if (int7 != 0) {
                        rotateGesturesEnabled = true;
                    }
                    this.setRotateGesturesEnabled(rotateGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int8 = parcel.readInt();
                    boolean allGesturesEnabled = false;
                    if (int8 != 0) {
                        allGesturesEnabled = true;
                    }
                    this.setAllGesturesEnabled(allGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean zoomControlsEnabled2 = this.isZoomControlsEnabled();
                    parcel2.writeNoException();
                    int n3 = 0;
                    if (zoomControlsEnabled2) {
                        n3 = 1;
                    }
                    parcel2.writeInt(n3);
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean compassEnabled2 = this.isCompassEnabled();
                    parcel2.writeNoException();
                    int n4 = 0;
                    if (compassEnabled2) {
                        n4 = 1;
                    }
                    parcel2.writeInt(n4);
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean myLocationButtonEnabled2 = this.isMyLocationButtonEnabled();
                    parcel2.writeNoException();
                    int n5 = 0;
                    if (myLocationButtonEnabled2) {
                        n5 = 1;
                    }
                    parcel2.writeInt(n5);
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean scrollGesturesEnabled2 = this.isScrollGesturesEnabled();
                    parcel2.writeNoException();
                    int n6 = 0;
                    if (scrollGesturesEnabled2) {
                        n6 = 1;
                    }
                    parcel2.writeInt(n6);
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean zoomGesturesEnabled2 = this.isZoomGesturesEnabled();
                    parcel2.writeNoException();
                    int n7 = 0;
                    if (zoomGesturesEnabled2) {
                        n7 = 1;
                    }
                    parcel2.writeInt(n7);
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean tiltGesturesEnabled2 = this.isTiltGesturesEnabled();
                    parcel2.writeNoException();
                    int n8 = 0;
                    if (tiltGesturesEnabled2) {
                        n8 = 1;
                    }
                    parcel2.writeInt(n8);
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean rotateGesturesEnabled2 = this.isRotateGesturesEnabled();
                    parcel2.writeNoException();
                    int n9 = 0;
                    if (rotateGesturesEnabled2) {
                        n9 = 1;
                    }
                    parcel2.writeInt(n9);
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int9 = parcel.readInt();
                    boolean indoorLevelPickerEnabled = false;
                    if (int9 != 0) {
                        indoorLevelPickerEnabled = true;
                    }
                    this.setIndoorLevelPickerEnabled(indoorLevelPickerEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean indoorLevelPickerEnabled2 = this.isIndoorLevelPickerEnabled();
                    parcel2.writeNoException();
                    int n10 = 0;
                    if (indoorLevelPickerEnabled2) {
                        n10 = 1;
                    }
                    parcel2.writeInt(n10);
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final int int10 = parcel.readInt();
                    boolean mapToolbarEnabled = false;
                    if (int10 != 0) {
                        mapToolbarEnabled = true;
                    }
                    this.setMapToolbarEnabled(mapToolbarEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean mapToolbarEnabled2 = this.isMapToolbarEnabled();
                    parcel2.writeNoException();
                    int n11 = 0;
                    if (mapToolbarEnabled2) {
                        n11 = 1;
                    }
                    parcel2.writeInt(n11);
                    return true;
                }
            }
        }
        
        private static class zza implements IUiSettingsDelegate
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public boolean isCompassEnabled() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.zzoz.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isIndoorLevelPickerEnabled() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.zzoz.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isMapToolbarEnabled() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.zzoz.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isMyLocationButtonEnabled() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.zzoz.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isRotateGesturesEnabled() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.zzoz.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isScrollGesturesEnabled() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.zzoz.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isTiltGesturesEnabled() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.zzoz.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isZoomControlsEnabled() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.zzoz.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isZoomGesturesEnabled() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.zzoz.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setAllGesturesEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzoz.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setCompassEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzoz.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setIndoorLevelPickerEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzoz.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setMapToolbarEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzoz.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setMyLocationButtonEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzoz.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setRotateGesturesEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzoz.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setScrollGesturesEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
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
            public void setTiltGesturesEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
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
            public void setZoomControlsEnabled(final boolean b) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (!b) {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.zzoz.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setZoomGesturesEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzoz.transact(5, obtain, obtain2, 0);
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
