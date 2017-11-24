package android.support.v4.media;

import android.util.*;
import android.os.*;
import android.support.v4.os.*;
import android.text.*;

private class ServiceImpl
{
    public void addSubscription(final String s, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
        MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this).postOrRun(new Runnable() {
            @Override
            public void run() {
                final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).get(serviceCallbacks.asBinder());
                if (connectionRecord == null) {
                    Log.w("MediaBrowserServiceCompat", "addSubscription for callback that isn't registered id=" + s);
                    return;
                }
                MediaBrowserServiceCompat.access$700(MediaBrowserServiceCompat.this, s, connectionRecord, bundle);
            }
        });
    }
    
    public void connect(final String s, final int n, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
        if (!MediaBrowserServiceCompat.access$400(MediaBrowserServiceCompat.this, s, n)) {
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + n + " package=" + s);
        }
        MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this).postOrRun(new Runnable() {
            @Override
            public void run() {
                final IBinder binder = serviceCallbacks.asBinder();
                MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).remove(binder);
                final ConnectionRecord connectionRecord = new ConnectionRecord();
                connectionRecord.pkg = s;
                connectionRecord.rootHints = bundle;
                connectionRecord.callbacks = serviceCallbacks;
                connectionRecord.root = MediaBrowserServiceCompat.this.onGetRoot(s, n, bundle);
                Label_0182: {
                    if (connectionRecord.root != null) {
                        break Label_0182;
                    }
                    Log.i("MediaBrowserServiceCompat", "No root for client " + s + " from service " + this.getClass().getName());
                    try {
                        serviceCallbacks.onConnectFailed();
                        return;
                    }
                    catch (RemoteException ex) {
                        Log.w("MediaBrowserServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + s);
                        return;
                    }
                    try {
                        MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).put(binder, connectionRecord);
                        if (MediaBrowserServiceCompat.this.mSession != null) {
                            serviceCallbacks.onConnect(connectionRecord.root.getRootId(), MediaBrowserServiceCompat.this.mSession, connectionRecord.root.getExtras());
                        }
                    }
                    catch (RemoteException ex2) {
                        Log.w("MediaBrowserServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + s);
                        MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).remove(binder);
                    }
                }
            }
        });
    }
    
    public void disconnect(final ServiceCallbacks serviceCallbacks) {
        MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this).postOrRun(new Runnable() {
            @Override
            public void run() {
                if (MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).remove(serviceCallbacks.asBinder()) != null) {}
            }
        });
    }
    
    public void getMediaItem(final String s, final ResultReceiver resultReceiver) {
        if (TextUtils.isEmpty((CharSequence)s) || resultReceiver == null) {
            return;
        }
        MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this).postOrRun(new Runnable() {
            @Override
            public void run() {
                MediaBrowserServiceCompat.access$900(MediaBrowserServiceCompat.this, s, resultReceiver);
            }
        });
    }
    
    public void registerCallbacks(final ServiceCallbacks serviceCallbacks) {
        MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this).postOrRun(new Runnable() {
            @Override
            public void run() {
                final IBinder binder = serviceCallbacks.asBinder();
                MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).remove(binder);
                final ConnectionRecord connectionRecord = new ConnectionRecord();
                connectionRecord.callbacks = serviceCallbacks;
                MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).put(binder, connectionRecord);
            }
        });
    }
    
    public void removeSubscription(final String s, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
        MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this).postOrRun(new Runnable() {
            @Override
            public void run() {
                final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).get(serviceCallbacks.asBinder());
                if (connectionRecord == null) {
                    Log.w("MediaBrowserServiceCompat", "removeSubscription for callback that isn't registered id=" + s);
                }
                else if (!MediaBrowserServiceCompat.access$800(MediaBrowserServiceCompat.this, s, connectionRecord, bundle)) {
                    Log.w("MediaBrowserServiceCompat", "removeSubscription called for " + s + " which is not subscribed");
                }
            }
        });
    }
}
