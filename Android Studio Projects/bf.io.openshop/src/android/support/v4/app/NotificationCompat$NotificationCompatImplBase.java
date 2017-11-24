package android.support.v4.app;

import android.app.*;
import java.util.*;
import android.os.*;

static class NotificationCompatImplBase implements NotificationCompatImpl
{
    @Override
    public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
        final Notification add = NotificationCompatBase.add(builder.mNotification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent);
        if (builder.mPriority > 0) {
            add.flags |= 0x80;
        }
        return add;
    }
    
    @Override
    public Action getAction(final Notification notification, final int n) {
        return null;
    }
    
    @Override
    public int getActionCount(final Notification notification) {
        return 0;
    }
    
    @Override
    public Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
        return null;
    }
    
    @Override
    public Bundle getBundleForUnreadConversation(final NotificationCompatBase.UnreadConversation unreadConversation) {
        return null;
    }
    
    @Override
    public String getCategory(final Notification notification) {
        return null;
    }
    
    @Override
    public Bundle getExtras(final Notification notification) {
        return null;
    }
    
    @Override
    public String getGroup(final Notification notification) {
        return null;
    }
    
    @Override
    public boolean getLocalOnly(final Notification notification) {
        return false;
    }
    
    @Override
    public ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] array) {
        return null;
    }
    
    @Override
    public String getSortKey(final Notification notification) {
        return null;
    }
    
    @Override
    public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(final Bundle bundle, final NotificationCompatBase.UnreadConversation.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2) {
        return null;
    }
    
    @Override
    public boolean isGroupSummary(final Notification notification) {
        return false;
    }
}
