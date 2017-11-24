package okio;

final class Segment
{
    static final int SIZE = 2048;
    final byte[] data;
    int limit;
    Segment next;
    boolean owner;
    int pos;
    Segment prev;
    boolean shared;
    
    Segment() {
        this.data = new byte[2048];
        this.owner = true;
        this.shared = false;
    }
    
    Segment(final Segment segment) {
        this(segment.data, segment.pos, segment.limit);
        segment.shared = true;
    }
    
    Segment(final byte[] data, final int pos, final int limit) {
        this.data = data;
        this.pos = pos;
        this.limit = limit;
        this.owner = false;
        this.shared = true;
    }
    
    public void compact() {
        if (this.prev == this) {
            throw new IllegalStateException();
        }
        if (this.prev.owner) {
            final int n = this.limit - this.pos;
            final int n2 = 2048 - this.prev.limit;
            int pos;
            if (this.prev.shared) {
                pos = 0;
            }
            else {
                pos = this.prev.pos;
            }
            if (n <= n2 + pos) {
                this.writeTo(this.prev, n);
                this.pop();
                SegmentPool.recycle(this);
            }
        }
    }
    
    public Segment pop() {
        Segment next;
        if (this.next != this) {
            next = this.next;
        }
        else {
            next = null;
        }
        this.prev.next = this.next;
        this.next.prev = this.prev;
        this.next = null;
        this.prev = null;
        return next;
    }
    
    public Segment push(final Segment segment) {
        segment.prev = this;
        segment.next = this.next;
        this.next.prev = segment;
        return this.next = segment;
    }
    
    public Segment split(final int n) {
        if (n <= 0 || n > this.limit - this.pos) {
            throw new IllegalArgumentException();
        }
        final Segment segment = new Segment(this);
        segment.limit = n + segment.pos;
        this.pos += n;
        this.prev.push(segment);
        return segment;
    }
    
    public void writeTo(final Segment segment, final int n) {
        if (!segment.owner) {
            throw new IllegalArgumentException();
        }
        if (n + segment.limit > 2048) {
            if (segment.shared) {
                throw new IllegalArgumentException();
            }
            if (n + segment.limit - segment.pos > 2048) {
                throw new IllegalArgumentException();
            }
            System.arraycopy(segment.data, segment.pos, segment.data, 0, segment.limit - segment.pos);
            segment.limit -= segment.pos;
            segment.pos = 0;
        }
        System.arraycopy(this.data, this.pos, segment.data, segment.limit, n);
        segment.limit += n;
        this.pos += n;
    }
}
