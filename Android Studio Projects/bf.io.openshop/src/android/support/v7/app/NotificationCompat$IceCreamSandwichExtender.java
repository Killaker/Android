package android.support.v7.app;

import android.support.v4.app.*;
import android.app.*;

private static class IceCreamSandwichExtender extends BuilderExtender
{
    @Override
    public Notification build(final Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        NotificationCompat.access$300(notificationBuilderWithBuilderAccessor, builder);
        return notificationBuilderWithBuilderAccessor.build();
    }
}
