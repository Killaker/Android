package android.support.v4.media;

import android.support.v4.media.session.*;
import android.os.*;
import java.util.*;

private class ServiceCallbacksCompat implements ServiceCallbacks
{
    final Messenger mCallbacks;
    
    ServiceCallbacksCompat(final Messenger mCallbacks) {
        this.mCallbacks = mCallbacks;
    }
    
    private void sendRequest(final int what, final Bundle data) throws RemoteException {
        final Message obtain = Message.obtain();
        obtain.what = what;
        obtain.arg1 = 1;
        obtain.setData(data);
        this.mCallbacks.send(obtain);
    }
    
    @Override
    public IBinder asBinder() {
        return this.mCallbacks.getBinder();
    }
    
    @Override
    public void onConnect(final String s, final MediaSessionCompat.Token token, Bundle bundle) throws RemoteException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("extra_service_version", 1);
        final Bundle bundle2 = new Bundle();
        bundle2.putString("data_media_item_id", s);
        bundle2.putParcelable("data_media_session_token", (Parcelable)token);
        bundle2.putBundle("data_root_hints", bundle);
        this.sendRequest(1, bundle2);
    }
    
    @Override
    public void onConnectFailed() throws RemoteException {
        this.sendRequest(2, null);
    }
    
    @Override
    public void onLoadChildren(final String s, final List<MediaBrowserCompat.MediaItem> list, final Bundle bundle) throws RemoteException {
        final Bundle bundle2 = new Bundle();
        bundle2.putString("data_media_item_id", s);
        bundle2.putBundle("data_options", bundle);
        if (list != null) {
            ArrayList list2;
            if (list instanceof ArrayList) {
                list2 = (ArrayList<? extends E>)list;
            }
            else {
                list2 = new ArrayList((Collection<? extends E>)list);
            }
            bundle2.putParcelableArrayList("data_media_item_list", list2);
        }
        this.sendRequest(3, bundle2);
    }
}
