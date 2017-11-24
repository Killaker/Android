package android.support.v4.media;

import android.os.*;

public interface ServiceImplApi21
{
    void addSubscription(final String p0, final ServiceCallbacks p1);
    
    void connect(final String p0, final Bundle p1, final ServiceCallbacks p2);
    
    void disconnect(final ServiceCallbacks p0);
    
    void removeSubscription(final String p0, final ServiceCallbacks p1);
}
