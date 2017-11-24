package android.support.v4.media;

import android.content.*;
import android.os.*;

class MediaBrowserServiceImplApi23 implements MediaBrowserServiceImpl
{
    private Object mServiceObj;
    
    @Override
    public IBinder onBind(final Intent intent) {
        return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, intent);
    }
    
    @Override
    public void onCreate() {
        MediaBrowserServiceCompatApi23.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi23.createService(), (MediaBrowserServiceCompatApi23.ServiceImplApi23)new ServiceImplApi23());
    }
}
