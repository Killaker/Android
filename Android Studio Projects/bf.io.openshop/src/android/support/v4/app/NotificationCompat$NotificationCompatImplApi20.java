package android.support.v4.app;

import android.app.*;
import java.util.*;
import android.os.*;

static class NotificationCompatImplApi20 extends NotificationCompatImplKitKat
{
    @Override
    public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
        final NotificationCompatApi20.Builder builder2 = new NotificationCompatApi20.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mPeople, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
        NotificationCompat.access$000(builder2, builder.mActions);
        NotificationCompat.access$100(builder2, builder.mStyle);
        return builderExtender.build(builder, builder2);
    }
    
    @Override
    public Action getAction(final Notification notification, final int n) {
        return (Action)NotificationCompatApi20.getAction(notification, n, Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
        return (Action[])NotificationCompatApi20.getActionsFromParcelableArrayList(list, Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public String getGroup(final Notification notification) {
        return NotificationCompatApi20.getGroup(notification);
    }
    
    @Override
    public boolean getLocalOnly(final Notification notification) {
        return NotificationCompatApi20.getLocalOnly(notification);
    }
    
    @Override
    public ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] array) {
        return NotificationCompatApi20.getParcelableArrayListForActions(array);
    }
    
    @Override
    public String getSortKey(final Notification notification) {
        return NotificationCompatApi20.getSortKey(notification);
    }
    
    @Override
    public boolean isGroupSummary(final Notification notification) {
        return NotificationCompatApi20.isGroupSummary(notification);
    }
}
