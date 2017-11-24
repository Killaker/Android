package android.support.v4.app;

import android.os.*;
import android.app.*;

private class NotificationSideChannelStub extends Stub
{
    public void cancel(final String s, final int n, final String s2) throws RemoteException {
        NotificationCompatSideChannelService.access$100(NotificationCompatSideChannelService.this, getCallingUid(), s);
        final long clearCallingIdentity = clearCallingIdentity();
        try {
            NotificationCompatSideChannelService.this.cancel(s, n, s2);
        }
        finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }
    
    public void cancelAll(final String s) {
        NotificationCompatSideChannelService.access$100(NotificationCompatSideChannelService.this, getCallingUid(), s);
        final long clearCallingIdentity = clearCallingIdentity();
        try {
            NotificationCompatSideChannelService.this.cancelAll(s);
        }
        finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }
    
    public void notify(final String s, final int n, final String s2, final Notification notification) throws RemoteException {
        NotificationCompatSideChannelService.access$100(NotificationCompatSideChannelService.this, getCallingUid(), s);
        final long clearCallingIdentity = clearCallingIdentity();
        try {
            NotificationCompatSideChannelService.this.notify(s, n, s2, notification);
        }
        finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }
}
