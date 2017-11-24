package com.squareup.picasso;

import android.widget.*;

static class RemoteViewsTarget
{
    final RemoteViews remoteViews;
    final int viewId;
    
    RemoteViewsTarget(final RemoteViews remoteViews, final int viewId) {
        this.remoteViews = remoteViews;
        this.viewId = viewId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final RemoteViewsTarget remoteViewsTarget = (RemoteViewsTarget)o;
            if (this.viewId != remoteViewsTarget.viewId || !this.remoteViews.equals(remoteViewsTarget.remoteViews)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * this.remoteViews.hashCode() + this.viewId;
    }
}
