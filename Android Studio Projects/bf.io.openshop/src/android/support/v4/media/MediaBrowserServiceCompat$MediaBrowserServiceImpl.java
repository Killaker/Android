package android.support.v4.media;

import android.content.*;
import android.os.*;

interface MediaBrowserServiceImpl
{
    IBinder onBind(final Intent p0);
    
    void onCreate();
}
