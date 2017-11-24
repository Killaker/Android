package android.support.v4.media;

import android.net.*;
import android.media.*;

static class Builder extends MediaDescriptionCompatApi21.Builder
{
    public static void setMediaUri(final Object o, final Uri mediaUri) {
        ((MediaDescription$Builder)o).setMediaUri(mediaUri);
    }
}
