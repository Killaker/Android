package android.support.v4.app;

import android.os.*;

public interface Factory
{
    RemoteInput build(final String p0, final CharSequence p1, final CharSequence[] p2, final boolean p3, final Bundle p4);
    
    RemoteInput[] newArray(final int p0);
}
