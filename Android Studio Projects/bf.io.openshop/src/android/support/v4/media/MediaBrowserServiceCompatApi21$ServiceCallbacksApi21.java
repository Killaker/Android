package android.support.v4.media;

import android.media.*;
import android.media.browse.*;
import android.os.*;
import java.util.*;

public static class ServiceCallbacksApi21 implements ServiceCallbacks
{
    private static Object sNullParceledListSliceObj;
    private final IMediaBrowserServiceCallbacksAdapterApi21 mCallbacks;
    
    static {
        final MediaBrowser$MediaItem mediaBrowser$MediaItem = new MediaBrowser$MediaItem(new MediaDescription$Builder().setMediaId("android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM").build(), 0);
        final ArrayList<MediaBrowser$MediaItem> list = new ArrayList<MediaBrowser$MediaItem>();
        list.add(mediaBrowser$MediaItem);
        ServiceCallbacksApi21.sNullParceledListSliceObj = ParceledListSliceAdapterApi21.newInstance(list);
    }
    
    ServiceCallbacksApi21(final Object o) {
        this.mCallbacks = new IMediaBrowserServiceCallbacksAdapterApi21(o);
    }
    
    @Override
    public IBinder asBinder() {
        return this.mCallbacks.asBinder();
    }
    
    @Override
    public void onConnect(final String s, final Object o, final Bundle bundle) throws RemoteException {
        this.mCallbacks.onConnect(s, o, bundle);
    }
    
    @Override
    public void onConnectFailed() throws RemoteException {
        this.mCallbacks.onConnectFailed();
    }
    
    @Override
    public void onLoadChildren(final String s, final List<Parcel> list) throws RemoteException {
        List<MediaBrowser$MediaItem> list2 = null;
        if (list != null) {
            list2 = new ArrayList<MediaBrowser$MediaItem>();
            for (final Parcel parcel : list) {
                parcel.setDataPosition(0);
                list2.add((MediaBrowser$MediaItem)MediaBrowser$MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
        }
        Object o;
        if (Build$VERSION.SDK_INT > 23) {
            if (list2 == null) {
                o = null;
            }
            else {
                o = ParceledListSliceAdapterApi21.newInstance(list2);
            }
        }
        else if (list2 == null) {
            o = ServiceCallbacksApi21.sNullParceledListSliceObj;
        }
        else {
            o = ParceledListSliceAdapterApi21.newInstance(list2);
        }
        this.mCallbacks.onLoadChildren(s, o);
    }
}
