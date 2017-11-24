package android.support.v7.util;

static class SyncQueueItem
{
    private static SyncQueueItem sPool;
    private static final Object sPoolLock;
    public int arg1;
    public int arg2;
    public int arg3;
    public int arg4;
    public int arg5;
    public Object data;
    private SyncQueueItem next;
    public int what;
    
    static {
        sPoolLock = new Object();
    }
    
    static SyncQueueItem obtainMessage(final int n, final int n2, final int n3) {
        return obtainMessage(n, n2, n3, 0, 0, 0, null);
    }
    
    static SyncQueueItem obtainMessage(final int what, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5, final Object data) {
        synchronized (SyncQueueItem.sPoolLock) {
            SyncQueueItem sPool;
            if (SyncQueueItem.sPool == null) {
                sPool = new SyncQueueItem();
            }
            else {
                sPool = SyncQueueItem.sPool;
                SyncQueueItem.sPool = SyncQueueItem.sPool.next;
                sPool.next = null;
            }
            sPool.what = what;
            sPool.arg1 = arg1;
            sPool.arg2 = arg2;
            sPool.arg3 = arg3;
            sPool.arg4 = arg4;
            sPool.arg5 = arg5;
            sPool.data = data;
            return sPool;
        }
    }
    
    static SyncQueueItem obtainMessage(final int n, final int n2, final Object o) {
        return obtainMessage(n, n2, 0, 0, 0, 0, o);
    }
    
    void recycle() {
        this.next = null;
        this.arg5 = 0;
        this.arg4 = 0;
        this.arg3 = 0;
        this.arg2 = 0;
        this.arg1 = 0;
        this.what = 0;
        this.data = null;
        synchronized (SyncQueueItem.sPoolLock) {
            if (SyncQueueItem.sPool != null) {
                this.next = SyncQueueItem.sPool;
            }
            SyncQueueItem.sPool = this;
        }
    }
}
