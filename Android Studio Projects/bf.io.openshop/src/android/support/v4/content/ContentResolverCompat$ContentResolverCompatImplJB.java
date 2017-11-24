package android.support.v4.content;

import android.content.*;
import android.net.*;
import android.database.*;
import android.support.v4.os.*;

static class ContentResolverCompatImplJB extends ContentResolverCompatImplBase
{
    @Override
    public Cursor query(final ContentResolver contentResolver, final Uri uri, final String[] array, final String s, final String[] array2, final String s2, final CancellationSignal cancellationSignal) {
        Label_0031: {
            if (cancellationSignal == null) {
                break Label_0031;
            }
            try {
                Object cancellationSignalObject = cancellationSignal.getCancellationSignalObject();
                return ContentResolverCompatJellybean.query(contentResolver, uri, array, s, array2, s2, cancellationSignalObject);
                cancellationSignalObject = null;
                return ContentResolverCompatJellybean.query(contentResolver, uri, array, s, array2, s2, cancellationSignalObject);
            }
            catch (Exception ex) {
                if (ContentResolverCompatJellybean.isFrameworkOperationCanceledException(ex)) {
                    throw new OperationCanceledException();
                }
                throw ex;
            }
        }
    }
}
