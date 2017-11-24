package android.support.v4.app;

import android.app.*;
import android.os.*;

public interface Factory
{
    Action build(final int p0, final CharSequence p1, final PendingIntent p2, final Bundle p3, final RemoteInputCompatBase.RemoteInput[] p4);
    
    Action[] newArray(final int p0);
}
