package android.support.v7.app;

import android.support.v4.app.*;
import android.app.*;

private static class JellybeanExtender extends BuilderExtender
{
    @Override
    public Notification build(final Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        NotificationCompat.access$300(notificationBuilderWithBuilderAccessor, builder);
        final Notification build = notificationBuilderWithBuilderAccessor.build();
        NotificationCompat.access$400(build, builder);
        return build;
    }
}
