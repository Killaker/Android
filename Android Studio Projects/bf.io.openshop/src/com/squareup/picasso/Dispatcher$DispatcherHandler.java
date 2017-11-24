package com.squareup.picasso;

import android.os.*;
import android.net.*;

private static class DispatcherHandler extends Handler
{
    private final Dispatcher dispatcher;
    
    public DispatcherHandler(final Looper looper, final Dispatcher dispatcher) {
        super(looper);
        this.dispatcher = dispatcher;
    }
    
    public void handleMessage(final Message message) {
        boolean b = true;
        switch (message.what) {
            default: {
                Picasso.HANDLER.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        throw new AssertionError((Object)("Unknown handler message received: " + message.what));
                    }
                });
            }
            case 1: {
                this.dispatcher.performSubmit((Action)message.obj);
            }
            case 2: {
                this.dispatcher.performCancel((Action)message.obj);
            }
            case 11: {
                this.dispatcher.performPauseTag(message.obj);
            }
            case 12: {
                this.dispatcher.performResumeTag(message.obj);
            }
            case 4: {
                this.dispatcher.performComplete((BitmapHunter)message.obj);
            }
            case 5: {
                this.dispatcher.performRetry((BitmapHunter)message.obj);
            }
            case 6: {
                this.dispatcher.performError((BitmapHunter)message.obj, false);
            }
            case 7: {
                this.dispatcher.performBatchComplete();
            }
            case 9: {
                this.dispatcher.performNetworkStateChange((NetworkInfo)message.obj);
            }
            case 10: {
                final Dispatcher dispatcher = this.dispatcher;
                if (message.arg1 != (b ? 1 : 0)) {
                    b = false;
                }
                dispatcher.performAirplaneModeChange(b);
            }
        }
    }
}
