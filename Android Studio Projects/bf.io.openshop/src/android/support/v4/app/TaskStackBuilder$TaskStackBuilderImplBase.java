package android.support.v4.app;

import android.content.*;
import android.os.*;
import android.app.*;

static class TaskStackBuilderImplBase implements TaskStackBuilderImpl
{
    @Override
    public PendingIntent getPendingIntent(final Context context, final Intent[] array, final int n, final int n2, final Bundle bundle) {
        final Intent intent = new Intent(array[-1 + array.length]);
        intent.addFlags(268435456);
        return PendingIntent.getActivity(context, n, intent, n2);
    }
}
