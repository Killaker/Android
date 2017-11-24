package android.support.v4.media;

import android.support.v4.media.session.*;
import android.support.v4.app.*;
import android.os.*;
import java.util.*;

private class ServiceCallbacksApi21 implements ServiceCallbacks
{
    final MediaBrowserServiceCompatApi21.ServiceCallbacks mCallbacks;
    Messenger mMessenger;
    
    ServiceCallbacksApi21(final MediaBrowserServiceCompatApi21.ServiceCallbacks mCallbacks) {
        this.mCallbacks = mCallbacks;
    }
    
    @Override
    public IBinder asBinder() {
        return this.mCallbacks.asBinder();
    }
    
    @Override
    public void onConnect(final String s, final MediaSessionCompat.Token token, Bundle bundle) throws RemoteException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.mMessenger = new Messenger((Handler)MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this));
        BundleCompat.putBinder(bundle, "extra_messenger", this.mMessenger.getBinder());
        bundle.putInt("extra_service_version", 1);
        this.mCallbacks.onConnect(s, token.getToken(), bundle);
    }
    
    @Override
    public void onConnectFailed() throws RemoteException {
        this.mCallbacks.onConnectFailed();
    }
    
    @Override
    public void onLoadChildren(final String s, final List<MediaBrowserCompat.MediaItem> list, final Bundle bundle) throws RemoteException {
        List<Parcel> list2 = null;
        if (list != null) {
            list2 = new ArrayList<Parcel>();
            for (final MediaBrowserCompat.MediaItem mediaItem : list) {
                final Parcel obtain = Parcel.obtain();
                mediaItem.writeToParcel(obtain, 0);
                list2.add(obtain);
            }
        }
        this.mCallbacks.onLoadChildren(s, list2);
    }
}
