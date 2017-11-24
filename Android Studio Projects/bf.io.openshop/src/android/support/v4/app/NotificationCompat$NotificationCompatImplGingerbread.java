package android.support.v4.app;

import android.app.*;

static class NotificationCompatImplGingerbread extends NotificationCompatImplBase
{
    @Override
    public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
        final Notification add = NotificationCompatGingerbread.add(builder.mNotification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent, builder.mFullScreenIntent);
        if (builder.mPriority > 0) {
            add.flags |= 0x80;
        }
        return add;
    }
}
