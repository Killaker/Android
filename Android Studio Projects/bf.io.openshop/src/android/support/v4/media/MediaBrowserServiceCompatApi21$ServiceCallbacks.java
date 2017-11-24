package android.support.v4.media;

import java.util.*;
import android.os.*;

public interface ServiceCallbacks
{
    IBinder asBinder();
    
    void onConnect(final String p0, final Object p1, final Bundle p2) throws RemoteException;
    
    void onConnectFailed() throws RemoteException;
    
    void onLoadChildren(final String p0, final List<Parcel> p1) throws RemoteException;
}
