package android.support.v4.media.session;

import android.support.v4.media.*;
import java.util.*;
import android.os.*;

private class MessageHandler extends Handler
{
    private static final int MSG_DESTROYED = 8;
    private static final int MSG_EVENT = 1;
    private static final int MSG_UPDATE_EXTRAS = 7;
    private static final int MSG_UPDATE_METADATA = 3;
    private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
    private static final int MSG_UPDATE_QUEUE = 5;
    private static final int MSG_UPDATE_QUEUE_TITLE = 6;
    private static final int MSG_UPDATE_VOLUME = 4;
    
    public MessageHandler(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        if (!Callback.access$300(Callback.this)) {
            return;
        }
        switch (message.what) {
            default: {}
            case 1: {
                Callback.this.onSessionEvent((String)message.obj, message.getData());
            }
            case 2: {
                Callback.this.onPlaybackStateChanged((PlaybackStateCompat)message.obj);
            }
            case 3: {
                Callback.this.onMetadataChanged((MediaMetadataCompat)message.obj);
            }
            case 5: {
                Callback.this.onQueueChanged((List<MediaSessionCompat.QueueItem>)message.obj);
            }
            case 6: {
                Callback.this.onQueueTitleChanged((CharSequence)message.obj);
            }
            case 7: {
                Callback.this.onExtrasChanged((Bundle)message.obj);
            }
            case 4: {
                Callback.this.onAudioInfoChanged((PlaybackInfo)message.obj);
            }
            case 8: {
                Callback.this.onSessionDestroyed();
            }
        }
    }
    
    public void post(final int n, final Object o, final Bundle data) {
        final Message obtainMessage = this.obtainMessage(n, o);
        obtainMessage.setData(data);
        obtainMessage.sendToTarget();
    }
}
