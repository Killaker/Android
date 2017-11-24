package android.support.v4.media.session;

import android.net.*;
import android.os.*;

public interface Callback extends MediaSessionCompatApi21.Callback
{
    void onPlayFromUri(final Uri p0, final Bundle p1);
}
