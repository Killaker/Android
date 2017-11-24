package android.support.v4.media.session;

import android.media.*;
import android.media.session.*;

static class QueueItem
{
    public static Object createItem(final Object o, final long n) {
        return new MediaSession$QueueItem((MediaDescription)o, n);
    }
    
    public static Object getDescription(final Object o) {
        return ((MediaSession$QueueItem)o).getDescription();
    }
    
    public static long getQueueId(final Object o) {
        return ((MediaSession$QueueItem)o).getQueueId();
    }
}
