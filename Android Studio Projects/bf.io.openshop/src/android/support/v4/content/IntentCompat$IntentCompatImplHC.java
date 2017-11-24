package android.support.v4.content;

import android.content.*;

static class IntentCompatImplHC extends IntentCompatImplBase
{
    @Override
    public Intent makeMainActivity(final ComponentName componentName) {
        return IntentCompatHoneycomb.makeMainActivity(componentName);
    }
    
    @Override
    public Intent makeRestartActivityTask(final ComponentName componentName) {
        return IntentCompatHoneycomb.makeRestartActivityTask(componentName);
    }
}
