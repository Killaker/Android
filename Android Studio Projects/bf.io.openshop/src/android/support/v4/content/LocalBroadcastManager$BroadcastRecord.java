package android.support.v4.content;

import android.content.*;
import java.util.*;

private static class BroadcastRecord
{
    final Intent intent;
    final ArrayList<ReceiverRecord> receivers;
    
    BroadcastRecord(final Intent intent, final ArrayList<ReceiverRecord> receivers) {
        this.intent = intent;
        this.receivers = receivers;
    }
}
