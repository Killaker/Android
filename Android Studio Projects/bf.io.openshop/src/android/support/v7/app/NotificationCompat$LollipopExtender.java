package android.support.v7.app;

import android.support.v4.app.*;
import android.app.*;

private static class LollipopExtender extends BuilderExtender
{
    @Override
    public Notification build(final Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        NotificationCompat.access$500(notificationBuilderWithBuilderAccessor, builder.mStyle);
        return notificationBuilderWithBuilderAccessor.build();
    }
}
