package android.support.v7.util;

import android.util.*;

class MessageThreadUtil$1$1 implements Runnable {
    @Override
    public void run() {
        for (SyncQueueItem syncQueueItem = MessageThreadUtil$1.access$000(MainThreadCallback.this).next(); syncQueueItem != null; syncQueueItem = MessageThreadUtil$1.access$000(MainThreadCallback.this).next()) {
            switch (syncQueueItem.what) {
                default: {
                    Log.e("ThreadUtil", "Unsupported message, what=" + syncQueueItem.what);
                    break;
                }
                case 1: {
                    MainThreadCallback.this.val$callback.updateItemCount(syncQueueItem.arg1, syncQueueItem.arg2);
                    break;
                }
                case 2: {
                    MainThreadCallback.this.val$callback.addTile(syncQueueItem.arg1, (TileList.Tile)syncQueueItem.data);
                    break;
                }
                case 3: {
                    MainThreadCallback.this.val$callback.removeTile(syncQueueItem.arg1, syncQueueItem.arg2);
                    break;
                }
            }
        }
    }
}