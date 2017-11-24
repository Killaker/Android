package okio;

import java.io.*;

static final class Okio$1 implements Sink {
    final /* synthetic */ OutputStream val$out;
    final /* synthetic */ Timeout val$timeout;
    
    @Override
    public void close() throws IOException {
        this.val$out.close();
    }
    
    @Override
    public void flush() throws IOException {
        this.val$out.flush();
    }
    
    @Override
    public Timeout timeout() {
        return this.val$timeout;
    }
    
    @Override
    public String toString() {
        return "sink(" + this.val$out + ")";
    }
    
    @Override
    public void write(final Buffer buffer, long n) throws IOException {
        Util.checkOffsetAndCount(buffer.size, 0L, n);
        while (n > 0L) {
            this.val$timeout.throwIfReached();
            final Segment head = buffer.head;
            final int n2 = (int)Math.min(n, head.limit - head.pos);
            this.val$out.write(head.data, head.pos, n2);
            head.pos += n2;
            n -= n2;
            buffer.size -= n2;
            if (head.pos == head.limit) {
                buffer.head = head.pop();
                SegmentPool.recycle(head);
            }
        }
    }
}