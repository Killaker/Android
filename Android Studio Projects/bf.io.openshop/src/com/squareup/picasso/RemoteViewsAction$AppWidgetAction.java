package com.squareup.picasso;

import android.widget.*;
import android.appwidget.*;

static class AppWidgetAction extends RemoteViewsAction
{
    private final int[] appWidgetIds;
    
    AppWidgetAction(final Picasso picasso, final Request request, final RemoteViews remoteViews, final int n, final int[] appWidgetIds, final int n2, final int n3, final String s, final Object o, final int n4) {
        super(picasso, request, remoteViews, n, n4, n2, n3, o, s);
        this.appWidgetIds = appWidgetIds;
    }
    
    @Override
    void update() {
        AppWidgetManager.getInstance(this.picasso.context).updateAppWidget(this.appWidgetIds, this.remoteViews);
    }
}
