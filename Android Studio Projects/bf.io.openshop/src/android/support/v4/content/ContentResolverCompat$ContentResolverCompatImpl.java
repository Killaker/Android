package android.support.v4.content;

import android.content.*;
import android.net.*;
import android.support.v4.os.*;
import android.database.*;

interface ContentResolverCompatImpl
{
    Cursor query(final ContentResolver p0, final Uri p1, final String[] p2, final String p3, final String[] p4, final String p5, final CancellationSignal p6);
}
