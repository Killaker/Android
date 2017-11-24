package android.support.v4.app;

import android.content.*;
import android.os.*;
import android.app.*;

static class TaskStackBuilderImplJellybean implements TaskStackBuilderImpl
{
    @Override
    public PendingIntent getPendingIntent(final Context context, final Intent[] array, final int n, final int n2, final Bundle bundle) {
        array[0] = new Intent(array[0]).addFlags(268484608);
        return TaskStackBuilderJellybean.getActivitiesPendingIntent(context, n, array, n2, bundle);
    }
}
