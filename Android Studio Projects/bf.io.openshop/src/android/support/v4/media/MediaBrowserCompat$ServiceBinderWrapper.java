package android.support.v4.media;

import android.content.*;
import android.support.v4.os.*;
import android.os.*;

private static class ServiceBinderWrapper
{
    private Messenger mMessenger;
    
    public ServiceBinderWrapper(final IBinder binder) {
        this.mMessenger = new Messenger(binder);
    }
    
    private void sendRequest(final int what, final Bundle data, final Messenger replyTo) throws RemoteException {
        final Message obtain = Message.obtain();
        obtain.what = what;
        obtain.arg1 = 1;
        obtain.setData(data);
        obtain.replyTo = replyTo;
        this.mMessenger.send(obtain);
    }
    
    void addSubscription(final String s, final Bundle bundle, final Messenger messenger) throws RemoteException {
        final Bundle bundle2 = new Bundle();
        bundle2.putString("data_media_item_id", s);
        bundle2.putBundle("data_options", bundle);
        this.sendRequest(3, bundle2, messenger);
    }
    
    void connect(final Context context, final Bundle bundle, final Messenger messenger) throws RemoteException {
        final Bundle bundle2 = new Bundle();
        bundle2.putString("data_package_name", context.getPackageName());
        bundle2.putBundle("data_root_hints", bundle);
        this.sendRequest(1, bundle2, messenger);
    }
    
    void disconnect(final Messenger messenger) throws RemoteException {
        this.sendRequest(2, null, messenger);
    }
    
    void getMediaItem(final String s, final ResultReceiver resultReceiver) throws RemoteException {
        final Bundle bundle = new Bundle();
        bundle.putString("data_media_item_id", s);
        bundle.putParcelable("data_result_receiver", (Parcelable)resultReceiver);
        this.sendRequest(5, bundle, null);
    }
    
    void registerCallbackMessenger(final Messenger messenger) throws RemoteException {
        this.sendRequest(6, null, messenger);
    }
    
    void removeSubscription(final String s, final Bundle bundle, final Messenger messenger) throws RemoteException {
        final Bundle bundle2 = new Bundle();
        bundle2.putString("data_media_item_id", s);
        bundle2.putBundle("data_options", bundle);
        this.sendRequest(4, bundle2, messenger);
    }
}
