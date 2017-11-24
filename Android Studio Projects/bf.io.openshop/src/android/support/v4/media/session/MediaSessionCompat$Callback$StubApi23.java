package android.support.v4.media.session;

import android.net.*;
import android.os.*;

private class StubApi23 extends StubApi21 implements MediaSessionCompatApi23.Callback
{
    @Override
    public void onPlayFromUri(final Uri uri, final Bundle bundle) {
        MediaSessionCompat.Callback.this.onPlayFromUri(uri, bundle);
    }
}
