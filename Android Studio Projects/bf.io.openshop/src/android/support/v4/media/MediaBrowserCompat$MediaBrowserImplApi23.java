package android.support.v4.media;

import android.content.*;
import android.os.*;
import android.support.annotation.*;

static class MediaBrowserImplApi23 extends MediaBrowserImplApi21
{
    public MediaBrowserImplApi23(final Context context, final ComponentName componentName, final ConnectionCallback connectionCallback, final Bundle bundle) {
        super(context, componentName, connectionCallback, bundle);
    }
    
    @Override
    public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
        MediaBrowserCompatApi23.getItem(this.mBrowserObj, s, itemCallback.mItemCallbackObj);
    }
}
