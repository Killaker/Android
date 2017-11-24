package android.support.v4.media.session;

import android.net.*;
import android.os.*;

static class TransportControlsApi23 extends TransportControlsApi21
{
    public TransportControlsApi23(final Object o) {
        super(o);
    }
    
    @Override
    public void playFromUri(final Uri uri, final Bundle bundle) {
        MediaControllerCompatApi23.TransportControls.playFromUri(this.mControlsObj, uri, bundle);
    }
}
