package android.support.v4.media;

import android.support.v4.media.session.*;
import android.os.*;
import java.util.*;

private interface ServiceCallbacks
{
    IBinder asBinder();
    
    void onConnect(final String p0, final MediaSessionCompat.Token p1, final Bundle p2) throws RemoteException;
    
    void onConnectFailed() throws RemoteException;
    
    void onLoadChildren(final String p0, final List<MediaBrowserCompat.MediaItem> p1, final Bundle p2) throws RemoteException;
}
