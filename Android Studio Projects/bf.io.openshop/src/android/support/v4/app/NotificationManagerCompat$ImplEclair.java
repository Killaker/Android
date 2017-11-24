package android.support.v4.app;

import android.app.*;

static class ImplEclair extends ImplBase
{
    @Override
    public void cancelNotification(final NotificationManager notificationManager, final String s, final int n) {
        NotificationManagerCompatEclair.cancelNotification(notificationManager, s, n);
    }
    
    @Override
    public void postNotification(final NotificationManager notificationManager, final String s, final int n, final Notification notification) {
        NotificationManagerCompatEclair.postNotification(notificationManager, s, n, notification);
    }
}
