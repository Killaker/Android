package android.support.v4.app;

import android.content.*;
import android.os.*;
import android.util.*;

static class ImplBase implements Impl
{
    @Override
    public void addResultsToIntent(final RemoteInput[] array, final Intent intent, final Bundle bundle) {
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    }
    
    @Override
    public Bundle getResultsFromIntent(final Intent intent) {
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        return null;
    }
}
