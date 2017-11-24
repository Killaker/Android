package android.support.v4.app;

import android.app.*;
import java.util.*;
import android.os.*;

static class NotificationCompatImplKitKat extends NotificationCompatImplJellybean
{
    @Override
    public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
        final NotificationCompatKitKat.Builder builder2 = new NotificationCompatKitKat.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mPeople, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
        NotificationCompat.access$000(builder2, builder.mActions);
        NotificationCompat.access$100(builder2, builder.mStyle);
        return builderExtender.build(builder, builder2);
    }
    
    @Override
    public Action getAction(final Notification notification, final int n) {
        return (Action)NotificationCompatKitKat.getAction(notification, n, Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public int getActionCount(final Notification notification) {
        return NotificationCompatKitKat.getActionCount(notification);
    }
    
    @Override
    public Bundle getExtras(final Notification notification) {
        return NotificationCompatKitKat.getExtras(notification);
    }
    
    @Override
    public String getGroup(final Notification notification) {
        return NotificationCompatKitKat.getGroup(notification);
    }
    
    @Override
    public boolean getLocalOnly(final Notification notification) {
        return NotificationCompatKitKat.getLocalOnly(notification);
    }
    
    @Override
    public String getSortKey(final Notification notification) {
        return NotificationCompatKitKat.getSortKey(notification);
    }
    
    @Override
    public boolean isGroupSummary(final Notification notification) {
        return NotificationCompatKitKat.isGroupSummary(notification);
    }
}
