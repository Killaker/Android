package android.support.v4.media;

import android.content.*;
import android.os.*;

class MediaBrowserServiceImplApi21 implements MediaBrowserServiceImpl
{
    private Object mServiceObj;
    
    @Override
    public IBinder onBind(final Intent intent) {
        return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, intent);
    }
    
    @Override
    public void onCreate() {
        MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi21.createService(), (MediaBrowserServiceCompatApi21.ServiceImplApi21)new ServiceImplApi21());
    }
}
