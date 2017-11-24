package com.squareup.picasso;

import android.os.*;

private static class StatsHandler extends Handler
{
    private final Stats stats;
    
    public StatsHandler(final Looper looper, final Stats stats) {
        super(looper);
        this.stats = stats;
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Picasso.HANDLER.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        throw new AssertionError((Object)("Unhandled stats message." + message.what));
                    }
                });
            }
            case 0: {
                this.stats.performCacheHit();
            }
            case 1: {
                this.stats.performCacheMiss();
            }
            case 2: {
                this.stats.performBitmapDecoded(message.arg1);
            }
            case 3: {
                this.stats.performBitmapTransformed(message.arg1);
            }
            case 4: {
                this.stats.performDownloadFinished((Long)message.obj);
            }
        }
    }
}
