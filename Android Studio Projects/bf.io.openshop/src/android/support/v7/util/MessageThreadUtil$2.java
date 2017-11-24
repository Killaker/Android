package android.support.v7.util;

import java.util.concurrent.atomic.*;
import java.util.concurrent.*;
import android.support.v4.content.*;
import android.util.*;

class MessageThreadUtil$2 implements BackgroundCallback<T> {
    private static final int LOAD_TILE = 3;
    private static final int RECYCLE_TILE = 4;
    private static final int REFRESH = 1;
    private static final int UPDATE_RANGE = 2;
    private Runnable mBackgroundRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                final SyncQueueItem next = BackgroundCallback.this.mQueue.next();
                if (next == null) {
                    break;
                }
                switch (next.what) {
                    default: {
                        Log.e("ThreadUtil", "Unsupported message, what=" + next.what);
                        continue;
                    }
                    case 1: {
                        BackgroundCallback.this.mQueue.removeMessages(1);
                        BackgroundCallback.this.val$callback.refresh(next.arg1);
                        continue;
                    }
                    case 2: {
                        BackgroundCallback.this.mQueue.removeMessages(2);
                        BackgroundCallback.this.mQueue.removeMessages(3);
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
    };
    AtomicBoolean mBackgroundRunning = new AtomicBoolean(false);
    private final Executor mExecutor = ParallelExecutorCompat.getParallelExecutor();
    private final MessageQueue mQueue = new MessageQueue();
    final /* synthetic */ BackgroundCallback val$callback;
    
    private void maybeExecuteBackgroundRunnable() {
        if (this.mBackgroundRunning.compareAndSet(false, true)) {
            this.mExecutor.execute(this.mBackgroundRunnable);
        }
    }
    
    private void sendMessage(final SyncQueueItem syncQueueItem) {
        this.mQueue.sendMessage(syncQueueItem);
        this.maybeExecuteBackgroundRunnable();
    }
    
    private void sendMessageAtFrontOfQueue(final SyncQueueItem syncQueueItem) {
        this.mQueue.sendMessageAtFrontOfQueue(syncQueueItem);
        this.maybeExecuteBackgroundRunnable();
    }
    
    @Override
    public void loadTile(final int n, final int n2) {
        this.sendMessage(SyncQueueItem.obtainMessage(3, n, n2));
    }
    
    @Override
    public void recycleTile(final TileList.Tile<T> tile) {
        this.sendMessage(SyncQueueItem.obtainMessage(4, 0, tile));
    }
    
    @Override
    public void refresh(final int n) {
        this.sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(1, n, null));
    }
    
    @Override
    public void updateRange(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(2, n, n2, n3, n4, n5, null));
    }
}