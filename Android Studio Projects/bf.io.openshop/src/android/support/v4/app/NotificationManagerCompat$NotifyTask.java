package android.support.v4.app;

import android.app.*;
import android.os.*;

private static class NotifyTask implements Task
{
    final int id;
    final Notification notif;
    final String packageName;
    final String tag;
    
    public NotifyTask(final String packageName, final int id, final String tag, final Notification notif) {
        this.packageName = packageName;
        this.id = id;
        this.tag = tag;
        this.notif = notif;
    }
    
    @Override
    public void send(final INotificationSideChannel notificationSideChannel) throws RemoteException {
        notificationSideChannel.notify(this.packageName, this.id, this.tag, this.notif);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotifyTask[");
        sb.append("packageName:").append(this.packageName);
        sb.append(", id:").append(this.id);
        sb.append(", tag:").append(this.tag);
        sb.append("]");
        return sb.toString();
    }
}
