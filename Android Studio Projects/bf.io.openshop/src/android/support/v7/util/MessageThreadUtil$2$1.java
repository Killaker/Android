package android.support.v7.util;

import android.util.*;

class MessageThreadUtil$2$1 implements Runnable {
    @Override
    public void run() {
        while (true) {
            final SyncQueueItem next = MessageThreadUtil$2.access$100(BackgroundCallback.this).next();
            if (next == null) {
                break;
            }
            switch (next.what) {
                default: {
                    Log.e("ThreadUtil", "Unsupported message, what=" + next.what);
                    continue;
                }
                case 1: {
                    MessageThreadUtil$2.access$100(BackgroundCallback.this).removeMessages(1);
                    BackgroundCallback.this.val$callback.refresh(next.arg1);
                    continue;
                }
                case 2: {
                    MessageThreadUtil$2.access$100(BackgroundCallback.this).removeMessages(2);
                    MessageThreadUtil$2.access$100(BackgroundCallback.this).removeMessages(3);
                    BackgroundCallback.this.val$callback.updateRange(next.arg1, next.arg2, next.arg3, next.arg4, next.arg5);
                    continue;
                }
                case 3: {
                    BackgroundCallback.this.val$callback.loadTile(next.arg1, next.arg2);
                    continue;
                }
                case 4: {
                    BackgroundCallback.this.val$callback.recycleTile((TileList.Tile)next.data);
                    continue;
                }
            }
        }
        BackgroundCallback.this.mBackgroundRunning.set(false);
    }
}