package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.*;
import java.util.*;
import android.os.*;

public abstract static class zza extends Binder implements IPolylineDelegate
{
    public static IPolylineDelegate zzdk(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof IPolylineDelegate) {
            return (IPolylineDelegate)queryLocalInterface;
        }
        return new IPolylineDelegate.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.remove();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final String id = this.getId();
                parcel2.writeNoException();
                parcel2.writeString(id);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.setPoints(parcel.createTypedArrayList((Parcelable$Creator)LatLng.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final List<LatLng> points = this.getPoints();
                parcel2.writeNoException();
                parcel2.writeTypedList((List)points);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.setWidth(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final float width = this.getWidth();
                parcel2.writeNoException();
                parcel2.writeFloat(width);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.setColor(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final int color = this.getColor();
                parcel2.writeNoException();
                parcel2.writeInt(color);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.setZIndex(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final float zIndex = this.getZIndex();
                parcel2.writeNoException();
                parcel2.writeFloat(zIndex);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final int int1 = parcel.readInt();
                boolean visible = false;
                if (int1 != 0) {
                    visible = true;
                }
                this.setVisible(visible);
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final boolean visible2 = this.isVisible();
                parcel2.writeNoException();
                int n3 = 0;
                if (visible2) {
                    n3 = 1;
                }
                parcel2.writeInt(n3);
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final int int2 = parcel.readInt();
                boolean geodesic = false;
                if (int2 != 0) {
                    geodesic = true;
                }
                this.setGeodesic(geodesic);
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final boolean geodesic2 = this.isGeodesic();
                parcel2.writeNoException();
                int n4 = 0;
                if (geodesic2) {
                    n4 = 1;
                }
                parcel2.writeInt(n4);
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final boolean equalsRemote = this.equalsRemote(zzdk(parcel.readStrongBinder()));
                parcel2.writeNoException();
                int n5 = 0;
                if (equalsRemote) {
                    n5 = 1;
                }
                parcel2.writeInt(n5);
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final int hashCodeRemote = this.hashCodeRemote();
                parcel2.writeNoException();
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final int int3 = parcel.readInt();
                boolean clickable = false;
                if (int3 != 0) {
                    clickable = true;
                }
                this.setClickable(clickable);
                parcel2.writeNoException();
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                final boolean clickable2 = this.isClickable();
                parcel2.writeNoException();
                int n6 = 0;
                if (clickable2) {
                    n6 = 1;
                }
                parcel2.writeInt(n6);
                return true;
            }
        }
    }
    
    private static class zza implements IPolylineDelegate
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public boolean equalsRemote(final IPolylineDelegate polylineDelegate) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                IBinder binder;
                if (polylineDelegate != null) {
                    binder = polylineDelegate.asBinder();
                }
                else {
                    binder = null;
                }
                obtain.writeStrongBinder(binder);
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
        public int getColor() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.zzoz.transact(8, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt();
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
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
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
        public List<LatLng> getPoints() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.zzoz.transact(4, obtain, obtain2, 0);
                obtain2.readException();
                return (List<LatLng>)obtain2.createTypedArrayList((Parcelable$Creator)LatLng.CREATOR);
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public float getWidth() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.zzoz.transact(6, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readFloat();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public float getZIndex() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.zzoz.transact(10, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readFloat();
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
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.zzoz.transact(16, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public boolean isClickable() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.zzoz.transact(18, obtain, obtain2, 0);
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
        public boolean isGeodesic() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
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
        public boolean isVisible() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
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
        public void remove() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                this.zzoz.transact(1, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setClickable(final boolean b) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                int n = 0;
                if (b) {
                    n = 1;
                }
                obtain.writeInt(n);
                this.zzoz.transact(17, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setColor(final int n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
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
        public void setGeodesic(final boolean b) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                int n = 0;
                if (b) {
                    n = 1;
                }
                obtain.writeInt(n);
                this.zzoz.transact(13, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setPoints(final List<LatLng> list) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                obtain.writeTypedList((List)list);
                this.zzoz.transact(3, obtain, obtain2, 0);
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
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                int n = 0;
                if (b) {
                    n = 1;
                }
                obtain.writeInt(n);
                this.zzoz.transact(11, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setWidth(final float n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                obtain.writeFloat(n);
                this.zzoz.transact(5, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setZIndex(final float n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                obtain.writeFloat(n);
                this.zzoz.transact(9, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
