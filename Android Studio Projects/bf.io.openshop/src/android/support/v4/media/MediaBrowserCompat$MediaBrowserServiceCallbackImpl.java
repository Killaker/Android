package android.support.v4.media;

import java.util.*;
import android.os.*;
import android.support.v4.media.session.*;

interface MediaBrowserServiceCallbackImpl
{
    void onConnectionFailed(final Messenger p0);
    
    void onLoadChildren(final Messenger p0, final String p1, final List p2, final Bundle p3);
    
    void onServiceConnected(final Messenger p0, final String p1, final MediaSessionCompat.Token p2, final Bundle p3);
}
