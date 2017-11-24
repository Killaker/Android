package okio;

final class SegmentPool
{
    static final long MAX_SIZE = 65536L;
    static long byteCount;
    static Segment next;
    
    static void recycle(final Segment next) {
        if (next.next != null || next.prev != null) {
            throw new IllegalArgumentException();
        }
        if (next.shared) {
            return;
        }
        synchronized (SegmentPool.class) {
            if (2048L + SegmentPool.byteCount > 65536L) {
                return;
            }
        }
        SegmentPool.byteCount += 2048L;
        next.next = SegmentPool.next;
        next.limit = 0;
        next.pos = 0;
        SegmentPool.next = next;
    }
    // monitorexit(SegmentPool.class)
    
    static Segment take() {
        synchronized (SegmentPool.class) {
            if (SegmentPool.next != null) {
                final Segment next = SegmentPool.next;
                SegmentPool.next = next.next;
                next.next = null;
                SegmentPool.byteCount -= 2048L;
                return next;
            }
            // monitorexit(SegmentPool.class)
            return new Segment();
        }
    }
}
