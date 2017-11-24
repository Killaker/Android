package android.support.v7.util;

static class MessageQueue
{
    private SyncQueueItem mRoot;
    
    SyncQueueItem next() {
        synchronized (this) {
            SyncQueueItem mRoot;
            if (this.mRoot == null) {
                mRoot = null;
            }
            else {
                mRoot = this.mRoot;
                this.mRoot = this.mRoot.next;
            }
            return mRoot;
        }
    }
    
    void removeMessages(final int n) {
        synchronized (this) {
            while (this.mRoot != null && this.mRoot.what == n) {
                final SyncQueueItem mRoot = this.mRoot;
                this.mRoot = this.mRoot.next;
                mRoot.recycle();
            }
        }
        if (this.mRoot != null) {
            Object mRoot2 = this.mRoot;
            SyncQueueItem access$201;
            for (SyncQueueItem access$200 = ((SyncQueueItem)mRoot2).next; access$200 != null; access$200 = access$201) {
                access$201 = access$200.next;
                if (access$200.what == n) {
                    ((SyncQueueItem)mRoot2).next = access$201;
                    access$200.recycle();
                }
                else {
                    mRoot2 = access$200;
                }
            }
        }
    }
    // monitorexit(this)
    
    void sendMessage(final SyncQueueItem mRoot) {
        synchronized (this) {
            if (this.mRoot == null) {
                this.mRoot = mRoot;
            }
            else {
                Object o;
                for (o = this.mRoot; ((SyncQueueItem)o).next != null; o = ((SyncQueueItem)o).next) {}
                ((SyncQueueItem)o).next = mRoot;
            }
        }
    }
    
    void sendMessageAtFrontOfQueue(final SyncQueueItem mRoot) {
        synchronized (this) {
            mRoot.next = this.mRoot;
            this.mRoot = mRoot;
        }
    }
}
