package android.support.v4.app;

import android.app.*;
import java.util.*;
import android.os.*;

static class NotificationCompatImplJellybean extends NotificationCompatImplBase
{
    @Override
    public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
        final NotificationCompatJellybean.Builder builder2 = new NotificationCompatJellybean.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
        NotificationCompat.access$000(builder2, builder.mActions);
        NotificationCompat.access$100(builder2, builder.mStyle);
        return builderExtender.build(builder, builder2);
    }
    
    @Override
    public Action getAction(final Notification notification, final int n) {
        return (Action)NotificationCompatJellybean.getAction(notification, n, Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public int getActionCount(final Notification notification) {
        return NotificationCompatJellybean.getActionCount(notification);
    }
    
    @Override
    public Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
        return (Action[])NotificationCompatJellybean.getActionsFromParcelableArrayList(list, Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public Bundle getExtras(final Notification notification) {
        return NotificationCompatJellybean.getExtras(notification);
    }
    
    @Override
    public String getGroup(final Notification notification) {
        return NotificationCompatJellybean.getGroup(notification);
    }
    
    @Override
    public boolean getLocalOnly(final Notification notification) {
        return NotificationCompatJellybean.getLocalOnly(notification);
    }
    
    @Override
    public ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] array) {
        return NotificationCompatJellybean.getParcelableArrayListForActions(array);
    }
    
    @Override
    public String getSortKey(final Notification notification) {
        return NotificationCompatJellybean.getSortKey(notification);
    }
    
    @Override
    public boolean isGroupSummary(final Notification notification) {
        return NotificationCompatJellybean.isGroupSummary(notification);
    }
}
