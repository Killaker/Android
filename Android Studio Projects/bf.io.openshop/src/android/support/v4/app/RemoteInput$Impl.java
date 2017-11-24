package android.support.v4.app;

import android.content.*;
import android.os.*;

interface Impl
{
    void addResultsToIntent(final RemoteInput[] p0, final Intent p1, final Bundle p2);
    
    Bundle getResultsFromIntent(final Intent p0);
}
