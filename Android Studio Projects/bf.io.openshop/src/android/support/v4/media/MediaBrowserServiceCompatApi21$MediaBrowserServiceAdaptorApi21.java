package android.support.v4.media;

import android.content.*;
import android.os.*;

static class MediaBrowserServiceAdaptorApi21
{
    ServiceBinderProxyApi21 mBinder;
    
    public IBinder onBind(final Intent intent) {
        if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
            return (IBinder)this.mBinder;
        }
        return null;
    }
    
    public void onCreate(final ServiceImplApi21 serviceImplApi21) {
        this.mBinder = new ServiceBinderProxyApi21(serviceImplApi21);
    }
    
    static class ServiceBinderProxyApi21 extends Stub
    {
        final ServiceImplApi21 mServiceImpl;
        
        ServiceBinderProxyApi21(final ServiceImplApi21 mServiceImpl) {
            this.mServiceImpl = mServiceImpl;
        }
        
        @Override
        public void addSubscription(final String s, final Object o) {
            this.mServiceImpl.addSubscription(s, new ServiceCallbacksApi21(o));
        }
        
        @Override
        public void connect(final String s, final Bundle bundle, final Object o) {
            this.mServiceImpl.connect(s, bundle, new ServiceCallbacksApi21(o));
        }
        
        @Override
        public void disconnect(final Object o) {
            this.mServiceImpl.disconnect(new ServiceCallbacksApi21(o));
        }
        
        @Override
        public void getMediaItem(final String s, final ResultReceiver resultReceiver) {
        }
        
        @Override
        public void removeSubscription(final String s, final Object o) {
            this.mServiceImpl.removeSubscription(s, new ServiceCallbacksApi21(o));
        }
    }
}
