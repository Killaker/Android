package android.support.v4.app;

import android.app.*;

interface Impl
{
    void cancelNotification(final NotificationManager p0, final String p1, final int p2);
    
    int getSideChannelBindFlags();
    
    void postNotification(final NotificationManager p0, final String p1, final int p2, final Notification p3);
}
