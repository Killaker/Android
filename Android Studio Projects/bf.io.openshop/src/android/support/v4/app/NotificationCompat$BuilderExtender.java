package android.support.v4.app;

import android.app.*;

protected static class BuilderExtender
{
    public Notification build(final NotificationCompat.Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        return notificationBuilderWithBuilderAccessor.build();
    }
}
