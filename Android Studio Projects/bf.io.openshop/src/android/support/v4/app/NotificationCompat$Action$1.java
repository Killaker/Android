package android.support.v4.app;

import android.app.*;
import android.os.*;

static final class NotificationCompat$Action$1 implements Factory {
    public NotificationCompat.Action build(final int n, final CharSequence charSequence, final PendingIntent pendingIntent, final Bundle bundle, final RemoteInputCompatBase.RemoteInput[] array) {
        return new NotificationCompat.Action(n, charSequence, pendingIntent, bundle, (RemoteInput[])array);
    }
    
    public NotificationCompat.Action[] newArray(final int n) {
        return new NotificationCompat.Action[n];
    }
}