package android.support.v4.app;

import android.content.*;
import android.os.*;

static class ImplJellybean implements Impl
{
    @Override
    public void addResultsToIntent(final RemoteInput[] array, final Intent intent, final Bundle bundle) {
        RemoteInputCompatJellybean.addResultsToIntent(array, intent, bundle);
    }
    
    @Override
    public Bundle getResultsFromIntent(final Intent intent) {
        return RemoteInputCompatJellybean.getResultsFromIntent(intent);
    }
}
