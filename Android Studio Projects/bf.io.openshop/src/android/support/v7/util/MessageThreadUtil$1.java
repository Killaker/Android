package android.support.v7.util;

import android.os.*;
import android.util.*;

class MessageThreadUtil$1 implements MainThreadCallback<T> {
    private static final int ADD_TILE = 2;
    private static final int REMOVE_TILE = 3;
    private static final int UPDATE_ITEM_COUNT = 1;
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    private Runnable mMainThreadRunnable = new Runnable() {
        @Override
        public void run() {
            for (SyncQueueItem syncQueueItem = MainThreadCallback.this.mQueue.next(); syncQueueItem != null; syncQueueItem = MainThreadCallback.this.mQueue.next()) {
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
    };
    private final MessageQueue mQueue = new MessageQueue();
    final /* synthetic */ MainThreadCallback val$callback;
    
    private void sendMessage(final SyncQueueItem syncQueueItem) {
        this.mQueue.sendMessage(syncQueueItem);
        this.mMainThreadHandler.post(this.mMainThreadRunnable);
    }
    
    @Override
    public void addTile(final int n, final TileList.Tile<T> tile) {
        this.sendMessage(SyncQueueItem.obtainMessage(2, n, tile));
    }
    
    @Override
    public void removeTile(final int n, final int n2) {
        this.sendMessage(SyncQueueItem.obtainMessage(3, n, n2));
    }
    
    @Override
    public void updateItemCount(final int n, final int n2) {
        this.sendMessage(SyncQueueItem.obtainMessage(1, n, n2));
    }
}