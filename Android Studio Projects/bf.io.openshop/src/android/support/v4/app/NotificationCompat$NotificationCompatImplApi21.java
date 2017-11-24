package android.support.v4.app;

import android.app.*;
import java.util.*;
import android.os.*;

static class NotificationCompatImplApi21 extends NotificationCompatImplApi20
{
    @Override
    public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
        final NotificationCompatApi21.Builder builder2 = new NotificationCompatApi21.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mCategory, builder.mPeople, builder.mExtras, builder.mColor, builder.mVisibility, builder.mPublicVersion, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
        NotificationCompat.access$000(builder2, builder.mActions);
        NotificationCompat.access$100(builder2, builder.mStyle);
        return builderExtender.build(builder, builder2);
    }
    
    @Override
    public Bundle getBundleForUnreadConversation(final NotificationCompatBase.UnreadConversation unreadConversation) {
        return NotificationCompatApi21.getBundleForUnreadConversation(unreadConversation);
    }
    
    @Override
    public String getCategory(final Notification notification) {
        return NotificationCompatApi21.getCategory(notification);
    }
    
    @Override
    public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(final Bundle bundle, final NotificationCompatBase.UnreadConversation.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2) {
        return NotificationCompatApi21.getUnreadConversationFromBundle(bundle, factory, factory2);
    }
}
