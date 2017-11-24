package com.squareup.picasso;

import android.widget.*;
import android.app.*;

static class NotificationAction extends RemoteViewsAction
{
    private final Notification notification;
    private final int notificationId;
    
    NotificationAction(final Picasso picasso, final Request request, final RemoteViews remoteViews, final int n, final int notificationId, final Notification notification, final int n2, final int n3, final String s, final Object o, final int n4) {
        super(picasso, request, remoteViews, n, n4, n2, n3, o, s);
        this.notificationId = notificationId;
        this.notification = notification;
    }
    
    @Override
    void update() {
        Utils.getService(this.picasso.context, "notification").notify(this.notificationId, this.notification);
    }
}
