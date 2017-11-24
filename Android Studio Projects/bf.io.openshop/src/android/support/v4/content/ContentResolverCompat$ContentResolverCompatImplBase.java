package android.support.v4.content;

import android.content.*;
import android.net.*;
import android.support.v4.os.*;
import android.database.*;

static class ContentResolverCompatImplBase implements ContentResolverCompatImpl
{
    @Override
    public Cursor query(final ContentResolver contentResolver, final Uri uri, final String[] array, final String s, final String[] array2, final String s2, final CancellationSignal cancellationSignal) {
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        return contentResolver.query(uri, array, s, array2, s2);
    }
}
