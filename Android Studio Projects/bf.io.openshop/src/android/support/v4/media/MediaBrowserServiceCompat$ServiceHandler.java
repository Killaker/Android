package android.support.v4.media;

import android.util.*;
import android.support.v4.os.*;
import android.os.*;

private final class ServiceHandler extends Handler
{
    private final ServiceImpl mServiceImpl;
    
    private ServiceHandler() {
        this.mServiceImpl = new ServiceImpl();
    }
    
    public ServiceImpl getServiceImpl() {
        return this.mServiceImpl;
    }
    
    public void handleMessage(final Message message) {
        final Bundle data = message.getData();
        switch (message.what) {
            default: {
                Log.w("MediaBrowserServiceCompat", "Unhandled message: " + message + "\n  Service version: " + 1 + "\n  Client version: " + message.arg1);
            }
            case 1: {
                this.mServiceImpl.connect(data.getString("data_package_name"), data.getInt("data_calling_uid"), data.getBundle("data_root_hints"), new ServiceCallbacksCompat(message.replyTo));
            }
            case 2: {
                this.mServiceImpl.disconnect(new ServiceCallbacksCompat(message.replyTo));
            }
            case 3: {
                this.mServiceImpl.addSubscription(data.getString("data_media_item_id"), data.getBundle("data_options"), new ServiceCallbacksCompat(message.replyTo));
            }
            case 4: {
                this.mServiceImpl.removeSubscription(data.getString("data_media_item_id"), data.getBundle("data_options"), new ServiceCallbacksCompat(message.replyTo));
            }
            case 5: {
                this.mServiceImpl.getMediaItem(data.getString("data_media_item_id"), (ResultReceiver)data.getParcelable("data_result_receiver"));
            }
            case 6: {
                this.mServiceImpl.registerCallbacks(new ServiceCallbacksCompat(message.replyTo));
            }
        }
    }
    
    public void postOrRun(final Runnable runnable) {
        if (Thread.currentThread() == this.getLooper().getThread()) {
            runnable.run();
            return;
        }
        this.post(runnable);
    }
    
    public boolean sendMessageAtTime(final Message message, final long n) {
        final Bundle data = message.getData();
        data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
        data.putInt("data_calling_uid", Binder.getCallingUid());
        return super.sendMessageAtTime(message, n);
    }
}
