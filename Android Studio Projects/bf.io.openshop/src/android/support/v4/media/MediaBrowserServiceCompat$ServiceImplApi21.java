package android.support.v4.media;

import android.os.*;

private class ServiceImplApi21 implements MediaBrowserServiceCompatApi21.ServiceImplApi21
{
    final ServiceImpl mServiceImpl;
    
    ServiceImplApi21() {
        this.mServiceImpl = MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this).getServiceImpl();
    }
    
    @Override
    public void addSubscription(final String s, final MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
        this.mServiceImpl.addSubscription(s, null, new MediaBrowserServiceCompat.ServiceCallbacksApi21(serviceCallbacks));
    }
    
    @Override
    public void connect(final String s, final Bundle bundle, final MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
        this.mServiceImpl.connect(s, Binder.getCallingUid(), bundle, new MediaBrowserServiceCompat.ServiceCallbacksApi21(serviceCallbacks));
    }
    
    @Override
    public void disconnect(final MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
        this.mServiceImpl.disconnect(new MediaBrowserServiceCompat.ServiceCallbacksApi21(serviceCallbacks));
    }
    
    @Override
    public void removeSubscription(final String s, final MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
        this.mServiceImpl.removeSubscription(s, null, new MediaBrowserServiceCompat.ServiceCallbacksApi21(serviceCallbacks));
    }
}
