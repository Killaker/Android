package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.*;
import com.google.android.gms.dynamic.*;
import android.os.*;

public abstract static class zza extends Binder implements zzf
{
    public static zzf zzdi(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzf) {
            return (zzf)queryLocalInterface;
        }
        return new zzf.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.remove();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final String id = this.getId();
                parcel2.writeNoException();
                parcel2.writeString(id);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                LatLng zzfz;
                if (parcel.readInt() != 0) {
                    zzfz = LatLng.CREATOR.zzfz(parcel);
                }
                else {
                    zzfz = null;
                }
                this.setPosition(zzfz);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final LatLng position = this.getPosition();
                parcel2.writeNoException();
                if (position != null) {
                    parcel2.writeInt(1);
                    position.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setTitle(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final String title = this.getTitle();
                parcel2.writeNoException();
                parcel2.writeString(title);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setSnippet(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final String snippet = this.getSnippet();
                parcel2.writeNoException();
                parcel2.writeString(snippet);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final int int1 = parcel.readInt();
                boolean draggable = false;
                if (int1 != 0) {
                    draggable = true;
                }
                this.setDraggable(draggable);
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean draggable2 = this.isDraggable();
                parcel2.writeNoException();
                int n3 = 0;
                if (draggable2) {
                    n3 = 1;
                }
                parcel2.writeInt(n3);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.showInfoWindow();
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.hideInfoWindow();
                parcel2.writeNoException();
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean infoWindowShown = this.isInfoWindowShown();
                parcel2.writeNoException();
                int n4 = 0;
                if (infoWindowShown) {
                    n4 = 1;
                }
                parcel2.writeInt(n4);
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final int int2 = parcel.readInt();
                boolean visible = false;
                if (int2 != 0) {
                    visible = true;
                }
                this.setVisible(visible);
                parcel2.writeNoException();
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean visible2 = this.isVisible();
                parcel2.writeNoException();
                int n5 = 0;
                if (visible2) {
                    n5 = 1;
                }
                parcel2.writeInt(n5);
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean zzj = this.zzj(zzdi(parcel.readStrongBinder()));
                parcel2.writeNoException();
                int n6 = 0;
                if (zzj) {
                    n6 = 1;
                }
                parcel2.writeInt(n6);
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final int hashCodeRemote = this.hashCodeRemote();
                parcel2.writeNoException();
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzw(zzd.zza.zzbs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setAnchor(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 20: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final int int3 = parcel.readInt();
                boolean flat = false;
                if (int3 != 0) {
                    flat = true;
                }
                this.setFlat(flat);
                parcel2.writeNoException();
                return true;
            }
            case 21: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean flat2 = this.isFlat();
                parcel2.writeNoException();
                int n7 = 0;
                if (flat2) {
                    n7 = 1;
                }
                parcel2.writeInt(n7);
                return true;
            }
            case 22: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setRotation(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 23: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final float rotation = this.getRotation();
                parcel2.writeNoException();
                parcel2.writeFloat(rotation);
                return true;
            }
            case 24: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setInfoWindowAnchor(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 25: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setAlpha(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 26: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final float alpha = this.getAlpha();
                parcel2.writeNoException();
                parcel2.writeFloat(alpha);
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
        public float getAlpha() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(26, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readFloat();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public String getId() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public LatLng getPosition() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(4, obtain, obtain2, 0);
                obtain2.readException();
                LatLng zzfz;
                if (obtain2.readInt() != 0) {
                    zzfz = LatLng.CREATOR.zzfz(obtain2);
                }
                else {
                    zzfz = null;
                }
                return zzfz;
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public float getRotation() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(23, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readFloat();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public String getSnippet() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(8, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public String getTitle() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(6, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public int hashCodeRemote() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(17, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void hideInfoWindow() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(12, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public boolean isDraggable() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
        public boolean isFlat() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(21, obtain, obtain2, 0);
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
        public boolean isInfoWindowShown() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
        public boolean isVisible() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
        public void remove() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(1, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setAlpha(final float n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                obtain.writeFloat(n);
                this.zzoz.transact(25, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setAnchor(final float n, final float n2) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                obtain.writeFloat(n);
                obtain.writeFloat(n2);
                this.zzoz.transact(19, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setDraggable(final boolean b) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                int n = 0;
                if (b) {
                    n = 1;
                }
                obtain.writeInt(n);
                this.zzoz.transact(9, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setFlat(final boolean b) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                int n = 0;
                if (b) {
                    n = 1;
                }
                obtain.writeInt(n);
                this.zzoz.transact(20, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setInfoWindowAnchor(final float n, final float n2) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                obtain.writeFloat(n);
                obtain.writeFloat(n2);
                this.zzoz.transact(24, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setPosition(final LatLng latLng) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                if (latLng != null) {
                    obtain.writeInt(1);
                    latLng.writeToParcel(obtain, 0);
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
        public void setRotation(final float n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                obtain.writeFloat(n);
                this.zzoz.transact(22, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setSnippet(final String s) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                obtain.writeString(s);
                this.zzoz.transact(7, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setTitle(final String s) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                obtain.writeString(s);
                this.zzoz.transact(5, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setVisible(final boolean b) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                int n = 0;
                if (b) {
                    n = 1;
                }
                obtain.writeInt(n);
                this.zzoz.transact(14, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void showInfoWindow() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.zzoz.transact(11, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public boolean zzj(final zzf zzf) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                IBinder binder;
                if (zzf != null) {
                    binder = zzf.asBinder();
                }
                else {
                    binder = null;
                }
                obtain.writeStrongBinder(binder);
                this.zzoz.transact(16, obtain, obtain2, 0);
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
        public void zzw(final zzd zzd) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                IBinder binder;
                if (zzd != null) {
                    binder = zzd.asBinder();
                }
                else {
                    binder = null;
                }
                obtain.writeStrongBinder(binder);
                this.zzoz.transact(18, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
