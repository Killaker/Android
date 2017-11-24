package android.support.v4.media.session;

import android.view.*;
import android.net.*;
import android.support.v4.media.*;
import android.content.*;
import android.os.*;

private class MessageHandler extends Handler
{
    private static final int KEYCODE_MEDIA_PAUSE = 127;
    private static final int KEYCODE_MEDIA_PLAY = 126;
    private static final int MSG_ADJUST_VOLUME = 16;
    private static final int MSG_COMMAND = 15;
    private static final int MSG_CUSTOM_ACTION = 13;
    private static final int MSG_FAST_FORWARD = 9;
    private static final int MSG_MEDIA_BUTTON = 14;
    private static final int MSG_NEXT = 7;
    private static final int MSG_PAUSE = 5;
    private static final int MSG_PLAY = 1;
    private static final int MSG_PLAY_MEDIA_ID = 2;
    private static final int MSG_PLAY_SEARCH = 3;
    private static final int MSG_PLAY_URI = 18;
    private static final int MSG_PREVIOUS = 8;
    private static final int MSG_RATE = 12;
    private static final int MSG_REWIND = 10;
    private static final int MSG_SEEK_TO = 11;
    private static final int MSG_SET_VOLUME = 17;
    private static final int MSG_SKIP_TO_ITEM = 4;
    private static final int MSG_STOP = 6;
    
    public MessageHandler(final Looper looper) {
        super(looper);
    }
    
    private void onMediaButtonEvent(final KeyEvent keyEvent, final Callback callback) {
        int n = 1;
        if (keyEvent != null && keyEvent.getAction() == 0) {
            long actions;
            if (MediaSessionImplBase.access$2800(MediaSessionImplBase.this) == null) {
                actions = 0L;
            }
            else {
                actions = MediaSessionImplBase.access$2800(MediaSessionImplBase.this).getActions();
            }
            switch (keyEvent.getKeyCode()) {
                default: {}
                case 79:
                case 85: {
                    int n2;
                    if (MediaSessionImplBase.access$2800(MediaSessionImplBase.this) != null && MediaSessionImplBase.access$2800(MediaSessionImplBase.this).getState() == 3) {
                        n2 = n;
                    }
                    else {
                        n2 = 0;
                    }
                    int n3;
                    if ((0x204L & actions) != 0x0L) {
                        n3 = n;
                    }
                    else {
                        n3 = 0;
                    }
                    if ((0x202L & actions) == 0x0L) {
                        n = 0;
                    }
                    if (n2 != 0 && n != 0) {
                        callback.onPause();
                        return;
                    }
                    if (n2 == 0 && n3 != 0) {
                        callback.onPlay();
                        return;
                    }
                    break;
                }
                case 126: {
                    if ((0x4L & actions) != 0x0L) {
                        callback.onPlay();
                        return;
                    }
                    break;
                }
                case 127: {
                    if ((0x2L & actions) != 0x0L) {
                        callback.onPause();
                        return;
                    }
                    break;
                }
                case 87: {
                    if ((0x20L & actions) != 0x0L) {
                        callback.onSkipToNext();
                        return;
                    }
                    break;
                }
                case 88: {
                    if ((0x10L & actions) != 0x0L) {
                        callback.onSkipToPrevious();
                        return;
                    }
                    break;
                }
                case 86: {
                    if ((0x1L & actions) != 0x0L) {
                        callback.onStop();
                        return;
                    }
                    break;
                }
                case 90: {
                    if ((0x40L & actions) != 0x0L) {
                        callback.onFastForward();
                        return;
                    }
                    break;
                }
                case 89: {
                    if ((0x8L & actions) != 0x0L) {
                        callback.onRewind();
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public void handleMessage(final Message message) {
        final Callback access$2700 = MediaSessionImplBase.access$2700(MediaSessionImplBase.this);
        if (access$2700 != null) {
            switch (message.what) {
                default: {}
                case 1: {
                    access$2700.onPlay();
                }
                case 2: {
                    access$2700.onPlayFromMediaId((String)message.obj, message.getData());
                }
                case 3: {
                    access$2700.onPlayFromSearch((String)message.obj, message.getData());
                }
                case 18: {
                    access$2700.onPlayFromUri((Uri)message.obj, message.getData());
                }
                case 4: {
                    access$2700.onSkipToQueueItem((long)message.obj);
                }
                case 5: {
                    access$2700.onPause();
                }
                case 6: {
                    access$2700.onStop();
                }
                case 7: {
                    access$2700.onSkipToNext();
                }
                case 8: {
                    access$2700.onSkipToPrevious();
                }
                case 9: {
                    access$2700.onFastForward();
                }
                case 10: {
                    access$2700.onRewind();
                }
                case 11: {
                    access$2700.onSeekTo((long)message.obj);
                }
                case 12: {
                    access$2700.onSetRating((RatingCompat)message.obj);
                }
                case 13: {
                    access$2700.onCustomAction((String)message.obj, message.getData());
                }
                case 14: {
                    final KeyEvent keyEvent = (KeyEvent)message.obj;
                    final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                    intent.putExtra("android.intent.extra.KEY_EVENT", (Parcelable)keyEvent);
                    if (!access$2700.onMediaButtonEvent(intent)) {
                        this.onMediaButtonEvent(keyEvent, access$2700);
                        return;
                    }
                    break;
                }
                case 15: {
                    final Command command = (Command)message.obj;
                    access$2700.onCommand(command.command, command.extras, command.stub);
                }
                case 16: {
                    MediaSessionImplBase.access$1700(MediaSessionImplBase.this, (int)message.obj, 0);
                }
                case 17: {
                    MediaSessionImplBase.access$1800(MediaSessionImplBase.this, (int)message.obj, 0);
                }
            }
        }
    }
    
    public void post(final int n) {
        this.post(n, null);
    }
    
    public void post(final int n, final Object o) {
        this.obtainMessage(n, o).sendToTarget();
    }
    
    public void post(final int n, final Object o, final int n2) {
        this.obtainMessage(n, n2, 0, o).sendToTarget();
    }
    
    public void post(final int n, final Object o, final Bundle data) {
        final Message obtainMessage = this.obtainMessage(n, o);
        obtainMessage.setData(data);
        obtainMessage.sendToTarget();
    }
}
