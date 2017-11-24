package android.support.v4.media;

import java.lang.ref.*;
import android.support.v4.media.session.*;
import android.util.*;
import java.util.*;
import android.os.*;

private static class CallbackHandler extends Handler
{
    private final MediaBrowserServiceCallbackImpl mCallbackImpl;
    private WeakReference<Messenger> mCallbacksMessengerRef;
    
    CallbackHandler(final MediaBrowserServiceCallbackImpl mCallbackImpl) {
        this.mCallbackImpl = mCallbackImpl;
    }
    
    public void handleMessage(final Message message) {
        if (this.mCallbacksMessengerRef == null) {
            return;
        }
        final Bundle data = message.getData();
        data.setClassLoader(MediaSessionCompat.class.getClassLoader());
        switch (message.what) {
            default: {
                Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: " + 1 + "\n  Service version: " + message.arg1);
            }
            case 1: {
                this.mCallbackImpl.onServiceConnected(this.mCallbacksMessengerRef.get(), data.getString("data_media_item_id"), (MediaSessionCompat.Token)data.getParcelable("data_media_session_token"), data.getBundle("data_root_hints"));
            }
            case 2: {
                this.mCallbackImpl.onConnectionFailed(this.mCallbacksMessengerRef.get());
            }
            case 3: {
                this.mCallbackImpl.onLoadChildren(this.mCallbacksMessengerRef.get(), data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), data.getBundle("data_options"));
            }
        }
    }
    
    void setCallbacksMessenger(final Messenger messenger) {
        this.mCallbacksMessengerRef = new WeakReference<Messenger>(messenger);
    }
}
