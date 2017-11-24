package android.support.v4.app;

import android.app.*;
import java.util.*;
import android.os.*;

interface NotificationCompatImpl
{
    Notification build(final NotificationCompat.Builder p0, final BuilderExtender p1);
    
    Action getAction(final Notification p0, final int p1);
    
    int getActionCount(final Notification p0);
    
    Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> p0);
    
    Bundle getBundleForUnreadConversation(final NotificationCompatBase.UnreadConversation p0);
    
    String getCategory(final Notification p0);
    
    Bundle getExtras(final Notification p0);
    
    String getGroup(final Notification p0);
    
    boolean getLocalOnly(final Notification p0);
    
    ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] p0);
    
    String getSortKey(final Notification p0);
    
    NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(final Bundle p0, final NotificationCompatBase.UnreadConversation.Factory p1, final RemoteInputCompatBase.RemoteInput.Factory p2);
    
    boolean isGroupSummary(final Notification p0);
}
